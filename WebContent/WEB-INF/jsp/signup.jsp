<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<div class="free-sign-up">
	<div class="sign-up-fields">

		<c:if test="${not empty isRegisterd }">
		<p style="color: red;">${isRegisterd}</p>
		</c:if>
		<form action="customer-signup" method="post">
			<table class="tb-user-input">
				<tr>
					<td width="290"><input type="email" name="email" id="email" placeholder="Email" required="required"/></td>
					<td align="left"><span>*</span></td>
				</tr>
				<tr>
					<td width="290"><input type="text" name="name" id="name" placeholder="Name" required="required"/></td>
					<td align="left"><span>*</span></td>
				</tr>
				<tr>
					<td width="290"><input type="password" name="password" id="password" placeholder="Password" required="required"/></td>
					<td align="left"><span>*</span></td>
				</tr>
				<tr>
					<td width="290"><input type="tel" name="phone" id="phone" placeholder="Tel" required="required"/></td>
					<td align="left"><span>*</span></td>
				</tr>
				<tr>
					<td width="290"><input type="text" name="nationality" id="nationality" placeholder="Nationality"/></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="290"><input type="text" name="city" id="city" placeholder="City"/></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
				    <td colspan="2"><button type="submit" style="position: relative;">Start Business <i class="fa fa-check-circle-o" style="font-size:17px; color:white; position: absolute; right: 40px;"></i></button></td>	
				</tr>
				<tr>
				    <td colspan="2" > 
					    By proceeding you agree to our <a href="#">Terms of Service</a>
				    </td>
				</tr>
			</table>
		</form>
	</div>

</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>