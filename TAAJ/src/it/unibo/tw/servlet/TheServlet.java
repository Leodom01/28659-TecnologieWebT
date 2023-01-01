package it.unibo.tw.servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;


public class TheServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Cookie getCookie(HttpServletRequest req, String cookieName) {
		for(Cookie temp : req.getCookies()) {
			if(temp.getName().equals(cookieName)) {
				return temp;
			}
		}
		return null;
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = this.getServletContext().getRealPath("/"+req.getParameter("filename"));
		System.out.println("Here is filename: "+fileName);
		
		StringBuilder strBldr = new StringBuilder();
		
		try {
			JSONArray jArr = (JSONArray) new JSONParser().parse(new FileReader(fileName));
			for(Object temp : jArr) {
				JSONObject jObj = (JSONObject) temp;
				System.out.println("Printo class: "+(String)jObj.get("groupName"));
				System.out.println("Printo componenti: "+(String)jObj.get("groupComponent"));
				strBldr.append("Gruppo "+(String)jObj.get("groupName")+" componenti: "+(String)jObj.get("groupComponent")+"\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.getWriter().append(strBldr.toString());
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
