package com.netcracker.controllers;

import com.netcracker.models.services.BookManager;
import sun.text.normalizer.UCharacterIterator;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseOracle {

    private static DataBaseOracle staticDataBaseOracle = new DataBaseOracle();

    public static DataBaseOracle getStaticDataBaseOracle() {
        return staticDataBaseOracle;
    }


    public Connection connection;
    private Driver driver;

    public DataBaseOracle() {
        driver = new oracle.jdbc.OracleDriver();
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Driver successfully registered!");

        try {

            Properties p = new Properties();
            p.setProperty("user", "TLT_7");
            p.setProperty("password", "TLT_7");
//            p.setProperty("useUnicode", "true");
//            p.setProperty("characterEncoding","UTF-8");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@sql.edu-netcracker.com:1251:XE", p);
            System.out.println("getConnection successfully !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
