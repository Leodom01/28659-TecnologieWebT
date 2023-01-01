<%@ page errorPage="../errors/failure.jsp"%>
<%@ page import="it.unibo.tw.beans.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
	<head>
		<title>Javascript, DOM and DHTML events</title>
		<meta name="Author" content="Leodom01">
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<!--  <meta http-equiv="Refresh" content= "0; URL=stillAServlet"/>  -->
		<script type="text/javascript">
			function tryEnableButtons(){
				for(let inputField : document.matrices.getElementsByTagName("input")){
					if(inputField.type === "text" && isNaN(parseInt(inputField.value))){
						document.getElementById("buttonsDiv").style.display = "none";
						return;
					}
				}
				document.getElementById("buttonsDiv").style.display = "block";	
			}
			
			function singleCalc(){
				var cols = 4;
				var rows = 2;
				var xhr = new XMLHttpRequest();
				
				
			}
			
			function multiCalc(){
				
			}
		</script>
	</head>
	<body>
		<form name="matrices">
		<table>
			<p>Matrice A</p>
			<tr>
				<td><input type="text" name="11A" onKeyUp="tryEnableButton()"></td><td><input type="text" name="12A" onKeyUp="tryEnableButton()"></td><td><input type="text" name="13A" onKeyUp="tryEnableButton()"></td><td><input type="text" name="14A" onKeyUp="tryEnableButton()"></td>
			</tr>
			<tr>
				<td><input type="text" name="21A" onKeyUp="tryEnableButton()"></td><td><input type="text" name="22A" onKeyUp="tryEnableButton()"></td><td><input type="text" name="23A" onKeyUp="tryEnableButton()"></td><td><input type="text" name="24A" onKeyUp="tryEnableButton()"></td>
			</tr>
			
		</table>
		<table>
			<p>Matrice B</p>
			<tr>
				<td><input type="text" name="11B" onKeyUp="tryEnableButton()"></td><td><input type="text" name="12B" onKeyUp="tryEnableButton()"></td><td><input type="text" name="13B" onKeyUp="tryEnableButton()"></td><td><input type="text" name="14B" onKeyUp="tryEnableButton()"></td>
			</tr>
			<tr>
				<td><input type="text" name="21B" onKeyUp="tryEnableButton()"></td><td><input type="text" name="22B" onKeyUp="tryEnableButton()"></td><td><input type="text" name="23B" onKeyUp="tryEnableButton()"></td><td><input type="text" name="24B" onKeyUp="tryEnableButton()"></td>
			</tr>
			
		</table>
			<div id="buttonsDiv">
				<button onClick="singleCalc()"> <button onClick="multiCalc()">
			</div>
		</form>
		
	<%
		if(request.getAttribute("result") != null && request.getAttribute("result").equals("ready")){
			//Disegno matrice single Thread
			%>
			<table>
				<p>Matrice resultSingle</p>
				<tr>
					<td><input type="text" name="11" value=<%=request.getAttribute("11")%>></td><td><input type="text" name="12" value=<%=request.getAttribute("12")%>></td><td><input type="text" name="13" value=<%=request.getAttribute("13")%>></td><td><input type="text" name="14" value=<%=request.getAttribute("14")%>></td>
				</tr>
				<tr>
					<td><input type="text" name="21" value=<%=request.getAttribute("21")%>></td><td><input type="text" name="22" value=<%=request.getAttribute("22")%>></td><td><input type="text" name="23" value=<%=request.getAttribute("23")%>></td><td><input type="text" name="24" value=<%=request.getAttribute("24")%>></td>
				</tr>
			
			</table>
			<%
		}
	%>
	
	</body>
</html>
