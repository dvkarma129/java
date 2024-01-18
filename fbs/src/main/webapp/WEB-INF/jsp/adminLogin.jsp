<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<style>
 @import url('https://fonts.googleapis.com/css2?family=Dancing+Script&display=swap');
  
*{
    padding: 0%;
    margin: 0%;
    box-sizing: border-box;
}


#container{
    width: 100%;
    position: relative;
    background-repeat: no-repeat;
    height: 1000px;
    background-image: url("https://www.superiorwallpapers.com/images/lthumbs/2016-06/11876_Airplane-wing-in-the-sky-wonderful-landscape.jpg");
    background-size: cover;
    background-position: center;
}

#form-container{
    width: 40%;
    height: 100%;
    position: absolute;
    border: 1px solid white;
    position: fixed;
    right: 0%;
    background-color: rgba(255, 255, 255, 0.5);
    opacity: 100%;

}
#form{
    width: 80%;
    border: 1px solid white;
    /* height: 500px; */
  margin: auto;
  margin-top: 20%;
 /* display: grid; */
 align-items: center;
 padding: 14%;
 background-color: rgba(255, 255, 255, 0.8);

}
#form>label{
    font-size: large;
    color:#457194;
}

#form>input{
   /* padding: 1e30%m 5em; */
    width: 100%;
    height: 2.5em;
    padding: 2%;
   font-size: 1em;
   border: 1px solid #457194;
  outline: none;
}

#form>input:nth-child(9){
    width: 35%;
    margin-left:35% ;
    margin-top: 10%;
    font-size: 15px;
    font-weight: 600;
    color: whitesmoke;
    background-color: #457194;
     border-radius: 2px;
   
}

#form>p:last-child{
    margin-top: 6%;
    font-size: small;
    text-align: center;

}
#form>p:last-child>a{
    text-decoration: none;
    color: #457194;
}
</style>
</head>
<body>
 <div id="container">
       <div id="form-container">
        <form id="form" method="post" action="/Admin/login">
             <label for="">G-Mail</label> <br>
             <input type="text" placeholder="Enter your name" name="adminGmail" required> <br>
             <label for="">Password</label> <br>
             <input type="password" placeholder="Enter your password" name="adminPassword" required>  <br>
             <input type="submit" value="Login">

             <p>Haven't registered yet? <a href="registration.html">Register here</a></p>

        </form>
       </div>

    </div>
</body>
</html>