<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section class="homePage">
	<h2> </h2>
	<c:forEach var="park" items="${parks}" varStatus="loop">
		<div class="park-div">
			<div class="img-div">
				<c:url var="parkImg" value="/img/parks/${park.imageName}"/>
				<c:url var="parkDetail" value="/parksDetailsPage" >
					<c:param name="parkCode" value="${park.parkCode}"/>
				</c:url>
				<a href="${parkDetail}">
				<img class="homePage-image" src="${parkImg}"/>
				</a>
			</div>
			<div class="text-div">
				<p class="park-name"><b>
				<a href="${parkDetail}"><c:out value="${park.parkName}"/>
				</a>
				</b></p>
				<p> <c:out value="${park.parkDescription}"/></p>
			</div>
		</div>
	</c:forEach>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>


