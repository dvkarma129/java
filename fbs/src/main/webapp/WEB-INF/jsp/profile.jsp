<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${f[0].getUserName()}</title>

    <style>
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        } 


        #container {
            width: 100%;
            position: relative;
            background-repeat: no-repeat;
            height: 570px;
            background-image: url("../image/bg1.jpg");
            background-size: cover;
            background-position: center;
            font-size: 1.5vw;
        }

        #form-container {
            width: 40%;
            height: 100%;
            position: absolute;
            border: 1px solid white;
            position: fixed;
            right: 0%;
            background-color: rgba(255, 255, 255, 0.5);
            opacity: 100%;

        }

        #form {
            width: 80%;
            border: 1px solid white;
            /* height: 500px; */
            margin: auto;
            margin-top: 10%;
            /* display: grid; */
            align-items: center;
            padding: 12%;
            background-color: rgba(255, 255, 255, 0.8);

        }

        #form>input {
            /* padding: 1e30%m 5em; */
            width: 100%;
            /* height: 33%; */
            padding: 2%;
            font-size: 1.2vw;
            border: 1px solid #457194;
            outline: none;
            margin-bottom: 6%;
        }

        #form>input:nth-child(13) {
            width: 35%;
            margin-left: 35%;
            margin-top: 3%;
            font-size: 1vw;
            font-weight: 600;
            color: whitesmoke;
            background-color: #457194;
            border-radius: 2px;
            cursor: pointer;

        }

       #form a {
       		margin-left: 30%;
            text-decoration: none;
            background-color: #457194;
            padding: 1.5%;
            font-weight: 600;
            font-size: 1.5vw;
            /* border: 1px solid #4684b2; */
            color: whitesmoke;
            border-radius: 2px;
        }
        
        #form a:hover,#form>input:nth-child(13):hover {
			color: #457194;
			background-color: whitesmoke;
			border: 1px solid #457194;
		}
    </style>
</head>
<%@ include file="homepageNavbar.jsp"%>
<body>
    <div id="container">
        <div id="form-container">
            <form method="post" action="/flight/updateUser" id="form" >
                <input type="text" value="${f[0].getUserName()}" name="userName"> <br>
                <input type="text" value="${f[0].getUserFullName()}" name="userFullName"> <br>
                <input type="text" value="${f[0].getUserContact()}" name="userContact"> <br>
                <input type="text" value="${f[0].getGender()}" name="gender"> <br>
                <input type="date" value="${f[0].getDob()}" name="dob"> <br>
                <input type="email" value="${f[0].getUserGmail()}" name="userGmail" readonly> <br>
                <input type="submit" value="Update"><br>
                <br> 
				
				<!-- <a href="getMyTickets">Ticket History</a> -->
            </form>
        </div>
    </div>
</body>

</html>