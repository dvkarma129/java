<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Flight</title>
    <style>
        form {
            width: 30%;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border: 2px solid black;
            border-radius: 5px;
            font-size: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        .input-field {
            width: 100%;
            padding: 5px;
            margin-bottom: 15px;
            border: 1px solid #070707;
            border-radius: 3px;
            box-sizing: border-box;
        }

        input[type="datetime-local"] {
            width: 100%;
        }

        input[type="submit"] {
            margin-left: 40%;
            width: 20%;
            background-color: rgb(42, 233, 42);
        }
    </style>
</head>
<body>
    <form method="POST" action="/Admin/newFlights"  >
        <label for="select1">Airline:</label>
        <select class="input-field" name="airline.airlineName">
            <c:forEach var="airline" items="${f[0]}">
                <option value="${airline.getAirlineName()}">${airline.getAirlineName()}</option>
            </c:forEach>
        </select>

        <br />

        <label for="inputText">Plane No:</label>
        <input type="number" name="planeNo" class="input-field" />

        <br />

        <label for="select2">Departure Location:</label>
        <select name="departureAirport.airportName" class="input-field">
            <c:forEach var="airport" items="${f[1]}">
                <option value="${airport.getAirportName()}">${airport.getAirportName()}</option>
            </c:forEach>
        </select>

        <label for="select3">Arrival Location:</label>
        <select name="arrivalAirport.airportName" class="input-field">
            <c:forEach var="airport" items="${f[1]}">
                <option  value="${airport.getAirportName()}">${airport.getAirportName()}</option>
            </c:forEach>
        </select>

        <br />

        <label for="dateTime1">Departure Date/Time:</label>
        <input type="datetime-local" name="departureDate" class="input-field" />

        <label for="dateTime2">Arrival Date/Time:</label>
        <input type="datetime-local" name="arrivalDate" class="input-field" />

        <br />

        <label for="inputText1">Seats:</label>
        <input type="text" name="seats" class="input-field" />

        <label for="inputText2">Price:</label>
        <input type="text" name="price" class="input-field" />

        <br />

        <input type="submit" value="Submit" />
    </form>
</body>
</html>
