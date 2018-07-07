<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<c:choose>
<c:when test="${not empty order}">
<div class="container-fluid" style="background-color: white; margin-bottom: 50px; margin-top: 20px;">
<table class="table-bordered table-hover" style="width: 100%">
<thead>
<tr>
<th>Order ID</th>
<th>Book ID</th>
<th>Tot Qty</th>
<th>Tot Price</th>
<th>Cust Name</th>
<th>Card Number</th>
<th>Phone</th>
<th>Email</th>
<th>County</th>
<th>Province</th>
<th>City</th>
<th>Address</th>
<th>Payment Status</th>
<th>ship Status</th>
<th><button class="btn btn-success" id="on-edit-mode">ON</button></th>
<th><button class="btn btn-success" id="off-edit-mode">OFF</button></th>
</tr>
</thead>
<tbody>
<c:forEach var ="order" items="${order}">
<tr class="cust-order${order.order.orderId}">
<td>${order.order.orderId}</td>
<td>${order.orderBook.bookId}</td>
<td>${order.orderBook.totalQty}</td>
<td>${order.orderBook.totalPrice}</td>
<td>${order.order.name}</td>
<td>${order.order.cardNumber}</td>
<td>${order.order.phone}</td>
<td>${order.order.email}</td>
<td>${order.order.country}</td>
<td>${order.order.province}</td>
<td>${order.order.city}</td>
<td>${order.order.address}</td>
<td class="order-m-inf pay${order.order.orderId}" data-orderId = "${order.order.orderId}">${order.order.paymentStatus}</td>
<td class="order-m-inf ship${order.order.orderId}" data-orderId = "${order.order.orderId}">${order.order.shipmentStatus}</td>
<td><button class="btn btn-warning btn-sm edit${order.order.orderId} edit" data-orderId = "${order.order.orderId}" >Edit</button></td>
<td><button class="btn btn-danger btn-sm delete${order.order.orderId} delete"  title="Delete?" data-orderId = "${order.order.orderId}">Delete</button></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#on-edit-mode").click(function(){
		$(".order-m-inf").attr("contenteditable", true);
	});
	$("#off-edit-mode").click(function(){
		var ed = document.getElementsByClassName("order-m-inf");
		if(ed[0].isContentEditable){
			$(".order-m-inf").attr("contenteditable", false);
		}
	});
	$(".edit").click(function(){
		var ed = document.getElementsByClassName("order-m-inf");
		if(ed[0].isContentEditable){
			var orderId =$(this). attr("data-orderId");
			$.ajax({
				type: "GET",
				url: "update-pay-ship-status",
				data: {PAY: $(this).parent().prev().prev().text(),
					   SHIP: $(this).parent().prev().text(),
					   ORDERID: orderId},
				success: function(result){
					       alert("UPDATE SUCCESSFUL!!");
				        },
				error: function(){
					    alert("ERROR!!");
				     }
				
			});
		}else{
			alert("NOT ON EDITING MODE!!")
		}
	});
	$(".delete").click(function(){
		var aaa = confirm("DO YOU WANT DELETE");
		if(aaa== true){
			var orderId = $(this).attr("data-orderId");
			//$.getJSON("delete-customer-order", {ID: orderId}, function(data){
			//	$(".cust-order" + orderId).remove();
			//});
			
			$.ajax({
				type: "GET",
				url: "delete-customer-order",
				data: {ID: orderId},
				success: function(result){
					$(".cust-order" + orderId).remove();
				},
				error: function(){
					alert("ERROR- FAIL TO DELETE!!");
				}
			});
		}
	});
});
</script>
</c:when>
<c:otherwise>
<div class="alert alert-warning">
  <strong>Warning!</strong> No Order
</div>

</c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>