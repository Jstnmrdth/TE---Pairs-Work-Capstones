<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="formAction" value="/user/new/brewer" />
<div class="container">
	<h2 class="user-dashboard">Add Brewer Account</h2><br>
	<form method="POST" action="${formAction}">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="userName">User Name: </label> <input type="text"
						id="userName" name="userName" placeHolder="User Name"
						class="form-control" />
				</div>
				<div class="form-group">
					<label for="password">Password: </label> <input type="password"
						id="password" name="password" placeHolder="Password"
						class="form-control" />
				</div>
				<div class="form-group">
					<label for="confirmPassword">Confirm Password: </label> <input
						type="password" id="confirmPassword" name="confirmPassword"
						placeHolder="Re-Type Password" class="form-control" />
				</div>
				<div class="form-group">
					<label for="breweryName">Brewery Name:</label> <select
						class="form-control" name="breweryId" required>
						<c:forEach var="brewery" items="${breweries}">
							<option value="${brewery.breweryId}"><c:out
									value="${brewery.name}" /></option>
						</c:forEach>
					</select>
				</div><br>
				<div class="login-btn">
				<button type="submit" class="btn btn-default">Create User</button>
				</div>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</form>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />