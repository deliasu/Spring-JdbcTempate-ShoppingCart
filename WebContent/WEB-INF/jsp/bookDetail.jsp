<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<section style="background-color:white;">
<c:if test = "${not empty book}">
<div id = "aaa">
<div class = "prod-detail-img">
	<img src = "resources/images/${book.image}" width = "200px" height = "200px" />
</div>
<div id ="right-bk-inf">
<div class = "prod-detail-briefdesc">
	${book.briefDesc}
</div>
<div class = "prod-detail-title">
	${book.title}
</div>
<div class = "prod-detail-price">
	Price: <strong class="price">&yen;${book.price}</strong>
</div>
<div class = "prod-detail-qty">
	<div class="qty">
	   Quantity:  <input type = "number" name = "quantity" id = "quantity" value="1" min="1" max="${book.stock}" style="height: 30px; width: 50px;" id="quantity" name ="quantity"/>
	</div>
	<div class="total-qty">
	Total Stock: <strong>${book.stock}</strong>
	</div>
	<div style="clear: both; margin-left: 10px; margin-bottom: 20px; color:red;" id = "qty-enough-not"></div>
</div>
     <div class="listandcart-btn-bd">
     	<div id ="list-btn-bd"><button  id="addtowishlist">Add to Wish List</button></div>
     	<div id ="cart-btn-bd"><button  id="addtocart">Add to Cart</button></div>
     </div>
</div>
</div>
<div>
<div class="product-detail-tab">
	<div class="details-desc-tabs">
		<h4 id="com-detail">Commodity Details</h4>
		<h4 id="detail-desc">Detail Description</h4>
	</div>
	<div class="product-detail-tab-c">
		<table class="table bordered">
		   <tr>
				<th>About Book<th>
				<th></th>
			</tr>
			<tr>
				<td>isbn<td>
				<td>${book.isbn}</td>
			</tr>
			<tr>
				<td>Title<td>
				<td>${book.title}</td>
			</tr>
			<tr>
				<td>Category<td>
				<td>${book.category}</td>
			</tr>
			<tr>
				<td>Language<td>
				<td>${book.language}</td>
			</tr>
			<tr>
				<td>Author<td>
				<td>${book.author}</td>
			</tr>
			<tr>
				<td>Publisher<td>
				<td>${book.publisher}</td>
			</tr>
			<tr>
				<td>PubDate<td>
				<td>${book.pubDate}</td>
			</tr>
			<tr>
				<td>Edition<td>
				<td>${book.edition}</td>
			</tr>
			<tr>
				<td>Price<td>
				<td>&yen;${book.price}</td>
			</tr>
		</table>
    </div>
    <div class="p-d-description" id="pdd">
          <p style="color: grey; margin: 20px;">${book.detailedDesc}
          </p>
    </div>
</div>
</div>
</c:if>
</section>
<div style="clear:both"></div>

<script type="text/javascript">
var totcart = 10;
$(document).ready(function(){
	$("#com-detail").click(function(){
		$("#pdd").hide();
		$(".product-detail-tab-c").toggle();
		$(this).css({
			"border": "1px solid red",
			"color": "red"
		});
		$("#detail-desc").css({
			"border": "1px solid blue",
			"color": "orange"
		});
	});
	$("#detail-desc").click(function(){
		$(".product-detail-tab-c").hide();
		$("#pdd").toggle();
		$(this).css({
			"border": "1px solid red",
			"color": "red"
		});
		$("#com-detail").css({
			"border": "1px solid blue",
			"color": "orange"
		});
	});
	//change qty event
	$("#quantity").change(function(){
		var qty = $("#quantity").val();
		if(qty > ${book.stock}){
			$("#qty-enough-not").text("Quantity in Inventory is less !!");
			$("#addtocart").css({
				"disabled": "disabled",
				"cursor" : "not-allowed"
			});
			$("#addtowishlist").css({
				"disabled": "disabled",
				"cursor" : "not-allowed"
			});
		}else{
			$("#qty-enough-not").text("");
			$("#addtocart").css({
				"enabled": "enabled",
				"cursor" : "pointer"
			});
			$("#addtowishlist").css({
				"enabled": "enabled",
				"cursor" : "pointer"
			});
			if(qty==""){
				$("#quantity").val(1);
			}
		}
	 });
	$("#addtocart").click(function(){
		var qty = $("#quantity").val();
		 var bid = ${book.id};
        if(qty <= ${book.stock}){
        	/*totcart = totcart + Number(qty);
        	if(totcart != null && totcart != 0){
        		document.getElementById("update-qty").innerHTML = totcart;
        	}*/
           location.href= "add-to-cart?id=" + bid + "&add-qty=" + qty;
        }
	});
	
	$("#addtowishlist").click(function(){
		var qty = $("#quantity").val();
		 var bid = ${book.id};
        if(qty <= ${book.stock}){
            location.href= "add-to-wish-list?id=" + bid + "&add-qty=" + qty;
        }
	});
});
</script>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>