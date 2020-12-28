package com.netcracker.purchases.views.web.servlets;

import com.netcracker.purchases.utils.CsvService;
import com.netcracker.purchases.utils.OracleService;
import com.netcracker.purchases.views.web.WebView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.netcracker.purchases.utils.CommonData.*;
import static com.netcracker.purchases.views.web.WebView.*;

public class DataSave extends HttpServlet {
    private static final String TITLE_VALUE = "Сохранение списка покупок";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);

        if (!IS_JDBC_INSTALLED) {
            String msg = CsvService.save(PURCHASES) ? MSG_LIST_SAVE_SUCCESS : MSG_LIST_SAVE_ERR;
            actionsResultSetAttribute(req, msg, BUTTON_BACK_VALUE, SHOW_MENU_JSP);
            req.getRequestDispatcher(ACTIONS_RESULT_JSP).forward(req, resp);
            return;
        }

        dataInputSetAttribute(req, TITLE_VALUE, DATA_SAVE, NAME_VARIABLE_FILE, NAME_VARIABLE_DB, MSG_SELECT_DATA_SAVE);
        req.getRequestDispatcher(DATA_INPUT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        String ans = req.getParameter(BUTTON_TYPE);

        String msg;
        if (ans.equals(NAME_VARIABLE_FILE)) {
            msg = CsvService.save(PURCHASES) ? MSG_LIST_SAVE_SUCCESS : MSG_LIST_SAVE_ERR;
        } else {
            msg = OracleService.save(PURCHASES) ? MSG_LIST_SAVE_SUCCESS : MSG_LIST_SAVE_ERR;
        }

        if (WebView.findCookie(req.getCookies(), IS_SAVED) != null) {
            actionsResultSetAttribute(req, msg, BUTTON_EXIT_VALUE, INDEX_JSP);
            resp.addCookie(new Cookie(IS_SAVED, ""));
            PURCHASES.clear();
        } else {
            actionsResultSetAttribute(req, msg, BUTTON_BACK_VALUE, SHOW_MENU_JSP);
        }

        req.getRequestDispatcher(ACTIONS_RESULT_JSP).forward(req, resp);
    }
}
