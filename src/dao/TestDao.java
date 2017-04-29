package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionPool;
import entity.User;

public class TestDao {
	ConnectionPool pool=null;
    Connection con =null;
	public TestDao(){
		try{   
            pool = ConnectionPool.getInstance();
            con = pool.getConnection();
        }catch(Exception se ){   
            System.out.println("数据库连接失败！");   
            se.printStackTrace() ;   
             }   
	}
	public int getTestCount() {
        int count = 0;
        try{   
            String sql = "select count(*) from lab_result";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
        }catch(SQLException se ){   
            System.out.println("test count error");   
            se.printStackTrace() ;   
        } 

        return count;
    }
	
	public String[] quryTestResult() {
		int count = 0;
		List<String> list = new ArrayList<String>();
        try{   
            String sql = "select distinct item_name from lab_test_items";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	count++;
            	list.add(rs.getString(1));
            }
            String[] s = new String[count];
            for(int i = 0; i< count; i++) {
            	s[i] = list.get(i);
            }
            return s;
        }catch(SQLException se ){   
            System.out.println("test count error");   
            se.printStackTrace() ;   
        } 

        return null;
    }
	
	public static void main(String[] args) {
		System.out.println((new TestDao()).getTestCount());
		String[] s = (new TestDao()).quryTestResult();
		for(int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}
}
