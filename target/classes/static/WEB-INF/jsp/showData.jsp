<%--
  Created by IntelliJ IDEA.
  User: lailai
  Date: 18-11-9
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="find">
    <button class="item b1"></button>
    <button class="item b2"></button>
    <button class="item b3"></button>
    <button class="item b4"></button>
    <button class="item b5"></button>
    <button class="item b6"></button>
    <button class="item b7"></button>
    <button class="item b8"></button>
    <button class="item b9"></button>
    <button class="item b10"></button>
</div>
<div id="show">
<c:forEach var="item" items="${list1}">
    <img src='../ct_data/picture/${item}'/>
</c:forEach>
</div>
</body>
</html>
