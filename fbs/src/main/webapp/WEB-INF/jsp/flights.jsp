<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
<script src="https://kit.fontawesome.com/1f3c3cb2ad.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Flights</title>
<style>
* {
	margin: 0%;
	padding: 0%;
	box-sizing: border-box;
}

#screen {
	width: 100%;
	font-family: Arial, Helvetica, sans-serif;
	/* border: 1px solid black; */
	display: flex;
	flex-wrap: wrap;
	justify-content: space-around;
}

#top-info {
	border: 1px solid #afa9a9;
	width: 90%;
	background-color: #f2f2f2;
	margin-top: 3%;
}

#top-info>div:nth-child(1) {
	float: left;
	margin-left: 2%;
	padding: 1%;
}

#top-info>div:nth-child(1)>h3 {
	padding: 5px;
	font-size: 2vw;
}

#top-info>div:nth-child(2) {
	float: right;
	margin-right: 2%;
	padding: 1%;
}

#top-info>div:nth-child(2)>button {
	padding: 6px 10px;
	border-radius: 5px;
	text-decoration: none;
	width: 100%;
	color: black;
	background-color: white;
}

#info-table {
	width: 90%;
	/* border: 2px solid green; */
	overflow-y: auto;
	height: 450px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 1.5%;
	text-align: center;
	border: 1px solid #afa9a9;
}

th {
	background-color: #f2f2f2;
	font-size: 1.5vw;
}

th:nth-child(2), td:nth-child(2) {
	width: 35%;
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
	padding: 8%;
	border-radius: 4px;
	color: blue;
}

#action>span:nth-child(2) {
	border: 2px solid red;
	padding: 8%;
	border-radius: 4px;
	color: red;
}
</style>
</head>
<%@ include file="navbar.jsp"%>
<body>

	<div id="screen">
		<div id="top-info">
			<div>
				<h3>Flight List</h3>
			</div>
			<div>
				<button>
					<a href="getNewFlights"><i class="fa-solid fa-plus"></i>&ensp;New
						Flight</a>
				</button>
			</div>
		</div>
		<div id="info-table">
			<table>
				<thead>

					<tr>
						<th>Sr. No.</th>
						<th>Information</th>
						<th>Seats</th>
						<th>Booked</th>
						<th>Available</th>
						<th>Price</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<%
					int i = 1;
					%>
					<c:forEach var="f" items="${f}">
						<tr>
							<td><%=i%> <%
 i++;
 %></td>
							<td>
								<p>Plane No: ${f.getPlaneNo()}</p> <%-- <p>Airline Name: ${f.airline.getAirlineId()}</p> --%>
								<p>Airline Name: ${f.airline.getAirlineName()}</p>
								<p>Departure-Location:
									${f.departureAirport.getAirportName()}</p>
								<p>Arrival-Location: ${f.arrivalAirport.getAirportName()}</p>
								<p>Departure-Date: ${f.getDepartureDate()}</p>
								<p>Arrival-Date: ${f.getArrivalDate()}</p>
							</td>
							<td>${f.getSeats()}</td>
							<td>${f.getSeatsBooked()}</td>
							<td>${f.getSeatsAvailable()}</td>
							<td>${f.getPrice()}</td>
							<td>
								<div id="action">
									<span><a
										href="getEditAirport?AirportId=${f.getFlightId()}"><i
											class="fa-solid fa-pen-to-square"></i></a></span> <span><a
										href="deleteFlight?FlightId=${f.getFlightId()}"><i
											class="fa-solid fa-trash-can" style="color: #ed071e"></i></a></span>
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
