<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Success Request</title>
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
        }

        p {
            font-size: 1.2em;
        }

        a {
            color: #2e8b57;
            text-decoration: none;
            margin-top: 20px;
            display: block;
        }

        a:hover {
            text-decoration: underline;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Success Request</h1>
    <p>Request sent successfully to <%= request.getAttribute("friendUsername") %>.</p>
    <a href="addFriends.jsp?username=<%= request.getAttribute("username") %>">Add Friends</a> <br>
    <a href="/BackToProfileServlet?username=<%= request.getAttribute("username") %>">Back to Profile</a>
</div>
</body>
</html>