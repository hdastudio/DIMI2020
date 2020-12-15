package com.netcracker.models.services;


import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.models.types.Book;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class BookManager {

    private DataBaseOracle dataBaseOracle;
    private ArrayList<Book> bookList;


    public BookManager(DataBaseOracle dataBaseOracle)
    {
        this.dataBaseOracle = dataBaseOracle;
        bookList = new ArrayList<Book>();
        readFile();   // читает файл
    }

    public void readFile()   // чтение файла, чтение из таблицы BOOKS в базе данных
    {
//        try(FileReader reader = new FileReader("table.csv"))
//        {
//            Scanner scan = new Scanner(reader);
//            String buffer = "";
//            ArrayList<String> bufferArrayString = new ArrayList<String>();
//
//            while (scan.hasNextLine())
//            {
//                buffer = scan.nextLine();
//                bufferArrayString = parseString(buffer);
//               // System.out.println(bufferArrayString );
//                String bookName = bufferArrayString.get(0);
//                String author = bufferArrayString.get(1);
//                String year = bufferArrayString.get(2);
//                int intYear =Integer.parseInt(year);
//              //  System.out.println(bookName + author + year);
//                bookList.add(new Book(bookName, author, intYear));
//            }
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }


        Connection connection = dataBaseOracle.getConnection();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            String req = "SELECT * FROM books";
            ResultSet resultSet = statement.executeQuery(req);

            int book_id = 0;
            String book_name = "";
            String book_author = "";
            int book_year =  0;

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

//    public void writeFile()    // Записываем лист в файл
//    {
//        try(FileWriter writer = new FileWriter("table.csv", false))
//        {
//            String buf;
//            for (int i = 0; i<getSize(); ++i)
//            {
//                buf = bookList.get(i).toString();
//                String bookName = bookList.get(i).getBookName();
//                String author = bookList.get(i).getAuthor();
//                int year = bookList.get(i).getPresentYear();
//
//                writer.write(bookName + ';' + author + ';' + year + '\n');
//            }
//
//            writer.flush();
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }
//    }

//    public ArrayList<String> parseString(String myString)  // разбиение строк на элементы
//    {
//        ArrayList<String> arrayString = new ArrayList<String>();
//        String buffer = "";
//        for (int i = 0; i<myString.length(); ++i)
//        {
//            if( myString.charAt(i) != ';')
//            {
//                buffer = buffer + myString.charAt(i);
//            } else {
//                arrayString.add(buffer);
//                buffer = "";
//            }
//        }
//        arrayString.add(buffer);
//        return arrayString;
//    }

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
        if ( 0 <=  index  &&  index < bookList.size() ) {
            return true;
        } else return false;
    }

    public  boolean  removeElem(int index) // удалет элемент
    {
        if (checkIndex(index) ) {
            bookList.remove(index);
            return true;
        } else return false;
    }

    public boolean addElem(Book book)  // добавдяет эдемент
    {
        if (bookList.contains(book))
         return false;
        else if (bookList.add(book))
            return true;
        else return false;
    }
    public Book getElem(int index)   // возврат Книги по индексу
    {
        if (checkIndex(index))
            return bookList.get(index);
        else return null;
    }
    public boolean editElements(int index, String bookName, String bookAuthor, int bookYear)  // редактирование
    {
        if (checkIndex(index) ) {
            Book book = bookList.get(index);
            book.setElements(bookName, bookAuthor, bookYear);

            return true;
        } else return false;
    }

    public boolean editElemBookName(int index, String bookName)
    {
        if (checkIndex(index) ) {
            Book book = bookList.get(index);
            book.setBookName(bookName);

            return true;
        } else return false;
    }
    public boolean editElemAuthor(int index, String author)
    {
        if ( checkIndex(index)) {
            Book book = bookList.get(index);
            book.setAuthor(author);
            return true;
        }
        else return false ;
    }
    public boolean editElemYear(int index, int year)
    {
        if ( checkIndex(index) ) {
            Book book = bookList.get(index);
            book.setPresentYear(year);
            return true;
        }
        else return  false;
    }
}
