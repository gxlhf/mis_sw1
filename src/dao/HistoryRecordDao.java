package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.ConnectionPool;
import entity.HistoryQueryItem;

public class HistoryRecordDao {
	ConnectionPool pool = null;
	Connection con = null;
	public HistoryRecordDao() {
		super();
	}

	/**
	 * 从数据库中查询该查询条件记录是否已存在于数据库中，若存在返回文件名，若不存在返回null
	 * use table : HistoryQueryItem 
	 */
	public String historyRecordExistJudge(HistoryQueryItem historyQueryItem) {
		String result = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			//SELECT * FROM HistoryQueryItem where sex = and minage = and maxage = and examclass = and testitem = and labvalfrom = and labvalto =  ; 
			String sql = "SELECT filename FROM historyqueryitem where "
					+ "sex = ? and "
					+ "minage = ? and "
					+ "maxage = ? and "
					+ "examclass = ? and "
					+ "labtype = ? and "
					+ "labsubtype = ? and "
					+ "labvalfrom = ? and "
					+ "labvalto = ? ";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, historyQueryItem.getSex());
			preparedStatement.setInt(2, historyQueryItem.getMinAge());
			preparedStatement.setInt(3, historyQueryItem.getMaxAge());
			preparedStatement.setString(4, historyQueryItem.getExamClass());
			preparedStatement.setString(5, historyQueryItem.getLabType());
			preparedStatement.setString(6, historyQueryItem.getLabSubType());
			preparedStatement.setString(7, historyQueryItem.getLabValFrom());
			preparedStatement.setString(8, historyQueryItem.getLabValTo());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.release(con);
		}
		return result;
	}
	
	/**
	 * 向数据库中插入该条历史查询记录，成功返回true，失败返回false
	 * insert table:historyqueryitem
	 */
	public boolean addHistoryRecord(HistoryQueryItem historyQueryItem) {
		boolean result = false;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			//found this return false.because it has exists
			if(null!=this.historyRecordExistJudge(historyQueryItem)){return false;}
			String sql = "insert into historyqueryitem(sex,minage,maxage,examclass,labtype,labsubtype,labvalfrom,labvalto,filename) values ("
					+ "?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, historyQueryItem.getSex());
			preparedStatement.setInt(2, historyQueryItem.getMinAge());
			preparedStatement.setInt(3, historyQueryItem.getMaxAge());
			preparedStatement.setString(4, historyQueryItem.getExamClass());
			preparedStatement.setString(5, historyQueryItem.getLabType());
			preparedStatement.setString(6, historyQueryItem.getLabSubType());
			preparedStatement.setString(7, historyQueryItem.getLabValFrom());
			preparedStatement.setString(8, historyQueryItem.getLabValTo());
			preparedStatement.setString(9, historyQueryItem.getFilename());
			int rs= preparedStatement.executeUpdate();  
			//System.out.println(rs);
			if(rs>0)result=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.release(con);
		}
		return result;
	}
	
	/**
	 * 向数据库中更新该条历史查询记录，成功返回true，失败返回false
	 * note:只考虑存不存在，记录不存在返回false，存在更新{若更新时Exception则返回false,否则true}
	 */
	public boolean updateHistoryRecord(HistoryQueryItem historyQueryItem) {
		boolean result = false;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			//Not found return false.  because nothing to update
			if(null==this.historyRecordExistJudge(historyQueryItem))return false;
			String sql = "update historyqueryitem set filename = ? where "
					+ "sex = ? and "
					+ "minage = ? and "
					+ "maxage = ? and "
					+ "examclass = ? and "
					+ "labtype = ? and "
					+ "labsubtype = ? and "
					+ "labvalfrom = ? and "
					+ "labvalto = ? ";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, historyQueryItem.getFilename());
			preparedStatement.setString(2, historyQueryItem.getSex());
			preparedStatement.setInt(3, historyQueryItem.getMinAge());
			preparedStatement.setInt(4, historyQueryItem.getMaxAge());
			preparedStatement.setString(5, historyQueryItem.getExamClass());
			preparedStatement.setString(6, historyQueryItem.getLabType());
			preparedStatement.setString(7, historyQueryItem.getLabSubType());
			preparedStatement.setString(8, historyQueryItem.getLabValFrom());
			preparedStatement.setString(9, historyQueryItem.getLabValTo());
			int rs= preparedStatement.executeUpdate();  
			//System.out.println(rs);
			if(rs>0)result=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.release(con);
		}
		return result;
	}
	public static void main(String[] args){
		HistoryRecordDao tmp=new HistoryRecordDao();
		boolean result=false;
	//	tmp.historyRecordExistJudge(new HistoryQueryItem("男",1,4,"1","1","1","1"));
		//return boolean
		/*add*/
		result=tmp.addHistoryRecord(new HistoryQueryItem("男",1,8,"testmark","testmark","subtest","1","1","old path",""));
		System.out.println("(true)add an unExists one:"+result);
		result=tmp.addHistoryRecord(new HistoryQueryItem("男",1,8,"testmark","testmark","subtest","1","1","old path",""));
		System.out.println("(false)add an Exists one:"+result);
		result = tmp.updateHistoryRecord(new HistoryQueryItem("男",1,8,"testmark","testmark","subtest","1","1","new path",""));
		System.out.println("(true)update an Exists one:"+result);
		result = tmp.updateHistoryRecord(new HistoryQueryItem("男",-1,-8,"testmark","testmark","subtest","1","1","new path",""));
		System.out.println("(false)update an unExists one:"+result);
		/*return*/
		//delete by hand
	}
}
