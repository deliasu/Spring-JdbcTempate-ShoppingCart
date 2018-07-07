<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<section id="slide-active-ss">
<div id="slide">
<div id = "active-ss"><img src="resources/images/slide-img1.jpg" id="ss"/></div>
<div id="inf-on-ss">
<div><i class="fa fa-check-circle-o" style="font-size:30px; color:red; font-weight: bold;"></i><a href="#">All Books</a></div>
		<div><i class="fa fa-check-circle-o" style="font-size:30px; color:red; font-weight: bold;"></i><a href="#">Categories</a></div>
		<div><i class="fa fa-check-circle-o" style="font-size:30px; color:red; font-weight: bold;"></i><a href="#">Sell Your Book</a></div>
</div>
<div id="sliding-to-l-r">
<div ><i class="fa fa-chevron-left" style="font-size:55px;color:red; padding: 10px;" id="slideleft"></i></div>
<div class="f-right"><i class="fa fa-chevron-right" style="font-size:55px;color:red; padding: 10px;" id="slideright"></i></div>
</div>
<div>
</div>
</div>
</section>
<section>

</section>
<script src="<c:url value = "resources/js/my-js1.js" />" type="text/javascript"></script>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>