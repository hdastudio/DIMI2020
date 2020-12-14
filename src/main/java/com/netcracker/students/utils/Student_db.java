package com.netcracker.students.utils;


import java.sql.*;

import static oracle.jdbc.OracleType.VARCHAR2;

public class Student_db {
    private final String URL = "jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe";
    private final String USERNAME = "TLT_14";
    private final String PASSWORD = "TLT_14";
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public void connectToDB() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("create table Student" + "(" +
                    "id number, " +
                    "last_name VARCHAR2(30), " +
                    "first_name VARCHAR2(20)," +
                    "middle_name VARCHAR2(30)," +
                    "age number," +
                    "faculty VARCHAR2(30)," +
                    "course number" +
                    ")");
            statement.executeQuery("create sequence my_seq " +
                    "minvalue 1  " +
                    "maxvalue 9999999 " +
                    "start with 1 " +
                    "increment by 1 " +
                    "cache 20");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertData(String lastname, String firstname, String middlename, int age, String faculty, int course) {
        try {
            statement.executeUpdate("INSERT INTO Student(id,last_name," +
                    "first_name," +
                    "middle_name," +
                    "age,faculty," +
                    "course) " +
                    "VALUES(my_seq.nextval,'" +
                    lastname +
                    "','" + firstname +
                    "','" + middlename + "'," +
                    age +
                    ",'" + faculty + "'," +
                    course + ")");
            //statement.executeQuery("update student set last_name='Marod' where id=1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(int id, String lastname, String firstname, String middlename, int age, String faculty, int course) {
        try {
            //statement = connection.createStatement();
            System.out.println("is updating");
            statement.executeQuery("update student " +
                    "set last_name='" + lastname +
                    "',first_name = '" + firstname +
                    "',middle_name ='" + middlename +
                    "',age=" + age +
                    ",faculty='" + faculty +
                    "',course=" + course +
                    " where id in " + id);
        } catch (SQLException e) {
            System.out.println("Студента с таким ID не существует!!");
        }

    }

    public void deleteData(int id) {
        try {
            statement.executeUpdate("delete student where id=" + id);
            System.out.println("Student with id = " + id + " is deleted");
        } catch (SQLException e) {
            System.out.println("Студента с таким ID не существует!!");
        }
    }

    public boolean isIdExist(int id) {
        try {
            resultSet = statement.executeQuery("select id from student");
            while (resultSet.next()) {
                if (id == resultSet.getInt("id")) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getDataCount() {
        try {
            resultSet = statement.executeQuery("select count(*) as count from student");
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void printData() {
        try {
            resultSet = statement.executeQuery("select * from student");
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getString("id") +
                        "\nLastname: " + resultSet.getString("last_name") +
                        "\nFirstname: " + resultSet.getString("first_name") +
                        "\nMiddlename: " + resultSet.getString("Middle_name") +
                        "\nAge: " + resultSet.getString("age") +
                        "\nFaculty: " + resultSet.getString("faculty") +
                        "\nCourse: " + resultSet.getString("course"));
                System.out.println("----------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
