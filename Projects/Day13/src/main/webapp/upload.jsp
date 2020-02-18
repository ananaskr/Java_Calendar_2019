<%--
  Created by IntelliJ IDEA.
  User: apple-qkl
  Date: 2020/2/5
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form method="post" action="/Day13_war_exploded/day13" enctype="multipart/form-data">
    <input type="file" name="uploadFile"/>
    <br/><br/>
    <input type="submit" value="upload"/>
</form>

</body>
</html>
