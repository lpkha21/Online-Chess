
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Play With Friend</title>
</head>
<body>
    <h1> Play With <%= request.getParameter("friendName") %> </h1>
    <form action="PlayWithFriendServlet" method="post">
        Time Option:<br>
        <input type="radio" id="option1" name="time" value="3">
        <label for="option1">3 minute</label><br>

        <input type="radio" id="option2" name="time" value="5">
        <label for="option2">5 minute</label><br>

        <input type="radio" id="option3" name="time" value="10">
        <label for="option3">10 minute</label><br>

        Color Option:<br>
        <input type="radio" id="white" name="color" value="white">
        <label for="option1">White</label><br>

        <input type="radio" id="black" name="color" value="black">
        <label for="option2">Black</label><br>

        <input type="radio" id="random" name="color" value="random">
        <label for="option2">Random</label><br>

        <input type="submit" value="Ask To Play">
    </form>
</body>
</html>
