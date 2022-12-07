<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Catalogue"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>
<%@ page import="java.util.List"%>
<%@ page import="it.unibo.tw.web.beans.Cart"%>
<%@ page import="java.io.FileWriter"%>

<!-- metodi richiamati nel seguito -->
<%!
void add(Cart cart, Item item) {

	//Se non c'e item allora crea e aggiungi
	if(!cart.getItems().contains(item)){
		cart.put(item, 1);
	}else{	//Se c'e incrementa
		cart.increaseOrder(item);
	}
}

void remove(Cart cart, Item item){
	int currentCount = cart.getOrder(item);
	
	if(currentCount > 1){
		cart.decreaseOrder(item);
	}else{
		cart.removeItem(item);
	}
}

void removeFromCatalogue(Catalogue cat, Cart cart){
	for(Item temp : cart.getItems()){
		int quantityInCart = cart.getOrder(temp);
		cat.removeQuantity(temp, quantityInCart);
	}
}

void writeToTxt(String fileName, Cart cart) throws Exception{
	FileWriter fwr = new FileWriter(fileName);
	for(Item temp : cart.getItems()){
		fwr.append("Prodotto: "+temp.getDescription()+" quantity: "+cart.getOrder(temp)+"\n");
	}
	fwr.close();
}
%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="pisi79">
		<title>Catalogue JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	

		<%@ include file="../fragments/header.jsp" %>
		<%@ include file="../fragments/menu.jsp" %>
	
		<div id="main" class="clear">

			<jsp:useBean id="catalogue" class="it.unibo.tw.web.beans.Catalogue" scope="application" />
			<jsp:useBean id="currentCart" class="it.unibo.tw.web.beans.Cart" scope="session" />
			
			<%	
			if ( request.getParameter("add") != null && request.getParameter("add").equals("ok") ) {
				String desc = request.getParameter("description");
				Item toAdd = catalogue.getItem(desc);
				int currentCartCount = 0;
				if(currentCart.getItem(desc) == null){
					currentCartCount = -1;
				}else{
					currentCartCount = currentCart.getOrder(toAdd);
				}
				
				if(toAdd != null && currentCartCount<catalogue.getItem(desc).getQuantity()){
					add(currentCart, toAdd);
				}
			}else if ( request.getParameter("remove") != null && request.getParameter("remove").equals("ok") ) {
				Item toRemove = currentCart.getItem(request.getParameter("description"));
				if(toRemove != null && currentCart.getOrder(toRemove)>0){
					remove(currentCart, toRemove);
				}
			}else if ( request.getParameter("checkout") != null && request.getParameter("checkout").equals("ok") ) {
				removeFromCatalogue(catalogue, currentCart);
				writeToTxt("NuovoFile.txt", currentCart);
				currentCart.empty();
			}
			System.out.println(System.getProperty("user.dir"));
					
				
			%>
			
			<div id="left" style="float: left; width: 48%; border-right: 1px solid grey">

				<p>Current catalogue:</p>
				<table class="formdata">
					<tr>
						<th style="width: 31%">Description</th>
						<th style="width: 31%">Price</th>
						<th style="width: 31%">Available quantity</th>
						<th style="width: 7%"></th>
					</tr>
					<% 
					Item[] items = catalogue.getItems().toArray(new Item[0]);
					for( Item anItem : items ){  
					%> 
						<tr>
							<td><%= anItem.getDescription() %></td>
							<td><%= anItem.getPrice() %> &#8364;</td>
							<td><%= anItem.getQuantity() %></td>
							<td>
								<a href="?add=ok&description=<%= anItem.getDescription() %>">
								Aggiungi prodotto</a>
							</td>
						</tr>
					<% } %>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>			
			</div>
			
			<div id="right" style="float: right; width: 48%">

				<p>Current cart:</p>
				<table class="formdata">
					<tr>
						<th style="width: 31%">Description</th>
						<th style="width: 31%">Price</th>
						<th style="width: 31%">Quantity</th>
						<th style="width: 7%"></th>
					</tr>
					<% 
					Item[] cartItems = currentCart.getItems().toArray(new Item[0]);
					for( Item anItem : cartItems ){  
					%> 
						<tr>
							<td><%= anItem.getDescription() %></td>
							<td><%= anItem.getPrice() %> &#8364;</td>
							<td><%= currentCart.getOrder(anItem) %></td>
							<td>
								<a href="?remove=ok&description=<%= anItem.getDescription() %>">
								Elimina un articolo</a>
							</td>
						</tr>
					<% } %>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>			
			</div>
			
			<form>
				<button type="submit" name="checkout" value="ok">Checkout!</button>
			</form>
		
			<div class="clear">
				<p>&nbsp;</p>
			</div>
			
		</div>
	
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
