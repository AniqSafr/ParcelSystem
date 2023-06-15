package com.xadmin.parcelsystem.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {

	private static Connection conn = null;
    private static final GetConnection sqlConn = new GetConnection();
    
    private GetConnection() {}

    public static GetConnection getInstance() {
        return sqlConn;
    }

    public static Connection getConnection() {
        
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/";
    String dbname = "parcelsystem";
    String username = "root";
    String pwd = "";
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbname, username, pwd);
            System.out.println("Connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return conn;
    }
}