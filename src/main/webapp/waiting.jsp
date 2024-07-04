<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Waiting</title>
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

        .content {
            text-align: center;
        }

        .logo {
            width: 100px; /* Adjust size as needed */
            height: auto;
            margin-bottom: 20px;
            animation: rotate 2s linear infinite; /* Animation properties */
        }

        h1 {
            color: #2e8b57;
            margin-bottom: 10px;
        }

        p {
            font-size: 1.2em;
            margin-bottom: 20px;
        }

        @keyframes rotate {
            from {
                transform: rotate(0deg); /* Start rotation from 0 degrees */
            }
            to {
                transform: rotate(360deg); /* Rotate 360 degrees */
            }
        }
    </style>
</head>
<body>
<div class="content">
    <img src="pieceImages/11.png" alt="Waiting Logo" class="logo">
    <h1>Please Wait</h1>
    <p>Waiting for another player...</p>
</div>

<%-- Example logic to handle waiting conditions --%>
<%
    ArrayList<String> queue = (ArrayList<String>) request.getAttribute("queue");
    if (queue.size() >= 2) {
        // Redirect logic or continue to game setup
    } else {
        // Display waiting message or additional logic
    }
%>

</body>
</html>
