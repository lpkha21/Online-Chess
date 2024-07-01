<%@ page import="com.mysql.cj.exceptions.DataReadException" %>
<%@ page import="javaClasses.DataBase" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/1/2024
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friend Requests</title>
</head>
<body>
    <h1> Friend Requests </h1>
    <p>requests: </p>
    <ul>
        <%
            String requests = (String) request.getAttribute("requests");
            String[] r = requests.split(",");
            for(int i = 0; i < r.length; i++){
                out.println("<li><a href='playWithFriend.jsp?friendName=" + r[i] + "'>" + r[i] + "</a></li>");
            }
        %>
    </ul>

</body>
</html>
