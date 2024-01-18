<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Navigation Bar</title>
    <script src="https://kit.fontawesome.com/1f3c3cb2ad.js" crossorigin="anonymous"></script>
    <style>
      .topnav {
        background-color: #333;
        overflow: hidden;
      }

      .topnav a {
        float: left;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 17px;
      }

      .topnav #account {
        float: right;
      }

      .topnav a i {
        margin-right: 5px;
      }

      .topnav a:hover {
        background-color: #0b2238;
      }

      .topnav a.active {
        background-color: #0b2238;
        color: white;
      }
    </style>
  </head>
  <nav>
    <div class="topnav">
      <a class="active" href="getHome"><i class="fas fa-home"></i> Home</a>
      <a href="getAirlines"><i class="fas fa-plane"></i> AirLines</a>
      <a href="getAirports"><i class="fas fa-building"></i> Airports</a>
      <a href="getFlights"><i class="fas fa-paper-plane"></i> Flights</a>
      <a href="getBookedTickets"><i class="fas fa-bookmark"></i> Booked</a>
      <a href="getUsers"><i class="fas fa-users"></i> Users</a>
      <a id="account" href="getLogin"><i class="fas fa-sign-out-alt"></i> Logout</a>
    </div>
  </nav>
</html>
    