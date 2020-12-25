package com.netcracker.models.services;


import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.models.types.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class BookManager {

    private DataBaseOracle dataBaseOracle;
    public ArrayList<Book> bookList;

    private static BookManager staticBookManager = new BookManager(DataBaseOracle.getStaticDataBaseOracle());

    public static BookManager getStaticBookManager() {
        return staticBookManager;
    }


    public BookManager(DataBaseOracle dataBaseOracle) {
        this.dataBaseOracle = dataBaseOracle;
        bookList = new ArrayList<Book>();
        readFile();   // читает файл
    }

    public void readFile()   // чтение файла, чтение из таблицы BOOKS в базе данных
    {
        Connection connection = dataBaseOracle.getConnection();

        Statement statement;
        try {
            statement = connection.createStatement();
            String req = "SELECT * FROM books";
            ResultSet resultSet = statement.executeQuery(req);

            int book_id;
            String book_name;
            String book_author;
            int book_year;

            while (resultSet.next()) {
                book_id = resultSet.getInt("book_id");
                book_name = resultSet.getString("book_name");
                book_author = resultSet.getString("book_author");
                book_year = resultSet.getInt("book_year");

                bookList.add(new Book(book_id, book_name, book_author, book_year));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean contains(Book book)  // размер листа
    {
        return bookList.contains(book);
    }

    public int getSize()  // размер листа
    {
        return bookList.size();
    }

    public boolean checkIndex(int index)  // проверят размеры, диапозон
    {
        return (0 <= index && index < bookList.size());
    }

    public boolean removeElem(int index) // удалет элемент
    {
        if (checkIndex(index)) {
            bookList.remove(index);
            return true;
        } else return false;
    }

    public boolean addElem(Book book)  // добавдяет эдемент
    {
        if (bookList.contains(book))
            return false;
        else return (bookList.add(book));

    }

    public Book getElem(int index)   // возврат Книги по индексу
    {
        if (checkIndex(index))
            return bookList.get(index);
        else return null;
    }

    public boolean editElements(int index, String bookName, String bookAuthor, int bookYear)  // редактирование
    {
        if (checkIndex(index)) {
            Book book = bookList.get(index);
            book.setElements(bookName, bookAuthor, bookYear);

            return true;
        } else return false;
    }

}
