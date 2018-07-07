<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<div class="payment-info-bk" style="margin-bottom: 100px;">
<form action="create-add-order" method="post">
<fieldset>
<legend>Payment Information</legend>
<table>
<tr>
<td height="50" width="100">Name</td>
<td><input type="text" id="name" name="name" placeholder="Name" class="form-control" required="required"></td>
</tr>
<tr>
<tr>
<td height="50">Card Number</td>
<td><input type="text" id="cardNumber" name="cardNumber" placeholder="card Number" class="form-control" required="required"></td>
</tr>
<tr>
<td height="50">Phone</td>
<td><input type="text" id="phone" name="phone" placeholder="Mobile"  class="form-control" required="required"></td>
</tr>
<tr>
<td height="50">Email</td>
<td><input type="email" id="email" name="email" placeholder="Email"  class="form-control" required="required"></td>
</tr>
<tr>
<td height="50">Country</td>
<td><input type="text" id="country" name="country" placeholder="Country"  class="form-control" required="required"></td>
</tr>
<tr>
<td height="50">Province</td>
<td><input type="text" id="province" name="province" placeholder="Province" class="form-control" required="required"></td>
</tr>
<tr>
<td height="50">City</td>
<td><input type="text" id="city" name="city" placeholder="City" class="form-control" required="required"></td>
</tr>
<tr>
<td height="50">Address</td>
<td><textarea rows="5" cols="50" name="address" id="address" class="form-control" required="required"></textarea></td>
</tr>
<tr>
<td height="50">&nbsp;</td>
<td><input type="submit" class="btn btn-primary" value="Pay Now"/> 
<input type="reset" class="btn btn-warning" value="Reset" style="float: right;"/>
</td>
</tr>
</table>
</fieldset>
</form>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>