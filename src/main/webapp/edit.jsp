<%@ page import="com.netcracker.models.types.Book" %>
<%@ page import="com.netcracker.models.services.BookManager" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22/12/2020
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> редактровать эллемент</h1>

<div>
    <ol>
        <%
            ArrayList<Book> books = BookManager.getStaticBookManager().bookList;
            for (int i = 0; i < books.size(); ++i) {
        %>
        <li><%= books.get(i) %>
        </li>
        <%
            }
        %>
    </ol>
</div>

<div>
    <form method="post">
        <label>номер книги:
            <input type="text" name="book_number"><br/>
        </label>
        <label>название книги:
            <input type="text" name="book_name"><br/>
        </label>
        <label>автор книги:
            <input type="text" name="book_author"><br/>
        </label>
        <label>год издания:
            <input type="text" name="book_year"><br/>
        </label>

        <button type="submit">редактировать</button>
    </form>
</div>

<div>
    <%
        Object correct ;
        correct = (request.getAttribute("editBook"));
        if (correct != null)
    %> <h1><%= correct %>
</h1>
</div>

<div>
    <button onclick="location.href='/'">назад</button>
</div>


</body>
</html>
