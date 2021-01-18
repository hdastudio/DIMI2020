package com.example.Model;


import com.example.DataBaseOperation.DataBaseOracle;
import com.example.DataBaseOperation.DeleteFrom;
import com.example.DataBaseOperation.InsertInto;
import com.example.DataBaseOperation.UpdateBook;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    DataBaseOracle dataBaseOracle = new DataBaseOracle();

    private static List<Book> books = new ArrayList<>();


    BookService() {
        readFromDataBase();
        System.out.println(books);
    }

    void readFromDataBase() {
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

                books.add(new Book(book_id, book_name, book_author, book_year));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    boolean remove(int index) {
       if (checkIndex(index) )
        {
            DeleteFrom deleteFrom = new DeleteFrom(dataBaseOracle, books.get(index).getBookId());
            books.remove(index);
            return     deleteFrom.delete();
        }
       return false;
    }


    public boolean contains(Book book)  // размер листа
    {
        return books.contains(book);
    }

    public int getListSize()  // размер листа
    {
        return books.size();
    }

    public boolean checkIndex(int index)  // проверят размеры, диапозон
    {
        return (0 <= index && index < books.size());
    }



    public boolean addElem(Book book)  // добавляет элемент
    {
        if (books.contains(book))
            return false;
        else {
           InsertInto insertInto = new InsertInto(dataBaseOracle, book);
            return (books.add(book) & insertInto.insert() );
        }
    }

    public Book getElem(int index)   // возврат Книги по индексу
    {
        if (checkIndex(index))
            return books.get(index);
        else return null;
    }


    public Book getBookById(String bookId)   // возврат Книги по индексу
    {
        bookId = bookId.replace(",", "");
        int id = Integer.parseInt(bookId);

        for (int i = 0; i < books.size(); ++i)
        {
            Book book = books.get(i);
            if (book.getBookId() == id)
                return book;
        }
        return null;
    }


    public boolean editElement(int index, String bookName, String bookAuthor, int bookYear)  // редактирование
    {
        if (checkIndex(index)) {
            Book book = books.get(index);
            book.setElements(bookName, bookAuthor, bookYear);

            return true;
        } else return false;
    }

    public List<Book> findAll() {
        return books;
    }

    public boolean editElement(int index, Book newBook )  // редактирование
    {
        if (checkIndex(index)) {

            if (!(books.contains(newBook) ) ) {  // изменить если нету такой книги
                Book oldBook = books.get(index);
                UpdateBook updateBook = new UpdateBook(dataBaseOracle, oldBook, newBook);
                updateBook.update();
                oldBook.setElements(newBook.getBookName(), newBook.getAuthor(), newBook.getPresentYear());
                return true;
            } else return false;
        } else return false;
    }




}
