<%--
  Created by IntelliJ IDEA.
  User: Zhang Jingzhuo
  Date: 2017/6/20
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>管理员信息列表</title>
</head>
<body>
<br>
<h4>管理员信息如下：</h4>
<br>

<table bgcolor="#ffb6c1">
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>性别</th>
        <th>学校</th>
        <th>学院</th>
        <th>key值</th>
    </tr>
    <tr>
        <td>${manager.mId}</td>
        <td>${manager.mName}</td>
        <td>${manager.mSex}</td>
        <td>${manager.mUniversity}</td>
        <td>${manager.mSchool}</td>
        <td>${manager.mKey}</td>


    </tr>
</table>

</body>
</html>

