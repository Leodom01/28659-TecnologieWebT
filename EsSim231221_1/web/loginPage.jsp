<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />

<!-- 	<meta http-equiv="Refresh" content= "2; URL=paginaPrincipale"/> -->
<title>Login page</title>
<%//Page used to login in case of complex logins, with groups and admin and non admin %>

<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
</head>
<body>
	<jsp:useBean id="gruppoUtenti" class="it.unibo.tw.beans.GruppoUtenti" scope="application"/>
	<p>
		Please choose your case to begin... &nbsp;
	</p>


	<div>
		<h5>Registration Form</h5>
		<form action="Register" method="post">
			<input type="text" size="20" name="username" placeholder="Insert the mail"><br>
			<input type="password" size="20" name="newPassword" placeholder="Insert the your pwd"><br>
			<input type="submit" value="Register">
		</form>
	</div>
	<div>
		<h5>Login Form</h5>
		<form action="Login" method="post">
			<input type="text" size="20" name="username" placeholder="Insert the mail"><br>
			<input type="password" size="20" name="password" placeholder="Insert the your pwd"><br>
			<input type="submit" value="Login">
		</form>
	</div>

	
</body>
</html>

