<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Login</title>
<link href="webjars/bootstrap/5.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.topnav {
	width: 100%; background-color : #333;
	overflow: hidden;
	display: flex;
	justify-content: space-between;
	background-color: #333;
}

/* Style the links inside the navigation bar */
.topnav a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

/* Change the color of links on hover */
.topnav a:hover {
	background-color: #ddd;
	color: black;
}

/* Add a color to the active/current link */
.topnav a.active {
	background-color: #04AA6D;
	color: white;
}
</style>
</head>
<div class="topnav">
	<div>
		<a class="active" href="#home">Home</a> <a href="#news">News</a> <a
			href="#contact">Contact</a>
	</div>
	<div id="logout">
		<a href="/logout">Logout</a>
	</div>
</div>
<body>


<div id="container">

		<table class="table table-hover table-dark">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">First</th>
					<th scope="col">Last</th>
					<th scope="col">Handle</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="e" items="${e}">
				<tr>
					<th scope="row">1</th>
					<td>${e.getFirstName()}</td>
					<td>${e.getLastName()}</td>
					<td>${e.getContact()}</td>
				</tr>
				
				</c:forEach>
			</tbody>
		</table>
	</div>  
</body>
</html>