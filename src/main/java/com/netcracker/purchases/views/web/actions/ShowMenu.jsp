<%@ page import="static com.netcracker.purchases.utils.CommonData.*" %>
<%@ page import="static com.netcracker.purchases.views.web.WebView.ACTIONS_PURCHASES" %><%--
  Created by IntelliJ IDEA.
  User: Dronald
  Date: 14.12.2020
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню приложения</title>
</head>
<body>

<table>
    <tr>
        <th><h2>
            <%=MSG_MENU%>
        </h2></th>
    </tr>
    <tr>
        <td>
            <form action="${pageContext.request.contextPath}/ActionsPurchases" method="get">
                <input type="submit" style="width: 100%" value="<%=ACTIONS_PURCHASES%>">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="${pageContext.request.contextPath}/ShowPurchases" method="get">
                <input type="submit" style="width: 100%" value="<%=MSG_SHOW%>">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="${pageContext.request.contextPath}/DataSave" method="get">
                <input type="submit" style="width: 100%" value="<%=MSG_SAVE%>">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="${pageContext.request.contextPath}/CloseApp" method="get">
                <input type="submit" style="width: 100%" value="<%=MSG_EXIT%>">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
