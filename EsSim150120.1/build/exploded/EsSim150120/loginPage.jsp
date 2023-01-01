<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />

<!-- 	<meta http-equiv="Refresh" content= "2; URL=paginaPrincipale"/> -->
<title>Login page</title>
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
			<input type="text" size="20" name="username" placeholder="Insert the username"><br>
			<input type="password" size="20" name="oldPassword" placeholder="Insert the your pwd (the old one if you want to reset it, if you want a new account be sure that this pw matches the one underneath)"><br>
			<input type="password" size="20" name="newPassword" placeholder="Insert the your pwd (the new one if you want to reset it or retype the new pw for new account creation, I'll use this second pw for new account)"><br>
			<input type="text" size="7" name="group" placeholder="Insert the group"><br>
			<input type="submit" value="Register">
		</form>
	</div>
	<div>
		<h5>Login Form</h5>
		<form action="Login" method="post">
			<input type="text" size="20" name="username" placeholder="Insert the username"><br>
			<input type="password" size="20" name="password" placeholder="Insert the your pwd"><br>
			<input type="text" size="7" name="group" placeholder="Insert the group"><br>
			<input type="submit" value="Login">
		</form>
	</div>

	<div>
		<%
		//Login outcome analysis
			String errLogin = (String) request.getAttribute("loginOutcome");
			request.removeAttribute("loginOutcome");
			if (errLogin != null) {
				switch (errLogin) {
					case "wrongPassword": {
		%>
		<p>
			<b> Errore: password errata, riprova</b>
		</p>
		<% 
	  		 		}break;
	  		 	
	  		  		case "pwExpired": {
	  	%>
		<p>
			<b> Errore: Password scaduta, prosegui con la registrazione</b>
		</p>
		<% 
	  		 		}break;
	  		 	
	  				case "userNotFound": {
  		%>
		<p>
			<b> Errore: Utente non trovato</b>
		</p>
		<% 
  		 			}break;
  		 	
	  				case "wrongGroupOrOther": {
  		%>
		<p>
			<b> Errore: Gruppo non valido o altri problemi</b>
		</p>
		<% 
  		 			}break;
  		 			
	  				case "tooManyAttempts": {
	  	%>
	  	<p>
	  		<b> Errore: Troppi tentativi, locked out baby!</b>
	  	</p>
	  	<% 
	  			  	}break;
  		 	
	  				
	  		  }
	  		}
	  	%>
	</div>
	<div>
		<%
		//Login outcome analysis
			String errRegistration = (String) request.getAttribute("registerOutcome");
			request.removeAttribute("registerOutcome");
			if (errRegistration != null) {
				switch (errRegistration) {
					case "newSuccessFullRegistration": {
		%>
		<p>
			<b> Successo: Nuova registrazione completata, ora puoi loggarti con le nuove credenziali</b>
		</p>
		<% 
	  		 		}break;
	  		 	
	  		  		case "newSuccessPwReplacement": {
	  	%>
		<p>
			<b> Successo: Cambio password eseguito, ora puoi loggarti con le nuove credenziali</b>
		</p>
		<% 
	  		 		}break;
	  		 	
	  				case "failedBecauseOfWrongPw": {
  		%>
		<p>
			<b> Errore: Password errata, ritenta (leggi gli hints nel form di registrazione!)</b>
		</p>
		<% 
  		 			}break;
  		 	
	  				case "failedBecauseNoNeedToReplace": {
  		%>
		<p>
			<b> Errore: La tua password è ancora valida, accedi con quella</b>
		</p>
		<% 
  		 			}break;
  		 			
	  				case "failedWrongGroupOrOther":{
	  	%>
	  	<p>
	  		<b> Errore: Ricontrolla i tuoi dati, potresti aver sbagliato gruppo</b>
	  	</p>
	  	<% 				
	  				}break;
  		 			
	  				case "invalidGroup":{
	  	%>
	  	<p>
	  		<b> Errore: hai inserito un gruppo non esistente</b>
	  	</p>
	  	<% 				
	  				}
  		 	
	  				
	  		  }
	  		}
	  	%>
	</div>
</body>
</html>

