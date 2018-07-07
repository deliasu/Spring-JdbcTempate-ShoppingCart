<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<c:choose>
<c:when test="${not empty allbooks}">
<div class="container-fluid" style="background-color: white">
<table class="table-bordered table-hover" style="width: 5000">
<thead>
<tr>
<th>ID</th>
<th>ISBN</th>
<th>Title</th>
<th>Category</th>
<th>Language</th>
<th>Author</th>
<th>Publisher</th>
<th>PubDate</th>
<th>Brief Desc</th>
<th>Detailed Desc</th>
<th>Edition</th>
<th>Image</th>
<th>Cost Price</th>
<th>Selling Price</th>
<th>Paid</th>
<th>Inventory</th>
<th><button class="btn btn-success" id="on-edit-mode">ON</button></th>
<th><button class="btn btn-success" id="off-edit-mode">OFF</button></th>
</tr>
</thead>
<tbody>
<c:forEach var="book" items="${allbooks}">
<tr id="row${book.id}">
<td width="50px" id="id${book.id}">${book.id}</td>
<td width="100px" id="isbn${book.id}" class="book-m-inf">${book.isbn}</td>
<td width="270px" id="title${book.id}" class="book-m-inf">${book.title}</td>
<td width="170px" id="category${book.id}" class="book-m-inf">${book.category}</td>
<td width="100px" id="language${book.id}" class="book-m-inf">${book.language}</td>
<td width="150px" id="author${book.id}" class="book-m-inf">${book.author}</td>
<td width="150px" id="publisher${book.id}" class="book-m-inf">${book.publisher}</td>
<td width="150px" id="pubDate${book.id}" class="book-m-inf">${book.pubDate}</td>
<td width="370px" id="briefDesc${book.id}" class="book-m-inf">${book.briefDesc}</td>
<td width="570px" id="detailedDesc${book.id}" class="book-m-inf">${book.detailedDesc}</td>
<td width="50px" id="edition${book.id}" class="book-m-inf">${book.edition}</td>
<td width="100px" id="image${book.id}" class="book-m-inf">${book.image}</td>
<td width="100px" id="cost${book.id}" class="book-m-inf">${book.cost}</td>
<td width="100px" id="price${book.id}" class="book-m-inf">${book.price}</td>
<td width="50px" id="paid${book.id}">${book.paid}</td>
<td width="50px" id="stock${book.id}" class="book-m-inf">${book.stock}</td>
<td><button class="btn btn-warning btn-sm edit" id="edit${book.id}" data-id="${book.id}" >Edit</button></td>
<td><button class="btn btn-danger btn-sm delete" id="delete${book.id}" data-id="${book.id}" title="Delete?">Delete</button></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<script type="text/javascript">
 $(document).ready(function(){
	$("#on-edit-mode").click(function(){
		$(".book-m-inf").attr("contenteditable", true);
	});
	$("#off-edit-mode").click(function(){
		var ed = document.getElementsByClassName("book-m-inf");
		if(ed[0].isContentEditable){
			$(".book-m-inf").attr("contenteditable", false);
		}
	});
	$(".edit").click(function(){
		var id = $(this).attr("data-id");

		var book ={
				 "id" : id,
				 "isbn": $("#isbn" + id).text(),
				 "title":$("#title" + id).text(),
				 "category": $("#category" + id).text(),
				 "language":$("#language" + id).text(),
				 "author": $("#author" + id).text(),
				 "publisher": $("#publisher" + id).text(),
				 "pubDate" : $("#pubDate" + id).text(),
				 "briefDesc" :$("#briefDesc" + id).text(),
				 "detailedDesc" : $("#detailedDesc" + id).text(),
				 "edition" : $("#edition" + id).text(),
				 "image" : $("#image" + id).text(),
				 "cost" :Number($("#cost" + id).text()),
				 "price" :Number($("#price" + id).text()),
				 "paid" :Number($("#paid" + id).text()),
				 "stock" :Number($("#stock" + id).text())
				 
		};
		$.ajax({
			type:"POST",
			contentType : 'application/json; charset=utf-8',
		    dataType : 'json',
			url:"edit-book",
		    data: JSON.stringify(book),
		    success: function(result){
		    	alert("UPDATE SUCCESSFUL!!");
		    },
		    error: function(){
		    	alert("ERROR!! fails to modify");
		    }
		})
	});
	$(document).on("click", "button.delete", function(){
		var aaa = confirm("A Record Will be deleted!!");
		if(aaa == true){
			var id = $(this).attr("data-id");
			$.getJSON("delete-book", {ID: id},
					function(data){
				    $("#row" + id).remove(); 
			});
		}
	});
 });
</script>
</c:when>
<c:otherwise>
<div class="jumbotron">
<p>No Book is System!!</p>
</div>
</c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>