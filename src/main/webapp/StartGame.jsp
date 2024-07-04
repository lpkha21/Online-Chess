<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/4/2024
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start Game</title>
</head>
<body>
    <form action="WaitingServlet" method="get">
        Time Option:<br>
        <input type="radio" id="option1" name="time" value="3">
        <label for="option1">3 minute</label><br>

        <input type="radio" id="option2" name="time" value="5">
        <label for="option2">5 minute</label><br>

        <input type="radio" id="option3" name="time" value="10">
        <label for="option3">10 minute</label><br>

        <input type="hidden" name="username" value="<%= request.getAttribute("username")%>">

        <input type="submit" value="Play">
    </form>

</body>
</html>
