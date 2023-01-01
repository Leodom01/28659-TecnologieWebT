<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.unibo.tw.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="gruppoUtenti" class="it.unibo.tw.beans.GruppoUtenti" scope="application"/>

	<h2>People status</h2>
	
	<%
	for(String currGroup : gruppoUtenti.getGroups()){
		%><p>Gruppo <%=currGroup%>:</p>
		  <table><%
		for(Utente tempUsr : gruppoUtenti.utenti){
			if(tempUsr.gruppo.equals(currGroup)){
				%><tr>
					<td><%=tempUsr.toString() %> </td>
					<td>days to expire date:</td>
					<td> <%= tempUsr.daysBeforeExp() %></td>
				</tr><%
			}
		}
		 %>
		 </table>
		 <hr/>
		 <% 
	}
	%>
	
	
</body>
</html>