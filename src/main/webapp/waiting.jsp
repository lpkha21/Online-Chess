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
            width: 100px;
            height: auto;
            margin-bottom: 20px;
            animation: rotate 2s linear infinite;
        }

        h1 {
            color: #2e8b57;
            margin-bottom: 10px;
        }

        p {
            font-size: 1.2em;
            margin-bottom: 20px;
        }

        .leave-button {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            margin-top: 20px;
        }

        .leave-button:hover {
            background-color: #c82333;
        }

        @keyframes rotate {
            from {
                transform: rotate(0deg);
            }
            to {
                transform: rotate(360deg);
            }
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        function checkQueue() {
            $.ajax({
                url: '/CheckQueueServlet',
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    if (data.queueSize >= 2) {
                        window.location.href = '/JoinGameServlet';
                    } else {
                        setTimeout(checkQueue, 1000);
                    }
                },
                error: function() {
                    setTimeout(checkQueue, 1000);
                }
            });
        }

        function leaveQueue() {
            $.ajax({
                url: '/LeaveQueueServlet',
                type: 'POST',
                success: function() {
                    window.location.href = '/BackToProfileServlet';
                },
                error: function() {
                    alert('Failed to leave the queue.');
                }
            });
        }

        $(document).ready(function() {
            checkQueue();

            $('.leave-button').click(function() {
                leaveQueue();
            });
        });
    </script>
</head>
<body>
<div class="content">
    <img src="pieceImages/11.png" alt="Waiting Logo" class="logo">
    <h1>Please Wait</h1>
    <p>Waiting for another player...</p>
    <button class="leave-button">Leave Queue</button>
</div>
</body>
</html>
