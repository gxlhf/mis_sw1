package database;

import java.sql.*;


public class DBConnection {
	// JDBC 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟捷匡拷URL锟斤拷
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/MIS?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";

	// 锟斤拷锟捷匡拷锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷锟斤拷
	static final String USER = "root";
	static final String PASSWORD = "ssss";

	/**
	 * 锟斤拷锟斤拷锟斤拷锟捷匡拷锟斤拷锟斤拷
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			System.out.println("Connecting to a selected database...");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected database successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 锟截憋拷锟斤拷锟捷匡拷锟斤拷锟斤拷印锟斤拷锟斤拷锟皆硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟皆�
	 * 
	 * @param conn
	 * @param stmt
	 * @param rset
	 */
	public static void clean(Connection conn, Statement stmt, ResultSet rset) {
		try {
			if (rset != null)
				rset.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 锟斤拷锟斤拷欠锟斤拷锟斤拷锟斤拷锟斤拷SQL锟斤拷锟侥硷拷录锟斤拷锟斤拷锟斤拷锟接硷拷录时锟斤拷锟斤拷录锟角凤拷锟窖达拷锟斤拷
	 * 
	 * @param SqlString
	 * @return
	 */
	public static boolean hasRecord(String SqlString) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(SqlString);
			while (rset.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(conn, stmt, rset);
		}
		return result;
	}

	/**
	 * 锟斤拷执锟斤拷询锟斤拷洌拷锟斤拷亟锟斤拷锟斤拷
	 * 
	 * @param SqlString
	 * @return
	 */
	public static ResultSet query(String SqlString) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(SqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(conn, stmt, rset);
		}
		return rset;
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟捷匡拷锟铰�
	 * 
	 * @param sql
	 * @return
	 */
	public static boolean update(String sql) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			result = true;
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			clean(conn, stmt, rset);
		}
		return result;
	}

	/**
	 * 删锟斤拷锟斤拷锟捷匡拷锟铰�
	 * 
	 * @param sql
	 * @return
	 */
	public static boolean delete(String sql) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
			result = true;
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			clean(conn, stmt, rset);
		}
		return result;
	}
	public static void main(String[] args){
		new DBConnection().getConnection();
	}
}
