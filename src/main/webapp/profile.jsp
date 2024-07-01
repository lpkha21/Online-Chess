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
    </style>
    <script type="text/javascript">
        function postToRequestServlet() {
            document.getElementById('requestForm').submit();
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Welcome <%= request.getAttribute("username") %></h1>
    <p>Friends list:</p>
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
        <a href="index.jsp">Log Out</a>
    </div>
</div>
</body>
</html>



