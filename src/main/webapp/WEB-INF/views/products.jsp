<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/11/17
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="common.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../../common/head.jsp" %>
    <link href="${ctx}/static/css/form.css" rel="stylesheet" type="text/css" media="all"/>

    <!-- the jScrollPane script -->
    <script type="text/javascript" src="${ctx}/static/js/jquery.jscrollpane.min.js"></script>
    <script type="text/javascript" id="sourcecode">
        $(function () {
            $('.scroll-pane').jScrollPane();
        });
    </script>
    <!-- //the jScrollPane script -->
    <script type="text/javascript" src="${ctx}/static/js/jquery.mousewheel.js"></script>
    <!-- the mousewheel plugin -->
    <script src="${ctx}/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                location="products?low="+$("#low").val()+"&top="+$("#top").val();
            })

            $(".item_add").click(function () {
                var succ = this;
                $.ajax({
                    url:"productAdd",
                    type:"POST",
                    data:{
                        cid1:$(this).next().next().val()
                    },
                    success:function(result){
                        $(succ).next("span").text(result.msg);

                    },
                    error:function (error) {
                        $(succ).next("span").text(error);                    },
                })
            })
        })
    </script>

</head>
<body>
<%@include file="../../common/header.jsp" %>
<!--products-->
<div class="products">
    <div class="container">
        <c:choose>
        <c:when test="${fn:length(requestScope.cakes) <=0}">
            <h1>没有相关蛋糕！</h1>
        </c:when>
        <c:otherwise>

                <h2>我们的蛋糕(${requestScope.size})</h2>



            <%--价格区间：<input id="low" type="number"> —— <input id="top" type="number"> <button id="btn">查询</button>--%>

        <div class="col-md-12 product-model-sec">
            <c:forEach items="${requestScope.cakes}" var="cake">
                <div class="product-grid">
                    <a href="single?id=${cake.id}">
                        <div class="more-product"><span> </span></div>
                        <div class="product-img b-link-stripe b-animate-go  thickbox">

                                <%-- //图片
                                 <script>console.log('--->${cake.name}---${fn:substring(cake.imgs, 0, fn:indexOf(cake.imgs, ","))}')</script>
                                 <c:forTokens items="${cake.imgs}" delims="," var="img">
                                     <script>console.log('${img}')</script>
                                 </c:forTokens>--%>
                            <img src="${ctx}/static/images/${fn:substring(cake.imgs, 0, fn:indexOf(cake.imgs, ","))}"
                                 class="img-responsive" alt="">
                            <div class="b-wrapper">
                                <h4 class="b-animate b-from-left  b-delay03">
                                    <button>查看</button>
                                </h4>
                            </div>
                        </div>
                    </a>
                    <div class="product-info simpleCart_shelfItem">
                        <div class="product-info-cust prt_name">
                            <h4>${cake.name}</h4>
                            <span class="item_price">$${cake.price}</span>
                            <div class="ofr">
                                <p class="pric1">
                                    <del>$${cake.pre_price}</del>
                                </p>
                            </div>
                            <span class="s" hidden="hidden">${sessionScope.map.username}</span>
                            <input type="button" class="item_add items" value="Add">
                            <span class="p" style="color: red"></span>
                            <input hidden="hidden" value="${cake.id}">
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        </c:otherwise>
        </c:choose>

        <c:forEach begin="1" end="${(requestScope.size+17) / 18 }" varStatus="i">
            <a href="products?page=${i.current}">${i.current}</a>
        </c:forEach>
    </div>
</div>

<%@include file="../../common/footer.jsp" %>
</body>
</html>
