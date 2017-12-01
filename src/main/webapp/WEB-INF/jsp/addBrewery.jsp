<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="pageTitle" value="Add Brewery" />
</c:import>

<div class="user-dashboard">

	<h2>Add Your Brewery!</h2>
	<p class="lead center">Tell us about your house of brews</p>
	<c:url var="formAction" value="/addBrewery" />
	<form method="POST" action="${formAction}">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputName">Brewery Name:</label> 
				<input type="text" class="form-control" name="name" id="inputName" placeholder="Brewery Name" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputDescription">Description:</label> 
				<input type="text" class="form-control" name="description" id="inputDescription" placeholder="Description" required>
			</div>
			<div class="form-group col-md-6">
				<label for="inputYearFounded">Year Founded:</label> 
				<input type="text" class="form-control" name="yearFounded" id="inputYearFounded" placeholder="Year Founded" required>
			</div>
		<div class="form-group col-md-6">
			<label for="inputAddress">Address:</label> 
			<input type="text" class="form-control" name="address" id="inputAddress" placeholder="1234 Main St, City, State, Zip" required>
		</div>
		 <div class="form-group col-md-6">
			<button type="submit" class="btn btn-default">Add Brewery</button>
		 </div>
		</div>
	</form>


</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />













