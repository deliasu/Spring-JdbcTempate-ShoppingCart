<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>
<div class="ets-login">
	<div class="login-info">
		<h3>Log In</h3>
		<c:if test="${not empty loginFail}">
		<p Style="color: red; text-align: center;">${loginFail}</p>
		</c:if>
	<form action="customer-login" method="post">
	   <table class="tb-user-input">
		<tr>
			<td width="300px" align="center"><input type="text" name="email" id="emai" placeholder="Email"/></td>
		</tr>
		<tr>
			<td width="300px" align="center"><input type="password" name="password" id="password" placeholder="Password"/></td>
		</tr>
		<tr>
			<td width="300px" align="center" ><button type="submit">LOG IN <i class="fa fa-check-circle-o" style="font-size:17px; color:white; position: absolute; right: 40px;"></i></button></td>
		</tr>
		<tr>
			<td width="300px" align="center"><p><a href="reset-password">Forgot Password?</a></p></td>
		</tr>
	</table>
	</form>
	</div>
	<div class="new-member">
		<div id="new-member-notice">
			New to ETBook? Welcome on Board-<a href="signup">Sign up here!</a><br />Open a free account now!!
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>