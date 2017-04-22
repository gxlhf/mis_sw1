package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnectionPool;
import entity.User;

public class UserDao {
	ConnectionPool pool=null;
    Connection con =null;
	public UserDao(){
		try{   
            pool = ConnectionPool.getInstance();
            con = pool.getConnection();
        }catch(Exception se ){   
            System.out.println("数据库连接失败！");   
            se.printStackTrace() ;   
             }   
	}
	public boolean verifyUser(User user){
        try{   
        	String sql = "select password from user where user.user = ? ";
        	PreparedStatement ps= con.prepareStatement(sql);
        	ps.setString(1, user.getUser());
        	ResultSet rs = ps.executeQuery();
        	while(rs.next())
        	{
        		String passwd=rs.getString(1);
        		String psdCmp=user.getPassword();
        		System.out.println("result: "+passwd+" "+psdCmp);
        		if(passwd.equals(psdCmp)){
        			return true;}
        		else {
        			return false;}
        	}
        }catch(SQLException se ){   
            System.out.println("用户识别失败！");   
            se.printStackTrace() ;   
             }   
		return false;
    } 
    //病人数量
    public int getPatientCount() {
        int count = 0;
        try{   
            String sql = "select count(*) from patient";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            count = rs.getInt(1);
        }catch(SQLException se ){   
            System.out.println("patient count error");   
            se.printStackTrace() ;   
        } 

        return count;
    }

    public int getExamCount() {
        int count = 0;
        try{   
            String sql = "select count(*) from exam";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            count = rs.getInt(1);
        }catch(SQLException se ){   
            System.out.println("exam count error");   
            se.printStackTrace() ;   
        } 

        return count;
    }

    public int getTestCount() {
        int count = 0;
        try{   
            String sql = "select count(*) from test";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            count = rs.getInt(1);
        }catch(SQLException se ){   
            System.out.println("test count error");   
            se.printStackTrace() ;   
        } 
        return count;
    }
}
