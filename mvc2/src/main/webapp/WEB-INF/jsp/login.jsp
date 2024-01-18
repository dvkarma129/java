<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Login</title>
<link href="webjars/bootstrap/5.2.3/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


	<div id="container">

		<h1>Login</h1>
		<div class="form">
		<%-- <pre>${error}</pre> --%>
			<form method = "post">
			Name: <input type="text" placeholder="enter email" name="name">
			<br>
			Pass: <input type="text" placeholder="enter password" name="password">
			<br>
			<input type="submit" class = "btn btn-submit">
			</form>
		</div>
	</div>
</body>
</html>