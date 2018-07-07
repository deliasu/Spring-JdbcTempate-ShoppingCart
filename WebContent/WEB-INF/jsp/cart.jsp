<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<c:choose>
<c:when test="${not empty cart}">
<section class = "cart-items"><div class = "select-all-blck"></div>
<!-- <div class = "select-all-blck"><input type="checkbox" id="selectall"/> Select All</div> -->
<c:forEach var ="cart" items = "${cart}">
<div class = "prod-cart-one" id="cartItem${cart.book.id}">
<div class ="prod-cart-selected">
<input type="checkbox" value="${cart.book.id}" data-checkbox-id = "${cart.book.id}" class="cartitem-each"/>
</div>
<div class = "prod-cart-img-bck">
<a href="product-details?id=${cart.book.id}"><img src="resources/images/${cart.book.image}" width ="90" height="100px"/></a>
</div>
<div class = "prod-cart-briefdesc">
<a href="product-details?id=${cart.book.id}">${cart.book.briefDesc}</a>
</div>
<div class = "prod-cart-prop">
isbn: ${cart.book.isbn}<br/>
pubdate:${cart.book.pubDate}<br/>
category: ${cart.book.category}<br/>
</div>
<div class="prod-cart-unitp">
<strong>&yen;${cart.book.price}</strong>
</div>
<div class="prod-cart-qty">
<input type="button" class = "prod-cart-decrease-qty" data-d="${cart.book.id}" value="-"/>
<input type="text" class="cart-qty" value="${cart.qty}" data-max="${cart.book.stock}"  id="s${cart.book.id}" readonly/>
<input type = "button" class = "prod-cart-increase-qty" data-i="${cart.book.id}" value="+" data-max="${cart.book.stock}"/>
</div>
<div class="prod-cart-totp">
<strong class="price" id = "p${cart.book.id}">&yen;${cart.totalPrice}</strong>
</div>
<div class="prod-cart-delete-and-wish-list">
<span data-idd="${cart.book.id}" class="delete-cart">Delete</span><br/>
<span data-idw="${cart.book.id}" class="add-to-wish-list-cart">Add to Wish List</span><br/>
</div>
</div>
</c:forEach>
<section class="order-prod-bck-wp">
<div class="order-prod-bck">
<div id="order-prod-bck-sq">
Selected Quantity: <strong id="sq-to-pay" style="color: red;">0</strong>
</div>
<div id="order-prod-bck-gt">
Grand Total: <strong id="gt-to-pay" style="color: red;">0</strong>
</div>
<div id="order-prod-bck-pb">
<input type="button" value = "Complete Order" class="btn btn-default" id="pay-btn" disabled="disabled" />
</div>
</div>
</section>
</section>
</c:when>
<c:otherwise>
<section style="background-color: white; padding-top: 50px;">
<div class="container">
<div class="jumbotron">
<h3 style="color:red;">Sorry!!</h3>
<p>Your Shopping Cart is Empty!!</p>
</div>
</div>
</section>
</c:otherwise>
</c:choose>
<script type="text/javascript">
$(document).ready(function(){
	/*
	$("#selectall").on("click", function(){
		if(this.checked){
			$(".cartitem-each").each(function(){
				$(this).attr("checked", true);
			});
		}else {
			$(".cartitem-each").each(function(){
			   $(this).attr("checked", false);
			});	
		}
	});
	$(".cartitem-each").on("click", function(){
		$(".cartitem-each").each(function(){
			if(!this.checked){
				$("#selectall").attr("checked", false);
			}
		});
	}); */
	
	$(".prod-cart-decrease-qty").click(function(){
		var oldValue = $(this).next().val();
		if(oldValue > 1){
			$(this).next().val(Number(oldValue) - 1);
			var qty = oldValue - 1;
			var id = $(this).attr("data-d");
			//make ajax request
			$.getJSON("update-quantity", {ID: $(this).attr("data-d"), 
                QUANTITY: $(this).next().val() },
         function(data){
            for( var index in data){
            	if(data[index].book.id == id){
                    $("#s" + id).val(data[index].qty);
                    $("#p" + id).html("<strong>&yen;</strong>" + data[index].totalPrice.toFixed(2));
            	}
           } 
            
          //now update grandTot-qty
    		var testChecked = false;
    		var checkedId = new Array();
    		var index = 0;
    		$(".cartitem-each").each(function(){
    			if(this.checked){
    				testChecked =  true;
    				checkedId[index] = $(this).val();
    			}
    			index++;
    		}); checkedId = checkedId.filter(Number);
    		if(testChecked){
    			$.ajax({
    				type: "GET",
    				url: "generate-order",
    				data:{ ID: checkedId},
    				success: function(result){
    					for(var index in result){
    						$("#sq-to-pay").text(result[index].grandQty);
    						$("#gt-to-pay").text(result[index].grandTotal.toFixed(2));
    					}
    				},
    				error: function(e){
    					alert("ERROR!!" + e);
    				}
    			});
    		}else{
    			$("#sq-to-pay").text("0");
    			$("#gt-to-pay").text("0");
    		}
    		//End grand update
            
      });
		}
		
	});
	$(".prod-cart-increase-qty").click(function(){
		var oldValue = $(this).prev().val(); 
		if(oldValue < Number($(this).attr("data-max"))){
			$(this).prev().val(Number(oldValue) + 1);
			var id = $(this).attr("data-i"); // NB totapPrice is p.concat(id)
			var qty = $(this).prev().val();  // NB qty is s.concat(id)
			//make ajax request
			
			$.getJSON("update-quantity", {ID: $(this).attr("data-i"), 
				                          QUANTITY: $(this).prev().val() },
			function(data){
				 for( var index in data){
				    if(data[index].book.id == id){
					    $("#s" + id).val(data[index].qty);
					    $("#p" + id).html("<strong>&yen;</strong>" + data[index].totalPrice.toFixed(2));
				    }
				 }  
				 
				//now update grandTot-qty
					var testChecked = false;
					var checkedId = new Array();
					var index = 0;
					$(".cartitem-each").each(function(){
						if(this.checked){
							testChecked =  true;
							checkedId[index] = $(this).val();
						}
						index++;
					}); checkedId = checkedId.filter(Number);
					if(testChecked){
						$.ajax({
							type: "GET",
							url: "generate-order",
							data:{ ID: checkedId},
							success: function(result){
								for(var index in result){
									$("#sq-to-pay").text( result[index].grandQty);
									$("#gt-to-pay").html("&yen;" + result[index].grandTotal.toFixed(2));
								}
							},
							error: function(e){
								alert("ERROR!!" + e);
							}
						});
					}else{
						$("#sq-to-pay").text("0");
						$("#gt-to-pay").text("0");
					}
					//End grand update
				
		    });
			
		}
		
		
	});
	/*
	$(".cart-qty").change(function(){
		var max = Number($(this).next().attr("data-max"));
		var quantity = Number($(this).val());
	});
	*/
	$(".prod-cart-increase-qty").hover(function(){
		var max= $(this).attr("data-max");
		 max = Number(max)
		 if(Number($(this).prev().val())== max){
			 $(this).css({
				"disabled": "disabled" ,
				"cursor" : "not-allowed"
			 });
		 }
		
	}, function(){
		$(this).css({
			"enabled": "enabled" ,
			"cursor" : "pointer"
		 });
	});
	$(".prod-cart-decrease-qty").hover(function(){
		var num= $(this).next().val();
		 num = Number(num)
		 if(num == 1){
			 $(this).css({
				"disabled": "disabled" ,
				"cursor" : "not-allowed"
			 });
		 }
		
	}, function(){
		$(this).css({
			"enabled": "enabled" ,
			"cursor" : "pointer"
		 });
	});
    
});
$(document).ready(function(){
	$(".order-prod-bck-wp").css({
		"position": "fixed",
		"bottom" : "0px"
	});
	$(window).scroll(function(){
		var height = $(window).scrollTop();
		if(height > 500){
			$(".order-prod-bck-wp").css({
				"position": "relative"
			});
		}else{
			$(".order-prod-bck-wp").css({
				"position": "fixed",
				"bottom" : "0px"
			});
		}
	});
});

