<%@ page errorPage="../errors/failure.jsp"%>
<%@ page import="it.unibo.tw.beans.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
	<head>
		<title>Javascript, DOM and DHTML events</title>
		<meta name="Author" content="Leodom01">
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<script>
			function vendiCarta(){
				//Controlla che a carta sia effettivamente sua vedendo la lista
				//Se è sua:
				document.getElementsByName("myForm")[0].submit();
				//Se non è sua:
				//alert("Non puoi vendere una carta non tua!")
			}
			
			function vendiCarta(){
				var valore = parseInt(document.getElementsByName("compraPrezzo")[0].value);
				if(valore === "NaN"){
					alert("inserisci un prezzo intero!");
					return;
				}else{
					document.getElementsByName("vendiForm")[0].submit();
				}
			}
		</script>
	</head>
	<body>
	
	<%
	
		if(((Date)this.getServletContext().getAttribute("dataInizio")).getTime()+30*60*1000 >= System.currentTimeMillis()){
			//partita finita
			RequestDispatcher rqd = this.getServletContext().getRequestDispatcher("/final.jsp");
			rqd.forward(request, response);
		}
		//Caso attesa giocatori
		if(this.getServletContext().getAttribute("dataInizio") == null){
			%><p>In attes che i giocatori rimanenti si uniscano, ogni tanto refreshi la schermata...</p><% 	//Bello aggiugnere refresh di deafult ogni x minuti con meta in HTML, lo faccio dopo se ho tempo
		}else{
			%><p>Le tue carte:</p>
			<ul>
				<%
					Utente currentUser = null;
					for(Utente temp : (ArrayList<Utente>)this.getServletContext().getAttribute("giocatori")){
						if(temp.getSessione().equals(request.getSession())){
							currentUser = temp;
							break;
						}
					}
					StringBuilder ownedCards = new StringBuilder();
					for(Integer card : currentUser.getCarte()){
						%><li>card</li><%
						ownedCards.append(" "+card);
					}
				
				%>
			</ul>
			<form name="myForm" method="post" action="Venditore">
				<input type="text" name="carta" placeholder="Inserisci qui la carta de vendere, non puoi se ce ne sono all'asta!"/>
				<button onClick="vendiCarta();">Clicca qui per vendere</button> 
			</form>
			<%
			//Caso vendita carta
			if(this.getServletContext().getAttribute("venditaCorrente") != null){
				Vendita currentVendita = (Vendita)this.getServletContext().getAttribute("venditaCorrente");
				%>
				<h4>Stiamo vendendo:</h4>
				<p><%=currentVendita.getCarta() %> al prezzo di <%= currentVendita.getPrezzoCorrente()%> scrivi qui sotto per offrire</p>
				<form name="vendiForm" method="post" action="Venditore">
				<input type="text" name="compraPrezzo" placeholder="Inserisci qui la carta de comprare"/>
				<button onClick="compraCarta();">Clicca qui per comprare</button> 
			</form>
				<%
			}
		}
	%>
	
	</body>
</html>
