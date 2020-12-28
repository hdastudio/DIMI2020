package com.netcracker.purchases.views.web;

import com.netcracker.purchases.models.services.PurchaseManager;
import com.netcracker.purchases.models.types.Purchase;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static com.netcracker.purchases.utils.CommonData.*;

public final class WebView {
    public static final String ENCODING = "UTF-8";
    public static final String DATA_INPUT_JSP = "/actions/SelectAnything.jsp";
    public static final String TITLE = "title";
    public static final String SERVLET_NAME = "servletName";
    public static final String MSG_INFO = "msgInfo";
    public static final String INPUT_STR = "inputStr";
    public static final String IS_SAVED = "isSaved";
    public static final String DATA_SAVE = "/DataSave";
    public static final String JSP_NAME = "jspName";
    public static final String INDEX_JSP = "/index.jsp";
    public static final String BUTTON_NAME = "buttonName";
    public static final String BUTTON_EXIT_VALUE = "Выход";
    public static final String ACTIONS_RESULT_JSP = "/actions/ActionsResult.jsp";
    public static final String ANS = "ans";
    public static final String MSG_INPUT_ERR = "MSG_INPUT_ERR";
    public static final String NUM = "num";
    public static final String BUTTON_BACK_VALUE = "Назад";
    public static final String SHOW_MENU_JSP = "/actions/ShowMenu.jsp";
    public static final String MSG_LIST_EMPTY = "MSG_LIST_EMPTY";
    public static final String LIST_EMPTY_JSP = "/actions/ListEmpty.jsp";
    public static final String NAME_VARIABLE_FILE = "Файл";
    public static final String NAME_VARIABLE_DB = "База данных";
    public static final String BUTTON_ONE_VALUE = "buttonOneValue";
    public static final String BUTTON_TWO_VALUE = "buttonTwoValue";
    public static final String BUTTON_TYPE = "buttonType";
    public static final String ACTIONS_PURCHASES = "Добавление, редактирование, удаление покупки";
    public static final String BUTTON_ADD_VALUE = "Добавить";
    public static final String BUTTON_EDIT_VALUE = "Редактировать";
    public static final String BUTTON_DEL_VALUE = "Удалить";
    public static final String ID = "id";
    public static final String PURCHASE_NAME = "purchaseName";
    public static final String PURCHASE_COUNT = "purchaseCount";
    public static final String PURCHASE_UNIT = "purchaseUnit";
    public static final String PURCHASE_COMMENT = "purchaseComment";
    public static final String MSG_ERR = "msgErr";

    private WebView() {
    }

    public static void showPurchase(HttpServletRequest req) {
        for (Purchase purchase : PURCHASES) {
            req.setAttribute("idLocal", purchase.getIdLocal());
            req.setAttribute("name", purchase.getName());
            req.setAttribute("count", purchase.getCount());
            req.setAttribute("unit", purchase.getUnit());
            req.setAttribute("comment", purchase.getComment());
        }

        req.setAttribute("PURCHASES", PURCHASES);
    }

    public static String findCookie(Cookie[] cookies, String key) {
        for (Cookie cookie : cookies) {
            if ((key.equals(cookie.getName())) && (!cookie.getValue().equals(""))) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void dataInputSetAttribute(HttpServletRequest req, String titleValue, String servletNameValue,
                                             String buttonOneName, String buttonTwoName, String msgInfo) {
        req.setAttribute(TITLE, titleValue);
        req.setAttribute(SERVLET_NAME, servletNameValue);
        req.setAttribute(BUTTON_ONE_VALUE, buttonOneName);
        req.setAttribute(BUTTON_TWO_VALUE, buttonTwoName);
        req.setAttribute(MSG_INFO, msgInfo + "<br>");
    }

    public static void actionsResultSetAttribute(HttpServletRequest req, String msg, String buttonValue,
                                                 String jspValue) {
        req.setAttribute(TITLE, msg);
        req.setAttribute(BUTTON_NAME, buttonValue);
        req.setAttribute(ANS, msg + "<br>");
        req.setAttribute(JSP_NAME, jspValue);
    }

    public static boolean dataInput(HttpServletRequest req, StringBuilder[] propPurchase) {
        String name = req.getParameter(PURCHASE_NAME);
        String count = req.getParameter(PURCHASE_COUNT);
        String unit = req.getParameter(PURCHASE_UNIT);
        String comment = req.getParameter(PURCHASE_COMMENT);

        String msgErr = "Введено некорректное значение в следующие поля: ";
        boolean isErr = false;

        if (PurchaseManager.checkEmpty(name)) {
            propPurchase[0] = new StringBuilder(req.getParameter(PURCHASE_NAME));
        } else {
            msgErr += MSG_NAME + " ";
            isErr = true;
        }

        if (PurchaseManager.checkCount(count)) {
            propPurchase[1] = new StringBuilder(req.getParameter(PURCHASE_COUNT));
        } else {
            msgErr += MSG_COUNT + " ";
            isErr = true;
        }

        if (PurchaseManager.checkEmpty(unit)) {
            propPurchase[2] = new StringBuilder(req.getParameter(PURCHASE_UNIT));
        } else {
            msgErr += MSG_UNIT;
            isErr = true;
        }

        propPurchase[3] = comment != null ? new StringBuilder(req.getParameter(PURCHASE_COMMENT)) :
                new StringBuilder();
        PurchaseManager.changeComment(propPurchase[3]);

        if (isErr) {
            req.setAttribute(MSG_ERR, msgErr);
        }
        return !isErr;
    }

}
