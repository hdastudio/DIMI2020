package com.netcracker.controllers.helpfun;

import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.models.services.BookManager;
import com.netcracker.models.types.Book;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertInto {
    public boolean insert(Book book) {
        BookManager bookManager = BookManager.getStaticBookManager();
        DataBaseOracle dataBaseOracle = DataBaseOracle.getStaticDataBaseOracle();

        String querty = "INSERT INTO books VALUES( " + book.getBookId() + ", '" + book.getBookName() +
                "', ' " + book.getAuthor() + " ', " + book.getPresentYear() + ")";


        Connection con = dataBaseOracle.getConnection();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(querty);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("не удалось выполгить запрос: insert into");
            return false;
        }
    }
}
