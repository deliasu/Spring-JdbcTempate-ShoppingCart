<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<div id="all-the-prods">
<c:if test="${not empty allbooks}">
<c:forEach var="book" items="${allbooks}">
 <div class = "products-block">
  <div class = "products">
	<div class = "image-block" style="width: 152px; height: 201px;" >
		<a href="product-details?id=${book.id}"><img src="resources/images/${book.image}" width="150px" height="200px" alt="${book.title}" /></a>
	</div>
	<div class = "price-number-ordered">
		<div class = "price"><strong style="color: red">&yen;${book.price}</strong></div>
		<div class = "orderd">${book.paid}PAID</div>
	</div>
	<div class="b-desc"><a href="product-details?id=${book.id}">${book.briefDesc}</a></div>
  </div>
 </div>
</c:forEach>
</c:if>
</div>
<div style="clear: left;"></div>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>