<%@ page import="java.util.ArrayList" %>
<%@ page import="com.netcracker.models.types.Book" %>
<%@ page import="com.netcracker.models.services.BookManager" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21/12/2020
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <H1>список книг</H1>

<div>
  <ol>
          <%
             ArrayList<Book> books = BookManager.getStaticBookManager().bookList;
              for (int i = 0; i < books.size(); ++i) {
          %>
          <li><%= books.get(i) %></li>
          <%
              }
          %>
    </ol>
  </div>

  <div>
      <button onclick="location.href='/'">назад</button>
  </div>

</body>
</html>
