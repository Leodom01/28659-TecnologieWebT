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
			function showBtn(nomeItem){
				nomeItem = "div"+nomeItem;
				document.getElementsByName(nomeItem)[0].style.display = "block";
			}
			
			function sendMyForm(nomeItem){
				console.log("Ruuning script: "+nomeItem);
				var divName = "div"+nomeItem
				var priceName = "price"+nomeItem
				var theDiv = document.getElementsByName(divName)[0];
				var price = document.getElementsByName(priceName)[0].value;
				if(!isNaN(price.slice(0, -1)) && price[0] !== "-" && price[price.length-1] === String.fromCharCode(8364)){
					document.getElementsByName(priceName)[0].value = price.slice(0, -1);
					theDiv.getElementsByTagName("form")[0].submit();
				}
			}			
			
		</script>
	</head>
	<body>
		<h3>Bentornato signore, ecco a lei le aste</h3>
		<ul>
		<%
			for(Asta item : (List<Asta>)this.getServletContext().getAttribute("allAste")){
				%><li>Asta per: <%=item.name%>
					<button name="btn<%=item.name%>" onClick="showBtn('<%=item.name%>')">Mostra elemento</button>
					<div name="div<%=item.name%>" style="display: none">
						<p>Nome: <%=item.name%></p>
						<p>Prezzo attuale: <%=item.price%></p>
						<p>Vincitore attuale: <%=item.winner%></p>
						<p>Tempo alla scadenza in minuti: <%=(item.endDate.getTime() - System.currentTimeMillis())/(1000*60)%></p>
						<form action="Dealer" method="post">
							<input type="text" name="price<%=item.name%>" placeholder="metti qui la tua offerta" onkeyup="sendMyForm('<%=item.name%>');"/>
							<input type="text" name="item" value="<%=item.name%>" style="display: none"/>
						</form>
					</div>
				   </li>	
				<% 
			}
		%>
		</ul>
	
	</body>
</html>
