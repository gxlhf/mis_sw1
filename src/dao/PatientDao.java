package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import database.ConnectionPool;
import entity.Exam;
import entity.HospitalSituation;
import entity.Patient;
import entity.Test;
import entity.TestResult;

public class PatientDao {
	ConnectionPool pool = null;
	Connection con = null;

	public PatientDao() {
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}

	}

	public int getPatientCount() {
		int result = 0;

		try {
			String sql = "SELECT COUNT(*) FROM patient_information";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Patient> queryPatient(String sex, int minAge, int maxAge, String examClass) {
		List<Patient> result = new ArrayList<Patient>();

		try {
			String sql = "SELECT patient_information.PATIENT_ID,patient_information.SEX,patient_information.PATIENT_NAME FROM "
					+ "(SELECT patient_to_exam.PATIENT_ID,SEX,EXAM_CLASS FROM exam_master INNER JOIN patient_to_exam ON patient_to_exam.EXAM_NO = exam_master.EXAM_NO) a INNER JOIN "
					+ "patient_information ON a.PATIENT_ID = patient_information.PATIENT_ID WHERE SEX = ? AND AGE >= ? AND AGE <= ? AND examClass =?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sex);
			preparedStatement.setInt(2, minAge);
			preparedStatement.setInt(3, maxAge);
			preparedStatement.setString(4, examClass);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.add(new Patient(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Patient> queryPatient(String sex, int minAge, int maxAge, String testItem, double valueStart,
			double valueEnd) {
		List<Patient> result = new ArrayList<Patient>();
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			String sql = "SELECT patient_information.PATIENT_ID, patient_information.PATIENT_NAME,patient_information.SEX,c.RESULT FROM "
					+ "(SELECT patient_to_test.PATIENT_ID,b.RESULT,b.ITEM_NAME FROM "
					+ "(SELECT a.TEST_NO,a.RESULT,lab_test_items.ITEM_NAME FROM "
					+ "(SELECT lab_test_master.TEST_NO,lab_result.RESULT FROM "
					+ "lab_result INNER JOIN lab_test_master ON lab_result.TEST_NO = lab_test_master.TEST_NO) a INNER JOIN "
					+ "lab_test_items ON a.TEST_NO=lab_test_items.TEST_NO) b INNER JOIN "
					+ "patient_to_test on b.TEST_NO = patient_to_test.TEST_NO) c INNER JOIN "
					+ "patient_information ON patient_information.PATIENT_ID = c.PATIENT_ID "
					+ "WHERE SEX = ? AND AGE >= ? AND AGE <= ? AND ITEM_NAME = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sex);
			preparedStatement.setInt(2, minAge);
			preparedStatement.setInt(3, maxAge);
			preparedStatement.setString(4, testItem);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				try {
					float value = Float.parseFloat(resultSet.getString(4));
				} catch (NumberFormatException e) {
					continue;
				}
				result.add(new Patient(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询病人一次住院的所有检查记录和测试记录
	 */
	public HospitalSituation getHospitalSituation(String patient_id, int sequence) {
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
			TestResult[] tR = null ;
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
					tR=new TestResult[tResultList.size()];
					tR=tResultList.toArray(tR);
				}
				String sqlFindItem = "select item_name from lab_test_items where test_no= ?";
				PreparedStatement preparedStatementFindTestItem = con.prepareStatement(sqlFindItem);
				preparedStatementFindTestItem.setString(1, templet.getTest_no());
				ResultSet resultSetTestItem = preparedStatementFindTestItem.executeQuery();
				while (resultSetTestItem.next()) {
					test.add(new Test(
							templet.getTest_no(),
							templet.getPatient_id(),
							templet.getVisit_id(),
							templet.getExecute_date(),
							templet.getAge(),
							templet.getRelevant_clinic_diag(),
							templet.getSpecimen(),
							resultSetTestItem.getString(1),
							tR
							));
				}
			}
			Exam[] exams=null;
			Test[] tests=null;
			exams=new Exam[exam.size()];
			exams=exam.toArray(exams);
			tests=new Test[test.size()];
			tests=test.toArray(tests);
			hospitalSituation = new HospitalSituation(sequence, exams, tests,
					patient_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hospitalSituation;
	}

	public static void main(String[] args) {
		System.out.println(
		new PatientDao().getHospitalSituation("302533", 1).toString());
		
	}

}
