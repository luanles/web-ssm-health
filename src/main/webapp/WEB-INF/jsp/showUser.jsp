<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户信息列表</title>
</head>
<body>
    <c:if test="${!empty userList}">
        <c:forEach var="user" items="${userList}">
            姓名：${user.userName} &nbsp;&nbsp;手机号：${user.userPhone} &nbsp;&nbsp;学校：${user.userUniversity} &nbsp;&nbsp;<br>
        </c:forEach>
    </c:if>
    <br>
    你好~你的名字是：${firstUser.userName}
    <br>
    你的创建时间是：firstUserTest.createTime

    <br>
    <br>
    <br>
    ${judgeState}
    <br>请求端的URL：
    <%=request.getRequestURL()%>
    <br>
    <%=request.getPathInfo()%>
    <br>
    <%=request.getParameter("username_login")%>
    <br>
    <%=request.getParameter("password_login")%>


</body>
</html>