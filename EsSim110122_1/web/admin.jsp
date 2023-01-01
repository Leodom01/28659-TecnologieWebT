<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%//Page used by logged admins user=admin pw=admin, you're sent here after landing on welcome.jsp and forward from servlet Home %>
</head>
<body>
	<p>Toatl submissions: <%= (Integer)this.getServletContext().getAttribute("totElab") %></p>
	<p>Last session in the last 30 days</p>	
	<ul>
	<%
	Map<String, Date> sessionMap = ((HashMap<String, Date>)this.getServletContext().getAttribute("last30daysSession"));
	for(String key : sessionMap.keySet()){
		long deltaDays = (new Date().getTime() - sessionMap.get(key).getTime())/(24*60*60*1000);
		if(deltaDays<30){
			%><li>Session ID: <%=key%> ultimo accesso <%=sessionMap.get(key).toString()%></li><%	
		}else{
			sessionMap.remove(key);
		}
		
	}
	%>
	</ul>
	<p>Session correntemente attive</p>	
	<ul>
	<%
	for(String key : sessionMap.keySet()){
		if(session.getSessionContext().getSession(key).isNew())
		%><li>Session ID: <%=key%> attiva da <%=sessionMap.get(key).toString()%></li><%
	}
	%>
	</ul>
</body>
</html>