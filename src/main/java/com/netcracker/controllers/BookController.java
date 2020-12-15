package com.netcracker.controllers;


import com.netcracker.models.services.BookManager;
import com.netcracker.models.types.Book;
import com.netcracker.views.BookView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BookController {
    final int amountOperation = 5; // количество операций
    private DataBaseOracle dataBaseOracle;
    private BookManager container;
    private BookView view;

    public BookController(BookManager container, BookView view, DataBaseOracle dataBaseOracle){
        this.dataBaseOracle = dataBaseOracle;
        this.container = container;
        this.view = view;
    }


    public void addContainerElem (){     // Добавление книги в контейнер
        Book book = view.addElem();

        boolean checkClone = container.contains(book);    // проверяет , есть ли такой же элемент

        if (!checkClone) {
            if (container.addElem(book)) {  // добваляем также и в базу данных

                String querty = "INSERT INTO books VALUES( " + book.getBookId() + ", '" + book.getBookName() +
                        "', ' " + book.getAuthor() + " ', " + book.getPresentYear() + ")";

                Connection con = dataBaseOracle.getConnection();
                try {
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(querty);
                    view.printAddElemTrue(book);  // если добавилось, вывести что добавилось
                } catch (SQLException throwables) {
                    view.printAddElemFalse(book);
                    throwables.printStackTrace();
                }
            } else view.printAddElemFalse(book);
        } else view.printErrorCloneElem(book);
    }

    public void removeContainerElem () {     // удаление элемента
        int index =  view.getIndex("удалить элемент номер = ");  // проверка на корректность

        if (container.checkIndex(index))
        {
           int deleteByHash = container.getElem(index).getHashBook();

                String querty = "delete from BOOKS\n" +
                        "where BOOK_ID = " + deleteByHash  ;
                Connection connection = dataBaseOracle.getConnection();
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(querty);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                container.removeElem(index);
                view.printDeleteTrue(index);  // вывести об успешном удалении

        }
        else
        {
            view.printDeleteFalse(index);   // вывести об неуспешном удалении
            view.printRangeIndex(0, container.getSize()-1);
        }


    }


    public void editContainerElem()
    {
        int index =  view.getIndex("редактировать элемент номер = ");  // проверка на корректность
        if (container.checkIndex(index)) {
            int hashOld = container.getElem(index).getHashBook();
            Book book = view.addElem();

            boolean checkClone = container.contains(book);    // проверяет , есть ли такой же элемент
               if (!checkClone)
               {
                   container.editElements(index, book.getBookName(), book.getAuthor(), book.getPresentYear());

                   String querty = "UPDATE BOOKS SET BOOK_ID = "+book.getHashBook()+
                           ", BOOK_NAME='"+book.getBookName()+"', BOOK_AUTHOR='"+
                           book.getAuthor()+"', BOOK_YEAR='"+ book.getPresentYear()+
                           "' WHERE BOOK_ID="+hashOld ;

                   Connection connection = dataBaseOracle.getConnection();
                   try {
                       Statement statement = connection.createStatement();
                       statement.executeUpdate(querty);
                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }

               } else view.printErrorCloneElem(book);  // если есть такойже эелемент, то не редактировать


        } else view.printRangeIndex(0, container.getSize() - 1);  // если ввели не в диапозоне
    }


    public void printAllElem()
    {
        for (int i = 0; i < container.getSize(); ++i)
            view.printElem(i, container.getElem(i));
    }

    public void saveFile()
    {
        //container.writeFile();
    }

    public void exit()
    {
        System.exit(0);
    }


    public void doSmt()
    {
        while (true) {
            int index = view.doSmt(amountOperation); // 5 количество операций

            if (index == 1) {        // добавить элемент в контейнер
                addContainerElem();  // добавить элемент в контейнер
            }
            else if (index == 2) {  // удалить элемент
                if (container.getSize() != 0)
                    removeContainerElem();
                else view.printZeroElem();
            }
            else if (index == 3)  // вывести все элементы
            {
                if (container.getSize() != 0)
                    printAllElem();
                else view.printZeroElem();
            }

            else if (index == 4) // редактировать элементы
            {
                if (container.getSize() != 0)
                    editContainerElem();
                else view.printZeroElem();
            }


            else if (index == 5) // сохранить элементы
                exit();

        }

    }
}
