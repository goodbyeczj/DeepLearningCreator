<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: czj
  Date: 18-9-4
  Time: 下午2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/model/update">
    <input type="hidden" name="id" value="${id}">
    NAME:<input name="name" type="text">
    DESCRIBE:<input name="describe_" type="text">
    <input type="submit" value="更新">
</form>
</body>
</html>
