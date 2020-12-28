<%--
  Created by IntelliJ IDEA.
  User: Dronald
  Date: 16.12.2020
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h2>
    <form action="${pageContext.request.contextPath}${jspName}">
        <font color="#b22222">
            ${MSG_JDBC_NOT_FOUND}
        </font>
        <font color="#228b22">
            ${ans}
        </font>
        <input type="submit" value="${buttonName}">
    </form>
</h2>
</body>
</html>
