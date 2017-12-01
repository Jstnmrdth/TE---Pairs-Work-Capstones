<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="container">
	<h2 class="user-dashboard">Add Your Beer</h2>
	<c:url var="formAction" value="/user/${currentUser.username}/addBeer" />
	
<form method="POST" action="${formAction}">
	<div class="form-row">
			<div class="user-dashboard">
					<p style="font-size: 40px">
						<c:out value="${currentUser.brewery.name}"/>	Brewery
					</p>
			</div>
			<div class="form-group col-md-6">
				<label for="inputBeerName">Beer Name:</label> 
				<input type="text" class="form-control" name="name" id="inputBeerName" placeholder="Beer Name" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputBeerType">Beer Type:</label> 
				<input type="text" class="form-control" name="beerType" id="inputBeerType" placeholder="Beer Type" required>
			</div>
		<div class="form-group col-md-6">
			<label for="inputAbv">ABV:</label> 
			<input type="text" class="form-control" name="abv" id="inputAbv" placeholder="e.g. 7.0" required>
		</div>
		<div class="form-group col-md-6">
			<label for="inputIbu">IBU:</label> 
			<input type="text" class="form-control" name="ibu" id="inputIbu" placeholder="e.g. 65" required>
		</div>
		<div class="form-group col-md-6">
			<label for="inputGlassType">Glass Type:</label> 
			<input type="text" class="form-control" name="glassType" id="inputGlassType" placeholder="Glass Type" required>
		</div>
			<div class="form-group col-md-6">
				<label for="inputDescription">Description:</label> 
				<textarea class="form-control" name="description" id="inputDescription" placeholder="Description" required></textarea>
			</div>
		<div class="form-group col-md-6 text-center" style="width:100%">
			<input type="hidden" id="hiddenBeerId" name="breweryId" value="${currentUser.brewery.breweryId}"/>
			<button type="submit" class="btn btn-default" data-toggle="confirmation">Add Beer</button>
		</div>
	</div>
		
</form>


</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />
