<%--
  Created by IntelliJ IDEA.
  User: czj
  Date: 18-9-6
  Time: 上午10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<select name="" id="">
    <option value="选择数据源">
    </option>
    <c:forEach var="item" items="${dataList}">
        <option value="${item.name}">
    </c:forEach>
</select>
</body>
</html>
