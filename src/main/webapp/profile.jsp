<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile of <%= request.getAttribute("username")%></title>
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

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            background-color: #fff;
            padding: 10px;
            margin: 5px 0;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        a {
            color: #2e8b57;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .friends-list {
            width: 100%;
            max-width: 300px;
        }

        .actions {
            margin-top: 20px;
        }

        .actions a {
            margin: 10px;
            display: inline-block;
        }

        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
            margin-top: 20px;
        }

        .form-container input[type="submit"] {
            background-color: #2e8b57;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
        }

        .form-container input[type="submit"]:hover {
            background-color: #276c4b;
        }

        .center {
            display: flex;
            justify-content: center;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        function checkFriendlyMatchRequest() {
            $.ajax({
                url: '/CheckFriendlyMatchRequestServlet',
                type: 'POST',
                dataType: 'json',
                success: function(data) {
                    if (data.isRequest === 1) {
                        window.location.href = 'answerFriendlyMatchRequest.jsp';
                    }else{
                        setTimeout(checkFriendlyMatchRequest, 1000);
                    }
                },
                error: function() {
                    setTimeout(checkFriendlyMatchRequest, 1000);
                }
            });
        }


        $(document).ready(function() {
            checkFriendlyMatchRequest();
        });
    </script>
</head>
<body>
<div class="container">
    <h1>Welcome <%= request.getAttribute("username") %></h1>
    <div class="form-container">
        <div class="center">
            <form action="StartGameServlet" method="post">
                <input type="hidden" name="username" value="<%= request.getAttribute("username")%>">
                <input type="submit" value="Start Game">
            </form>
        </div>
    </div>

    <p>Play with Friends:</p>
    <ul class="friends-list">
        <%
            String friends = (String) request.getAttribute("friends");
            if (friends != null && !friends.isEmpty()) {
                String[] fr = friends.split(",");
                for (String friend : fr) {
                    out.println("<li><a href='playWithFriend.jsp?friendName=" + friend + "'>" + friend + "</a></li>");
                }
            } else {
                out.println("<li>No friends found.</li>");
            }
        %>
    </ul>
    <div class="actions">
        <a href="addFriends.jsp?username=<%= request.getAttribute("username") %>">Add Friends</a>
        <a href="/RequestServlet?username=<%= request.getAttribute("username")%>"> Requests</a>
        <a href="/FriendsServlet?username=<%= request.getAttribute("username")%>"> Remove Friends</a>
        <a href="index.jsp">Log Out</a>
    </div>
</div>
</body>
</html>
