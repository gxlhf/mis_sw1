package entity;

import java.sql.*;
import java.util.ArrayList;

import database.ConnectionPool;
import database.DBConnection;

public class User {

    private String userID;// 用户ID
    private String userName;// 用户名
    private String password;// 密码
    private String email;// 密码

    public User() {
    }

    public User(String userID, String userName, String password, String email) {
        super();
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}