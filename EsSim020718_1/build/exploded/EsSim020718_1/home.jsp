<%@ page errorPage="../errors/failure.jsp"%>
<%@ page import="it.unibo.tw.beans.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.File"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
	<head>
		<jsp:useBean id="counter" class="it.unibo.tw.beans.Counter" scope="session"/>
		<title>Home</title>
		<meta name="Author" content="Leodom01">
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<!--  <meta http-equiv="Refresh" content= "0; URL=stillAServlet"/>  -->
	</head>
	<script>
		function checkedBox(){
			var inputs = document.getElementsByName("myForm")[0].elements;
			var fileNames = [];
			var checkedBox = 0;
			for(var currentIn of inputs){
				if(currentIn.checked){
					checkedBox++
					fileNames.push(currentIn.innerHTML);
				}
			}
			document.cookie = "fileToScan:"+fileNames.join("/")
			if(checkedBox >= 3){
				sendForm(fileNames);
			}
		}

		function sendForm(fileNames) {
				$.ajax({
					url: './CounterServlet',
					data: {
						"files" : fileNames.join(",")
  					},
					dataType: 'type',
					type: 'post',
					aync: 'true',
					success: function(data) {
						document.getElementsByName("servletResult")[0].innerHTML = data.split(",")[0];
						document.getElementsByName("servletTime")[0].innerHTML = data.split(",")[1];
					},
					error: () => { }
				});
				<%
				try{
					String cookieContent;
					String[] filesFromCookie = null;
					for(Cookie cook : request.getCookies()){
						if(cook.getName().equals("fileToScan")){
							filesFromCookie = cook.getValue().split("/");
							break;
						}
					}
					String servletRes = ((Counter)this.getServletContext().getAttribute("counter")).fileToCount(filesFromCookie);
					%>
					document.getElementsByName("beanResult")[0].innerHTML = <%=servletRes.split(",")[0]%>
					document.getElementsByName("beanTime")[0].innerHTML = <%=servletRes.split(",")[1]%>
					<%
				}catch(Exception e){
					System.out.println("Errore: "+ e.getMessage());
					e.printStackTrace();
				}
				%>
			}
	</script>
	
	<body>
	
	<%	
	try{
		//Caricamento della lista di file
		ArrayList<String> files = new ArrayList<>();
		if(session.getAttribute("files") == null){
			//String dir = this.getInitParameter("dir");
			System.out.println(this.getServletContext().getInitParameter("dir"));
			
			String dir = "/file-dir";
			String[] fileList = new File(dir).list();
			ArrayList<String> reallyFiles = new ArrayList<>();
			for(int i = 0; i<fileList.length; i++){
				if(new File(fileList[i]).isFile()){
					reallyFiles.add(fileList[i]);
				}
			}
			session.setAttribute("files", reallyFiles);
			files = reallyFiles;
		}else{
			files = (ArrayList<String>)session.getAttribute("files");
		}
	}catch(Exception e){
		System.out.println("Errore: "+ e.getMessage());
		e.printStackTrace();
	}
		
		//Stampo il form per i file
		%>
		<form name="myForm">
			<% 
				for(String temp : (ArrayList<String>)session.getAttribute("files")){
					%>
					<input type="checkbox" name="<%=temp%>" onclick="checkedBox()">
					<label for="<%=temp%>"><%=temp%></label>
					<%
				}
			%>
		</form>
		<div name="reaultDiv">
			<p name="beanResult"></p>
			<p name="beanTime"></p>
			<p name="servletResult"></p>
			<p name="servletTime"></p>
		</div>
		<%
	%>
	
	</body>
</html>
