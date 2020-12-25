package com.netcracker.controllers.servlets;

import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.controllers.helpfun.CheckNumber;
import com.netcracker.controllers.helpfun.DeleteFrom;
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


public class RemoveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("remove.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("number");

        System.out.println("index " + index);

        int intIndex = -1;
        boolean correct = false;
        CheckNumber checkNumber = new CheckNumber(index);
        if (checkNumber.getCorrect()) // проверка числа на корректность
        {
            correct = true;
            intIndex = checkNumber.getnumber();
        }


        if (correct) {  // если корректное число
            DeleteFrom deleteFrom = new DeleteFrom();
            intIndex--;
            if (deleteFrom.delete(intIndex)) {
                req.setAttribute("removeBook", "книга удалена");
                doGet(req, resp);
            } else {
                req.setAttribute("removeBook", "книга не удалена");
                doGet(req, resp);
            }
        } else {
            req.setAttribute("removeBook", "не корректное число");
            doGet(req, resp);
        }

    }
}