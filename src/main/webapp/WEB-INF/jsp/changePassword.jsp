<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="user-dashboard">
	<h2>Change Password</h2>
	<c:url var="formAction" value="/user/${currentUser.username}/changePassword" />
	
	<div class="row">
		<div class="col-xs-offset-3 col-xs-6">
			<form action="${formAction}" method=POST id="changePasswordForm">
				<div class="form-group">
					<label for="password">New Password:</label>
					<input type="password" id="password" name="password" class="form-control" />
				</div>
				<div class="form-group">
					<label for="confirmPassword">Confirm Password:</label>
					<input type="password" id="confirmPassword" name="confirmPassword" class="form-control" />
				</div>
				<button type="submit" class="btn btn-default">Update Password</button>
			</form>
		</div>
	</div>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />