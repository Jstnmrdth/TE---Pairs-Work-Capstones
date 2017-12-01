<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<h2 class="user-dashboard">${currentUser.username}'sDashboard</h2>
<br>


<c:if test="${currentUser.role.equals('brewer')}">
	<h2 class="user-dashboard">
		Brewery:
		<c:out value="${currentUser.brewery.name }" />
	</h2>
	<br>
</c:if>
<c:url var="updatePassword"
	value="/user/${currentUser.username}/changePassword" />
<c:url var="addBeer" value="/user/${currentUser.username}/addBeer" />
<c:url var="deleteBeer" value="/user/${currentUser.username}/deleteBeer" />
<c:url value="/addBrewery" var="addBreweryHref" />
<c:url value="/user/${currentUser.username}/brewer/update"
	var="updateBrewery" />
<c:url value="/user/${currentUser.username}/addBrewer"
	var="addBrewerUser" />
<c:url value="/user/deleteAccount" var="deleteAccount" />

<div class="container">
	<div class="row dashboard-buttons">
		<div class="col-xs-offset-5 col-xs-2">
			<form action="${updatePassword}" method=GET id="changePasswordLink">
				<button class="btn btn-default btn-block" type="submit">Update
					Password</button>
			</form>
		</div>
		<!-- BREWER ROLES -->
		<c:if test="${currentUser.role.equals('brewer')}">
			<div class="col-xs-offset-5 col-xs-2">
				<form action="${addBeer}" method=GET id="addBeerLink">
					<button class="btn btn-default btn-block" type="submit">Add
						Beer</button>
				</form>
			</div>
			<div class="col-xs-offset-5 col-xs-2">
				<form action="${deleteBeer}" method=GET id="deleteBeerLink">
					<button class="btn btn-default btn-block" type="submit">Delete
						Beer</button>
				</form>
			</div>
		</c:if>
		<!-- ADMIN ROLES -->
		<c:if test="${currentUser.role.equals('admin')}">
			<div class="col-xs-offset-5 col-xs-2">
				<form action="${addBreweryHref}" method=GET id="addBeerLink">
					<button class="btn btn-default btn-block" type="submit">Add
						Brewery</button>
				</form>
			</div><br>
			<div class="col-xs-offset-5 col-xs-2">
				<form action="${deleteBreweryHref}" method=GET id="deleteBreweryLink">
					<button class="btn btn-default btn-block" type="submit">Delete
						Brewery</button>
				</form>
			</div><br>
			<div class="col-xs-offset-5 col-xs-2">
				<form action="${addBrewerUser}" method=GET id="createBrewerLink">
					<button class="btn btn-default btn-block" type="submit">New
						Brewer Account</button>
				</form>
			</div><br>
			<div class="col-xs-offset-5 col-xs-2">
				<form action="${banUserHref}" method=GET id="banUserLink">
					<button class="btn btn-default btn-block" type="submit">Ban
						User</button>
				</form>
			</div><br>
		</c:if>
		<!-- ----- -->
		<!-- BREWER ROLES -->
		<c:if test="${currentUser.role.equals('brewer')}">
			<div class="col-xs-offset-5 col-xs-2">
				<form action="${updateBrewery}" method=GET id="updateBreweryLink">
					<button class="btn btn-default btn-block" type="submit">Update
						Brewery</button>
				</form>
			</div>

		</c:if>
		<!-- ----- -->
		<br> <br>
		<div class="col-xs-offset-5 col-xs-2">
			<form action="${deleteAccount}" method=POST id="deleteAccount">
				<script>
					$("#deleteAccount").submit(function(event) {
						if (!confirmDelete()) {
							event.preventDefault();
						}
					});
					function confirmDelete() {
						var x = confirm("Are you sure you want to delete your account?");
						if (x)
							return true;
						else
							return false;
					}
				</script>
				<input class="btn btn-default btn-block" type=submit id="submit"
					value="Delete Account"> <input type="hidden"
					id="usernameHidden" name="username"
					value="${currentUser.username }" />
			</form>
		</div>
	</div>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />