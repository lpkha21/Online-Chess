<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Play With Friend</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            color: #333;
        }

        h1 {
            color: #2e8b57;
            margin-bottom: 20px;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .option-group {
            margin-bottom: 15px;
        }

        .option-group label {
            margin-right: 10px;
        }

        input[type="radio"] {
            margin-right: 5px;
        }

        label {
            font-size: 1.2em;
        }

        input[type="submit"] {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 1em;
            color: #fff;
            background-color: #2e8b57;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #246b3a;
        }
    </style>
</head>
<body>
<h1> Play With <%= request.getParameter("friendName") %> </h1>
<form action="PlayWithFriendServlet" method="post">
    <div class="option-group">
        <span>Time Option:</span>
        <input type="radio" id="option1" name="time" value="3">
        <label for="option1">3 minutes</label>

        <input type="radio" id="option2" name="time" value="5">
        <label for="option2">5 minutes</label>

        <input type="radio" id="option3" name="time" value="10">
        <label for="option3">10 minutes</label>
    </div>

    <div class="option-group">
        <span>Color Option:</span>
        <input type="radio" id="white" name="color" value="white">
        <label for="white">White</label>

        <input type="radio" id="black" name="color" value="black">
        <label for="black">Black</label>

        <input type="radio" id="random" name="color" value="random">
        <label for="random">Random</label>
    </div>

    <input type="hidden" name="friendName" value="<%= request.getParameter("friendName")%>">

    <input type="submit" value="Ask To Play">
</form>
</body>
</html>
