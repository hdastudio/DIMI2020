<%@ page import="com.netcracker.models.services.BookManager" %>
<%@ page import="com.netcracker.models.types.Book" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22/12/2020
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Remove</title>
</head>
<body>
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
    <form method="post">
        <label>удалить книгу номер:
            <input type="text" name="number"><br />
        </label>

        <button type="remove">удалить</button>
    </form>
</div>

 <div>
     <%
         String correct= "";
                 correct =  (String) (request.getAttribute("removeBook"));
         if (correct != null)
     %> <h3> <%= correct %></h3>
 </div>

 <div>
     <button onclick="location.href='/'">назад</button>
 </div>

</body>
</html>
