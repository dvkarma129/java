<!DOCTYPE html>
<!-- Created By CodingNepal -->
<html lang="en" dir="ltr">
   <head>
   <link rel="icon" type="image/x-icon" href="../image/icooon.PNG">
      <meta charset="utf-8">
      <title>User not found</title>
      <link rel="stylesheet" href="style.css">
   </head>
   <style>
   @import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap');
*{
  margin: 0;
  padding: 0;
  outline: none;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}
body{
  height: 100vh;
  background-size: 400%;
}
#error-page{
  position: absolute;
  top: 10%;
  left: 15%;
  right: 15%;
  bottom: 10%;
  display: flex;
  align-items: center;
  justify-content: center;
  
}
#error-page .content{
  max-width: 600px;
  text-align: center;
}
.content h2.header{
  font-size: 5vw;
  line-height: 1em;
  position: relative;
}

.content p{
  font-size: 1.2em;
  color: #0d0d0d;
}
.content .btns{
  margin: 25px 0;
  display: inline-flex;
}
.content .btns a{
  display: inline-block;
  margin: 0 10px;
  text-decoration: none;
  border: 2px solid #009879;
  color: #009879;
  font-weight: 500;
  padding: 10px 25px;
  border-radius: 25px;
  text-transform: uppercase;
}

   </style>
   
   <body>
      <div id="error-page">
         <div class="content">
            <h2 class="header" data-text="404">
               Opps! <br>User not found
            </h2>
            <p>
               Sorry, E-mail or Password is wrong try login again.
            </p>
            <div class="btns">
               <a href="getLogin">Login</a>
               <a href="getRegister">Register</a>
            </div>
         </div>
      </div>
   </body>
</html>