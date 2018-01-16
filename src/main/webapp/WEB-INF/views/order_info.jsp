<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/11/17
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="common.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
    <%@include file="../../common/head.jsp"%>

    <title>订单管理</title>

    <link href="${ctx}/static/css/admin.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/amazeui.css" rel="stylesheet" type="text/css">

    <link href="${ctx}/static/css/personal.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/orstyle.css" rel="stylesheet" type="text/css">

    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/amazeui.js"></script>

</head>

<body>
<!--头 -->
<%@include file="../../common/header.jsp"%>


<b class="line"></b>
<div class="center">
    <div class="col-main">
        <div style="margin-left: 0px" class="main-wrap">

            <div class="user-orderinfo">

                <!--标题 -->
                <div class="am-cf am-padding">
                    <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">确认订单</strong> / <small>Order&nbsp;details</small>（信息不符合，请修改后点确认）</div>
                </div>
                <hr/>
                <form action="sure" method="post">
                <div class="order-infoaside">
                    <div class="order-logistics">
                        <a href="logistics.html">
                            <div class="icon-log">
                                <i><img src="../images/receive.png"></i>
                            </div>
                            <span class="am-icon-angle-right icon"></span>
                        </a>
                        <div class="clear"></div>
                    </div>
                    <div class="order-addresslist">
                        <div class="order-address">
                            <div class="icon-add">
                            </div>
                            <p class="new-tit new-p-re">


                                <span class="new-txt">收件人姓名：<input required type="text" name="username" value="${sessionScope.get("user").username}"></span>
                                <br><span class="new-txt-rd2">收件人电话： <input required type="text" name="tel" value="${sessionScope.get("user").tel}"></span>
                            </p>
                            <div class="new-mu_l2a new-p-re">
                                <p class="new-mu_l2cw">
                                    <span class="title">收货地址： <input type="text" required name="address" value="${sessionScope.get("user").addr}"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="order-infomain">

                    <div class="order-top">
                        <div class="th th-item">
                            <td class="td-inner">商品</td>
                        </div>
                        <div class="th th-price">
                            <td class="td-inner">单价</td>
                        </div>
                        <div class="th th-number">
                            <td class="td-inner">数量</td>
                        </div>
                        <div class="th th-operation">
                            <td class="td-inner">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        </div>
                        <div class="th th-amount">
                            <td class="td-inner">合计</td>

                        </div>

                        <div class="th th-change">
                            <td class="td-inner">交易操作</td>
                        </div>
                    </div>

                    <div class="order-main">

                        <div class="order-status3">
                            <div class="order-content">
                                <div class="order-left">

                                    <c:forEach items="${requestScope.list}" var="cake">

                                        <ul class="item-list">
                                            <li class="td td-item">
                                                <div class="item-pic">
                                                    <a href="#" class="J_MakePoint">
                                                        <img src="${ctx}/static/images/${fn:substring(cake.imgs, 0, fn:indexOf(cake.imgs, ","))}" class="itempic J_ItemImg">
                                                    </a>
                                                </div>
                                                <div class="item-info">
                                                    <div class="item-basic-info">
                                                        <a href="#">
                                                            <p>${cake.name}</p>
                                                            <p class="info-little">${cake.comm}

                                                        </a>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="td td-price">
                                                <div class="item-price">
                                                   ${cake.price}
                                                       <input type="text" hidden="hidden" value="${cake.price}" name="price">
                                                       <input type="text" hidden="hidden" value="${cake.count}" name="count">
                                                       <input type="text" hidden="hidden" value="${cake.id}" name="cid">

                                                </div>
                                            </li>
                                            <li class="td td-number">
                                                <div class="item-number">
                                                    <span>×</span>${cake.count}
                                                </div>
                                            </li>
                                            <li class="td td-operation">
                                                <div class="item-operation">

                                                </div>
                                            </li>
                                        </ul>
                                    </c:forEach>

                                </div>
                                <div class="order-right">
                                    <li class="td td-amount">
                                        <div class="item-amount">
                                            合计：${requestScope.money}
                                            <input type="text" hidden="hidden" value="${requestScope.money}" name="money">
                                            <input type="text" hidden="hidden" value="${requestScope.single}" name="single">
                                        </div>
                                    </li>
                                    <div class="move-right">
                                        <li class="td td-change">

                                                <input type="submit" value=" 确认订单">
                                        </li>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                </form>
            </div>

        </div>
        <!--底部-->

        <%@include file="../../common/footer.jsp"%>

</body>

</html>