package com.netcracker.controllers.servlets;

import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.controllers.helpfun.CheckCorrectBook;
import com.netcracker.controllers.helpfun.CheckNumber;
import com.netcracker.controllers.helpfun.UpdateBook;
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


public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("edit.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("book_number");
        String bookName = req.getParameter("book_name");
        String bookAuthor = req.getParameter("book_author");
        String bookYear = req.getParameter("book_year");

        System.out.println("bookYear " + bookYear);

        boolean correcNumber = false;
        int intIndex = -1;

        CheckNumber checkNumber = new CheckNumber(index);
        if (checkNumber.getCorrect()) // проверка числа на корректность
        {
            correcNumber = true;
            intIndex = checkNumber.getnumber();
        }

        if (correcNumber) {
            intIndex--;
            Book book;

            CheckCorrectBook checkCorrectBook = new CheckCorrectBook(bookName, bookAuthor, bookYear);

            if (checkCorrectBook.checkCorrectBook()) {  // если корректный book
                book = checkCorrectBook.getBook();
                BookManager bookManager = BookManager.getStaticBookManager();
                if (bookManager.checkIndex(intIndex)) // если есть индекс , входит в промежуток от 0 до количества книг
                {
                    int hashOld = bookManager.getElem(intIndex).getHashBook(); // получает хеш книги
                    boolean checkClone = bookManager.contains(book);    // проверяет , есть ли такой же элемент

                    if (!checkClone) {
                        UpdateBook updateBook = new UpdateBook();
                        if (updateBook.update(book, intIndex, hashOld)) {
                            req.setAttribute("editBook", "книга " + (intIndex + 1) + " обновлена");
                            doGet(req, resp);
                        } else // не смог обновить элемент
                        {
                            req.setAttribute("editBook", "книга " + (intIndex + 1) + "не обновлена");
                            doGet(req, resp);
                        }
                    } else // уже есть такой элемент
                    {
                        req.setAttribute("editBook", "уже есть такая книга");
                        doGet(req, resp);
                    }
                } else { // если нету индекса
                    req.setAttribute("editBook", "нету такого индекса");
                    doGet(req, resp);
                }
            } else // не корректный book
            {
                req.setAttribute("editBook", "не корректный ввод книги");
                doGet(req, resp);
            }
        } else // неправильный номер
        {
            req.setAttribute("editBook", "не корректный ввод индекса");
            doGet(req, resp);
        }
    }
}