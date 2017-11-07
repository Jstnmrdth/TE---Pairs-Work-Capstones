<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<section>
	<div  style="display: block; height:300px"id="weightOutput" >
		<img style="float:left; display:inline-block; height:200px; width:200px;" src="img/${planet.toLowerCase()}.jpg">
		<p style="word-wrap: break-word; position:relative; max-width: 40; top:15px; display:inline; color: black; vertical-align:top">If you are ${earthage} years old on Earth,
		 it would take ${String.format("%.2f",traveltime)} years to reach ${planet} by ${transportation} and you would be ${String.format("%.2f", planetage)} years old when you arrived.</p>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />