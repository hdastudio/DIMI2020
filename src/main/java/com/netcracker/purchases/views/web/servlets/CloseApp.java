package com.netcracker.purchases.views.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.netcracker.purchases.utils.CommonData.*;
import static com.netcracker.purchases.views.web.WebView.*;

public class CloseApp extends HttpServlet {
    private static final String TITLE_VALUE = "Выход из приложения";
    private static final String SERVLET_NAME_VALUE = "/CloseApp";
    private static final String BUTTON_ONE_NAME = "Да";
    private static final String BUTTON_TWO_NAME = "Нет";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);

        dataInputSetAttribute(req, TITLE_VALUE, SERVLET_NAME_VALUE, BUTTON_ONE_NAME, BUTTON_TWO_NAME, MSG_DATA_SAVE);
        req.getRequestDispatcher(DATA_INPUT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        String ans = req.getParameter(BUTTON_TYPE);

        if (ans.equals(BUTTON_ONE_NAME)) {
            resp.addCookie(new Cookie(IS_SAVED, "true"));
            resp.sendRedirect(req.getContextPath() + DATA_SAVE);
        } else {
            PURCHASES.clear();
            actionsResultSetAttribute(req, MSG_APP_CLOSE, BUTTON_EXIT_VALUE, INDEX_JSP);
            req.getRequestDispatcher(ACTIONS_RESULT_JSP).forward(req, resp);
        }

    }
}
