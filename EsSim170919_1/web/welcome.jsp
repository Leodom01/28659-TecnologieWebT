<%@ page errorPage="../errors/failure.jsp"%>
<%@ page import="it.unibo.tw.beans.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
	<head>
		<title>Javascript, DOM and DHTML events</title>
		<meta name="Author" content="Leodom01">
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<script type="text/javascript" src="scripts/utils.js"></script>
		<!--  <meta http-equiv="Refresh" content= "0; URL=stillAServlet"/>  -->
		<script type="text/javascript">
		
			var ALBERGHI = 4;
			function getPrice(){
				if(document.forms[0].getElementsByName("albergo")[0].value < 0 || document.forms[0].getElementsByName("albergo")[0].value > ALBERGHI){
					alert("Seleziona un numero entro"+ALBERGHI);
					return;
				}
				var date1 = document.forms[0].getElementsByName("date_start")[0].value;
				var date2 = document.forms[0].getElementsByName("date_end")[0].value;
				var currentDate = 200;
				if(date1<currentDate || date1>date2){
					alert("Le date non vanno bene");
					return;
				}
				document.forms[0].submit();
			}
		</script>
	</head>
	<body>
		<jsp:useBean id="lookingAt" class="it.unibo.tw.beans.DatiReader" scope="application"/>
		
		<form action="PriceRequest" method="post" id="dataForm">
			<p>Seleziona albergo:</p>
			<% 
			for(String current : datiReader.getIDs()){
				%><input type="radio" name="albergo" value="<%=current%>"><%=current%><br/><%
			}
			%>
			<p>Seleziona date</p>
			<input type="text" name="date_start" placeholder="Starting date [1-365]">
			<input type="text" name="date_end" placeholder="Ending date [1-365]" onfocusout="getPrice()">
		</form>
	<%
		if(request.getAttribute("price") != null){
			%>
			<p>Prezzo finale: <%=request.getAttribute("price")%></p>
			<form action="Book" method="post" id="bookForm">
				<input type="radio" name="book" value="book">
				<input type="radio" name="forget" value="forget">
				<input type="submit" value="invia">
			</form>
			<%
		}
	
		if(request.getAttribute("booking") != null && request.getAttribute("booking").equals("successful")){
			%><p>Complimenti prenotazione eseguita!</p><%
		}else if(request.getAttribute("booking") != null && request.getAttribute("booking").equals("unsuccessful")){
			%><p>Prenotazione non eseguita!</p><%
		}
	%>
	
	</body>
</html>
