<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
    <script src="https://kit.fontawesome.com/487f0ebb06.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tickets Confirmation</title>
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
            align-items: center;
            min-height: 90vh;
        }

        #other {
            width: 100%;
            max-width: 800px;
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

        .confirmation-button {
            text-align: center;
            margin-top: 20px;
        }

        .confirmation-button button {
            padding: 10px 30px;
            background-color: #54bde2;
            border: none;
            color: white;
            border-radius: 3px;
            cursor: pointer;
        }

        .confirmation-button button:hover {
            background-color: #0f525c;
        }
    </style>
</head>
<%@ include file="homepageNavbar.jsp"%>
<body>
    <div id="body">
        <div id="other">
            <div class="ticket-card">
                <div class="logo">
                    <img alt="Air-Asia logo"
                        src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/AirAsia_New_Logo.svg/1200px-AirAsia_New_Logo.svg.png"
                        height="100%" width="100%">
                </div>
                <div class="info">
                    <p>${f[1].departureAirport.getAirportName()} &rarr; ${f[1].arrivalAirport.getAirportName()}</p>
                    <p>Airlines: <b>${f[1].airline.getAirlineName()}</b></p>
                    <p>Departure-Date: <b>${f[1].getDepartureDate()}</b></p>
                    <p>Arrival-Date: <b>${f[1].getArrivalDate()}</b></p>
                    <p>Seats: <br><h2>${f[3]}</h2></p>
                </div>
                <div class="confirmation-button">
                    <h2>${f[2]} Rs /-</h2>
                    <form method="post" action="/flight/bookFlight">
                        <input type="hidden" name="FlightId" value="${f[1].getFlightId()}">
                        <input type="hidden" name="mySeats" value="${f[3]}">
                        <button>Confirm Purchase</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
