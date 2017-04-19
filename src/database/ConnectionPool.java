package database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPool {

    private Vector<Connection> pool;

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    /**
     * ���ӳصĴ�С��Ҳ�������ӳ����ж��ٸ����ݿ����ӡ�
     */
    private int poolSize = 1;

    private static ConnectionPool instance = null;

    /**
     * ˽�еĹ��췽������ֹ�ⲿ��������Ķ���Ҫ���ñ���Ķ���ͨ��getIstance������
     * ʹ�������ģʽ�еĵ���ģʽ��
     */
    private ConnectionPool() {
        init();
    }

    /**
     * ���ӳس�ʼ����������ȡ�����ļ������� �������ӳ��еĳ�ʼ����
     */
    private void init() {
        pool = new Vector<Connection>(poolSize);
        readConfig();
        addConnection();
    }

    /**
     * �������ӵ����ӳ���
     */
    public synchronized void release(Connection conn) {
        pool.add(conn);

    }

    /**
     * �ر����ӳ��е��������ݿ�����
     */
    public synchronized void closePool() {
        for (int i = 0; i < pool.size(); i++) {
            try {
                ((Connection) pool.get(i)).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pool.remove(i);
        }
    }

    /**
     * ���ص�ǰ���ӳص�һ������
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * �������ӳ��е�һ�����ݿ�����
     */
    public synchronized Connection getConnection() { 
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }

    /**
     * �����ӳ��д�����ʼ���õĵ����ݿ�����
     */
    private void addConnection() {
        Connection conn = null;
        for (int i = 0; i < poolSize; i++) {

            try {
                Class.forName(driverClassName);
                conn = java.sql.DriverManager.getConnection(url, username, password);
                pool.add(conn);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * ��ȡ�������ӳص������ļ�
     */
    private void readConfig() {
        try {
            String path = this.getClass().getResource("/").getPath();
            path=path.substring(0, path.lastIndexOf("/"))+"/dbpool.properties";
            System.out.println(path);
            FileInputStream is = new FileInputStream(path);
            Properties props = new Properties();


            props.load(is);
            this.driverClassName = props.getProperty("driverClassName");
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");
            this.url = props.getProperty("url");
            this.poolSize = Integer.parseInt(props.getProperty("poolSize"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("��ȡ�����ļ�����. ");        
        }
    }
}