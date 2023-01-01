/** 
 * Check for non-blank field in a input field
 */
function nonBlank(myField) {
    	var result = true;
	if ( myField.value == "") {
		alert("Please enter a value for the '" + myField.name + "' field.");
		myField.focus();
		result = false;
	}
	return result;
}
/**
 * Check for "valid" email: not empty, has "@" sign and "."
 */
function validEmail(myField) {
	var result = false;
	if ( nonBlank(myField) ) {
		var tempstr = new String(myField.value);
		var aindex = tempstr.indexOf("@");
		if (aindex > 0 ) {
			var pindex = tempstr.indexOf(".",aindex);
		        if ( (pindex > aindex+1) && (tempstr.length > pindex+1) ) {
				result = true;
			} else {
				result = false;
			}
		}
	}
	if (!result) {
		alert("Please enter a valid email address in the form: yourname@yourdomain.com");
		myField.focus();
	}
	return result;
}

/*
 * Funzione per estrarre il contenuto CDATA presente
 * all'interno di un nodo XML 
 * [ad esempio su <guida>ajax</guida> restituisce solo ajax].
 *
 * Utile a far rimanere leggibile il codice di parsificaXml() 
 */
function leggiContenuto(item, nomeNodo) {
	return item.getElementsByTagName(nomeNodo).item(0).firstChild.nodeValue;
}

/*
 * Funzione che genera una lista XHTML 
 * con gli item presi dal testo RSS (linguaggio basato su xml)
 * ricevuto come argomento xml
 */
function parsificaXml( xml ) {
   
	// variabili di funzione
	var

		// Otteniamo la lista degli item dall'RSS 2.0 di edit
		items = xml.getElementsByTagName("item"),

		// Predisponiamo una struttura dati in cui memorrizzare le informazioni di interesse
		itemNodes = new Array(),

		// la variabile di ritorno, in questo esempio, e' testuale
		risultato = "";

	// ciclo di lettura degli elementi
	for (    var a = 0, b = items.length;    a < b;   a++   ) {
		itemNodes[a] = new Object();
		itemNodes[a].title = leggiContenuto(items[a],"title");
		itemNodes[a].description = leggiContenuto(items[a],"description");
		itemNodes[a].link = leggiContenuto(items[a],"link");
	}

	// non resta che popolare la variabile di ritorno
	// con una lista non ordinata di informazioni

	// apertura e chiusura della lista sono esterne al ciclo for 
	// in modo che eseguano anche in assenza di items
	risultato = "<ul>";

	for( var c = 0; c < itemNodes.length; c++ ) {
		risultato += '<li class="item"><strong>' + itemNodes[c].title +'</strong><br/>';
		risultato += itemNodes[c].description +"<br/>";
		risultato += '<a href="' + itemNodes[c].link + '">approfondisci</a><br/></li>';
	};

	// chiudiamo la lista creata
	risultato += "</ul>";

     // restituzione dell'html da aggiungere alla pagina
     return risultato;

}

/*
 * Crea una richietsa AJAX e ne gestisce il valore di ritorno o gli errori.
 */
