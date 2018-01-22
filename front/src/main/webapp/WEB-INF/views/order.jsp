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

    <title>订单管理</title>

    <link href="${ctx}/static/css/admin.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/amazeui.css" rel="stylesheet" type="text/css">

    <link href="${ctx}/static/css/personal.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/orstyle.css" rel="stylesheet" type="text/css">

    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/amazeui.js"></script>
    <script src="static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(function () {
            $("span[class='div']").click(function () {
//                alert("ddd")
//                console.log($(this).parent().children(":first").val())
                $.ajax({
                    url: "${ctx}/remind",
                    method: "POST",
                    data: {orderId: $(this).parent().children(":first").val()},
                    success: function (result) {
                        alert("已经提醒卖家发货！")
                    }
                })
            })

            $("span[class='pay']").click(function () {
//                alert("ddd")
//                console.log($(this).parent().children(":first").val())
                $.ajax({
                    url: "${ctx}/order_pay",
                    method: "POST",
                    data: {orderId: $(this).parent().children(":first").val()},
                    success: function (result) {
                        alert("支付成功，我们将尽快为您发货！")
                        location.reload();
                    }
                })
            })

            $("span[class='confirm']").click(function () {
//                alert("ddd")
                var orderId = $(this).parent().children(":first").val()
                $.ajax({
                    url: "${ctx}/order_confirm",
                    method: "POST",
                    data: {orderId: $(this).parent().children(":first").val()},
                    success: function (result) {
//                        跳转到评论界面
                        location = "${ctx}/commentList?orderId="+orderId

                    }
                })
            })

            $("span[class='commend']").click(function () {
//                alert("ddd")
                var orderId = $(this).parent().children(":first").val()

                        location = "${ctx}/commentList?orderId="+orderId

            })
        })


    </script>

</head>

<body>
<!--头 -->
<%@include file="../../common/header.jsp" %>

