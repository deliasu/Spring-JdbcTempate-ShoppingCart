<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<h2 style="color: red; text-align:center">ETBook Management Gateway</h2>
<div class="alert alert-info">
</div>
<div class="container">
<div class="jumbotron">
<div class="row">
    <div class="col-sm-2">
    <a href="add-book-form" >Add Book</a>
    </div>
    <div class="col-sm-2">
    <a href="modify-book">Delete Book</a>
    </div>
    <div class="col-sm-3">
    <a href="modify-book">Modify Book</a>
    </div>
     <div class="col-sm-3">
    <a href="manage-order">Manage order</a>
    </div>
    <div class="col-sm-2">
    <a href="monthly-report">Monthly Report</a>
    </div>
</div>
</div>
<div class="jumbotron" style = "background-color: white">
<marquee>
View and modify data by clicking on the links above..
</marquee>
</div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>