package com.example.DataBaseOperation;



import com.example.Model.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertInto {

    DataBaseOracle dataBaseOracle;
    Book book;
    final String query;

    public InsertInto(DataBaseOracle dataBaseOracle, Book book) {
        this.dataBaseOracle = dataBaseOracle;
        this.book = book;

        query = "INSERT INTO books VALUES( " + book.getBookId() + ", '" + book.getBookName() +
                "', ' " + book.getAuthor() + " ', " + book.getPresentYear() + ")";
    }

    public boolean insert() {
        Connection con = dataBaseOracle.getConnection();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("не удалось выполгить запрос: insert into");
            return false;
        }
    }
}
