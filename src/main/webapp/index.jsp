<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <body>

    <h3>欢迎使用壹心理</h3>

    <!-- 下面环节为注册窗口 -->
    <h4>注册新账户</h4> <a href="/user/toLogin">已有账户？请登录</a>

    <form action="/user/login" name="register" method="get">
        <label>学号</label><br>
        <input name="ID" type="text" id="ID"/>
        <br>
        <label>密码</label><br>
        <input name="password" type="password" id="password"/>
        <br>
        <input name="submit_register" type="submit" id="submit_register"/>
    </form>

    <br>
    <a href="/user/manage" methods="get">查看管理员！</a>
    <a href="/user/articleList" methods="get">查看文章列表</a>
    <a href="/user/articleContent" methods="get">查看单个文章</a>
   <!-- <a href="/user/daoru" methods="get">导入xml数据库</a> -->
    <a href="/user/permitManage" methods="get">查看管理员注册审核信息</a>


    <br>

    </body>
</html>
