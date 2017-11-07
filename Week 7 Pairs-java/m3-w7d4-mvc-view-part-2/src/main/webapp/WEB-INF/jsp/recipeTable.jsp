<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="/WEB-INF/jsp/header.jsp">
	<c:param name="pageTitle" value="Recipe Table View"/>
</c:import>

    <section id="main-content">
	<h2>Recipes</h2>
      <table cellspacing="0"> 
      	<tr>
      		<th></th>
      		<c:forEach var="recipe" items="${recipes}">
      			<c:url var="recipeUrl" value="/recipeDetails">
    					<c:param name="recipeId" value="${recipe.recipeId}"/>
    				</c:url>
      			<td>
      				<a href="${recipeUrl}">
      					<img src="img/recipe${recipe.recipeId}.jpg" class="photo"/>
      				</a>
      			</td> 
      		</c:forEach>
      	</tr>
      	<tr>
      		<th> Name </th>
      		    <c:forEach var="recipe" items="${recipes}" varStatus="Loop">
      		    		<c:url var="recipeUrl" value="/recipeDetails">
    						<c:param name="recipeId" value="${recipe.recipeId}"/>
    					</c:url>
      				<td><a href="${recipeUrl}"><c:out value="${recipe.name}"/></a></td>
      			</c:forEach>
      	</tr>
      	<tr>
      		<th> Type </th>
      		    <c:forEach var="recipe" items="${recipes}" varStatus="Loop">
      				<td><c:out value="${recipe.recipeType}"/></td>
      			</c:forEach>
      	</tr>
      		<tr>
      		<th> Cook Time </th>
      		    <c:forEach var="recipe" items="${recipes}" varStatus="Loop">
      				<td>${recipe.cookTimeInMinutes} min</td>
      			</c:forEach>
      	</tr>
      		<tr>
      		<th> Ingredients </th>
      		    <c:forEach var="recipe" items="${recipes}" varStatus="Loop">
      				<td>${recipe.ingredients.size()} ingredients</td>
      			</c:forEach>
      	</tr>
      		<tr>
      		<th> Rating </th>
      		    <c:forEach var="recipe" items="${recipes}" varStatus="Loop">
      		    <fmt:parseNumber var = "i" integerOnly = "true" type = "number" value = "${recipe.averageRating}" />
      				<td><img src="img/${i}-star.png" class="star"/></td>
      			</c:forEach>
      	</tr>
      </table>

    </section>

<c:import url="/WEB-INF/jsp/footer.jsp"/>