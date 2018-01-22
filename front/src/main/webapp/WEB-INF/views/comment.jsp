<%--
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

    <title>发表评论</title>

    <link href="${ctx}/static/css/admin.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/amazeui.css" rel="stylesheet" type="text/css">

    <link href="${ctx}/static/css/personal.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/appstyle.css" rel="stylesheet" type="text/css">

    <script src="${ctx}/static/js/jquery-1.7.2.min.js" type="text/javascript"></script>

</head>

<body>
<!--头 -->
<%@include file="../../common/header.jsp" %>

<b class="line"></b>
<div class="center">
    <div class="col-main">
        <div class="main-wrap">

            <div class="user-comment">
                <!--标题 -->
                <div class="am-cf am-padding">
                    <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">发表评论</strong> / <small>Make&nbsp;Comments</small></div>
                </div>
                <hr/>

                <div class="comment-main">

                    <form action="${ctx}/commendOk" method="post">

                        <input type="hidden" name="orderId" value="${requestScope.orderId}"/>
                    <c:forEach items="${requestScope.list}" var="product">
                        <input type="hidden" value="${product.id}" name="oid"/>
                        <input type="hidden" value="${product.pid}" name="pid"/>
                        <div class="comment-list">
                            <div class="item-pic">
                                <a href="#" class="J_MakePoint">
                                    <img src="${ctx}/static/images/${fn:substring(product.imgs, 0, fn:indexOf(product.imgs,','))}" class="itempic">
                                </a>
                            </div>

                            <div class="item-title">

                                <div class="item-name">
                                    <a href="#">
                                        <p class="item-basic-info">${product.name}</p>
                                    </a>
                                </div>
                                <div class="item-info">
                                    <div class="info-little">
                                    </div>
                                    <div class="item-price">
                                        价格：<strong>$${product.price}</strong>
                                    </div>
                                </div>
                            </div>
                            <div class="clear"></div>
                            <div class="item-comment" >
                                <textarea name="describes" placeholder="请写下对宝贝的感受吧，对他人帮助很大哦！" ></textarea>
                            </div>


                            <div class="item-opinion">
                                <label><input name="grade" value="1" type="radio"/><i class="op1"></i>好评</label>&nbsp;
                                <label><input name="grade" value="2" type="radio"/><i class="op1"></i>中评</label>&nbsp;
                                <label><input name="grade" value="3" type="radio"/><i class="op1"></i>差评</label>&nbsp;
                            </div>
                        </div>
                        <br><br>
                        <div class="info-btn">
                                <%--<div class="am-btn am-btn-danger">发表评论</div>--%>
                            <input type="submit" value="发表评论"/>
                        </div>
                        <br><br><br><br>

                    </c:forEach>


                    </form>
                    <script type="text/javascript">
                        $(document).ready(function() {
                            $(".comment-list .item-opinion li").click(function() {
                                $(this).prevAll().children('i').removeClass("active");
                                $(this).nextAll().children('i').removeClass("active");
                                $(this).children('i').addClass("active");

                            });
                        })
                    </script>
                </div>
            </div>
        </div>

    </div>


</div>

</body>

</html>