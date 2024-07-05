
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start Game</title>
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

        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #2e8b57;
        }

        .time-options {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 20px;
        }

        label {
            font-size: 1.2em;
        }

        input[type="radio"] {
            margin-right: 5px;
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
<div class="container">
    <h1>Choose Time Option</h1>
    <form action="WaitingServlet" method="get">
        <div class="time-options">
            <label for="option1">
                <input type="radio" id="option1" name="time" value="3"> 3 minutes
            </label>
            <label for="option2">
                <input type="radio" id="option2" name="time" value="5"> 5 minutes
            </label>
            <label for="option3">
                <input type="radio" id="option3" name="time" value="10"> 10 minutes
            </label>
        </div>
        <input type="hidden" name="username" value="<%= request.getAttribute("username")%>">
        <input type="submit" value="Play">
    </form>
</div>
</body>
</html>
