<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/30/2024
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Information Incorrect</title>
</head>
<body>
<h1>Please Try Again</h1>
<p>Either your username or password is incorrect. Please try again.</p>
<form action="LoginServlet" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>
<a href="createAccount.jsp">Create New Account</a>
</body>
</html>
