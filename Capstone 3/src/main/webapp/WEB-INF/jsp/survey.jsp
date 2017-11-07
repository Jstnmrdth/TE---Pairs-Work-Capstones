<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<div id="alignnavbaronsurvey">
	<h2>Thank you for taking our survey!</h2>
	<p style="font-size:18px">Please complete the information below and click submit to find out the most popular park!</p>
	<br>


<c:url var="survey" value="/survey" />
<form action="${survey}" method="POST">
	Favorite National Park:
		<select name="parkCode" id="favoriteNationalPark" style="width:207px" required>
				<c:forEach var="park" items="${parks}">
	            <option value="${park.parkCode}">${park.parkName}</option>   
	            </c:forEach>
	    </select>
	<br><br>
	
	
		Your email:
		<input type="text" name="emailAddress"  id="yourEmailForm" style="width: 200px"required>

	<br><br>

	State of residence: 
		<select name="state" id="yourStateForm" style="width:207px" required>
			<option value="AL">Alabama</option>
			<option value="AK">Alaska</option>
			<option value="AZ">Arizona</option>
			<option value="AR">Arkansas</option>
			<option value="CA">California</option>
			<option value="CO">Colorado</option>
			<option value="CT">Connecticut</option>
			<option value="DE">Delaware</option>
			<option value="DC">District Of Columbia</option>
			<option value="FL">Florida</option>
			<option value="GA">Georgia</option>
			<option value="HI">Hawaii</option>
			<option value="ID">Idaho</option>
			<option value="IL">Illinois</option>
			<option value="IN">Indiana</option>
			<option value="IA">Iowa</option>
			<option value="KS">Kansas</option>
			<option value="KY">Kentucky</option>
			<option value="LA">Louisiana</option>
			<option value="ME">Maine</option>
			<option value="MD">Maryland</option>
			<option value="MA">Massachusetts</option>
			<option value="MI">Michigan</option>
			<option value="MN">Minnesota</option>
			<option value="MS">Mississippi</option>
			<option value="MO">Missouri</option>
			<option value="MT">Montana</option>
			<option value="NE">Nebraska</option>
			<option value="NV">Nevada</option>
			<option value="NH">New Hampshire</option>
			<option value="NJ">New Jersey</option>
			<option value="NM">New Mexico</option>
			<option value="NY">New York</option>
			<option value="NC">North Carolina</option>
			<option value="ND">North Dakota</option>
			<option value="OH">Ohio</option>
			<option value="OK">Oklahoma</option>
			<option value="OR">Oregon</option>
			<option value="PA">Pennsylvania</option>
			<option value="RI">Rhode Island</option>
			<option value="SC">South Carolina</option>
			<option value="SD">South Dakota</option>
			<option value="TN">Tennessee</option>
			<option value="TX">Texas</option>
			<option value="UT">Utah</option>
			<option value="VT">Vermont</option>
			<option value="VA">Virginia</option>
			<option value="WA">Washington</option>
			<option value="WV">West Virginia</option>
			<option value="WI">Wisconsin</option>
			<option value="WY">Wyoming</option>
		</select>

	<br><br>
	
		Activity level:
		<input type="radio" name="activityLevel" value="inactive" id="activityLevelStyle"> Inactive
		<input type="radio" name="activityLevel" value="sedentary"> Sedentary
		<input type="radio" name="activityLevel" value="active"> Active
		<input type="radio" name="activityLevel" value="extremelyactive"> Extremely Active
		<br><br>
		<div id="centeredSubmit" ><input type="submit" id="styledSubmit"></div> 
	</form>
</div>	