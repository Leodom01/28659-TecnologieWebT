<%@ page errorPage="../errors/failure.jsp"%>
<%@ page import="it.unibo.tw.beans.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.json.JSONObject"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
	<head>
		<title>Javascript, DOM and DHTML events</title>
		<meta name="Author" content="Leodom01">
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<script type="text/javascript" src="scripts/utils.js"></script>
		<!--  <meta http-equiv="Refresh" content= "0; URL=stillAServlet"/>  -->
	</head>
	<body>
	<form name="inputForm" action="Ereaser" method="post">
		<input id="myInput"type="text" name="text" maxlength=5000 onkeyup="checkAndSend(document)"/>
	</form>
	
	<%
		if(request.getAttribute("result") != null){
			JSONObject jObj = new JSONObject((String)request.getAttribute("result"));
			String text = (String)jObj.get("text");
			Integer chars = Integer.parseInt((String)jObj.get("chars"));
			
			%>
			<h1>Ecco il risultato</h1>
			<p><%=text%></p>
			<p>Total chars now: <%=chars%></p>
			<% 
		}
		
	%>
	
	</body>
</html>
