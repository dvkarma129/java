<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
    <script src="https://kit.fontawesome.com/487f0ebb06.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Flights</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f0f0f0;
        }

        #body {
            display: flex;
            justify-content: center;
            /* align-items: center; */
            min-height: 100vh;
        }

        #other {
            width: 100%;
            max-width: 1200px;
            padding: 20px;
        }

        .ticket-card {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin: 20px 0;
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            width: 100px;
            height: 100px;
            margin-right: 20px;
        }

        .info {
            flex: 1;
        }

        .info p {
            margin: 5px 0;
        }

        .info h2 {
            color: #54bde2;
        }

        .book-form {
            flex: 0.5;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .book-form select {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
        }

        .book-form label {
            font-size: 18px;
        }

        .book-form button {
            padding: 10px 30px;
            background-color: #54bde2;
            border: none;
            color: white;
            border-radius: 3px;
            cursor: pointer;
            right: 0%;
        }

        .book-form button:hover {
            background-color: #0f525c;
        }
    </style>
</head>
<%@ include file="homepageNavbar.jsp"%>
<body>
    <div id="body">
        <div id="other">
            <c:forEach var="f" items="${f[1]}">
                <div class="ticket-card">
                    <div class="logo">
                        <img alt="${f.airline.getAirlineName()}"
                            src="${f.airline.getAirlineImgPath()}"
                            height="100%" width="100%">
                    </div>
                    <div class="info">
                        <p>${f.departureAirport.getAirportName()} --> ${f.arrivalAirport.getAirportName()}</p>
                        <p>Airlines: <b>${f.airline.getAirlineName()}</b></p>
                        <p>Departure-Date: <b>${f.getDepartureDate()}</b></p>
                        <p>Arrival-Date: <b>${f.getArrivalDate()}</b></p>
                        <p>Available Seats: <br><h2>${f.getSeatsAvailable()}/${f.getSeats()}</h2></p>
                    </div>
                    <div class="book-form">
                        <h2> &#8377; ${f.getPrice()} per Seat</h2>
                        <form method="post" action="getBookFlights?FlightId=${f.getFlightId()}&mySeats=${mySeats}">
                            <label for="mySeats">Select No of Seats (Limit 10):</label>
                            <select name="mySeats" id="mySeats">
                                <%
                                for (int i = 1; i <= 10; i++) {
                                %>
                                <option value="<%=i%>"><%=i%></option>
                                <%
                                }
                                %>
                            </select>
                            <br>
                            <button>Book</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
