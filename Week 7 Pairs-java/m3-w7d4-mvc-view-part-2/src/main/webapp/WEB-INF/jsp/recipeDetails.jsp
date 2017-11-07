<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="/WEB-INF/jsp/header.jsp">
	<c:param name="pageTitle" value="Recipe Detail View"/>
</c:import>

    <section id="main-content" class="detail-view">
    		<div class="detail-top">
    			<div class="left">
	    			<img src="img/recipe${recipe.recipeId}.jpg"/>
	    		</div>
	    		
	    		<div class="right">
	    			<h3><c:out value="${recipe.name}"/></h3>
	    			<p><c:out value="${recipe.recipeType}"/></p>
	    			<p><strong>Cook Time</strong> <c:out value="${recipe.cookTimeInMinutes}"/> minutes</p>
	    			<p><c:out value="${recipe.description}"/></p>
	    		</div>
    		</div>
    		
		<div class="detail-bottom">
			<h4>Ingredients</h4>
			<ul>
				<c:forEach var="ingredient" items="${recipe.ingredients}">
					<li><c:out value="${ingredient.quantity}"/> <c:out value="${ingredient.name}"/></li>
				</c:forEach>
			</ul>
			
			<hr>
			<h4>Preparation</h4>
			
			<ol>
				<c:forEach var="step" items="${recipe.preparationSteps}">
					<li><c:out value="${step}"/></li>
				</c:forEach>
			</ol>
			
		</div>		
    
    </section>

<c:import url="/WEB-INF/jsp/footer.jsp"/>