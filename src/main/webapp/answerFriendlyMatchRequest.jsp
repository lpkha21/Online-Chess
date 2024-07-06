<%@ page import="javaClasses.FriendlyMatch" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Answer Friendly Match Request</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }

        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .btn-container {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 20px;
        }

        .btn {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #45a049;
        }

        .btn-reject {
            background-color: #f44336;
        }

        .btn-reject:hover {
            background-color: #e31b0c;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <ul>
        <%
            FriendlyMatch friendlyMatch = (FriendlyMatch) session.getAttribute("friendlyMatchRequest");
            String friend = friendlyMatch.getFirstUsername();
        %>
        <li>
            <%= friend %> has sent you a friendly match request.
            <div class="btn-container">
                <form action="AcceptFriendlyMatchServlet" method="get">
                    <input type="submit" class="btn" value="Accept">
                </form>
                <form action="RejectFriendlyMatchServlet" method="get">
                    <input type="submit" class="btn btn-reject" value="Reject">
                </form>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
