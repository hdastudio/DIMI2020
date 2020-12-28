<%@ page import="com.netcracker.purchases.utils.CommonData" %>
<%@ page import="static com.netcracker.purchases.views.web.WebView.BUTTON_TYPE" %>
<%--
  Created by IntelliJ IDEA.
  User: Dronald
  Date: 14.12.2020
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h2>
    <form action="${pageContext.request.contextPath}${servletName}" method="post">
        ${msgInfo}
        <input type="submit" name="<%=BUTTON_TYPE%>" value="${buttonOneValue}">
        <input type="submit" name="<%=BUTTON_TYPE%>" value="${buttonTwoValue}">
    </form>
</h2>
</body>
</html>
