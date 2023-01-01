<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Finale</title>
</head>
<body>
	<%
		if(this.getServletContext().getAttribute("cartaVincente") == null){
			int winner = new Random().nextInt(20);
			this.getServletContext().setAttribute("cartaVincente", winner);
			%>La carta vincente è la numero <%=winner%><%
		}else{
			%>La carta vincente è la numero <%=(Integer)this.getServletContext().getAttribute("cartaVincente")%><%
		}
	%>
</body>
</html>