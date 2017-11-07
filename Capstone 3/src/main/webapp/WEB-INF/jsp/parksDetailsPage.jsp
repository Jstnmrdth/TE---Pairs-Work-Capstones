<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

	<section>
		<c:url var="parkImg" value="/img/parks/${park.imageName}"/>
		<div class = "detailPageImageDiv">
			<img class="detailPageImage" src="${parkImg}"/>
		</div>
		
		<div id="parkNameStyle">
			<c:out value="${park.parkName}" />
		</div>
		
		<div class="quoteDiv">
			"<c:out value="${park.inspirationalQuote}" />" -
			<c:out value="${park.inspirationalQuoteSource}"/>
		</div>
		
		<div class="parkInfo">
			<h2 class="parkDetailHeader">Park Details</h2>
			<b>State:</b> <c:out value="${park.state}" /><br>
			<b>Area:</b> <c:out value="${park.acreage}" /> acres<br>
			<b>Elevation:</b> <c:out value="${park.elevationInFeet}" /> feet<br>
			<b>Miles of Trail:</b> <c:out value="${park.milesOfTrail}" /> miles<br>
			<b>Number of Campsites:</b> <c:out value="${park.numberOfCampsites}" /><br>
			<b>Climate:</b> <c:out value="${park.climate}" /><br>
			<b>Year Founded:</b> <c:out value="${park.yearFounded}" /><br>
			<b>Annual Visitor Count:</b> <c:out value="${park.annualVisitorCount}" /><br>
			<b>Number of Animal Species:</b> <c:out value="${park.numberOfAnimalSpecies}" /><br>
			<b>Entry Fee:</b> $<c:out value="${park.entryFee}" /><br>
		</div>
		
		<div id="parkDescription">
			<h2 class="parkDetailHeader">Park Description</h2>
			<c:out value="${park.parkDescription}" />
		</div>
		
		<div id="todaysWeather">
			<h3>Today</h3>
			<c:url var="weatherImg" value="/img/weather/${weather.imageName}"/>
				<div>
				<img class="todayImg" src="${weatherImg}"/>
				</div>
				<b>High:</b> <c:out value="${weather.high}" /><br>
				<b>Low:</b> <c:out value="${weather.low}" /><br>
				<p class="todayRecommendation"><i><c:out value="${weather.recommendation}" /></i></p>
		</div>
		
		<div id="weekForecast">
			<h3>This Week's Forecast</h3>
			<c:forEach var="forecast" items="${fourday}">
				<div class="weekForecast">
					<c:url var="weatherImg" value="/img/weather/${forecast.imageName}"/>
					<div>
						<img class="weekImg" src="${weatherImg}"/>
					</div>
					<b>High:</b> <c:out value="${forecast.high}" /><br>
					<b>Low:</b> <c:out value="${forecast.low}" /><br>
				</div>	
			</c:forEach>		
		</div>
		<div id="tempButtons">
			
			<c:url var="parkDetails" value="/parksDetailsPage"/>
			<c:if test="${temperature == 'fahrenheit'}">
				<form action="${parkDetails}" method="POST">
				<input type="hidden" name="temperature" value="celsius">
				<input type="hidden" name="parkCode" value="${park.parkCode}">
				<button class="temp-btn" type="submit">Celsius</button>
				</form>
			</c:if>
			<c:if test="${temperature == 'celsius'}">
				<form action="${parkDetails}" method="POST">
				<input type="hidden" name="temperature" value="fahrenheit">
				<input type="hidden" name="parkCode" value="${park.parkCode}">
				<button class="temp-btn" type="submit">Fahrenheit</button>
				</form>
			</c:if>
		</div>
		
	</section>
	
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>	