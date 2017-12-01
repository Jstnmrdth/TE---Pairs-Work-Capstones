<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="jumbotron"
	style="background-image: url('img/homepage/homepage.jpg')">
	<section class="homePage container-fluid">
		<h2 id="title-text-homepage" class="center">
			<style>
@import url('https://fonts.googleapis.com/css?family=Work+Sans');
</style>
			Monthly Featured Brewery
		</h2>
		<div class="row">
			<c:url var="breweryDetailsPageUrl" value="/breweryDetailsPage">
				<c:param name="breweryId" value="1" />
			</c:url>
			<a href="${breweryDetailsPageUrl}"> ${brewery.name}
				<div class="brewery col-sm-6 col-md-4">
					<h2 id="featured" class="centered">${landGrant.name }</h2>
					<c:url var="breweryImg" value="img/LGTaproom.jpg" />
					<img class="img-responsive img-rounded" src="${breweryImg}" /><br>
				</div>
			</a>
			<c:url var="breweryDetailsPageUrl" value="/breweryDetailsPage">
				<c:param name="breweryId" value="1" />
			</c:url>
			<a href="${breweryDetailsPageUrl}"> ${brewery.name}
				<div class="brewery col-sm-6 col-md-4">
					<h2 id="featured" class="centered">Greens Keeper</h2>
					<c:url var="breweryImg" value="img/Greenskeeper.jpg" />
					<img class="img-responsive img-rounded" src="${breweryImg}" /> <br>
				</div>
			</a>
			<div class="brewery col-sm-6 col-md-4">
				<h2 id="featured" class="centered">Location</h2>
				<div class="iframe-container">
					<div id="map" class="img-rounded"></div>
				</div>
			</div>
		</div>
	</section>
</div>

<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBQJt8sM0_cDCBf16mHs46XUmxHJg22JHY&callback=initMap"></script>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />