<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<div class="jumbotron"
	style="background-image: url('img/homepage/homepage.jpg')">
	
<section class="breweryPage container-fluid">
	<div class="row">
		<c:forEach var="brewery" items="${breweries}" varStatus="loop">
			<div class="brewery col-xs-6 col-sm-4 col-md-3">
			<c:url var="breweryDetailsPageUrl" value="/breweryDetailsPage">
				<c:param name="breweryId" value="${brewery.breweryId}"/>
			</c:url>	
				<h3><a href="${breweryDetailsPageUrl}"> ${brewery.name} </a></h3>
				<div class="container hover10">
				<c:choose>
					<c:when test="${empty brewery.imagePath}">
						<c:url var="breweryImg" value="img/breweries/Default.jpg"/>
					</c:when>
					<c:otherwise>
						<c:url var="breweryImg" value="img/breweries/${brewery.imagePath}"/>
					</c:otherwise>
				</c:choose>
				<a href="${breweryDetailsPageUrl}"> <img class="img-responsive img-rounded image" src="${breweryImg}"/>
				</a>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp"/>
