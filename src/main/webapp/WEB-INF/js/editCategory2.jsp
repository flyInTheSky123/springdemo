<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-04-12
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%--RestFul 风格--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div style="margin:0px auto; width:500px">

    <form action="../categories/${c.id}" method="post">
        <%--虽然 method="post" , 但是springmvc 看到   name="_method"  时，会根据value 里面的值进行修改--%>
        <input type="hidden" name="_method" value="PUT">
        name: <input name="name" value="${c.name}"> <br>
        <button type="submit">提交</button>

    </form>
</div>
