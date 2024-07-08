<%@ page import="com.mysql.cj.exceptions.DataReadException" %>
<%@ page import="javaClasses.DataBase" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Remove Friends</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f8ff;
      margin: 0;
      padding: 20px;
    }

    h1 {
      color: #2e8b57;
    }

    ul {
      list-style-type: none;
      padding: 0;
    }

    li {
      margin-bottom: 10px;
      border: 1px solid #ccc;
      padding: 10px;
      background-color: #fff;
      display: flex;
      align-items: center;
    }

    .btn-container {
      margin-left: auto;
    }

    .btn {
      padding: 5px 10px;
      margin-left: 10px;
      cursor: pointer;
      background-color: #2e8b57;
      color: white;
      border: none;
      border-radius: 4px;
      text-decoration: none;
      text-align: center;
    }

    .btn-reject {
      background-color: #dc3545;
    }

    .btn:hover, .btn-reject:hover {
      background-color: #276c4b;
    }

    .back-link {
      display: block;
      margin-top: 20px;
      text-decoration: none;
      color: #2e8b57;
    }

    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<h1>Friends:</h1>
<ul>
  <%
    String friends = (String) request.getAttribute("friends");
    if (friends != null && !friends.isEmpty()) {
      String[] fr = friends.split(",");
      for (String friend : fr) {
  %>
  <li>
    <%= friend %>
    <div class="btn-container">
      <form action="RemoveFriendsServlet" method="post">
        <input type="hidden" name="friendUsername" value="<%= friend %>">
        <input type="hidden" name="username" value="<%= request.getAttribute("username") %>">
        <input type="submit" class="btn btn-reject" value="Remove">
      </form>
    </div>
  </li>
  <%
    }
  } else {
  %>
  <li>No Friends.</li>
  <%
    }
  %>
</ul>
<a class="back-link" href="/BackToProfileServlet?username=<%= request.getAttribute("username") %>">Back to Profile</a>
</body>
</html>
