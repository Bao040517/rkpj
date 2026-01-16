package Utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class ConnectionOpen {
    private static final String url = "jdbc:postgresql://localhost:5432/ShoppSP";
    private static final String user = "postgres";
    private static final String password = "admin";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement callSt) {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (callSt != null) {
            try {
                callSt.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
