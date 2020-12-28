package com.netcracker.purchases.views.web.servlets;

import com.netcracker.purchases.views.web.WebView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.netcracker.purchases.utils.CommonData.*;
import static com.netcracker.purchases.views.web.WebView.*;

public class ShowPurchases extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);

        if (PURCHASES.size() == 0) {
            req.setAttribute(MSG_LIST_EMPTY, MSG_LIST_EMPTY_VALUE);
            req.getRequestDispatcher(LIST_EMPTY_JSP).forward(req, resp);
            return;
        }

        WebView.showPurchase(req);
        req.setAttribute("MSG_SHOW_SUCCESS", "<br>" + MSG_SHOW_SUCCESS + "<br>");
        req.getRequestDispatcher("/actions/ShowPurchases.jsp").forward(req, resp);
    }
}
