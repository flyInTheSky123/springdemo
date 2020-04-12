<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-04-12
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="upload" method="post" enctype="multipart/form-data">
    添加图片：<input type="file" name="file" accept="image/*">
    <input type="submit" value="上传">
</form>