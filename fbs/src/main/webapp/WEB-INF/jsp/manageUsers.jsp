<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Users</title>
<script src="https://kit.fontawesome.com/1f3c3cb2ad.js"
	crossorigin="anonymous"></script>

<style>
* {
	padding: 0%;
	margin: 0%;
	box-sizing: border-box;
}

#screen {
	width: 100%;
	/* position: fixed; */
	background-repeat: no-repeat;
	background-size: cover;
	background-image: url(./img/bg1.jpg);
	background-position: center;
}

#show-table {
	width: 94%;
	/* border: 2px solid rgb(27, 26, 26); */
	margin: auto;
	overflow-y: auto;
	height: 469px;
	/* overflow: hidden; */
}

#air-table {
	background-color: rgba(255, 255, 255, 0.671);
	border-collapse: collapse;
	font-size: 1.3vw;
	font-family: sans-serif;
	margin: auto;
	width: 100%;
	table-layout: fixed;
}

#air-table thead tr {
	font-weight: bold;
	background-color: #f2f2f2;
       
	border: none;
	border-bottom: 1px solid black;
}

#air-table th, #air-table td {
	text-align: center;
	 border: 1px solid #afa9a9;
	word-wrap: break-word;
	padding: 1%;
}

#air-table thead th:nth-child(1) {
	width: 7%;
}


#air-table thead th:nth-child(4) {
	width: 8%;
}

#air-table thead th:nth-child(5) {
	width: 10%;
}

#air-table thead th:nth-child(3), th:nth-child(7) {
	width: 12%;
}

#action {
	width: 100%;
}

#action>span:nth-child(1) {
	border: 2px solid blue;
	padding: 4%;
	border-radius: 4px;
	color: blue;
}

#action>span:nth-child(2) {
	border: 2px solid red;
	padding: 4%;
	border-radius: 4px;
	color: red;
}
</style>
</head>
<%@ include file="navbar.jsp"%>
<body>


	<div id="screen">
		<div id="show-table">
			<table id="air-table">
				<thead>
					<tr>
						<th>Sr.No</th>
						<th>Full Name</th>
						<th>Contact</th>
						<th>Gender</th>
						<th>D.O.B</th>
						<th>G-Mail</th>
						<th>Balance</th>
						<th>Action</th>
					<tr>
				</thead>
				<tbody>
					<c:forEach var="u" items="${u}">
						<tr>
							<td>${u.getUserId()}</td>
							<td>${u.getUserFullName()}</td>
							<td>${u.getUserContact()}</td>
							<td>${u.getGender()}</td>
							<td>${u.getDob()}</td>
							<td>${u.getUserGmail()}</td>
							<td>${u.getBalance()}</td>
							<td>
								<div id="action">
									<span><a href="getEditUserId?UserId=${u.getUserId()}"><i
											class="fa-solid fa-pen-to-square"></i></a></span> <span><a
										href="deleteUserId?UserId==${u.getUserId()}"><i
											class="fa-solid fa-trash-can" style="color: #ed071e;"></i></a></span>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>