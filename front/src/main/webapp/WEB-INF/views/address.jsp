<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/1/22
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><%--
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

    <title>address</title>

    <link href="${ctx}/static/css/admin.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/amazeui.css" rel="stylesheet" type="text/css">

    <link href="${ctx}/static/css/personal.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/addstyle.css" rel="stylesheet" type="text/css">

    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/amazeui.js"></script>
    <script src="${ctx}/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/layer/layer.js"></script>
    <script type="text/javascript">

        $(function () {
            $(".a").click(function () {
//                var id = this.parentNode.parentNode.parentNode.firstChild.nextSibling.valueOf().val()
//                var name = this.parentNode.parentNode.parentNode.firstChild.nextSibling.nextSibling.nextSibling.valueOf().val()
//                var tel = this.parentNode.parentNode.parentNode.firstChild.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.valueOf()
//                var addr =this.parentNode.parentNode.parentNode.firstChild.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.valueOf()
//                var flag =  this.parentNode.parentNode.parentNode.firstChild.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.valueOf()
                var id = $(this).parent().parent().children(".id").val()
                var name = $(this).parent().parent().children(".name").val()
                var tel = $(this).parent().parent().children(".tel").val()
                var addr = $(this).parent().parent().children(".addr").val()
                var flag = $(this).parent().parent().children(".flag").val()


              layer.open({
                    type: 1,
                    title:"编辑",
                    skin: 'layui-layer-rim', //加上边框
                    area: ['420px', '240px'], //宽高
                    content: '<form action="${ctx}/edit" method="post">'+
                    '&nbsp;&nbsp;收件人姓名：<input name="name" value="'+name+'"/><br>'+
                    '&nbsp;&nbsp;收件人电话：<input name="tel" value="'+tel+'"/><br>'+
                    '&nbsp;&nbsp;收件人地址：<input name="addr" value="'+addr+'" /><br>'+
                    '<input name="id" type="hidden" value="'+id+'"/>&nbsp;&nbsp;'+
                    '是否设置为默认地址<input type="radio" name="flag" value="1"/> 是<input type="radio" name="flag" checked value="0"/>否<br><br>&nbsp;&nbsp;<input type="submit" value="提交"/></form>'

                });
                /*layer.open({
                  title:"编辑",
                    content: '<form action="/edit"><input/><input/><input/></form>'
                });
*/
            })
        })


    </script>
</head>
<body>
<%@include file="../../common/header.jsp" %>
<div class="center">
    <div class="col-main">
        <div class="main-wrap">

            <div class="user-address">
                <!--标题 -->
                <div class="am-cf am-padding">
                    <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">地址管理</strong> / <small>Address&nbsp;list</small></div>
                </div>
                <hr/>
                <ul class="am-avg-sm-1 am-avg-md-3 am-thumbnails">
                    <c:forEach items="${requestScope.list}" var="address" varStatus="status">


                        <input type="hidden" value="${address.id}" class="id">
                        <input type="hidden" value="${address.name}" class="name">
                        <input type="hidden" value="${address.tel}" class="tel">
                        <input type="hidden" value="${address.addr}" class="addr">
                        <input type="hidden" value="${address.flag}" class="flag">

                        <c:if test="${address.flag == 1 }">
                            <li class="user-addresslist defaultAddr">
                                <input type="hidden" value="${address.id}" class="id">
                                <input type="hidden" value="${address.name}" class="name">
                                <input type="hidden" value="${address.tel}" class="tel">
                                <input type="hidden" value="${address.addr}" class="addr">
                                <input type="hidden" value="${address.flag}" class="flag">
                                <span class="new-option-r"><i class="am-icon-check-circle"></i>默认地址</span>
                                <p class="new-tit new-p-re">
                                    <span class="new-txt">${address.name}</span>
                                    <span class="new-txt-rd2">${address.tel}</span>
                                </p>
                                <div class="new-mu_l2a new-p-re">
                                    <p class="new-mu_l2cw">
                                        <span class="title">地址：</span>
                                        <span class="street">${address.addr}</span></p>
                                </div>
                                <div class="new-addr-btn">
                                    <button class="a">编辑</button>
                                    <span class="new-addr-bar">|</span>
                                    <a href="${ctx}/del?id=${address.id}">删除</a>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${address.flag != 1 }">
                            <li class="user-addresslist defaultAddr">
                                <input type="hidden" value="${address.id}" class="id">
                                <input type="hidden" value="${address.name}" class="name">
                                <input type="hidden" value="${address.tel}" class="tel">
                                <input type="hidden" value="${address.addr}" class="addr">
                                <input type="hidden" value="${address.flag}" class="flag">
                                <p class="new-tit new-p-re">
                                    <span class="new-txt">${address.name}</span>
                                    <span class="new-txt-rd2">${address.tel}</span>
                                </p>
                                <div class="new-mu_l2a new-p-re">
                                    <p class="new-mu_l2cw">
                                        <span class="title">地址：</span>
                                        <span class="street">${address.addr}</span></p>
                                </div>
                                <div class="new-addr-btn">
                                    <button class="a">编辑</button>
                                    <span class="new-addr-bar">|</span>
                                    <a href="${ctx}/del?id=${address.id}">删除</a>
                                </div>
                            </li>
                        </c:if>

                    </c:forEach>
                </ul>
                <div class="clear"></div>
                <a class="new-abtn-type" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0}">添加新地址</a>
                <!--例子-->
                <div class="am-modal am-modal-no-btn" id="doc-modal-1">

                    <c:if test="${fn:length(requestScope.list)<3}">
                        <div class="add-dress">

                            <!--标题 -->
                            <div class="am-cf am-padding">
                                <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">新增地址</strong> / <small>Add&nbsp;address</small></div>
                            </div>
                            <hr/>

                            <div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
                                <form class="am-form am-form-horizontal" action="${ctx}/edit" method="post" id="form">

                                    <div class="am-form-group">
                                        <label for="user-name" class="am-form-label">收货人</label>
                                        <div class="am-form-content">
                                            <input type="text" id="user-name" placeholder="收货人" name="name">
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="user-phone" class="am-form-label">手机号码</label>
                                        <div class="am-form-content">
                                            <input id="user-phone" name="tel" placeholder="手机号必填" <%--type="email"--%>>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <label <%--for="user-intro"--%> class="am-form-label">地址</label>
                                        <div class="am-form-content">
                                            <textarea class="" name="addr" rows="3" <%--id="user-intro"--%> placeholder="输入详细地址"></textarea>
                                            <small>100字以内写出你的详细地址...</small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <input type="submit" value="保存" class="am-btn am-btn-danger"/>
                                            <%--<button class="am-btn am-btn-danger">取消</button>--%>
                                            <%--<input type="button" value="取消" class="am-btn am-btn-danger"/>--%>
                                            <%--<a href="javascript: void(0)" class="am-close am-btn am-btn-danger" data-am-modal-close>取消</a>--%>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </c:if>

                </div>

            </div>

            <script type="text/javascript">
                $(document).ready(function() {
                    $(".new-option-r").click(function() {
                        $(this).parent('.user-addresslist').addClass("defaultAddr").siblings().removeClass("defaultAddr");
                    });

                    var $ww = $(window).width();
                    if($ww>640) {
                        $("#doc-modal-1").removeClass("am-modal am-modal-no-btn")
                    }

                })
            </script>

            <div class="clear"></div>

        </div>
    </div>
</div>
</body>
</html>
