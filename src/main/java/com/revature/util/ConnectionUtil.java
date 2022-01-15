package com.revature.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

//    public static void main(String... args) {
//        try {
//            getConnection();
//            System.out.println("Connected successfully");
//        } catch (SQLException e) {
//            System.out.println("Connection failed ");
//            e.printStackTrace();
//        }
//    }


    //this will connect to our DB
    public static Connection getConnection() throws SQLException {
        //for many frameworks using JDBC it is necessary to 'register' the driver package you are using
        //we are using the postgres driver, this is to make the framework aware of it.
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        //after registering the driver, we need the location, DBname, username and pw before connecting to it
        String url = "jdbc:postgresql://javafs220103.cludzulbnky0.us-east-1.rds.amazonaws.com:5432/project0";
       //it is possible and preferable to hide this info in env variables
        String username = System.getenv("sqlUserName");

        String password = System.getenv("sqlPassword"); //can be done on system or through IDE (System.getenv
        // ("var-name");
        return DriverManager.getConnection(url, username, password);
        //DAO loosely couples the application from the dialect being used so you can easily change the dialect, and
        // easier to test (TDD)
    }
}
