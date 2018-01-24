<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/1/22
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/11/17
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="common.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
    <%@include file="../../common/head.jsp" %>

    <title>address</title>

    <link href="${ctx}/static/css/admin.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/amazeui.css" rel="stylesheet" type="text/css">

    <link href="${ctx}/static/css/personal.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/cmstyle.css" rel="stylesheet" type="text/css">

    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/amazeui.js"></script>
    <script src="static/js/jquery-1.8.3.min.js" type="text/javascript"></script>

</head>
<body>
<%@include file="../../common/header.jsp" %>

<div class="center">
    <div class="col-main">
        <div class="main-wrap">

            <div class="user-comment">
                <!--标题 -->
                <div class="am-cf am-padding">
                    <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">评价管理</strong> / <small>Manage&nbsp;Comment</small></div>
                </div>
                <hr/>

                <div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>

                    <ul class="am-avg-sm-2 am-tabs-nav am-nav am-nav-tabs">
                        <li class="am-active"><a href="#tab1">所有评价</a></li>

                    </ul>

                    <div class="am-tabs-bd">
                        <div class="am-tab-panel am-fade am-in am-active" id="tab1">

                            <div class="comment-main">
                                <div class="comment-list">
                                    <c:forEach items="${requestScope.list}" var="pro">
                                        <ul class="item-list">


                                            <div class="comment-top">
                                                <div class="th th-price">
                                                    <td class="td-inner">评价</td>
                                                </div>
                                                <div class="th th-item">
                                                    <td class="td-inner">商品</td>
                                                </div>
                                            </div>
                                            <li class="td td-item">
                                                <div class="item-pic">
                                                    <a href="#" class="J_MakePoint">
                                                        <img src="${ctx}/static/images/${fn:substring(pro.imgs, 0, fn:indexOf(pro.imgs, ","))}">
                                                    </a>
                                                </div>
                                            </li>

                                            <li class="td td-comment">
                                                <div class="item-title">
                                                    <div class="item-opinion">好评</div>
                                                    <div class="item-name">
                                                        <a href="#">
                                                            <a href="single?id=${pro.pid}">${pro.name}</a>
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="item-comment">
                                                ${pro.describes}
                                                </div>

                                                <div class="item-info">
                                                    <div>
                                                        <p class="info-time">${pro.create_time}</p>

                                                    </div>
                                                </div>
                                            </li>

                                        </ul>
                                    </c:forEach>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

</div>


</body>
</html>
