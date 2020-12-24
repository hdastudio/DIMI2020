package com.netcracker.controllers.servlets;

import com.netcracker.models.services.BookManager;
import com.netcracker.models.types.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class ListBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookManager bookManager = BookManager.getStaticBookManager();
        ArrayList<Book> books = bookManager.bookList;
        req.setAttribute("userNames", books);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(req, resp);
    }
}