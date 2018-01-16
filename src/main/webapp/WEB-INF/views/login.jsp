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
    <style>
        body{padding:10px;}
        .pick-area{display: inline-block;}
        .a{float:left;width:100%;margin:10px;}
        .a a{float:left;margin:10px;}
        .a input{float:left;width:400px;padding:10px;margin:10px;outline-color: gold;}
        h1{text-align: center;}
        h3{margin:5px;color:#333;}
        h4{margin:5px;color:#666;padding:0 20px;font-weight: 100;}
        .blank{float:left;width:50%;height:400px;background:cyan;border-top:1px solid #333;margin-top:50px;}
        .blank2{float:left;width:50%;height:400px;background:lightpink;border-top:1px solid #333;padding-top:50px;}
        .box{width:100%;overflow: hidden;background:lightgreen;}
    </style>
    <script src="${ctx}/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
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
                            alert(result.error)
                            $("input[name = 'tell']").val(""),
                            $("input[name = 'password1']").val("")

                        }else {

                            //刷新页面
                            location ="${ctx}/history"
                            //location.replace(location.href);
//                            location.reload();
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
        })
    </script>

</head>
<body>
<%@include file="../../common/header.jsp"%>
<!--account-->
<div class="account">
    <div class="container">
        <div class="register">
            <form action="regist" method="post">
                <div class="register-top-grid">
                    <h3>欢迎来到yummy</h3>

                    <div>
                        <form id="loginForm"  >

                            <div class="input">
                                <br>
                                <span>电话<label></label></span>
                                <input type="text" name="tell" id="email" required>
                                <br>
                                <span>密码<label></label></span>
                                <input type="password" name="password1" id="password"  required>
                                <br><br><br>

                                <input type="submit" id="login" value="登陆">
                                <p>新用户 ? <a class="sign" href="${ctx}/account">注册</a> <span><a href="${ctx}/toPassword">忘记密码？</a></span></p>
                            </div>


                        </form>
                    </div>
</div>
<!--//account-->

            </form></div></div></div></body></html>