<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/11/17
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../../common/head.jsp"%>
    <script src="${ctx}/static/js/imagezoom.js"></script>
    <!-- FlexSlider -->
    <script defer src="${ctx}/static/js/jquery.flexslider.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/flexslider.css" type="text/css" media="screen" />
    <script>
        // Can also be used with $(document).ready()
        $(window).load(function() {
            $('.flexslider').flexslider({
                animation: "slide",
                controlNav: "thumbnails"
            });
            $("#quantity").click(function () {
                $("#s").text("")
            })

            $(".btn_form").children("a").click(function () {


                var cid = ${requestScope.cake.id};
                var  quantity  = $("#quantity").val();

                var formDate={
                    text:$(this).text(),
                    quantity: $("#quantity").val(),
                    cid:${requestScope.cake.id}
                }
                var user = "${sessionScope.user.username}";
                if(!user){
                    var uri =  "/single?id="+ ${requestScope.cake.id};
                    console.log(uri)
                    location = "${ctx}/login?URI="+uri;
                }



                $.ajax({
                    url:"${ctx}/doAddCart",
                    type:"POST",
                    data:formDate,
                    success:function(result){
                        if(result.msg == ""){
                            location = "do_order?cakeid="+cid+"&quantity="+quantity;
                        }else{
                            $("#s").text(result.msg);
                        }

                    }

                })

                return false;
            })
           
        })

    </script>
    <!--//FlexSlider -->
</head>
<body>
<%@include file="../../common/header.jsp"%>
<!--//single-page-->
<div class="single">
    <div class="container">
        <br><br>
        <div class="single-grids">

            <div class="col-md-4 single-grid">
                <div class="flexslider">
                    <ul class="slides">
                        <c:forTokens items="${requestScope.cake.imgs}" delims="," var="img" varStatus="status">
                            <li data-thumb="${ctx}/static/images/${img}">
                                <div class="thumb-image"> <img src="${ctx}/static/images/${img}" data-imagezoom="true" class="img-responsive"> </div>
                            </li>

                        </c:forTokens>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 single-grid simpleCart_shelfItem">
                <h3>${requestScope.cake.name}</h3>
                <p>${requestScope.cake.comm}</p>
               <%-- <ul class="size">
                    <h3>尺寸</h3>

                    <li><lable><a href="#"><input type="radio" name="size">4寸</a></lable></li>
                    <li><lable><a href="#"><input type="radio" name="size">10寸</a></lable></li>
                    <li><lable><a href="#"><input type="radio" name="size">12寸</a></lable></li>
                    <li><lable><a href="#"><input type="radio" name="size">13寸</a></lable></li>
                </ul>--%>

                <div class="galry">
                    <div class="prices">
                        <h5 class="item_price">$${requestScope.cake.price}</h5>
                    </div>
                    <div class="rating">
                        <span>☆</span>
                        <span>☆</span>
                        <span>☆</span>
                        <span>☆</span>
                        <span>☆</span>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <p class="qty"> Qty :  </p><input min="1" type="number" id="quantity" name="quantity" value="1" class="form-control input-small">

                <div class="btn_form">
                    <a href="#" class="add-cart item_add">加入购物车</a>
                    <a href="do_order?cid=${requestScope.cake.id}" class="add-cart item_add">立即购买</a><br>
                    <span id="s"></span>
                </div>
                <div class="tag">
                    <p>分类 : <a href="#"> Cakes</a></p>
                </div>
            </div>
            <div class="col-md-4 single-grid1">
                <h2>账户</h2>
                <ul>
                    <c:if test="${sessionScope.user == null}">
                        <li><a href="${ctx}/account">注册</a></li>
                        <li><a href="${ctx}/login">登录</a></li>
                    </c:if>
                    <li><a href="${ctx}/password">忘记密码</a></li>
                    <c:if test="${sessionScope.user != null}">
                        <li><a href="${ctx}/account">我的账户</a></li>
                    </c:if>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>

<!--related-products-->
<div class="related-products">
    <div class="container">
        <h3>相关产品</h3>
        <div class="product-model-sec single-product-grids">
            <div class="product-grid single-product">
                <a href="single.jsp">
                    <div class="more-product"><span> </span></div>
                    <div class="product-img b-link-stripe b-animate-go  thickbox">
                        <img src="${ctx}/static/images/m1.png" class="img-responsive" alt="">
                        <div class="b-wrapper">
                            <h4 class="b-animate b-from-left  b-delay03">
                                <button>View</button>
                            </h4>
                        </div>
                    </div>
                </a>
                <div class="product-info simpleCart_shelfItem">
                    <div class="product-info-cust prt_name">
                        <h4>Product #1</h4>
                        <span class="item_price">$2000</span>
                        <div class="ofr">
                            <p class="pric1"><del>$2300</del></p>
                            <p class="disc">[15% Off]</p>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>

</div>

<%@include file="../../common/footer.jsp"%>
</body>
</html>