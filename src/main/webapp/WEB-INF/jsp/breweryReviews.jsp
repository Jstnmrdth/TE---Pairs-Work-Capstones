<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div class="jumbotron"
	style="background-image: url('img/homepage/homepage.jpg')">
	<h2 id="title-text">
		<style>
@import url('https://fonts.googleapis.com/css?family=Work+Sans');
</style>
		Brew Reviews
	</h2>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h4 style="text-align: center">Top Beers</h4>
			</div>
			<div class="table-bg">
				<table class="table table-hover table-responsive">
					<thead>
						<tr>
							<th scope="col">Beer</th>
							<th scope="col">Description</th>
							<th scope="col">Rating</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${beerReviews}" var="review">
							<c:url var="beerReviews" value="/reviews/${review.beer.name}">
								<c:param name="beerId" value="${review.beer.beerId }" />
							</c:url>
							<tr class="beer-row" data-href="${beerReviews}">
								<th scope="row">${review.beer.name}</th>
								<td>${review.beer.description}</td>
								<td>${review.averageRating}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<c:url value="/reviewBeer" var="reviewBeerHref" />
				<%--PLACE HOLDER VALUE!! --%>
				<div class="review-btn">
					<a href="${reviewBeerHref}" class="btn btn-primary" role="button">Tell
						us what you think!</a>
				</div>
			</div>
			<div></div>
		</div>
	</div>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />