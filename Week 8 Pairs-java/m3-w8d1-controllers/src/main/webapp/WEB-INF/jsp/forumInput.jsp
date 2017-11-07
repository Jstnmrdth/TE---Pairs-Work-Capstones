<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<section id="forumOutput" >
	<c:url value="/forumPost" var="forumInput"/>
	<form action="${forumInput}" method="POST">
		<div  style="display: block;"id="weightOutput" >
			<table>
				<tr>
					<td><label style="color: black; font-size: smaller;" for="username">User Name</label></td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td><label style="color: black;" for="subject">Subject</label></td>
					<td><input type="text" name="subject"/></td>
				</tr>
				<tr>
					<td><label style="color: black;" for="message">Message</label></td>
					<td><input type="text" name="message"/></td>
				</tr>
				<tr>
					<td></td>
					<td> <input type="submit" value="Submit"/></td>
				</tr>
			</table>
		</div>
	</form>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />