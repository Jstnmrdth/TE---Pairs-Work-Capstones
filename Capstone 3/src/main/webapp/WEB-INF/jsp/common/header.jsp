<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>National Park Weather Service</title>
    <c:url value="/css/nationalparkgeek.css" var="cssHref" />
    <link rel="stylesheet" href="${cssHref}">
</head>

<body>
    <header>
    		<c:url value="/" var="homePageHref" />
    		<c:url value="/survey" var="survey" />
    		<c:url value="/img/logo.png" var="logoSrc" />
        <a href="${homePageHref}">
        		<img src="${logoSrc}" alt="National Park Geek" />
        </a>
        </header>
<div>
    <nav>
        <ul>
		  <li><a href="${homePageHref}">Home</a></li>
		  <li><a href="${survey}">Survey</a></li>                       
        </ul>
    </nav>
</div>