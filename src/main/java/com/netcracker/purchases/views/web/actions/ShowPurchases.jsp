<%@ page import="static com.netcracker.purchases.utils.CommonData.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dronald
  Date: 14.12.2020
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Отображение списка покупок</title>
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
    </tr>
    <% int i = 0; %>
    <c:forEach items="${PURCHASES}" var="PURCHASES">
        <tr>
            <td>
                <%=i++%>
            </td>
            <td> ${PURCHASES.idLocal} </td>
            <td> ${PURCHASES.name} </td>
            <td> ${PURCHASES.count} </td>
            <td> ${PURCHASES.unit} </td>
            <td> ${PURCHASES.comment} </td>
        </tr>
    </c:forEach>
</table>
<h2>
    <font color="#228b22">
        ${MSG_SHOW_SUCCESS}
    </font>
</h2>
<form action="${pageContext.request.contextPath}/actions/ShowMenu.jsp">
    <input type="submit" value="Назад">
</form>
</body>
</html>
