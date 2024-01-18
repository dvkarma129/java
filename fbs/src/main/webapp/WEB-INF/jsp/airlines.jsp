<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Airlines</title>
<script src="https://kit.fontawesome.com/1f3c3cb2ad.js"
	crossorigin="anonymous"></script>

<style>
* {
	margin: 0;
	padding: 0;
}

#container {
	width: 100%;
	/* border: 5px solid black; */
	display: flex;
	justify-content: space-evenly;
	position: relative;
}

#add-form {
	width: 30%;
	border: 1px solid #afa9a9;
	/* padding: 4%; */
	margin: 4%;
	background-color: rgba(255, 255, 255, 0.671);
	margin-left: 10%;
	height: 50%;
}

#form {
	/* border: 1px solid rgb(22, 94, 143); */
	margin: auto;
	/* align-items: center; */
	padding: 10%;
}

#form>h1 {
	font-size: 3vw;
	text-align: center;
	color: rgb(34, 32, 32);
	margin-bottom: 15%;
}

#form>label {
	/* margin-bottom: 55px; */
	font-size: 2vw;
	color: #457194;
}

#form>input {
	width: 96%;
	height: 2.5em;
	margin-top: 3%;
	margin-bottom: 5%;
	padding: 2%;
	font-size: 1.3vw;
	border: 1px solid #afa9a9;
	/* outline: none; */
}

#form>input:nth-child(8) {
	width: 35%;
	margin-left: 33%;
	margin-top: 5%;
	font-size: 1.5vw;
	font-weight: 600;
	color: black;
	border-radius: 10px;
	background-color: #f2f2f2;
	border: 1px solid #afa9a9;
	cursor: pointer;
}

#form>input:nth-child(5):hover {
	background-color: #0b2238;
	color: white;
	cursor: pointer;
}

#show-table {
	width: 65%;
	/* border: 2px solid rgb(27, 26, 26); */
	margin: 4%;
	margin-right: 10%;
	overflow-y: auto;
	height: 469px;
}

#air-table {
	background-color: rgba(255, 255, 255, 0.671);
	border-collapse: collapse;
	font-size: 1.3vw;
	font-family: sans-serif;
	width: 100%;
	margin: auto;
	padding: 10%;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 2%;
	text-align: center;
	border: 1px solid #afa9a9;
}

th {
	background-color: #f2f2f2;
	font-size: 1.5vw;
}

th:nth-child(3), td:nth-child(3) {
	width: 35%;
}
th:nth-child(2), td:nth-child(2), th:nth-child(1), td:nth-child(1) {
	width: 14%;
}

td:nth-child(2) {
	text-align: left;
	line-height: 30px;
}

td:last-child {
	text-align: center;
}

#top-info>div:nth-child(2)>button:hover {
	background-color: rgb(245, 239, 239);
	color: black;
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

.logo {
	height: 60px;
	width: 60px;
}
</style>
</head>
<%@ include file="navbar.jsp"%>
<body>
	<div id="container">

		<div id="add-form">
			<form method="post" action="/Admin/newAirline"
				enctype="multipart/form-data" id="form">
				<h1>New Airline</h1>
				<input type="text" placeholder="Airline name eg: Airasia"
					name="airlineName" required>
					<br>
					 <input type="file"
					placeholder="Airline logo" name="airlineImg"> 
					<input
					type="submit" value="Add">
			</form>
		</div>

		<div id="show-table">
			<table id="air-table">
				<thead>
					<tr>
						<th>Sr.No.</th>
						<th class="logo">Logo</th>
						<th>Airline Name</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${a}">
						<tr>
							<td>${a.getAirlineId()}</td>
							<td>
								<div class="logo">
									<img alt="" src="${a.getAirlineImgPath()}" height="100%"
										width="100%">
								</div>
							</td>
							<td>${a.getAirlineName()}</td>
							<td>
								<div id="action">
									<span><a
										href="getEditAirline?AirlineId=${a.getAirlineId()}"><i
											class="fa-solid fa-pen-to-square"></i></a></span> <span><a
										href="deleteAirline?AirlineId=${a.getAirlineId()}"><i
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