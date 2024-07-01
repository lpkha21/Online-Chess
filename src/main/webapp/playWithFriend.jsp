<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/1/2024
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Play With Friend</title>
</head>
<body>
    <h1> Play With <%= request.getParameter("friendName") %> </h1>
    <form action="PlayWithFriendServlet" method="post">
        Time Option:<br>
        <input type="radio" id="option1" name="time">
        <label for="option1">3 minute</label><br>

        <input type="radio" id="option2" name="time">
        <label for="option2">5 minute</label><br>

        <input type="radio" id="option3" name="time">
        <label for="option3">10 minute</label><br>

        Color Option:<br>
        <input type="radio" id="color1" name="color">
        <label for="option1">White</label><br>

        <input type="radio" id="color2" name="color">
        <label for="option2">Black</label><br>

        <input type="submit" value="Ask To Play">
    </form>
</body>
</html>
