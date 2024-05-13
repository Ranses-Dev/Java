package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionServer {

    private static Connection myConn;

    public static Connection mysqlServerConnection() {
        try {
            myConn = DriverManager.getConnection(Constant.URL_MYSQL, Constant.USER_MYSQL, Constant.PASSWORD_MYSQL);
            return myConn;
        } catch (Exception e) {
            Constant.MESSAGE = e.getMessage();
            return null;
        }
    }
}
