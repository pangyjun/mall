<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/11/17
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="../../common/head.jsp"%>
</head>
<body>
<%@include file="../../common/header.jsp"%>
<!--banner-->
<div class="banner">
  <div class="container">
    <h2 class="hdng">Yummy <span>Cakes</span> for u</h2>
    <p>Our best cakes make your day special</p>
    <div class="banner-text">
      <img src="${ctx}/static/images/2.png" alt=""/>
    </div>
  </div>
</div>
<!--//banner-->
<!--gallery-->
<div class="gallery">
  <div class="container">
    <div class="gallery-grids">

      <div class="col-md-6 gallery-grid glry-one" >
        <a href="single?id=${applicationScope.one.id}"><img  src="${ctx}/static/images/${fn:substring(applicationScope.one.imgs, 0, fn:indexOf(applicationScope.one.imgs, ","))}" class="img-responsive" alt=""/>
          <div class="gallery-info">
            <a class="shop" href="single?id=${applicationScope.one.id}">详情</a>
            <div class="clearfix"> </div>
          </div>
        </a>
        <div class="galy-info">
          <p>${applicationScope.one.name}</p>
          <div class="galry">
            <div class="prices">
              <h5 class="item_price">￥${applicationScope.one.price}</h5>
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
        </div>
      </div>

      <div class="col-md-6 gallery-grid glry-two">
        <a href="single?id=${applicationScope.two.id}"><img  src="${ctx}/static/images/${fn:substring(applicationScope.two.imgs, 0, fn:indexOf(applicationScope.two.imgs, ","))}" class="img-responsive" alt=""/>
          <div class="gallery-info galrr-info-two">
            <a class="shop" href="single?id=${applicationScope.two.id}">详情</a>
            <div class="clearfix"> </div>
          </div>
        </a>

        <div class="galy-info">
          <p>${applicationScope.two.name}</p>
          <div class="galry">
            <div class="prices">
              <h5 class="item_price">￥${applicationScope.two.price}</h5>
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
        </div>
      </div>
      <c:forEach items="${applicationScope.new8_10}" var="cake">
        <div class="col-md-3 gallery-grid ">
          <a href="single?id=${cake.id}"><img src="${ctx}/static/images/${fn:substring(cake.imgs, 0, fn:indexOf(cake.imgs, ","))}" class="img-responsive" alt=""/>
            <div class="gallery-info">
              <a class="shop" href="single?id=${cake.id}">详情</a>
              <div class="clearfix"> </div>
            </div>
          </a>
          <div class="galy-info">
            <p>${cake.name}</p>
            <div class="galry">
              <div class="prices">
                <h5 class="item_price">￥${cake.price}</h5>
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
          </div>
        </div>

      </c:forEach>

  </div>
</div>
<!--//gallery-->
<!--subscribe-->
    <br><br><br>
    <div class="register-top-grid">
        <h3>最近浏览</h3>


    </div>
</div>
<div class="subscribe">
  <div class="container">
      <c:forEach items="${requestScope.list}" var="cake">
          <div class="col-md-3 gallery-grid ">
              <a href="single?id=${cake.id}"><img src="${ctx}/static/images/${fn:substring(cake.imgs, 0, fn:indexOf(cake.imgs, ","))}" class="img-responsive" alt=""/>
                  <div class="gallery-info">
                      <a class="shop" href="single?id=${cake.id}">详情</a>
                      <div class="clearfix"> </div>
                  </div>
              </a>
              <div class="galy-info">
                  <p>${cake.name}</p>
                  <div class="galry">
                      <div class="prices">
                          <h5 class="item_price">￥${cake.price}</h5>
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
              </div>
          </div>

      </c:forEach>
  </div>
</div>
<!--//subscribe-->
<!--footer-->

<%@include file="../../common/footer.jsp"%>
</body>
</html>