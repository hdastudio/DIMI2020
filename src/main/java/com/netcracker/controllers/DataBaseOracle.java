package com.netcracker.controllers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseOracle {
    public Connection connection;
    private   Driver driver;
    public DataBaseOracle()
    {
         driver = new oracle.jdbc.OracleDriver();
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Driver successfully registered!");

        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@sql.edu-netcracker.com:1251:XE", "TLT_7", "TLT_7");
            System.out.println("getConnection successfully !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
