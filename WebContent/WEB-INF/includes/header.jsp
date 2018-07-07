<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>
<c:choose>
<c:when test="${title != null}">
${title}
</c:when>
<c:otherwise>
ETBook
</c:otherwise>
</c:choose>
</title>
<link type="image/jpg" href="resources/images/bk-icon.jpg" rel="icon"/>


<%--  <script src="<c:url value = "resources/js/jquery-2.1.0.js" />"></script>--%>
 
 <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
 
<link rel="stylesheet" type="text/css" href="<c:url value = "resources/css/my-css1.css" />"></link>
<link rel="stylesheet" type="text/css" href="<c:url value = "resources/css/my-css2-sh-c.css" />"></link>
<link rel="stylesheet" type="text/css" href="<c:url value = "resources/css/my-css3-log.css" />"></link>
<link rel="stylesheet" type="text/css" href="<c:url value = "resources/css/my-css-manage.css" />"></link>
</head>
<body>
<header>
<nav id="top-nav">
<div><a href="signup">Sign Up</a></div>
<div><a href="login"><span class="glyphicon glyphicon-lock" style="font-size: 12px"></span>Log In</a></div>
<div><a href="#">Sell Your Book</a></div>
<div><a href="#">About Us</a></div>
<c:if test="${sessionScope.customerInfo != null }">
    <div><span id="user-id"><strong>Hi ${sessionScope.customerInfo.name}</strong></span><a href="customer-logout"> &#40;logout&#41; </a></div>
</c:if>
/nav>
<%--<section id="logo-s-c-pos">
<div id="log-b">
<img src="resources/images/logo-etbook.jpg"/>
</div>
<div id="query-search-b">
<form action ="quick-search" method = "get" id="query-search-f">
<input type="text" id="search" name="search" placeholder="Search" onkeyup="showResult(this.value)"/>
<button type="submit" id="search-btn">Search</button>
</form>

<div class="result" style="position: absolute; width: 250px; z-index: 5; background-color: #fff; display: none;" id="ls-block" >
</div>
</div>
<div id="cart-b">
<a href="#"><i class<="fa fa-heart-o" style="font-size:18px">&nbsp;</i>Wish list</a>
<a href="cart"><i class="glyphicon glyphicon-shopping-cart">&nbsp;</i>Cart</a>
</div>
</section>
<nav id="btm-nav-link">
<div><a href="index.jsp">Home</a></div>
<div><a href="all-books">Browse All Books</a></div>
</nav>--%>
</header>