function craftAndSendAJAX(theUri, theElement, theXhr) {
    
	theXhr.onreadystatechange = function() { 
		
		theElement.class = "content";
		
		if ( theXhr.readyState === 2 ) {
		    	theElement.innerHTML = "Richiesta inviata...";
		}else if ( theXhr.readyState === 3 ) {
	    		theElement.innerHTML = "Ricezione della risposta...";
		}else if ( theXhr.readyState === 4 ) {
			
			if ( theXhr.status === 200 ) {
				//TODO Se risposta 200
				if ( theXhr.responseXML ) {
					//TODO: Se response XML esiste
				}else {
					//TODO: Se risposta XML non esiste
				}
			}else {
				//TODO Se codice != 200
			}
		}
	
	} 
	// impostazione richiesta asincrona in GET
	try {
		theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	theXhr.send(null);

}

/*
 * Gestiamo l'attesa associando a ogni richiesta AJAX una funzione che
 * a intervalli di tempo prefissati ne verifica il completamento 
 * e che la abortisce in caso di superamento del tempo massimo di attesa.
 * theElement serve per essere modificato al fine di indicare l'abort della chiamata
 */
function gestisciAttesa(ajax, theElement, inizioChiamata, massimaAttesa) {

	// un solo gestore per richiesta
	if (ajax.gestita) {
		// non siamo noi, e' gia qualcun altro. restituiamo il controllo
		return;
	} else {
		// lo faremo noi
		ajax.gestita = true;
	}

	verificaTempoTrascorso = function() {
		// se la richiesta si e' conclusa non eseguo
		if (ajax.readyState == 4) {
			return;
		}

		// ogni chiamata asincrona a questa funzione
		// dovra'� verificare la durata dell'interazione
		// e' necessario quindi ridichiarare la variabile
		// al fine di ottenere il nuovo oggetto Date
		dataChiamata = new Date();

		if ((dataChiamata.getTime() - inizioChiamata) > massimaAttesa) {

			// ... interrompiamo la richiesta ed
			// informarmiamo l'utente di quanto avvenuto.

			// Quindi riassegnamo onreadystatechange ad una
			// funzione vuota, poiche' quest'evento sara'�
			// sollevato chiamando il metodo abort()
			ajax.onreadystatechange = function() {
				if (console) {
					console.info("Richiesta AJAX abortita");
					return;
				}
			}

			// e' possibile a questo punto richiamare il metodo abort
			// ed annullare le operazioni dell'oggetto XMLHttpRequest
			ajax.abort();

			// TODO: Comunica che la funzione è stata abortita

		}

		// se invece il tempo è inferiore al timeout
		else {
			// si richiama questa stessa funzione, con un tempo
			// che non dovrà essere ne alto ne troppo basso.
			setTimeout(verificaTempoTrascorso, 100);
		}
	}

	// definita la funzione non resta che avviarla
	verificaTempoTrascorso();

}

/**
 * document.getElementById(id) does the same stuff
 */
function myGetElementById(idElemento) {

	// elemento da restituire
	var elemento;

	// se esiste il metodo getElementById questo if sara'� 
	// diverso da false, null o undefined
	// e sara'� quindi considerato valido, come un true
	if ( document.getElementById )
		elemento = document.getElementById(idElemento);

	// altrimenti e' necessario usare un vecchio sistema
	else
		elemento = document.all[idElemento];

	// restituzione elemento
	return elemento;

} 

/** 
 * Ottiene un parametro indicato nell'URL della pagina
 */
function myGetRequestParameter ( parameterName ) {

	// variabili
	// estraiamo i parametri di get dalla uri della pagina
	var queryString = window.top.location.search.substring(1);

	// Add "=" to the parameter name (i.e. parameterName=value)
	// torna utile nello split della query per cercare il parametro voluto
	var parameterName = parameterName + "=";
		
	if ( queryString.length > 0 ) {

		// Find the beginning of the string
		begin = queryString.indexOf ( parameterName );

		// If the parameter name is not found, skip it, otherwise return the value
		if ( begin != -1 ) {

			// Add the length (integer) to the beginning
			begin += parameterName.length;

			// Multiple parameters are separated by the "&" sign
			end = queryString.indexOf ( "&" , begin );
	
			if ( end == -1 ) {
				end = queryString.length
			}// if ( ! end )

			// Return the string (unescapes special characters such as & / = etc...)
			return unescape ( queryString.substring ( begin, end ) );
		} // if ( begin )

		// Return "null" if no parameter has been found
		return "null";

	} // if ( querystring )

}

function checkAndSend(document){
	var val = document.getElementsByName("inputForm")[0].myInput.value;
	if(val.length >= 50 ||
	val[val.length-1] === String.fromCharCode(8364)){
		document.getElementsByName("inputForm")[0].submit();
	}
}









