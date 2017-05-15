package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionPool;
import entity.InHospitalRecord;

public class ExamDao {
	ConnectionPool pool = null;
	Connection con = null;

	public ExamDao() {
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
	}

	/**
	 * 查询 检查指标，返回字符串数组 检查指标包括黑白超，内镜，介入，心电图等
	 */
	public String[] queryExamClass() {
		List<String> list = new ArrayList<>();
		String[] queryExamClassResult = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "select distinct exam_class from exam_master";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString(1));
			}
			queryExamClassResult = new String[list.size()];
			for (int index = 0; index < list.size(); index++) {
				queryExamClassResult[index] = list.get(index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return queryExamClassResult;
	}

	/**
	 * 查询数据库中检查项的数量
	 */
	public int getExamCount() {
		int getExamCountResult = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "select distinct exam_class from exam_master";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int count = 0;
			while (resultSet.next()) {
				count++;
			}
			getExamCountResult = count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return getExamCountResult;
	}

	/**
	 * 根据患者编号，查询患者所有住院记录
	 */
	public InHospitalRecord[] getInHospitalRecord(String patient_id) {
		InHospitalRecord[] inHospitalRecords = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<InHospitalRecord> list = null;

		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "select * from inhospitalrecord where patientid = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, patient_id);
			
			resultSet = preparedStatement.executeQuery();
			
			list = new ArrayList<InHospitalRecord>();
			InHospitalRecord inHospitalRecord = null;
			while (resultSet.next()) {
				inHospitalRecord = new InHospitalRecord();
				
				inHospitalRecord.setSequence(resultSet.getInt(2));
				inHospitalRecord.setPatientId(resultSet.getString(1));
				inHospitalRecord.setInTime(resultSet.getString(3));
				inHospitalRecord.setInAge(resultSet.getString(5));
				inHospitalRecord.setDiag(resultSet.getString(6));
				
				list.add(inHospitalRecord);
			}
			inHospitalRecords = new InHospitalRecord[list.size()];
			for(int i = 0; i<list.size(); i++)
			{
				inHospitalRecords[i] = list.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return inHospitalRecords;
	}

	public static void main(String[] args) {
		InHospitalRecord[] InHospitalRecords = new ExamDao().getInHospitalRecord("204020");
		for (InHospitalRecord inHospitalRecord : InHospitalRecords) {
			System.out.println(inHospitalRecord.getDiag());
		}
	}

}
