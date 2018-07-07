<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<div class="manage-add-prod-bck">
<form action="add-book" method="post">
<fieldset>
<legend>Book Information</legend>
<table>
<tr>
<td height="50">ISBN</td>
<td><input type="text" name="isbn" id="isbn" placeholder="isbn" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td height="50">Title</td>
<td><input type="text" name="title" id="title" placeholder="Title" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<tr>
<td height="50">Category</td>
<td><input type="text" name="category" id="category" placeholder="Category" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td height="50">PubDate</td>
<td><input type="text" name="pubDate" id="pubDate" class="form-control manage-add" placeholder ="YYYY/MM/DD" required="required"/></td>
</tr>
<tr>
<td height="50">Author</td>
<td><input type="text" name="author" id="author" placeholder="Author" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td height="50">Publisher</td>
<td><input type="text" name="publisher" id="publisher" placeholder="Publisher" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td height="50">Language</td>
<td><input type="text" name="language" id="language" placeholder="Language" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td height="50">Edition</td>
<td><input type="text" name="edition" id="edition" placeholder="Edition" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td height="50">BriefDesc</td>
<td><input type="text" name="briefDesc" id="briefDesc" placeholder="Brief Desc" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td height="50">Cost Price</td>
<td><input type="number" name="cost" id="cost" placeholder="Cost Price" min="1" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td height="50">Selling Price</td>
<td><input type="number" name="price" id="price" placeholder="Selling Price" min="1" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td height="50">Total Stock</td>
<td><input type="number" name="stock" id="stock" placeholder="Total Stock" min="1" class="form-control manage-add" required="required"/></td>
</tr>
<tr>
<td>DetailedDesc</td>
<td><textarea rows="5" cols="50" class="form-control" name="detailedDesc" id="detailedDesc" required="required"></textarea></td>
</tr>
<tr>
<tr>
<td  height="60">&nbsp;</td>
<td><input type="submit" value="Add Book" class="btn btn-primary"/><input type="reset" value="Reset" class="btn btn-warning" style="float: right" />
</td>
</tr>
</table>
</fieldset>
</form>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>