<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="/WEB-INF/jsp/header.jsp">
	<c:param name="pageTitle" value="Recipe Tile View"/>
</c:import>

    <section id="main-content">
		<h2>Recipes</h2>
       <div class="container">
	       <c:forEach var="recipe" items="${recipes}">
	       
	       	<c:url var="recipeUrl" value="/recipeDetails">
    				<c:param name="recipeId" value="${recipe.recipeId}"/>
    			</c:url>
	       	<fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${recipe.averageRating}" />
	       	
	       	<div class="content">
		       	<a href="${recipeUrl}">
		       		<img src="img/recipe${recipes.indexOf(recipe)}.jpg" class="photo"/>
		       	</a>
		       <div class="subcontent">
		       		<a href="${recipeUrl}">
		       			<h3><c:out value="${recipe.name}"/></h3>
		       		</a>
		       	</div>
		       	<div class="bottom">
		       			<img src="img/${i}-star.png" class="star"/>
		       			<p>${recipe.ingredients.size()} ingredients</p>
		       	</div>
		    </div>
	       </c:forEach>
	   </div>

    </section>

<c:import url="/WEB-INF/jsp/footer.jsp"/>