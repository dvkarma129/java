<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
<meta charset="ISO-8859-1">
<script src="https://kit.fontawesome.com/1f3c3cb2ad.js"
	crossorigin="anonymous"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
<
style>* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

#container {
	width: 100%;
	/* border: 5px solid black; */
	display: flex;
	justify-content: space-evenly;
	position: relative;
	/* background-repeat: no-repeat;
        background-size: cover;
        background-image: url(../image/bg1.jpg);
        background-position: center; */
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
	font-size: 2.5vw;
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
	width: 45%;
	margin-left: 28%;
	margin-top: 5%;
	font-size: 1.5vw;
	font-weight: 600;
	color: black;
	border-radius: 10px;
	background-color: #f2f2f2;
	border: 1px solid #afa9a9;
	cursor: pointer;
}

#form>input:nth-child(8):hover {
	background-color: #0b2238;
      color: white;
      cursor: pointer;
}
</style>

<title>Add new Airline</title>
</head>
<%@ include file="navbar.jsp"%>
<body>
	<div id=container>
		<div id="add-form">
			<form method="post" action="/Admin/editAirport" id="form">
				<h1>Update Airport</h1>
				<input type="text" name="airportId" value="${ap.getAirportId()}" readonly /><br />
				<input type="text" name="airportName" value="${ap.getAirportName()}" required /><br />
				<input type="text" name="airportLocation" value="${ap.getAirportLocation()}" required /><br /> 
			 <input type="submit" value="Update"/>
			</form>
		</div>
	</div>
</body>
</html>