<b class="line"></b>
<div class="center">
    <div class="col-main">
        <div style="margin-left: 0px" class="main-wrap">
            <div class="user-order">

                <!--标题 -->
                <div class="am-cf am-padding">
                    <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">订单管理</strong> /
                        <small>Order</small>
                    </div>
                </div>
                <hr/>

                <div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>

                    <ul class="am-avg-sm-5 am-tabs-nav am-nav am-nav-tabs">
                        <li class="am-active"><a href="#tab1">所有订单</a></li>
                        <li><a href="#tab2">待付款</a></li>
                        <li><a href="#tab3">待发货</a></li>
                        <li><a href="#tab4">待收货</a></li>
                        <li><a href="#tab5">待评价</a></li>
                    </ul>

                    <div class="am-tabs-bd">
                        <div class="am-tab-panel am-fade am-in am-active" id="tab1">
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
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>
                            <div class="order-main">
                                <div class="order-list">
                                    <c:forEach items="${requestScope.success}" var="all">
                                        <div class="order-status5">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${all.id}</a></div>
                                                <span>成交时间：${all.order_time}</span>
                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">
                                                    <c:forEach items="${all.list}" var="list">
                                                        <ul class="item-list">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="#" class="J_MakePoint">
                                                                        <img src="${ctx}/static/images/${fn:substring(list.imgs, 0, fn:indexOf(list.imgs,','))}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="#">
                                                                            <p>${list.name}</p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                        ${list.price}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${list.count}
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
                                                            合计：$${all.tot_money}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">交易成功</p>
                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <a href="order_list?orderid=${all.id}"
                                                                   style="color: white"> 删除订单</a></div>
                                                    </div>
                                                    </li>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <!--交易失败-->
                                    <c:forEach items="${requestScope.nopay}" var="map">
                                        <div class="order-status0">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${map.id}</a></div>
                                                <span>成交时间：${map.order_time}</span>
                                                <!--    <em>店铺：小桔灯</em>-->
                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">

                                                    <c:forEach items="${map.list}" var="list">
                                                        <ul class="item-list">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="#" class="J_MakePoint">
                                                                        <img src="${ctx}/static/images/${fn:substring(list.imgs, 0, fn:indexOf(list.imgs,','))}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="#">
                                                                            <p>${list.name}</p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                    $${list.price}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${list.count}
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
                                                            合计：$${map.tot_money}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">交易关闭</p>
                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <a href="order_list?orderid=${map.id}"
                                                                   style="color: white"> 删除订单
                                                                </a>
                                                            </div>
                                                            <br><br>
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <input type="hidden" value="${map.id}"/>
                                                                <span class="pay">一键支付</span>
                                                            </div>


                                                        </li>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <!--待发货-->
                                    <c:forEach items="${requestScope.nosend}" var="map">
                                        <div class="order-status2">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${map.id}</a></div>
                                                <span>成交时间：${map.order_time}</span>
                                                <!--    <em>店铺：小桔灯</em>-->
                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">


                                                    <c:forEach items="${map.list}" var="list">
                                                        <ul class="item-list">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="#" class="J_MakePoint">
                                                                        <img src="${ctx}/static/images/${fn:substring(list.imgs, 0, fn:indexOf(list.imgs,','))}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="#">
                                                                            <p>${list.name}</p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                    $${list.price}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${list.count}
                                                                </div>
                                                            </li>
                                                            <li class="td td-operation">
                                                                <div class="item-operation">
                                                                    <a href="refund.html">退款</a>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </c:forEach>
                                                </div>
                                                <div class="order-right">
                                                    <li class="td td-amount">
                                                        <div class="item-amount">
                                                            合计：$${map.tot_money}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">买家已付款</p>
                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <input type="hidden" value="${map.id}"/>
                                                                <span class="div">提醒发货</span>
                                                            </div>
                                                        </li>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>
                                    <!--不同状态订单-->
                                    <c:forEach items="${requestScope.noreceive}" var="map">
                                        <div class="order-status3">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${map.id}</a></div>
                                                <span>成交时间：${map.order_time}</span>
                                                <!--    <em>店铺：小桔灯</em>-->
                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">

                                                    <c:forEach items="${map.list}" var="list">
                                                        <ul class="item-list">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="#" class="J_MakePoint">
                                                                        <img src="${ctx}/static/images/${fn:substring(list.imgs, 0, fn:indexOf(list.imgs,','))}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="#">
                                                                            <p>${list.name}</p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                    $${list.price}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${list.count}
                                                                </div>
                                                            </li>
                                                            <li class="td td-operation">
                                                                <div class="item-operation">
                                                                    <a href="refund.html">退款/退货</a>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </c:forEach>

                                                </div>
                                                <div class="order-right">
                                                    <li class="td td-amount">
                                                        <div class="item-amount">
                                                            合计：$${map.tot_money}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">卖家已发货</p>
                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <input type="hidden" value="${map.id}"/>
                                                                <span class="confirm">确认收货</span>
                                                            </div>
                                                        </li>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                        <div class="am-tab-panel am-fade" id="tab2">

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
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>

                            <div class="order-main">
                                <div class="order-list">

                                    <c:forEach items="${requestScope.nopay}" var="map">
                                        <div class="order-status1">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${map.id}</a></div>
                                                <span>成交时间：${map.order_time}</span>
                                                <!--    <em>店铺：小桔灯</em>-->
                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">


                                                    <c:forEach items="${map.list}" var="list">
                                                        <ul class="item-list">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="#" class="J_MakePoint">
                                                                        <img src="${ctx}/static/images/${fn:substring(list.imgs, 0, fn:indexOf(list.imgs,','))}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="#">
                                                                            <p>${list.name}</p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                    $${list.price}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${list.count}
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
                                                            合计：$${map.tot_money}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">等待买家付款</p>
                                                                <p class="order-info"><a href="#">取消订单</a></p>

                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <input type="hidden" value="${map.id}"/>
                                                                <span class="pay">一键支付</span>
                                                            </div>
                                                        </li>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="am-tab-panel am-fade" id="tab3">
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
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>

                            <div class="order-main">
                                <div class="order-list">
                                    <c:forEach items="${requestScope.nosend}" var="map">
                                        <div class="order-status2">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${map.id}</a></div>
                                                <span>成交时间：${map.order_time}</span>
                                                <!--    <em>店铺：小桔灯</em>-->
                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">
                                                    <c:forEach items="${map.list}" var="list">
                                                        <ul class="item-list">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="#" class="J_MakePoint">
                                                                        <img src="${ctx}/static/images/${fn:substring(list.imgs, 0, fn:indexOf(list.imgs,','))}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="#">
                                                                            <p>${list.name}</p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                    $${list.money}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${list.count}
                                                                </div>
                                                            </li>
                                                            <li class="td td-operation">
                                                                <div class="item-operation">
                                                                    <a href="refund.html">退款</a>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </c:forEach>
                                                </div>
                                                <div class="order-right">
                                                    <li class="td td-amount">
                                                        <div class="item-amount">
                                                            合计：$${map.tot_money}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">买家已付款</p>
                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <input type="hidden" value="${map.id}"/>
                                                                <span class="div">提醒发货</span>
                                                            </div>
                                                        </li>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="am-tab-panel am-fade" id="tab4">
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
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>

                            <div class="order-main">
                                <div class="order-list">
                                    <c:forEach items="${requestScope.noreceive}" var="map">
                                        <div class="order-status3">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${map.id}</a></div>
                                                <span>成交时间：${map.order_time}</span>
                                                <!--    <em>店铺：小桔灯</em>-->
                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">
                                                    <c:forEach items="${map.list}" var="list">
                                                        <ul class="item-list">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="#" class="J_MakePoint">
                                                                        <img src="${ctx}/static/images/${fn:substring(list.imgs, 0, fn:indexOf(list.imgs,','))}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="#">
                                                                            <p>${list.name}</p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                    $${list.price}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${list.count}
                                                                </div>
                                                            </li>
                                                            <li class="td td-operation">
                                                                <div class="item-operation">
                                                                    <a href="refund.html">退款/退货</a>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </c:forEach>
                                                </div>
                                                <div class="order-right">
                                                    <li class="td td-amount">
                                                        <div class="item-amount">
                                                            合计：$${map.tot_price}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">卖家已发货</p>
                                                                <p class="order-info"><a href="orderinfo.html">订单详情</a>
                                                                </p>
                                                                <p class="order-info"><a href="logistics.html">查看物流</a>
                                                                </p>
                                                                <p class="order-info"><a href="#">延长收货</a></p>
                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <input type="hidden" value="${map.id}"/>
                                                                <span class="confirm">确认收货</span>
                                                            </div>
                                                        </li>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                        </div>
                        <div class="am-tab-panel am-fade" id="tab5">
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
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>

                            <div class="order-main">
                                <div class="order-list">
                                    <!--不同状态的订单	-->
                                    <c:forEach items="${requestScope.noconfirm}" var="map">
                                        <div class="order-status4">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${map.id}</a></div>
                                                <span>成交时间：${map.order_time}</span>

                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">
                                                    <c:forEach items="${map.list}" var="list">
                                                        <ul class="item-list">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="#" class="J_MakePoint">
                                                                        <img src="${ctx}/static/images/${fn:substring(list.imgs, 0, fn:indexOf(list.imgs,','))}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="#">
                                                                            <p>${list.name}</p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                    $${list.price}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${list.count}
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
                                                            合计：$${map.tot_money}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">交易成功</p>
                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <a href="#">
                                                                <div class="am-btn am-btn-danger anniu">
                                                                    <input type="hidden" value="${map.id}"/>
                                                                    <span class="commend">评价商品</span>
                                                                </div>
                                                            </a>
                                                        </li>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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