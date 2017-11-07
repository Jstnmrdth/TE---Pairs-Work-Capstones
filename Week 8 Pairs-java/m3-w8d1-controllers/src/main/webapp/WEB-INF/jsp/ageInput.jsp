<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<section id="weightOutput" >
	<c:url value="/ageOutput" var="ageInput"/>
	<form action="${ageInput}" method="GET">
	<table>
	<tr>
		<td><label style="color: black;" for="planets">Choose a Planet</label></td>
		<td><select id="planets" name="planet">
			<option value="Mercury">Mercury</option>
			<option value="Venus">Venus</option>
			<option value="Earth">Earth</option>
			<option value="Mars">Mars</option>
			<option value="Jupiter">Jupiter</option>
			<option value="Saturn">Saturn</option>
			<option value="Uranus">Uranus</option>
			<option value="Neptune">Neptune</option>
			<option value="Pluto">Pluto</option>
		</select></td>
	</tr>
	<tr>
		<td><label style="color: black;" for="earthage">Enter your Earth Age</label></td>
		<td><input type="text" name="earthage"/></td>
	</tr>
	<tr>
		<td></td>
		<td> <input type="submit" value="Calculate Age"/></td>
	</tr>
	</table>
	</form>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />