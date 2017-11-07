<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<section>
	<div  style="display: block;"id="weightOutput" >
		<h1 style="color: black; text-align: center;">Solar System Geek Forum</h1>
			<c:url value="/forumPost" var="forumpost"/>
			 	<p style="text-align: center;"><a href="${forumpost}">Post A Message</a></p>
	<table>
		<c:forEach var="i" begin="0" end="${posts.size()-1}">
		<tr><td><h2 style="color: black;"> ${posts.get(i).getSubject()}</h2><br>
			   <span style="color: black;">by ${posts.get(i).getUsername()} on ${posts.get(i).getDatePosted()}<br>
			   ${posts.get(i).getMessage()}</span><br>
			</td>
		</tr>
			   
		</c:forEach>
	
	</table>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />