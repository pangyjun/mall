<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/11/17
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../../common/head.jsp"%>
    <link rel="stylesheet" href="${ctx}/static/css/pick-pcc.min.1.0.1.css">
</head>
<body>
<%@include file="../../common/header.jsp"%>
<!--account-->
<div class="account">
    <div class="container">
        <div class="register">
            <form action="regist" met1hod="post">
                <div class="register-top-grid">

                    <%response.setHeader("refresh","3;URL = order?oderid="+request.getAttribute("oderid"));%>
                    <h1>评论成功！，3s后自动进入您的订单</h1>
                    <p>如果没有自动跳转，请点击 <a href="order">这里</a></p>>




                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

