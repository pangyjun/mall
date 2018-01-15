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
            //页面加载完成之后执行
            $("input[name = 'username']").keyup(function () {
//                console.log($("input[name = 'username']").val())
                console.log($(this).val())

                //键盘事件，发起ajax请求
                $.ajax({
                    url:"regAjax",
                    type:"POST",
                    //传递参数
                    data:{username:$(this).val()},
                    success:function (result) {
                        if(result.username){
                        $("#s1").text(result.username);
                        }else{
                            $("#s1").text(result.msg);
                        }
                    },
                    error:function (error) {
                        $("#s1").text(error);
                    }
                });
            });
            $("input[name = 'tel']").keyup(function () {
                $.ajax({
                    url:"regAjax",
                    type:"POST",
                    data:{tel:$(this).val()},
                    success:function (result) {
                        if(result.tel){
                            $("#s2").text(result.tel);
                        }else{
                            $("#s2").text(result.msg);
                        }
                    },
                    error:function (error) {
                        $("#s2").text(error);
                    }
                });
            });

            $("input[name='rePassword']").blur(function () {
              if($(this).val()!=($("input[name='password']").val())){
                  $("#s3").text("两次密码不一致");
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
                    <h3>个人信息</h3>
                    <div class="input">
                        <c:if test="${sessionScope.user == null}" >
                            <span>（<label>*</label>为必填信息）</span>
                        </c:if>
                        <c:if test="${sessionScope.user != null }" >
                            <div class="input">
                                <span style="color: red;">( 若修改信息，请点击submit，才能将修改的信息保存 )</span>
                                <span style="color: cornflowerblue;">${requestScope.success}</span>
                            </div>
                        </c:if>

                        <br>
                        <span>姓名<label>*</label></span>
                        <input type="text" name="username" value="${sessionScope.user.username}" required><span style="color: red" id="s1" required></span>
                    </div>
                    <div class="input" >
                        <span>联系电话<label>*</label></span>
                        <input type="text" name="tel" value="${sessionScope.user.tel}" required><span style="color: red" id="s2" required></span>
                    </div>

                    <c:if test="${sessionScope.user!= null}" >
                        <div class="input">
                            <span>性别</span>
                            <input type="radio" name="gender" value="男" <c:if test='${sessionScope.user.gender== "男"}'>checked="checked"</c:if> >男
                            <input type="radio" name="gender" value="女" <c:if test='${sessionScope.user.gender== "女"}'>checked="checked"</c:if> >女
                        </div>
                    </c:if>
                    <%--<div class="input">
                        <c:if test="${fn:length(sessionScope.user)>0}" >
                            <span>地址(请手动写全收货地址):</span>
                        <div class="a">
                            <a href="javascript:void(0)" class="pick-area pick-area4" name="新疆维吾尔自治区/博尔塔拉蒙古自治州"></a>
                            </span><input type="text" name="addr"  value="${sessionScope.user.addr}">
                        </div>
                        </c:if>

                    </div>--%>

                    <div class="clearfix"> </div>
                </div>
                <c:if test="${sessionScope.user == null}" >
                <div class="register-bottom-grid">
                <h3>登录信息</h3>
                <div class="input">
                    <span>密码<label>*</label></span>
                    <input type="password" name="password" pattern="^\w{6,}$" value="${sessionScope.user.password}" title="密码最少六位数" required>
                </div>

                    <div class="input">
                        <span>确认密码<label>*</label></span>
                        <input type="password" pattern="^\w{6,}$"; name="rePassword" required><span style="color: red" id="s3" ></span>
                    </div>

                </div>
                </c:if>

                <div class="clearfix"> </div>
                <div class="register-but">
                    <input type="submit" value="submit">
                    <c:if test="${sessionScope.user == null }" >
                        <input type="reset" value="重置">
                    </c:if>

                </div>
            </form>
           
        </div>
    </div>
</div>
<!--//account-->
<!--footer-->
<%@include file="../../common/footer.jsp"%>
<script src="static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/pick-pcc.min.1.0.1.js"></script>
<script type="text/javascript">

    $(".pick-area4").pickArea({
        "format":"province/city/county", //格式
        "width":"420",//设置“省市县”文本边框的宽度
        "height":"48",//设置“省市县”文本边框的高度
        "borderColor":"#435abd",//设置“省市县”文本边框的色值
        "arrowColor":"#435abd",//设置下拉箭头颜色
        "listBdColor":"#435abd",//设置下拉框父元素ul的border色值
        "color":"#435abd",//设置“省市县”字体颜色
        "fontSize":"20px",//设置字体大小
        "hoverColor":"#435abd",
        "paddingLeft":"30px",//设置“省”位置处的span相较于边框的距离
        "arrowRight":"30px",//设置下拉箭头距离边框右侧的距离
        "maxHeight":"300px",
        //"preSet":"",//数据初始化 ；这里的数据初始化有两种方式，第一种是用preSet属性设置，第二种是在a标签里使用name属性设置
        "getVal":function(){//这个方法是每次选中一个省、市或者县之后，执行的方法
            //console.log($(".pick-area-hidden").val())
            //console.log($(".pick-area-dom").val())
            var thisdom = $("."+$(".pick-area-dom").val());//返回的是调用这个插件的元素pick-area，$(".pick-area-dom").val()的值是该元素的另一个class名，这个class名在dom结构中是唯一的，不会有重复，可以通过这个class名来识别这个pick-area
            thisdom.next().val($(".pick-area-hidden").val());//$(".pick-area-hidden").val()是页面中隐藏域的值，存放着每次选中一个省、市或者县的时候，当前文本存放的省市县的最新值，每点击一次下拉框里的li，这个值就会立即更新
        }
    });



    $(".pick-area7").pickArea({"format":"","width":"150px","borderColor":"#7894D4","color":'#7894D4',"arrowColor":"#7894D4"});

</script>
</body>
</html>