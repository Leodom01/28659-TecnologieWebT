<%@ page errorPage="../errors/failure.jsp"%>
<%@ page import="it.unibo.tw.beans.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
	<head>
		<title>Javascript, DOM and DHTML events</title>
		<meta name="Author" content="Leodom01">
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="-1"/>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<!--  <meta http-equiv="Refresh" content= "0; URL=stillAServlet"/>  -->
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<p>Aste attive:</p>
		<ul>
		<%
			for(Asta temp : (List<Asta>)this.getServletContext().getAttribute("allAste")){
				if(temp.endDate.getTime() > System.currentTimeMillis()){
					%><li>Asta per <%=temp.name%></li><%
				}
			}
		%>
		</ul>
		<p>Aste non attive:</p>
		<ul>
		<%
			for(Asta temp : (List<Asta>)this.getServletContext().getAttribute("allAste")){
				if(temp.endDate.getTime() < System.currentTimeMillis()){
					%><li>Asta per <%=temp.name%> vinta da <%=temp.winner%> per euro<%=temp.price%></li><%
				}
			}
		%>
		</ul>
		
	</body>
</html>
