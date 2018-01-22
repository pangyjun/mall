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
        $(function () {
            //页面加载完成之后执行
            $("input[type ='button']").click(function () {
                //键盘事件，发起ajax请求
                $.ajax({
                    url:"passAjax",
                    type:"POST",
                    //传递参数
                    data:{username:$("input[name='username']").val(),
                        tel:$("input[name='tel']").val()

                    },
                    success:function (result) {
                        if(result.msg == "信息正确" ){
                            $("h4").text(result.msg+"，请修改密码：");
                            $("#user").val($("input[name='username']").val())
                            $("#div").css('visibility','visible')

                        }else{
                            alert(result.msg)
                           $("input[name='username']").val(""),
                           $("input[name='tel']").val("")

//                            $("h4").text(result.msg);
                            $("#pass").text("");

                        }

                    },
                    error:function (error) {

                    }
                });
            });


            $("input[name='rePassword']").blur(function () {
                if($(this).val()!=($("input[name='password']").val())){
//                    $("#s3").text("两次密码不一致");
                    alert("两次密码不一致")
                }
            });


            $("input[name='password']").focus(function () {
                $(this).val("");
                $("input[name='rePassword']").val("");
                $("#s3").text("");

            });

            $("input[name='rePassword']").focus(function () {
                $(this).val("");
                $("#s3").text("");
            });


        });


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
                    <h3>忘记密码？</h3>
                    <h5 style="color: #00BFF0;"><I>请填写以下信息修改密码：</I></h5>
                    <div class="input">
                        <br>
                        <span>姓名<label>*</label></span>
                        <input type="text" name="username" >
                    </div>
                    <div class="input">
                        <span>联系电话<label>*</label></span>
                        <input type="text" name="tel" >
                    </div>

                    <div class="input">
                        <input type="button" value="确认">
                    </div>
                    <br><br><br>
                    <div class="input" id="p">
                        <I><h4 style="color: #00BFF0"></h4></I><br>
                        <h5 id="pass" style="color:#F07818"> </h5>
                    </div>

                </div>
            </form>


            <div class="register-bottom-grid" style="visibility: hidden" id="div">
            <form method="post" action="${ctx}/updatePass">

                    <div class="input">
                        <input type="hidden" name="username" id="user"/>
                        <span>密码<label>*</label></span>
                        <input type="password" name="password" pattern="^\w{6,}$"  title="密码最少六位数" required>
                    </div>
                    <div class="input">
                        <span>确认密码<label>*</label></span>
                        <input type="password" pattern="^\w{6,}$"; name="rePassword" required><span style="color: red" id="s3" ></span>
                    </div>

                <div class="clearfix"> </div>
                <div class="register-but">
                    <input type="submit" value="submit" >


                </div>


            </form>
            </div>

        </div>
    </div>
</div>
</body>
</html>

