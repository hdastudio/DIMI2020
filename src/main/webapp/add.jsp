<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21/12/2020
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add book</title>
</head>
<body>

<div>
<form method="post">
    <label>название книги:
        <input type="text" name="book_name"><br />
    </label>
    <label>автор книги:
        <input type="text" name="book_author"><br />
    </label>
    <label>год издания:
        <input type="text" name="book_year"><br />
    </label>

    <button type="add book">добавить</button>
</form>
</div>

<div>
    <%
        String correct ;
        correct  =  (String) (request.getAttribute("addBook"));
    %> <h3> <%= correct %></h3>
</div>

<div>
    <button onclick="location.href='/'">назад</button>
</div>

</body>
</html>
