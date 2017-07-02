<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Zhang Jingzhuo
  Date: 2017/6/6
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户信息列表</title>
</head>
<body>
    <br>

    <br>${article}
    <br>待审核管理员的信息如下 <br>


    <table >
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>手机号</th>
            <th>学校</th>
            <th>学院</th>
            <th>性别</th>
            <th>操作</th>
        </tr>

    <c:if test="${!empty managerList}">
        <c:forEach var="manager" items="${managerList}">
            <tr>
                <td>${manager.mId}</td>
                <td>${manager.mName}</td>
                <td>${manager.mPhone}</td>
                <td>${manager.mUniversity}</td>
                <td>${manager.mSchool}</td>
                <td>${manager.mSex}</td>
                <td> <a href="/user/permitOneManage?status=1&mId="${manager.mId}>通过</a>  <a href="/user/permitOneManage?status=0&mId="${manager.mId}>拒绝</a></td>
            </tr>
        </c:forEach>
    </c:if>
    </table>

    <br>${s}


</body>
</html>
