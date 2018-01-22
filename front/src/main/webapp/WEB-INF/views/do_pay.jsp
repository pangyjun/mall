<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/11/17
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../../common/head.jsp"%>
    <link rel="stylesheet" href="${ctx}/static/css/pick-pcc.min.1.0.1.css">
    <script src="${ctx}/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">


    </script>

</head>
<body>
<%@include file="../../common/header.jsp"%>
<!--account-->
<div class="account">
    <div class="container">
        <div class="register">

                <div class="register-top-grid">
                    <h3>付款</h3>
                    <div class="input">
                        <br>
                        <span>支付金额<label>*</label></span>
                        ${requestScope.tot_money}
                        <%--<input type="text" hidden="hidden" value="${requestScope.tot_money}" name="money">--%>
                        <%--<span>支付密码<label>*</label></span>--%>
                        <%--<input type="password" name="password">--%>
                        <br>
                        <br>
                        <button><a href="success?str=下单成功，我们将尽快为您发货&oderid= ${requestScope.oderid}+&tot_money= ${requestScope.tot_money}">确认</a></button><span id="span"></span>
                        <br><br>
                        <a href="cart">取消支付</a>

                    </div>

                </div>
        </div>
    </div>
</div>
</body>
</html>

