<%--
  Created by IntelliJ IDEA.
  User: Zhang Jingzhuo
  Date: 2017/6/6
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>

    <!-- 下面环节为登录窗口 -->
    <h4>已有账户？请登录</h4>

    <form action="/user/showUser" name="login" method="get">

        <label for="username_login">用户名</label>
        <input name="username_login" type="text" id="username_login"/>
        <br>
        <label for="password_login">密  码</label>
        <input name="password_login" type="password" id="password_login"/>
        <br>
        <input name="submit_login" type="submit" id="submit_login" value="Log in"/>

    </form>

</body>
</html>
