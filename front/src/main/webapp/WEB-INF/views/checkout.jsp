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
    <%@include file="../../common/head.jsp" %>
    <script src="static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(function () {
            $("input[name='cake']").click(function () {
                /*console.log($(this)[0].checked)*/
                $("input[type='submit']").val("结算（"+$("input[name='cake']:checked").length+")");

                $.ajax({
                    url: "${ctx}/totle",
                    type: "POST",
                    data: {
                        cid:$(this).val(),
                        checked:$(this)[0].checked


                    },
                    success: function (result) {
                            $("#s").text(result.msg)
                    },
                    error: function (error) {

                    },
                })
            });
        })
        
        $(window).load(function () {
            var cb = document.getElementsByName("cake");
            var se = document.getElementById("select");


            //为每一个要选中的多选框添加一个点击事件
            for(var i = 0; i < cb.length; i++) {
                var c = cb[i];
                c.onclick = function(){


                    //默认全选
                    se.checked = true;
                    //判断是否全选
                    for (var j = 0;j < cb.length;j++) {
                        if(!cb[j].checked){
                            se.checked = false;
                            break;
                        }
                    }

                }
            }

            //全选，全不选6
            se.onclick = function() {
                for(var i = 0; i < cb.length; i++) {
                    cb[i].checked = this.checked;
                    if(this.checked){
                        $("input[type='submit']").val("结算（"+cb.length+")");
                    }else{
                        $("input[type='submit']").val("结算（"+0+")");
                    }
                    $.ajax({
                        url: "${ctx}/tot",
                        type: "POST",
                        data: {
                            cid:$(this).val(),
                            t_checked:$(this)[0].checked


                        },
                        success: function (result) {
                            $("#s").text(result.msg)
                        },
                        error: function (error) {

                        },
                    })
                }
            }




            $("input[name='quantity']").click(function () {

                var money = this;
                $.ajax({
                    url: "${ctx}/doCart",
                    type: "POST",
                    data: {
                        count: $(this).val(),
                        cid: $(this).next("input[name ='cid']").val(),
                    },
                    success: function (result) {
                        console.log(result)
                        $(money).parent().parent().nextAll("li").children("p").children("span").text(result.msg);
                        $("#s").text(result.totle)

                    },
                    error: function (error) {
                        $(money).parent().parent().nextAll("li").children("p").children("span").text(error.msg);

                    },
                })
            })

        });
    </script>

</head>
<body>
<%@include file="../../common/header.jsp" %>
<!--cart-items-->
<div class="cart-items">
    <div class="container">
        <h2>购物车（${fn:length(requestScope.carts)}）</h2>


        <br><br>
        <form action="do_order" method="post">
            <c:forEach items="${requestScope.carts}" var="cake">
                <input type="checkbox" name="cake" value="${cake.pid} "  style="height: 25px;width: 25px">
                <div class="cart-header">
                    <div class="cart-sec simpleCart_shelfItem">


                        <div class="cart-item cyc">
                            <a href="single?id=${cake.pid}">
                            <img src="${ctx}/static/images/${fn:substring(cake.imgs, 0, fn:indexOf(cake.imgs, ","))}"
                                 alt=""></a>
                        </div>
                        <div class="cart-item-info">

                            <h3><a href="single?id=${cake.pid}"> ${cake.name}</a></h3>
                            <ul class="qty">
                                <br/><br/>
                                <li><p>单价: $${cake.price}</p></li>
                                <br/><br>
                                <li><p>数量:<input min="1" type="number" name="quantity" value="${cake.count}"
                                                 class="form-control input-small"/>
                                    <input hidden="hidden" type="text" name="cid" value="${cake.pid}">
                                </p></li>

                                <br/><br>
                                        <li><p>金额:<span>${cake.count*cake.price}</span>
                                  </p></li>


                            </ul>
                            <div class="delivery">
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div style="padding-top: 80px">
                            <a href="deleteCart?cid=${cake.pid}"><img src="${ctx}/static/images/close_1.png" alt=""></a>
                        </div>

                        <div class="clearfix"></div>
                    </div>
                </div>


            </c:forEach>
            <br><br><br>
            <hr><br><br><br>
            <div>
                <input type="checkbox" id="select" style="height:18px;width: 18px"/> 全选
                <div style="float: right">
                    合计：<span id="s">0</span> &nbsp;&nbsp;&nbsp;
                    <input type="submit" value="结算(0)" style="background-color: #F07818;color: white"/>

                </div>
                <br><br>
                <a href="order_list">我的订单</a>
                <br>
                <a href="address_list">收货地址管理</a>
   <br>
                <a href="commend_list">评价清单</a>

            </div>

        </form>



    </div>
</div>
<!--//checkout-->
<!--footer-->
<%@include file="../../common/footer.jsp" %>
</body>
</html>