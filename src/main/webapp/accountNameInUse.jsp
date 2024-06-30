<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/30/2024
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
</head>
<body>
<h1>The Name <%= request.getAttribute("username")%> is Already In Use</h1>
<p>Please enter another name and password.</p>
<form action="CreateAccountServlet" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
