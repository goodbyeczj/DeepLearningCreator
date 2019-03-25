<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: czj
  Date: 18-9-1
  Time: 下午2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach var="item" items="${dataSetNameList}">
        <tr>
            <td>数据名称</td>
            <td>${item.name}</td>
            <td><a href="/data/delete?name=${item.name}">删除</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
