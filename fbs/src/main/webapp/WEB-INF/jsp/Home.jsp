<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
    <script src="https://kit.fontawesome.com/487f0ebb06.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flight</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f2f2f2;
        }

        #body {
            display: flex;
            justify-content: center;
            min-height: 90vh;
        }

        #form-body {
            width: 100%;
            background-image: url("http://www.addcovers.com/covers/bqoobouo4mx9chq.jpg");
            height: 400px;
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center;
            padding-top: 3%;
        }

        .form-container {
            background-color: rgba(14, 13, 13, 0.8);
            border-radius: 8px;
            padding: 30px;
            width: 55%;
            margin: auto;
        }

        .form-container h2 {
            text-align: center;
            color: #fff;
            margin-bottom: 20px;
        }

        form {
            background-color: #fff;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        form label {
            color: #333;
            font-size: 1.2em;
        }

        form select, form input[type="date"] {
            padding: 10px;
            width: 100%;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        form .form-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }

        #search-button {
            background-color: #559dcd;
            color: #fff;
            border: 2px solid #559dcd;
            padding: 12px 30px;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            margin: auto;
        }

        #search-button:hover {
            color: #559dcd;
            background-color: whitesmoke;
            border: 2px solid #559dcd;
        }
    </style>
</head>
<%@ include file="homepageNavbar.jsp"%>
<body>
    <div id="body">
        <div id="form-body">
            <div class="form-container">
                <h2>Search Flights</h2>
                <form method="post" action="/flight/getSearchedFlights">
                    <div>
                        <label>From</label>
                        <select name="departureAirport.airportName" class="input-field">
                            <c:forEach var="airport" items="${f[2]}">
                                <option value="${airport.airportName}">${airport.airportName} - ${airport.airportLocation}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label>To</label>
                        <select name="arrivalAirport.airportName" class="input-field">
                            <c:forEach var="airport" items="${f[2]}">
                                <option value="${airport.airportName}">${airport.airportName} - ${airport.airportLocation}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label for="departure-date">Departure Date</label>
                        <input type="date" id="departure-date" name="departureDate">
                    </div>

                    <div class="form-actions">
                        <button id="search-button">
                            <i class="fa-solid fa-plane-departure"></i> Find Flights
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
