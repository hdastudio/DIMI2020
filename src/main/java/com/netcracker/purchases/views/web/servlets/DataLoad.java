package com.netcracker.purchases.views.web.servlets;

import com.netcracker.purchases.utils.CsvService;
import com.netcracker.purchases.utils.OracleService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.netcracker.purchases.utils.CommonData.*;
import static com.netcracker.purchases.views.web.WebView.*;


public class DataLoad extends HttpServlet {
    private static final String TITLE_VALUE = "Загрузка списка покупок";
    private static final String SERVLET_NAME_VALUE = "/DataLoad";
    private static final String BUTTON_NEXT_VALUE = "Далее";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        resp.addCookie(new Cookie(IS_SAVED, ""));

        if (!IS_JDBC_INSTALLED) {
            req.setAttribute("MSG_JDBC_NOT_FOUND", MSG_JDBC_NOT_FOUND + "<br>");
            String msg = CsvService.load(PURCHASES) ? MSG_LIST_LOAD_SUCCESS : MSG_LIST_LOAD_ERR;
            actionsResultSetAttribute(req, msg, BUTTON_NEXT_VALUE, SHOW_MENU_JSP);
            req.getRequestDispatcher(ACTIONS_RESULT_JSP).forward(req, resp);
            return;
        }

        dataInputSetAttribute(req, TITLE_VALUE, SERVLET_NAME_VALUE, NAME_VARIABLE_FILE, NAME_VARIABLE_DB,
                MSG_SELECT_DATA_LOAD);
        req.getRequestDispatcher(DATA_INPUT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        String ans = req.getParameter(BUTTON_TYPE);

        String msg;
        if (ans.equals(NAME_VARIABLE_FILE)) {
            msg = CsvService.load(PURCHASES) ? MSG_LIST_LOAD_SUCCESS : MSG_LIST_LOAD_ERR;
        } else {
            msg = OracleService.load(PURCHASES) ? MSG_LIST_LOAD_SUCCESS : MSG_LIST_LOAD_ERR;
        }

        actionsResultSetAttribute(req,msg, BUTTON_NEXT_VALUE,SHOW_MENU_JSP);
        req.getRequestDispatcher(ACTIONS_RESULT_JSP).forward(req, resp);
    }
}
