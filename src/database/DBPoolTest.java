package database;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBPoolTest {

    public static void main(String[] args) throws Exception {
        String sql = "select user from user";
        long start = System.currentTimeMillis();
        ConnectionPool pool = null;

        for (int i = 0; i < 100; i++) {
            pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            }
            rs.close();
            stmt.close();
            pool.release(conn);
        }

        pool.closePool();
        System.out.println("经过100次的循环调用，使用连接池花费的时间:" + (System.currentTimeMillis() - start) + "ms\n");
    }
}