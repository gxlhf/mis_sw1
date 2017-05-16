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
	}
	public int getTestCount() {
		try{   
            pool = ConnectionPool.getInstance();
            con = pool.getConnection();
        }catch(Exception se ){   
            System.out.println("数据库连接失败！");   
            se.printStackTrace() ;   
             }   
        int count = 0;
        PreparedStatement ps = null;
		ResultSet rs = null;
        try{   
            String sql = "select count(*) from lab_result";
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
        }catch(SQLException se ){   
            System.out.println("test count error");   
            se.printStackTrace() ;   
        } finally {
				pool.release(con);
        }

        return count;
    }
	
	public String[] quryTestResult() {
		try{   
            pool = ConnectionPool.getInstance();
            con = pool.getConnection();
        }catch(Exception se ){   
            System.out.println("数据库连接失败！");   
            se.printStackTrace() ;   
             }   
		int count = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
        try{   
            String sql = "select distinct item_name from lab_test_items";
            System.out.println(con);
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
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
        } finally {
			pool.release(con);
        }

        return null;
    }
	
	
	/**
	 * 根据检验指标大类查询检验指标具体类别
	 * 如参数血常规，返回所有血常规的具体检验项，如白细胞，血红蛋白等
	 */
	public String[] queryTestItem(String TestClass){
		try{   
            pool = ConnectionPool.getInstance();
            con = pool.getConnection();
        }catch(Exception se ){   
            System.out.println("数据库连接失败！");   
            se.printStackTrace() ;   
             }   
		int count = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
        try{   
            String sql = "select reportitemname from itemtoresult where itemname =?";
            ps= con.prepareStatement(sql);
            ps.setString(1, TestClass.replaceAll("\\s*",""));
            System.out.println("testClass:"+TestClass);
            rs = ps.executeQuery();
            while(rs.next()) {
            	count++;
            	list.add(rs.getString(1));
            }
            String[] s = new String[count];
            //test
            System.out.println(s.length);
            System.out.println(count);
            for(int i = 0; i< count; i++) {
    			System.out.println(s[i]);
            }
            //test over
            
            for(int i = 0; i< count; i++) {
            	s[i] = list.get(i);
            }
            return s;
        }catch(SQLException se ){   
            System.out.println("test count error");   
            se.printStackTrace() ;   
        } finally {
				pool.release(con);
				con=null;
				System.out.println("TestDAO.queryTestItem 已经释放");
        }

        return null;
	}
	
	public static void main(String[] args) {
		System.out.println((new TestDao()).getTestCount());
		User user=new User("", "", "");
		String[] s = (new TestDao()).queryTestItem("肾功能");
		for(int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}
}
