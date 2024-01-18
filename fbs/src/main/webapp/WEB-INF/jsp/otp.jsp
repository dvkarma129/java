<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>

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
	height: 625px;
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
	margin-top: 30%;
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

#form>input:nth-child(5) {
	width: 35%;
	margin-left: 35%;
	margin-top: 3%;
	font-size: 1vw;
	font-weight: 600;
	color: whitesmoke;
	background-color: #457194;
	border-radius: 2px;
}

#form>input:nth-child(5):hover {
	color: #457194;
	background-color: whitesmoke;
	border-radius: 2px #457194 ;
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
			<form id="form" method="post" action="/flight/otpVerification">
				<label>Enter OTP sent on e-mail  ${f[0].getUserGmail()}</label>
				<input type="text" placeholder="Enter otp" name="userOtp" required="required">
				<br> 
				<input type="submit"
					value="Confirm">

			</form>
		</div>
	</div>
</body>

</html>