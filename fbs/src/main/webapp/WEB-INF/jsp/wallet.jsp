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
	max-width: 400px;
	width: 100%;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	border-radius: 5px;
	margin: 10% auto;
	text-align: center;
}

h2 {
	margin-top: 0;
	padding-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

label {
	display: block;
	margin-top: 20px;
	font-weight: bold;
}

#current-balance {
	font-size: 20px;
	font-weight: bold;
	margin-top: 10px;
}

input[type="number"] {
	width: 94%;
	padding: 10px;
	margin-top: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

button {
	background-color: #54bde2;
	color: #fff;
	border: 2px solid #54bde2;
	padding: 10px 20px;
	cursor: pointer;
	width: 30%;
	margin-top: 20px;
	border-radius: 5px;
}

button:hover {
	background-color: white;
	border : 2px solid #54bde2;
	border-radius: 5px;
	color: #54bde2;
}
</style>
</head>
<%@ include file="homepageNavbar.jsp"%>
<body>
	<div class="container">
		<h2>Add Money to Wallet</h2>
		<div id="current-balance">Wallet Balance: &#8377; ${f[0].getBalance()}</div>
		<form id="walletForm" method="post" action="/flight/addBalance">
			<label for="amount">Enter Amount:</label> <input type="number"
				id="amount" name="balance" placeholder="Enter amount.." required>

			<button type="submit">Add Money</button>
		</form>
	</div>
</body>

</html>