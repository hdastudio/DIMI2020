<%@ page import="static com.netcracker.purchases.utils.CommonData.*" %>
<%@ page import="static com.netcracker.purchases.views.web.WebView.ACTIONS_PURCHASES" %>
<%@ page import="static com.netcracker.purchases.views.web.WebView.BUTTON_TYPE" %>
<%@ page import="static com.netcracker.purchases.views.web.WebView.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: Dronald
  Date: 16.12.2020
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=ACTIONS_PURCHASES%>
    </title>
</head>
<body>
<table border="1">
    <tr>
        <th>
            <%=MSG_NUM%>
        </th>
        <th>
            <%=MSG_ID%>
        </th>
        <th>
            <%=MSG_NAME%>
        </th>
        <th>
            <%=MSG_COUNT%>
        </th>
        <th>
            <%=MSG_UNIT%>
        </th>
        <th>
            <%=MSG_COMMENT%>
        </th>
        <th>
            Действия
        </th>
    </tr>
    <% int i = 0; %>
    <c:forEach items="${PURCHASES}" var="PURCHASES">
        <form action="${pageContext.request.contextPath}/ActionsPurchases" method="post">
            <tr>
                <td><input type="text" name="<%=ID%>" style="border: none" value="<%=i++%>" readonly></td>
                <td>${PURCHASES.idLocal}</td>
                <td><input type="text" name="<%=PURCHASE_NAME%>" style="border: none" value="${PURCHASES.name}"></td>
                <td><input type="text" name="<%=PURCHASE_COUNT%>" style="border: none" value="${PURCHASES.count}"></td>
                <td><input type="text" name="<%=PURCHASE_UNIT%>" style="border: none" value="${PURCHASES.unit}"></td>
                <td><input type="text" name="<%=PURCHASE_COMMENT%>" style="border: none" value="${PURCHASES.comment}">
                </td>
                <td>
                    <input type="submit" name="<%=BUTTON_TYPE%>" value="<%=BUTTON_EDIT_VALUE%>">
                    <input type="submit" name="<%=BUTTON_TYPE%>" value="<%=BUTTON_DEL_VALUE%>">
                </td>
            </tr>
        </form>
    </c:forEach>
    <form action="${pageContext.request.contextPath}/ActionsPurchases" method="post">
        <tr>
            <td>
                *
            </td>
            <td>
                *
            </td>
            <td><input type="text" name="<%=PURCHASE_NAME%>" style="border: none"></td>
            <td><input type="text" name="<%=PURCHASE_COUNT%>" style="border: none"></td>
            <td><input type="text" name="<%=PURCHASE_UNIT%>" style="border: none"></td>
            <td><input type="text" name="<%=PURCHASE_COMMENT%>" style="border: none"></td>
            <td>
                <input type="submit" name="<%=BUTTON_TYPE%>" style="width: 100%" value="<%=BUTTON_ADD_VALUE%>">
            </td>
        </tr>
    </form>
</table>
<h2>
    <font color="#228b22">
        ${msgInfo}
    </font>
    <font color="#b22222">
        ${msgErr}
    </font>
</h2>
<form action="${pageContext.request.contextPath}/actions/ShowMenu.jsp">
    <input type="submit" value="Назад">
</form>
</body>
</html>
