package it.unibo.tw.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;


public class Cruci extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Cookie getCookie(HttpServletRequest req, String cookieName) {
		for(Cookie temp : req.getCookies()) {
			if(temp.getName().equals(cookieName)) {
				return temp;
			}
		}
		return null;
	}

	public void init(){
		this.getServletContext().setAttribute("matrice", new char[100]);
		ArrayList<String> parole = new ArrayList<>(10);
		try {
			BufferedReader fr = new BufferedReader(new FileReader("cruciverba.txt"));
			String line;
			while((line = fr.readLine()) != null){
				parole.add(line);
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().setAttribute("parole", parole);
 	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Character newChar = null;
		int charPos = -1;

		String matrix = new String(((char[])this.getServletContext().getAttribute("matrice")));
		
		for(int i = 0; i<100; i++){
			if(req.getParameter(Integer.toString(i)) != null && 
			req.getParameter(Integer.toString(i)).equals(Character.toString(matrix.charAt(i)))){
				newChar = Integer.toString(i).charAt(0);
				charPos = i;
				break;
			}
		}
		if(newChar == null){
			JsonObject jObj = new JsonObject();
			jObj.addProperty("result", "nessun nuovo valore");
			jObj.addProperty("matrix", new String(((char[])this.getServletContext().getAttribute("matrice"))));

			resp.getWriter().append(jObj.toString());
			return;
		}

		synchronized(matrix){
			char[] newMat = matrix.toCharArray();
			newMat[charPos] = newChar;
 			matrix = newMat.toString();
		}

		JsonObject jObj = new JsonObject();
		jObj.addProperty("result", "valore assegnato");
		jObj.addProperty("matrix", new String(((char[])this.getServletContext().getAttribute("matrice"))));


	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
