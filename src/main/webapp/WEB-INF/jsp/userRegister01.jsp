<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2022/12/15
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<h1>用户注册</h1>
<div style="color: red">${regMessage}</div>
<form action="userRegister01" method="post">
    帐号：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="确定">
    <input type="reset" value="重置">
</form>
<a href="toLogin01"><input type="button" value="用户登录"></a>
</body>
</html>