$(document).ready(function(){
	$(".cartitem-each").change(function(){
		var testChecked = false;
		var checkedId = new Array();
		checkedId = checkedId.filter(Number);
		var index = 0;
		$(".cartitem-each").each(function(){
			if(this.checked){
				testChecked =  true;
				checkedId[index] = $(this).val();
			}
			index++;
		}); checkedId = checkedId.filter(Number);
		if(testChecked){
			$.ajax({
				type: "GET",
				url: "generate-order",
				data:{ ID: checkedId.filter(Number)},
				success: function(result){
					for(var index in result){
						$("#sq-to-pay").text(result[index].grandQty);
						$("#gt-to-pay").html("&yen;" + result[index].grandTotal.toFixed(2));
					}
				},
				error: function(e){
					alert("ERROR!!" + e);
				}
			});
			$("#pay-btn").css({
				"background-color": "red",
				"enabled": "enabled"
			});
			$("#pay-btn").removeAttr("disabled");
		}else{
			$("#pay-btn").css({
				"background-color": "grey",
			});
			$("#pay-btn").attr("disabled", "disabled");
			$("#sq-to-pay").text("0");
			$("#gt-to-pay").text("0");
		}
		
	});
});
//order item(s)
$(document).ready(function(){
	$("#pay-btn").click(function(){
		window.open("pay-order", "_blank");
	});
});
$(document).ready(function(){
	$(".delete-cart").click(function(){
		var id = $(this).attr("data-idd");
		$.ajax({
			type: "POST",
			url: "delete-cart-item",
			data: {ID: Number($(this).attr("data-idd"))},
			success: function(result){
				$("#cartItem" + id).remove();
			},
			error: function(){
				$(this).css({
					"color": "red"
				});
			}
		});
	});
});
</script>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>