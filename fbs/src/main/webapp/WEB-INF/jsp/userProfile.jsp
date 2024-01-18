<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .profile {
            height: 15%;
            width: 15%;
            margin: auto;
            /* background-color: black; */
        }

        img {
            border-radius: 50%;
            border: 3px solid #54bde2;
            box-shadow: 0px 0px 15px 0px rgba(84,189,226,1);
        }

        .profile-card {
            background-color: #fff;
            border-radius: 8px;
            padding: 5%;
            /* box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); */
            /* box-shadow: 0px 0px 15px 0px rgba(84,189,226,1); */
        }

        .profile-card h2 {
            margin-top: 10px;
            padding-bottom: 15px;
            text-align: center;
            font-size: xx-large;
        }

        .profile-details {
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-gap: 10px;
            margin-bottom: 20px;
            margin-left: 13%;
        }

        .profile-details p {
            margin: 0;
            margin-bottom: 10px;
        }

        .profile-buttons {
            text-align: center;
            margin-top: 20px;
        }

        button {
            padding: 10px 20px;
            margin: 5px;
            font-size: 13px;
            cursor: pointer;
            border-radius: 5px;
            background-color: #54bde2;
            color: #fff;
            border: 1px solid white;
        }

        button:hover {
            background-color: white;
            color: black;
            border: 1px solid #007bff;
        }
    </style>
</head>
<%@ include file="homepageNavbar.jsp"%>
<body>
    <div class="container">
        <div class="profile-card">
            <div class="profile">
                <img src="https://images.megapixl.com/4707/47075259.jpg"
                    height="100%" width="100%">
            </div>
            <h2>${f[0].getUserName()}</h2>
            <div class="profile-details">
                <p><strong>User Name: </strong>${f[0].getUserName()}</p>
                <p><strong>Full Name: </strong>${f[0].getUserFullName()}</p>
                <p><strong>Contact: </strong>${f[0].getUserContact()}</p>
                <p><strong>Gender: </strong>${f[0].getGender()}</p>
                <p><strong>Date of Birth: </strong>${f[0].getDob()}</p>
                <p><strong>Email: </strong>${f[0].getUserGmail()}</p>
            </div>

            <div class="profile-buttons">
            	<a href="getMyTickets"><button id="ticketHistoryBtn">Ticket History</button></a>
                <a href = "getUpdateProfile"><button id="updateProfileBtn">Update Profile</button></a>
            </div>
        </div>
    </div>
</body>

</html>