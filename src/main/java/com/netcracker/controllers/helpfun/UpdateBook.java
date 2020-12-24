package com.netcracker.controllers.helpfun;

import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.models.services.BookManager;
import com.netcracker.models.types.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateBook {
    public boolean update(Book book, int intNuber, int hashOld) {
        BookManager bookManager = BookManager.getStaticBookManager();
        DataBaseOracle dataBaseOracle = DataBaseOracle.getStaticDataBaseOracle();

        String querty = "UPDATE BOOKS SET BOOK_ID = " + book.getHashBook() +
                ", BOOK_NAME='" + book.getBookName() + "', BOOK_AUTHOR='" +
                book.getAuthor() + "', BOOK_YEAR='" + book.getPresentYear() +
                "' WHERE BOOK_ID=" + hashOld;

        Connection connection = dataBaseOracle.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(querty);
            bookManager.editElements(intNuber, book.getBookName(), book.getAuthor(), book.getPresentYear());
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
