package com.netcracker.purchases.utils;

import com.netcracker.purchases.models.services.PurchaseManager;
import com.netcracker.purchases.models.types.Purchase;

import java.sql.*;
import java.util.List;

public final class OracleService {
    private static final String HOST = "sql.edu-netcracker.com";
    private static final int PORT = 1251;
    private static final String SID = "xe";
    private static final String USER_AND_PWD = "TLT_15";
    private static final String URL = String.format("jdbc:oracle:thin:@%s:%d:%s", HOST, PORT, SID);

    private OracleService() {
    }

    public static boolean load(List<Purchase> purchases) {
        try (Connection connection = DriverManager.getConnection(URL, USER_AND_PWD, USER_AND_PWD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from purchases");

            while (resultSet.next()) {
                purchases.add(new Purchase(resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5)));
            }
            return true;
        } catch (SQLException e) {
            PurchaseManager.cleanArray(purchases);
            return false;
        }
    }

    public static boolean save(List<Purchase> purchases) {
        try (Connection connection = DriverManager.getConnection(URL, USER_AND_PWD, USER_AND_PWD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from purchases");

            String format = "insert into purchases values (%d, '%s', %d, '%s', '%s')";
            for (Purchase purchase : purchases) {
                String request = String.format(format, purchase.getIdLocal(), purchase.getName(),
                        purchase.getCount(), purchase.getUnit(), purchase.getComment());
                statement.executeUpdate(request);
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean isJdbcInstalled() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
