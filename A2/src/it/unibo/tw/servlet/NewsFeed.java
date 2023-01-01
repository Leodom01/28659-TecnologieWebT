package it.unibo.tw.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class NewsFeed extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Cookie getCookie(HttpServletRequest req, String cookieName) {
		for(Cookie temp : req.getCookies()) {
			if(temp.getName().equals(cookieName)) {
				return temp;
			}
		}
		return null;
	}
	
	public void init() {
		try {
			BufferedReader bfr = new BufferedReader(new FileReader("./news.txt"));		//Forse dovrei aggiungere il percorso assoluto della servlet getAbsolutePath ma dovrei prima testarlo
			this.getServletContext().setAttribute("masterFr", bfr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
		
		Map<String, BufferedReader> stream = new HashMap<String, BufferedReader>();
		this.getServletContext().setAttribute("listFr", stream);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//In realtà così leggo solo una news, forse sarebbe meglio avere una mappa con HTTPSession come key e un filereader solo per lei, e quando mi arriva una richiesta vado aprendere il suo reader e leggo una riga
		//Come faccio io lo sto vedendo piu come uno stream ma non mi è troppo chiaro cosa dovrebbe fare il file news.txt
		
		if(this.getCookie(req, "news") != null) {
			BufferedReader bfr = ((Map<String, BufferedReader>)this.getServletContext().getAttribute("listFr")).get(this.getCookie(req, "news").getValue());
			while(!bfr.ready()) {
				
			}
			
			BufferedReader rdr = new BufferedReader(req.getReader());
			String line;
			while((line = rdr.readLine()) != null) {
				bldr.append(line);
			}
			line = bldr.toString();
			
			JSONObject jObj = new JSONObject(line);
			Character toFind = new Character(((String) jObj.get("char")).charAt(0));
			
			String justReade = bfr.readLine();
			
			justReade = rdr.readLine();
			String argomento = justRead.split(":")[0];
			String[] wordsInArg = argomento.split(" ");
			for(String wordOfArg : wordsInArg) {
				if(new Character(wordOfArg.charAt(0)).equals(toFind)) {
					resp.getWriter().append(justRead);
					return;
				}
			}
			return;
		}else {
			long val = System.currentTimeMillis();
			resp.addCookie(new Cookie("news", val.toString()));
			((Map<String, BufferedReader>)this.getServletContext().getAttribute("masterFr")).put(val, ((BufferedReader)this.getServletContext().getAttribute("masterFr")).copy()));
		}
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		return;
	}
	
}
