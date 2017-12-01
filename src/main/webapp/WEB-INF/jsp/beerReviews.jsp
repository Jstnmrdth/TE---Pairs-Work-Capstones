<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="fixed"
	style="background-image: url('img/homepage/homepage.jpg')">

<h2 id="title-text">${beer.name} - ${beer.brewery.name}</h2> <br>

<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<h4 style="text-align: center">Top Beers</h4>
		</div>
		<div class="table-bg">
			<table class="table table-hover table-responsive">
				<thead>
					<tr>
						<th scope="col">User</th>
						<th scope="col">Description</th>
						<th scope="col" class="center">Rating</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${reviews}" var="review">
					<c:url var="beerReviews" value="/reviews/${review.beer.name}"><c:param name="beerId" value="${review.beer.beerId }"/> </c:url>
					<tr>
						<th scope="row">
							${review.user.username}
						</th>
						<td>
							${review.reviewDescription}
						</td>
						<td class="center">
							${review.rating}
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
			<div>
			</div>
	</div>
</div> 
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />