package com.netcracker.purchases.views.web.servlets;

import com.netcracker.purchases.models.services.PurchaseManager;
import com.netcracker.purchases.models.types.Purchase;
import com.netcracker.purchases.views.web.WebView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.netcracker.purchases.utils.CommonData.*;
import static com.netcracker.purchases.views.web.WebView.*;

public class ActionsPurchases extends HttpServlet {
    private static final String SELECT_PURCHASE_JSP = "/actions/ActionsPurchases.jsp";
    private static final String MSG_ADD_ERR = "Добавление невозможно!";
    private static final String MSG_EDIT_ERR = "Редактирование невозможно!";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);

        if (PURCHASES.size() == 0) {
            req.setAttribute(MSG_LIST_EMPTY, MSG_LIST_EMPTY_VALUE);
            req.getRequestDispatcher(LIST_EMPTY_JSP).forward(req, resp);
            return;
        }

        WebView.showPurchase(req);
        req.getRequestDispatcher(SELECT_PURCHASE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        String method = req.getParameter(BUTTON_TYPE);

        if (method.equals(BUTTON_ADD_VALUE)) {
            doPut(req, resp);
            return;
        } else if (method.equals(BUTTON_DEL_VALUE)) {
            doDelete(req, resp);
            return;
        }

        StringBuilder[] propPurchase = new StringBuilder[4];

        if (dataInput(req, propPurchase)) {
            String num = req.getParameter(ID);
            Purchase purchase = PURCHASES.get(Integer.parseInt(num));
            PurchaseManager.edit(purchase, propPurchase);
            req.setAttribute(MSG_INFO, MSG_EDIT_SUCCESS + "<br>");
        } else {
            req.setAttribute(MSG_ERR, req.getAttribute(MSG_ERR) + "<br>" + MSG_EDIT_ERR);
        }

        WebView.showPurchase(req);
        req.getRequestDispatcher(SELECT_PURCHASE_JSP).forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter(ID);

        PurchaseManager.del(number, PURCHASES);

        WebView.showPurchase(req);
        req.setAttribute(MSG_INFO, MSG_DEL_SUCCESS + "<br>");
        req.getRequestDispatcher(SELECT_PURCHASE_JSP).forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder[] propPurchase = new StringBuilder[4];

        if (dataInput(req, propPurchase)) {
            PurchaseManager.add(PURCHASES, propPurchase);
            req.setAttribute(MSG_INFO, MSG_ADD_SUCCESS + "<br>");
        } else {
            req.setAttribute(MSG_ERR, req.getAttribute(MSG_ERR) + "<br>" + MSG_ADD_ERR);
        }

        WebView.showPurchase(req);
        req.getRequestDispatcher(SELECT_PURCHASE_JSP).forward(req, resp);
    }
}
