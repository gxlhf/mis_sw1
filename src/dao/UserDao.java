package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnectionPool;
import entity.User;

public class UserDao {
	ConnectionPool pool = null;
	Connection con = null;

	public UserDao() {
	}
	/*
	 * 用户名格式：由字母数字下划线组成且开头必须是字母，不能超过16位
	 * */
	private boolean isUser(String user){
		return user.matches("[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}");
	}
	/*
	 * 密码格式：字母和数字构成，不能超过16位；
	 * */
	private boolean isPassword(String password){
		return password.matches("[a-zA-Z0-9]{1,16}");
	}
	/*
	 * 
	 * note：result==0 success
	 * 		result >0 error about password
	 * 			1:password error
	 * 			2:password invalid
	 *		result <0 error about user
	 *			-1:user error
	 *			-2:user invalid
	 * */
	
	public int verifyUser(User user) {
		int  result = -1;
		if(!isUser(user.getUser())){
			return -2;
		}
		if(!isPassword(user.getPassword())){
			return 2;
		}
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
			String sql = "select password from user where user.user = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUser());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String passwd = rs.getString(1);
				String psdCmp = user.getPassword();
				System.out.println("result: " + passwd + " " + psdCmp);
				if (passwd.equals(psdCmp)) {
					result = 0;
				} else {
					result = 1;
				}
			}
		} catch (SQLException se) {
			System.out.println("用户识别失败！");
			se.printStackTrace();
		} finally {
			pool.release(con);
		}
		return result;
	}
}
