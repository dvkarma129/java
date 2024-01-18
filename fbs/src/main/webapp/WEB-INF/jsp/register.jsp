<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>registration</title>

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
            height: 618px;
            background-image: url("../image/bg3.png");
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

        #form>input:nth-child(15) {
            width: 35%;
            margin-left: 35%;
            margin-top: 3%;
            font-size: 1vw;
            font-weight: 600;
            color: whitesmoke;
            background-color: #457194;
            border-radius: 2px;

        }

        #form>p:last-child {
            margin-top: 3%;
            font-size: 1vw;
            text-align: center;

        }

        #form>p:last-child>a {
            text-decoration: none;
            color: #457194;
        }
    </style>
</head>

<body>
    <div id="container">
        <div id="form-container">
            <form method="post" action="/flight/register" id="form" >
            <input type="text" placeholder="Enter userName" name="userName"> <br>
                <input type="text" placeholder="Enter FullName" name="userFullName"> <br>
                <input type="text" placeholder="Enter Contact" name="userContact"> <br>
                <input type="text" placeholder="Enter Gender" name="gender"> <br>
                <input type="date" placeholder="Enter DOB" name="dob"> <br>
                <input type="email" placeholder="Enter G-Mail" name="userGmail"> <br>
                <input type="password" placeholder="Enter Password" name="userPassword"> <br>
                <input type="submit" value="Register">

                <p>Registered Already? <a href="getLogin">Login</a></p>

            </form>
        </div>
    </div>
</body>

</html>