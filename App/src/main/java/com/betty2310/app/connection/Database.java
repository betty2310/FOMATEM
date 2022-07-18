package com.betty2310.app.connection;

import com.betty2310.app.Login;

import java.sql.*;

public class Database {
    public Connection connection() {

        final String url = "jdbc:postgresql://localhost:5432/" + Login.databaseName;
        final String user = Login.userName;
        final String pass = Login.passName;

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
