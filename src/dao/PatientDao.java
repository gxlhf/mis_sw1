package dao;
//test the sourceTree work status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import database.ConnectionPool;
import entity.Exam;
import entity.HospitalSituation;
import entity.Patient;
import entity.QueryResult;
import entity.Test;
import entity.TestResult;

public class PatientDao {
	ConnectionPool pool = null;
	Connection con = null;

	public PatientDao() {
		// do nothing
	}

	public int getPatientCount() {
		int result = 0;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			String sql = "SELECT COUNT(*) FROM patient_information";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.release(con);
		}
		return result;
	}

	/**
	 * 根据性别，年龄段，检查指标查询患者信息，返回患者类列表 当性别和检验指标为不限的时候，默认为全选，查询全部结果
	 */
	public List<QueryResult> queryPatient(String sex, int minAge, int maxAge, String examClass) {

		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		List<QueryResult> result = new ArrayList<QueryResult>();
		String last_id = "", patient_id = "";
		String patient_name = "";
		String patient_sex = "";
		String patient_diag = "";
		String patient_birthday = "";
		int hospitalCount = 0;
		int last_visit = 0, visit = 0;
		Map<Integer, String> clinicDiagMap = new TreeMap<Integer, String>();
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			String sql = "SELECT a.PATIENT_ID,a.SEX,a.VISIT_ID,a.CLIN_DIAG,patient_information.PATIENT_NAME,patient_information.DATE_OF_BIRTH FROM "
					+ "(SELECT exam_master.PATIENT_ID,exam_master.SEX,exam_master.VISIT_ID,exam_master.CLIN_DIAG FROM exam_master "
					+ "WHERE SEX LIKE ? AND exam_master.EXAM_CLASS LIKE ? "
					+ "AND YEAR(exam_master.REQ_DATE_TIME)-YEAR(exam_master.DATE_OF_BIRTH) >= ? AND "
					+ "YEAR(exam_master.REQ_DATE_TIME)-YEAR(exam_master.DATE_OF_BIRTH) <= ?) a "
					+ "LEFT JOIN patient_information ON a.PATIENT_ID = patient_information.PATIENT_ID ORDER BY a.PATIENT_ID,a.VISIT_ID";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, "%"+sex+"%");
			preparedStatement.setString(2, "%"+examClass+"%");
			preparedStatement.setInt(3, minAge);
			preparedStatement.setInt(4, maxAge);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				patient_id = resultSet.getString(1);
				if (patient_id.equals(last_id)) { // 同一人的记录
					visit = resultSet.getInt(3);
					if (visit == last_visit) { // 同一次的记录
						continue;
					} else { // 不同次的记录
						patient_diag = resultSet.getString(4);
						//System.out.println(patient_name + " " + visit + " " + last_visit);
						clinicDiagMap.put(visit, patient_diag);
						++hospitalCount;
						last_visit = visit;
					}
				} else { // 新的人的记录
					if (!patient_id.equals("")) {// 保存上一个人的记录
						result.add(new QueryResult(new Patient(last_id, patient_name, patient_sex, patient_birthday),
								hospitalCount, clinicDiagMap));
					}
					clinicDiagMap = new TreeMap<Integer, String>();
					hospitalCount = 1;
					patient_sex = resultSet.getString(2);
					visit = resultSet.getInt(3);
					patient_diag = resultSet.getString(4);
					patient_name = resultSet.getString(5);
					patient_birthday = resultSet.getString(6);
					last_id = patient_id;
					last_visit = 0;
					clinicDiagMap.put(visit, patient_diag);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.release(con);
		}
		return result;
	}

	public List<QueryResult> queryPatient(String sex, int minAge, int maxAge, String testItem, double valueStart,
			double valueEnd) {

		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		List<QueryResult> result = new ArrayList<QueryResult>();
		String last_id = "", patient_id = "";
		String patient_name = "";
		String patient_sex = "";
		String patient_diag = "";
		String patient_birthday = "";
		int hospitalCount = 0;
		int last_visit = 0, visit = 0;
		double resultValue = 0;
		Map<Integer, String> clinicDiagMap = new TreeMap<Integer, String>();
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			String sql = "SELECT a.PATIENT_ID,PATIENT_NAME,SEX,VISIT_ID,RELEVANT_CLINIC_DIAG,RESULT,patient_information.DATA_OF_BIRTH "
					+ "FROM (SELECT lab_test_master.TEST_NO,PATIENT_ID,VISIT_ID,RELEVANT_CLINIC_DIAG,RESULT from "
					+ "lab_result LEFT JOIN lab_test_master USING(TEST_NO) WHERE SEX LIKE'%?%' AND "
					+ "AGE>=? AND AGE<=? AND REPORT_ITEM_NAME LIKE '%?%') a LEFT JOIN patient_information USING(PATIENT_ID)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sex);
			preparedStatement.setInt(2, minAge);
			preparedStatement.setInt(3, maxAge);
			preparedStatement.setString(4, testItem);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				try {
					resultValue = Double.parseDouble(resultSet.getString(6));
				} catch (NumberFormatException e) {
					continue;
				}
				if (resultValue < valueStart || resultValue > valueEnd) {
					continue;
				}
				patient_id = resultSet.getString(1);
				if (patient_id == last_id) { // 同一人的记录
					visit = resultSet.getInt(4);
					if (visit == last_visit) { // 同一次的记录
						continue;
					} else { // 不同次的记录
						patient_diag = resultSet.getString(5);
						clinicDiagMap.put(visit, patient_diag);
						++hospitalCount;
						last_visit = visit;
					}
				} else { // 新的人的记录
					if (!patient_id.equals("") && hospitalCount!=0) {// 保存上一个人的记录
						result.add(new QueryResult(new Patient(patient_id, patient_name, patient_sex, patient_birthday),
								hospitalCount, clinicDiagMap));
					}
					clinicDiagMap.clear();
					last_id = patient_id;
					hospitalCount = 1;
					patient_name = resultSet.getString(2);
					patient_sex = resultSet.getString(3);
					visit = resultSet.getInt(4);
					patient_diag = resultSet.getString(5);
					patient_birthday = resultSet.getString(6);
					last_id = patient_id;
					clinicDiagMap.put(visit, patient_diag);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.release(con);
		}
		return result;
	}

	/**
	 * 查询病人一次住院的所有检查记录和测试记录
	 */
	public HospitalSituation getHospitalSituation(String patient_id, int sequence) {
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		HospitalSituation hospitalSituation = null;
		try {
			// new hospitalSituation
			LinkedList<Exam> exam = new LinkedList<Exam>();
			LinkedList<Test> test = new LinkedList<Test>();
			/*
			 * public Exam(String exam_no, String patient_id, String visit_id,
			 * String req_date_time, String exam_sub_class, String exam_class,
			 * String clin_symp, String phys_sign, String clin_diag, String
			 * description, String impression)
			 */
			String sqlFindExam = "select a.exam_no,a.patient_id,a.visit_id,a.req_date_time,a.exam_sub_class,a.exam_class,"
					+ "a.clin_symp,a.phys_sign,a.clin_diag,b.description,b.impression "
					+ "from exam_master a left join exam_report b on a.EXAM_NO = b.EXAM_NO where a.patient_id = ? AND a.visit_id= ?";
			// 考虑可能有exam但是没有report的情况或者有report没有exam
			PreparedStatement preparedStatementFindExam = con.prepareStatement(sqlFindExam);
			preparedStatementFindExam.setString(1, patient_id);
			preparedStatementFindExam.setInt(2, sequence);
			ResultSet resultSetExam = preparedStatementFindExam.executeQuery();
			while (resultSetExam.next()) {
				Exam tmp = new Exam(resultSetExam.getString(1), resultSetExam.getString(2), resultSetExam.getString(3),
						resultSetExam.getString(4), resultSetExam.getString(5), resultSetExam.getString(6),
						resultSetExam.getString(7), resultSetExam.getString(8), resultSetExam.getString(9),
						resultSetExam.getString(10), resultSetExam.getString(11));
				exam.add(tmp);
			}

			/*
			 * public Test(String test_no, String patient_id, String visit_id,
			 * String execute_date, int age, String relevant_clinic_diag, String
			 * specimen, String item_name, TestResult[] test_result)
			 */
			String sqlFindTest = "select * from lab_test_master where patient_id=? AND visit_id=?";
			// 考虑可能有exam但是没有report的情况或者有report没有exam
			PreparedStatement preparedStatementFindTest = con.prepareStatement(sqlFindTest);
			preparedStatementFindTest.setString(1, patient_id);
			preparedStatementFindTest.setInt(2, sequence);
			ResultSet resultSetTest = preparedStatementFindTest.executeQuery();

			// find result
			LinkedList<TestResult> tResultList = new LinkedList<TestResult>();
			TestResult[] tR = null;
			boolean resultJ = true;
			while (resultSetTest.next()) {
				Test templet = new Test(resultSetTest.getString(1), resultSetTest.getString(2),
						resultSetTest.getString(3), resultSetTest.getString(4), resultSetTest.getInt(6),
						resultSetTest.getString(7), resultSetTest.getString(8), null, null);

				if (resultJ) {
					resultJ = false;
					String sqlFindResult = "select PRINT_ORDER,REPORT_ITEM_NAME,RESULT,UNITS,ABNORMAL_INDICATOR,NORMAL_VALUE from  lab_result where test_no= ?";
					PreparedStatement preparedStatementFindTestResult = con.prepareStatement(sqlFindResult);
					preparedStatementFindTestResult.setString(1, templet.getTest_no());
					ResultSet resultSetTestResult = preparedStatementFindTestResult.executeQuery();
					while (resultSetTestResult.next()) {
						tResultList.add(new TestResult(resultSetTestResult.getInt(1), resultSetTestResult.getString(2),
								resultSetTestResult.getString(3), resultSetTestResult.getString(4),
								resultSetTestResult.getString(5), resultSetTestResult.getString(6)));
					}
					tR = new TestResult[tResultList.size()];
					tR = tResultList.toArray(tR);
				}
				String sqlFindItem = "select item_name from lab_test_items where test_no= ?";
				PreparedStatement preparedStatementFindTestItem = con.prepareStatement(sqlFindItem);
				preparedStatementFindTestItem.setString(1, templet.getTest_no());
				ResultSet resultSetTestItem = preparedStatementFindTestItem.executeQuery();
				while (resultSetTestItem.next()) {
					test.add(new Test(templet.getTest_no(), templet.getPatient_id(), templet.getVisit_id(),
							templet.getExecute_date(), templet.getAge(), templet.getRelevant_clinic_diag(),
							templet.getSpecimen(), resultSetTestItem.getString(1), tR));
				}
			}
			Exam[] exams = null;
			Test[] tests = null;
			exams = new Exam[exam.size()];
			exams = exam.toArray(exams);
			tests = new Test[test.size()];
			tests = test.toArray(tests);
			hospitalSituation = new HospitalSituation(sequence, exams, tests, patient_id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.release(con);
		}
		return hospitalSituation;
	}

	public Integer[] getPatientNumChange(String datefrom, String dateto) {
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFrom = sdf.parse(datefrom);
			Date dateTo = sdf.parse(dateto);
			Date date = sdf.parse(datefrom);
			Calendar cal = Calendar.getInstance();
			LinkedList<Integer> lKInteger = new LinkedList<Integer>();
			// dateFrom<=dateTo
			if (dateFrom.getTime() > dateTo.getTime())
				return null;
			while (dateFrom.getTime() <= date.getTime() && dateTo.getTime() >= date.getTime()) {
				String sql = "select count(*) from inhospitalrecord where intime<= ? and outtime >= ?";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, sdf.format(date));
				preparedStatement.setString(2, sdf.format(date));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					lKInteger.add(resultSet.getInt(1));
					break;
				}
				cal.setTime(date);
				cal.add(Calendar.DATE, 1);
				date = cal.getTime();
			}
			return lKInteger.toArray(new Integer[lKInteger.size()]);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.release(con);
		}
		return null;
	}

	/**
	 * 根据患者编号，查询患者信息
	 */
	public Patient queryPatientInfo(String patient_id) {
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			String sql = "select Patient_id,patient_name,sex,date_of_Birth from patient_information where patient_id = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, patient_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return new Patient(resultSet.getString(1),resultSet.getString(2),
						resultSet.getString(3),resultSet.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.release(con);
			pool=null;
		}
		return null;
	}

	public static void main(String[] args) {
		/*System.out.println(new PatientDao().getHospitalSituation("302533", 1).getExam().length);
		Integer[] tmp = new PatientDao().getPatientNumChange("2015-01-24", "2015-01-24");
		for (Integer x : tmp) {
			System.out.println(x);
		}
		Patient p= new PatientDao().queryPatientInfo("123141");
		System.out.println(p.getPatient_id()+" "+p.getPatient_name()+" "+p.getSex()+" "+p.getBirthday());*/
		/*List<QueryResult> list = new PatientDao().queryPatient("男", 10, 70, "DR/CR");
		for(QueryResult q: list) {
			System.out.println(q.getPatient().getPatient_name() + ":" + q.getHospitalCount());
			Map<Integer, String> map = q.getClinicDiagMap();
			for(Integer i: map.keySet()) {
				System.out.println("\t" + i + " " + map.get(i));
			}
		}*/
		//System.out.println(new PatientDao().getHospitalSituation("123141", 3).getTest().length);
		List<QueryResult> list = new PatientDao().queryPatient("", -1, 1000, "彩超");
		for(QueryResult qr: list) {
			System.out.println(qr.getPatient().getPatient_name());
			for(Integer integer : qr.getClinicDiagMap().keySet()) {
				System.out.println("\t" + qr.getClinicDiagMap().get(integer));
			}
		}
	}

}
