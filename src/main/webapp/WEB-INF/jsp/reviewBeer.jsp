<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
</c:import>

<style type="text/css">
#limit {
margin-left: 80px;
margin-right: 80px;
} 
</style>

<div class="jumbotron"
	style="background-image: url('img/homepage/homepage.jpg')">
	<section class="container-fluid">
		<h2 id="title-text">Review a Beer</h2>
		<p id="featured">Tell us about your favorite brews</p><br>
		<c:url var="formAction" value="/reviewBeer" />

		<script>
	var beers = {};
	<c:forEach var="brewery" items="${breweryMap.keySet()}">
		beers["${brewery.name.replaceAll(" ", "_").replaceAll("-", "_")}"] = [
		<c:forEach var="beer" items="${breweryMap.get(brewery)}">
			{"name": "${beer.name}", "id": ${beer.beerId} },
		</c:forEach>
		];
	</c:forEach>
	</script>

		<form method="POST" action="${formAction}">
			<div id="limit" class="row">
				<div class="form-group col-sm-12">
					<label for="inputName">Brewery Name:</label> <select class="form-control" id="brewerydropdown">
						<c:forEach var="brewery" items="${breweries}">
							<option value="${brewery.breweryId}"data-beer-list="${brewery.name.replaceAll(" ", "_").replaceAll("-", "_")}"><c:out value="${brewery.name}"/></option>
							<c:set var="breweryChoice" value="${brewery}" />
						</c:forEach>
					</select>
				</div>
				<div class="form-group col-sm-12">
					<label for="inputName">Beer Name:</label> <select name="beerId"
						class="form-control" id="beerDropdown">

					</select>
				</div>
				<div class="form-group col-sm-12">
					<label for="description">Description:</label>
					<textarea name="description" class="form-control" rows="7"
						id="comment"></textarea>
				</div>
				<div class="form-group col-sm-4">
					<label for="rating">Rating:</label> <select name="rating"
						class="form-control" id="rating">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
					</select>
				</div>
			</div><br><br>
			<input type="hidden" value="${currentUser.userId}" name="userId" />
			<div id="limit" class="row">
				<div class="col-md-2">
					<button type="submit" class="btn btn-default">Submit
						Review</button>
				</div>
			</div>
		</form>

		<script>
		$('#brewerydropdown').on('change', function(event) {
			var beerList = beers[$(this).find(':selected').data('beer-list')];
			$('#beerDropdown').empty();
			for(var beer of beerList) {
				var option = $('<option>').text(beer.name).val(beer.id);
				$('#beerDropdown').append(option);
			}
		});
	</script>
	</section>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />