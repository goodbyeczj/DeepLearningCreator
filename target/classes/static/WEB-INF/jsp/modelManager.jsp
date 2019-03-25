<%--
  Created by IntelliJ IDEA.
  User: czj
  Date: 18-9-4
  Time: 下午2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>模型名称</td>
            <td>${item.name}</td>
            <td><a href="/model/delete?id=${item.id}">删除</a></td>
            <td><a href="/model/edit?id=${item.id}">修改</a> </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
