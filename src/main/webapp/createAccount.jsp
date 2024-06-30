<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/30/2024
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Create Account</title>
</head>
<body>
<h1>Create a New Account</h1>
<p>Please enter proposed name and password</p>
<form action="CreateAccountServlet" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
