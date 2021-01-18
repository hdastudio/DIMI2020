package com.example.DataBaseOperation;




import com.example.Model.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateBook {

    Book oldBook;
    Book newBook;
    DataBaseOracle dataBaseOracle;
    final String query;

    public UpdateBook(DataBaseOracle dataBaseOracle, Book oldBook, Book newBook) {
        this.dataBaseOracle = dataBaseOracle;
        this.oldBook = oldBook;
        this.newBook = newBook;

        query = "UPDATE BOOKS SET BOOK_ID = " + newBook.getBookId() +
                ", BOOK_NAME='" + newBook.getBookName() + "', BOOK_AUTHOR='" +
                newBook.getAuthor() + "', BOOK_YEAR='" + newBook.getPresentYear() +
                "' WHERE BOOK_ID=" + oldBook.getBookId();
    }

    public boolean update() {

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
