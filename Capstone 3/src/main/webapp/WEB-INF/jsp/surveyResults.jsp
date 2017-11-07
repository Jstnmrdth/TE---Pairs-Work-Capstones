<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

	<h2 style="margin-left:35px">Top 5 National Parks</h2> <!-- put into div -->
	
	<c:forEach var="park" items="${popularParks}">
		<div class="park-div">
			<div class="img-div">
				<c:url var="parkImg" value="/img/parks/${park.key.imageName}"/>
				<img class="homePage-image" src="${parkImg}"/>
			</div>
			<div class="text-div">
				<p class="park-name">
				<c:out value="${park.key.parkName}"/>
				<p> <c:out value="${park.key.parkDescription}"/></p>
				Number of votes today: <c:out value="${park.value}" /> 
				</p>
			</div>
		</div>
	
	</c:forEach>
