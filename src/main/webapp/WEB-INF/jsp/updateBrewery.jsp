<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="pageTitle" value="Add Brewery" />
</c:import>

<div class="container">

	<h2 class="user-dashboard">Update Brewery Information</h2>
	<p class="lead-center">Tell us about your house of brews</p>
	<c:url var="formAction" value="/updateBrewery" />
	<form method="POST" action="${formAction}">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputName">Brewery Name:</label> 
				<input type="text" value="${currentUser.brewery.name }" class="form-control" name="name" id="inputName" placeholder="Brewery Name" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputDescription">Description:</label> 
				<input type="text" value="${currentUser.brewery.description }" class="form-control" name="description" id="inputDescription" placeholder="Description" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputYearFounded">Year Founded:</label> 
				<input type="text" value="${currentUser.brewery.yearFounded }" class="form-control" name="yearFounded" id="inputYearFounded" placeholder="Year Founded" required>
			</div>
		<div class="form-group col-md-6">
			<label for="inputAddress">Address:</label> 
			<input type="text" value="${currentUser.brewery.address }" class="form-control" name="address" id="inputAddress" placeholder="1234 Main St, City, State, Zip" required>
		</div>
		 <div class="form-group text-center col-md-6" style="width:100%">
		 	<input type="hidden" id="breweryIdHidden" name="breweryId"
					value="${currentUser.brewery.breweryId }" />
			<button type="submit" class="btn btn-default">Update</button>
		 </div>
		</div>
	</form>


</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />



