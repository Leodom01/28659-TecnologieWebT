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
		<!--  <meta http-equiv="Refresh" content= "0; URL=stillAServlet"/>  -->
	</head>
	<body>
	<p>We're working for you...</p>
	<%
	String text = request.getParameter("text");
	Random rand = new Random();
	
	char toDel = (char) (97+rand.nextInt(26));
	
	text = text.replace(Character.toString(toDel), "");
	Integer finalLength = text.length();
	
	JSONObject jObj = new JSONObject();
	jObj.put("text", text);
	jObj.put("chars", finalLength.toString());
	
	request.setAttribute("result", jObj.toString());
	RequestDispatcher reqd = this.getServletContext().getRequestDispatcher("/welcome.jsp");
	
	reqd.forward(request, response);
	%>
	
	</body>
</html>
