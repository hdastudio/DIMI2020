package com.netcracker.controllers.servlets;


import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.controllers.helpfun.CheckCorrectBook;
import com.netcracker.controllers.helpfun.InsertInto;
import com.netcracker.models.services.BookManager;
import com.netcracker.models.types.Book;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("add.jsp");
        req.setAttribute("addNew", "статус");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("book_name");
        String bookAuthor = req.getParameter("book_author");
        String bookYear = req.getParameter("book_year");


        boolean correct = false;
        String errorString = "";
        Book book = null;

        CheckCorrectBook checkCorrectBook = new CheckCorrectBook(bookName, bookAuthor, bookYear);
        if (checkCorrectBook.checkCorrectBook()) {  // проверка книги на корректность
            correct = true;
            book = checkCorrectBook.getBook();
        } else errorString = checkCorrectBook.getErrorString();

//        System.out.println("errorString =" + errorString);

        if (correct) {
            BookManager bookManager = BookManager.getStaticBookManager();


            if (!bookManager.contains(book)) {   // проверить есть ли такая книга уже
                InsertInto insertInto = new InsertInto(); // вставка в базу данных , метод insert into
                if (insertInto.insert(book))  // если успешно всталено в базу данных
                {
                    bookManager.addElem(book);
                    req.setAttribute("addBook", "книга добавлена");
                    doGet(req, resp);
                } else  // если не всталено в базу данных
                {
                    req.setAttribute("addBook", "не удалось добавить книгу");
                    doGet(req, resp);
                }
            } else {    // если уже есть такая книга
                req.setAttribute("addBook", "такая книга уже сть");
                doGet(req, resp);
            }
        } else {       // если форма с ошибками
            req.setAttribute("addBook", errorString);
            doGet(req, resp);
        }

    }
}