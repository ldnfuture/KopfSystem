package at.ldnfuture.kopfsystem.data.mysql;

import at.ldnfuture.kopfsystem.main.Main;
import at.ldnfuture.kopfsystem.data.Data;

import java.sql.*;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 10.05.2021
 */
public class MySQL {

    public static Connection connection;

    public static void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + Data.MySQLHost + ":" + Data.MySQLPort + "/" + Data.MySQLDatabase + "?autoReconnect=true", Data.MySQLUser, Data.MySQLPassword);
                Main.log(" §cDie Verbindung zu MySQL wurde hergestellt!");
            } catch (SQLException var1) {
                Main.log(" §cDie Verbindung zu MySQL konnte nicht hergestellt werden!");
            }
        } else {
            System.out.println(" §cEs besteht bereits eine Verbindung zu MySQL!");
        }

    }

    public static ResultSet getResult(String sql) {
        if (isConnected()) {
            try {
                return connection.createStatement().executeQuery(sql);
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

        return null;
    }

    public static void close() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException var1) {
                var1.printStackTrace();
            }
        }

    }

    public static boolean isConnected() {
        return connection != null;
    }

    public static void update(String qry) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

}