<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<div class="row">
	<br> <br>
	<div class="col-sm-offset-4 col-sm-4">
		<c:url var="formType" value="/login" />
		<form method="POST" action="${formType}">
			<div class="form-group">
				<label for="username">Username: </label> <input type="text"
					id="username" name="username" placeHolder="Username"
					class="form-control" />
			</div>
			<div class="form-group">
				<label for="password">Password: </label> <input type="password"
					id="password" name="password" placeHolder="Password"
					class="form-control" />
			</div>
			<input type="hidden" name="destination" value="${param.destination}"><br>
			<div class="login-btn">
				<button type="submit" class="btn btn-default">Login</button>
			</div>
		</form>
	</div>
</div>