<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<title>ajax练习</title>
<script src="${ctx}/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {

        //页面加载完成之后执行
        $(".simpleCart_empty").click(function () {
            //键盘事件，发起ajax请求
            $.ajax({
                url:"empty",
                type:"POST",
                //传递参数
                success:function (result) {
                    location.reload();
                    location.reload();
                    },
                error:function (error) {
                }
            });
            return false;
        });
        $("input[id ='login']").click(function () {
            //键盘事件，发起ajax请求
            $.ajax({
                url:"loginAjax",
                type:"POST",
                //传递参数
                data:{
                    tel1:$("input[name = 'tell']").val(),
                    password1:$("input[name = 'password1']").val()
                },
                success:function (result) {
                    if (result.error){
                        //
                        $("span[id='error']").text(result.error);
                    }else {

                        //刷新页面
                    //location.replace(location.href);
                        location.reload();

                    }
                    /*属性值
                    alert($("input").attr("value"))
                    * alert($(this).val())*/
                },
                error:function (error) {
                    $("span[id = 'error']").text(error);
                    /* alert("error")*/
                }
            });
            return false;
        });

        $(".form-control").keyup(function () {

            //键盘事件，发起ajax请求
           $.ajax({
                url:"SearchAjax",
                type:"POST",
                //传递参数
                data:{
                    text:$(this).val()
                },
                success:function (result) {
                    if (result.error) {

                    }
                },
                    error:function (error) {
                        $("span[id = 'error']").text(error);
                        /!* alert("error")*!/
                    }
                })
        });
    });
</script>
<!--header-->
<div class="header">
    <div class="container">
        <nav class="navbar navbar-default" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <h1 class="navbar-brand"><a  href="${ctx}/toindex">Yummy</a></h1>
            </div>
            <!--navbar-header-->
            <c:set var="zz" scope="page" value="纸杯蛋糕"/>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="${ctx}/toindex" class="active">主页</a></li>

                    <c:forEach items="${applicationScope.cbs}" var="c1">
                        <c:if test="${c1.NAME ==pageScope.zz}">
                            <li><a href="${ctx}/products?id1=${c1.ID}">纸杯蛋糕</a></li>
                        </c:if>
                        <c:if test="${c1.NAME !=pageScope.zz}">
                            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">${c1.NAME}<b class="caret"></b></a>
                                <ul class="dropdown-menu multi-column columns-4">
                                    <div class="row">
                                            <c:forEach items="${c1.c2}" var="c2" varStatus="ccc">
                                                <div class="col-sm-4">
                                                <h4>${c2.NAME}</h4>
                                                    <ul class="multi-column-dropdown">
                                                        <c:forEach items="${c2.c3}" var="c3" varStatus="counter">
                                                            <li><a class="list" href="${ctx}/products?id1=${c1.ID}&id2=${c2.ID}&id3=${c3.ID}">${c3.NAME}</a></li>


                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </c:forEach>
                                    </div>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                    <li class="dropdown grid">
                        <a href="${ctx}/products?id=all" >全部</a>
                    </li>
                </ul>
                <!--/.navbar-collapse-->
            </div>
            <!--//navbar-header-->
        </nav>
        <div class="header-info">

            <div class="header-right search-box">
                <a href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
                <div class="search">
                    <form class="navbar-form" method="post" action="products">
                        <input type="text" class="form-control" name="search">
                       <button type="submit" class="btn btn-default" aria-label="Left Align">
                            查找
                        </button>


                    </form>
                </div>
            </div>
            <c:if test="${sessionScope.user  == null}" >
               <div class="header-right login">
                    <a href="${ctx}/login"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
                </div>
            </c:if>
            <c:if test="${sessionScope.user != null}" >
                <div class="header-right login">
                    <a href="${ctx}/account" style="color: white; display: block ;height: 52px; padding-top: 17.2px;padding-bottom: 17.2px">${sessionScope.user.username}</a><a
                       href="logoff" style="color: white;">注销</a>
                </div>
            </c:if>

            <div class="header-right cart">

                <c:if test="${sessionScope.user != null}" >
                    <a href="${ctx}/cart"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                    <div class="cart-box">

                        <p><a href="${ctx}/deleteCart" class="simpleCart_empty">清空购物车</a></p>
                        <div class="clearfix"> </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.user == null}" >
                    <a href="${ctx}/cart"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>

                </c:if>

            </div>


            <div class="clearfix"> </div>
        </div>

        <div class="clearfix"> </div>
    </div>

</div>
<!--//header-->