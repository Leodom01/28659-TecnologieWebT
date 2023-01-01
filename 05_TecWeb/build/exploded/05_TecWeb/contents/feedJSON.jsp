<?xml version='1.0' encoding='ISO-8859-1'?>

<!-- 
NOTA BENE!!!  
Il PROLOGO XML deve SEMPRE essere collocato nella PRIMA RIGA!

Nessun carattere in output fino a questo momento!! 

Usero' ora l'out della jsp per restituire l'xml che ajax si attende!!!!

OSSERVATE IL TRAFFICO HTTP NEL TUNNEL!!!!!

-->

<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="false"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Feed"%>
<%@ page import="it.unibo.tw.web.beans.FeedDb"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!-- librerie di terze parti -->
<!-- n.d. -->

<!-- creazione Java Bean con scope di applicazione -->
<jsp:useBean id="feedDb" class="it.unibo.tw.web.beans.FeedDb"  scope = "application"/>

<%

// QUESTO E' IMPORTANTISSIMO AFFINCHE' L'INTERPRETE JAVASCRIPT RICONOSCA IL CONTENUTO COME XML!!!!!!
response.setHeader("Content-Type","application/xml");
Gson bigG = new Gson();

%>
<!-- segue quindi il codice.... questa volta NON HTML!!!!! ... restituito al client -->

<rss version='2.0'>
	<channel>
		<title><![CDATA[HomePage - LASTAMPA.it]]></title>
		<link>http://www.lastampa.it/redazione/</link>
		<description><![CDATA[lastampa.it - I titoli della Home Page]]></description>
		<lastBuildDate><![CDATA[Mon, 16 May 2011 15:58:35 +0200]]></lastBuildDate>
		<docs></docs>
		<managingEditor></managingEditor>

		<webMaster>teleservizi s.r.l.</webMaster>
		<language>it-IT</language>
		<image>
  			<url>http://www.lastampa.it/common/images/lastampatop.gif</url> 
  			<title>lastampa.it</title> 
  			<link>http://www.lastampa.it/redazione/</link> 
  			<width></width> 
  			<height></height> 
  		</image>

<%
	
	if(request.getParameter("category") != null){
		// recupero il tipo di categoria cercata dai parametri della richiesta
		String category = request.getParameter("category");
		
		// recupero i feed corrispondenti da database
		List<Feed> someFeeds = feedDb.find(category);
		
		// li stampo su out
		for ( Feed feed : someFeeds ) {
			%>
			<item><%=bigG.toJson(feed)%></item>
			<% 
		}
	}else if(request.getParameter("fill") != null && !request.getParameter("fill").trim().equals("")){
		String toFill = request.getParameter("fill");
		String result = feedDb.getCategoriesMatching(toFill);
		
		if(result != null){
			%>
			<inferredString><%=bigG.toJson(result)%></inferredString>
			<% 
		}
	}
%>

	</channel>
</rss>
