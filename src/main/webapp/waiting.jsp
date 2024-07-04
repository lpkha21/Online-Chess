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
                transform: rotate(0deg); /* Start rotation from 0 degrees */
            }
            to {
                transform: rotate(360deg); /* Rotate 360 degrees */
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
                        // Redirect to the game page or perform additional logic
                        window.location.href = '/Game';
                    } else {
                        // Continue checking
                        setTimeout(checkQueue, 1000); // Check again after 1 second
                    }
                },
                error: function() {
                    setTimeout(checkQueue, 1000); // Retry after 1 second on error
                }
            });
        }

        function leaveQueue() {
            // Perform AJAX call to remove user from the queue
            $.ajax({
                url: '/LeaveQueueServlet',
                type: 'POST', // Use POST for actions that modify data
                success: function() {
                    // Redirect or handle leaving the queue
                    window.location.href = '/BackToProfileServlet'; // Redirect to home or another page
                },
                error: function() {
                    // Handle error, if needed
                    alert('Failed to leave the queue.');
                }
            });
        }

        $(document).ready(function() {
            checkQueue(); // Initial call to start checking queue

            // Bind click event to leave button
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
