<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<section>
	<div  style="display: block; height:40 max-width:40"id="weightOutput" >
		<img style="margin-bottom:100px; display:inline-block; height:200px; width:200px;" src="img/${planet.toLowerCase()}.jpg">
		<p style="position:relative; max-width: 40; left: -30px; top:15px; display:inline; color: black; vertical-align:top">If you are ${earthage} years old on Earth,
		 you would be ${String.format("%.2f",planetage)} years old on ${planet}.</p>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />