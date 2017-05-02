package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionPool;
import entity.Patient;

public class PatientDao {
	ConnectionPool pool = null;
	Connection con = null;
	
	public int getPatientCount() {
		int result = 0;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try{
			String sql="SELECT COUNT(*) FROM patient_information";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Patient> queryPatient(String sex, int minAge, int maxAge, String examClass) {
		List<Patient> result = new ArrayList<Patient>();
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			String sql = "SELECT patient_information.PATIENT_ID,patient_information.SEX,patient_information.PATIENT_NAME FROM " +
					"(SELECT patient_to_exam.PATIENT_ID,SEX,EXAM_CLASS FROM exam_master INNER JOIN patient_to_exam ON patient_to_exam.EXAM_NO = exam_master.EXAM_NO) a INNER JOIN " +
					"patient_information ON a.PATIENT_ID = patient_information.PATIENT_ID WHERE SEX = ? AND AGE >= ? AND AGE <= ? AND examClass =?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sex);
			preparedStatement.setInt(2, minAge);
			preparedStatement.setInt(3, maxAge);
			preparedStatement.setString(4, examClass);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				result.add(new Patient(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Patient> queryPatient(String sex, int minAge, int maxAge, String testItem, double valueStart, double valueEnd) {
		List<Patient> result = new ArrayList<Patient>();
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			String sql = "SELECT patient_information.PATIENT_ID, patient_information.PATIENT_NAME,patient_information.SEX,c.RESULT FROM " +
					"(SELECT patient_to_test.PATIENT_ID,b.RESULT,b.ITEM_NAME FROM " +
					"(SELECT a.TEST_NO,a.RESULT,lab_test_items.ITEM_NAME FROM " +
					"(SELECT lab_test_master.TEST_NO,lab_result.RESULT FROM " +
					"lab_result INNER JOIN lab_test_master ON lab_result.TEST_NO = lab_test_master.TEST_NO) a INNER JOIN " +
					"lab_test_items ON a.TEST_NO=lab_test_items.TEST_NO) b INNER JOIN " +
					"patient_to_test on b.TEST_NO = patient_to_test.TEST_NO) c INNER JOIN " +
					"patient_information ON patient_information.PATIENT_ID = c.PATIENT_ID " +
					"WHERE SEX = ? AND AGE >= ? AND AGE <= ? AND ITEM_NAME = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sex);
			preparedStatement.setInt(2, minAge);
			preparedStatement.setInt(3, maxAge);
			preparedStatement.setString(4, testItem);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				try {
				float value = Float.parseFloat(resultSet.getString(4));
				} catch(NumberFormatException e) {
					continue;
				}
				result.add(new Patient(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
