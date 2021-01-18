package com.example.DataBaseOperation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteFrom {

    int bookId;
    DataBaseOracle dataBaseOracle;
    final String query;

    public DeleteFrom(DataBaseOracle dataBaseOracle, int bookId) {
        this.dataBaseOracle = dataBaseOracle;
        this.bookId = bookId;
        query = "delete from BOOKS\n" +
                "where BOOK_ID = " + bookId;
    }

    public boolean delete() {

        System.out.println(query);

        Connection connection = dataBaseOracle.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
