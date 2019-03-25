<%--
  Created by IntelliJ IDEA.
  User: czj
  Date: 18-8-29
  Time: 下午4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#submit").click(function(){
            var size = $(".size").val();
            var htmlobj=$.ajax({url:"/model/search",async:false,type: "POST",
                data: size});
            $("#show").html(htmlobj.responseText);
        });
    });
</script>
<body>
成功！
<form name="Form2" action="/data/add" method="post"  enctype="multipart/form-data">
    <h1>使用spring mvc提供的类的方法上传文件</h1>
    <input type="hidden" name="type" value="1"/>
    <input type="file" name="file"webkitdirectory>
    <input type="submit" value="upload"/>
</form>
<form action="${pageContext.request.contextPath }/model/add">
    <table border="1">
        <tr>
            <td>名字</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>描述</td>
            <td><input type="text" name="describe_"></td>
        </tr>
        <tr>
            <td>类型</td>
            <td><input type="text" name="types"></td>
        </tr>
        <tr>
            <td><input type="submit" value="增加"></td>
        </tr>
    </table>
</form>
<form action="${pageContext.request.contextPath }/model/manage">
    <table border="1">
        <tr>
            <td><input type="submit" value="模型管理"></td>
        </tr>
    </table>
</form>
<form action="${pageContext.request.contextPath }/data/manage">
    <table border="1">
        <tr>
            <td><input type="submit" value="数据管理"></td>
        </tr>
    </table>
</form>
<form action="${pageContext.request.contextPath }/model/showCore">
    <table border="1">
        <tr>
            <td><input type="submit" value="显示代码"></td>
        </tr>
    </table>
</form>
<div id="in">
    <form>
        <input type="text" name="size" class="size">
        <input type="button" value="search" id="button">
    </form>
</div>
<div id="show"></div>
</body>
</html>
