<%--
  Created by IntelliJ IDEA.
  User: Dronald
  Date: 16.12.2020
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список с покупками пуст</title>
</head>
<body>
<font color="#b22222">
    ${MSG_LIST_EMPTY}
</font>
<form action="${pageContext.request.contextPath}/actions/ShowMenu.jsp">
    <input type="submit" value="Назад">
</form>
</body>
</html>
