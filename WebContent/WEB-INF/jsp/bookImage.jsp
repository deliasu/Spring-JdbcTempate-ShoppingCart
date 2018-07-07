<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<c:choose>
<c:when test="${not empty sessionScope.addBookInfo}">
<div class="manage-add-prod-bck">
<form action="add-book-image-and-save" method="post" enctype="multipart/form-data">
<fieldset>
<legend>Add Image</legend>
<table>
<tr>
<td height="50">Upload Image</td>
<td><input type="file" name="image" id="image" class="form-control"/></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value ="Submit" class="btn btn-primary"/></td>
</tr>
</table>
</fieldset>
</form>
</div>
</c:when>
<c:otherwise>
<div class="jumbotron">
<h3>Not Allowed!!</h3>
</div>
</c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>