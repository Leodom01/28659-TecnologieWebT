package it.unibo.tw.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Ereaser extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Cookie getCookie(HttpServletRequest req, String cookieName) {
		for(Cookie temp : req.getCookies()) {
			if(temp.getName().equals(cookieName)) {
				return temp;
			}
		}
		return null;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String text = req.getParameter("text");
		Random rand = new Random();
		
		char toDel = (char) (97+rand.nextInt(26));
		
		
		int elabCount = (int) this.getServletContext().getAttribute("totElab");
		elabCount++;
		this.getServletContext().setAttribute("totElab", elabCount);
		Map<String, Date> thisSessions = (Map<String, Date>) this.getServletContext().getAttribute("last30daysSession"); 
		thisSessions.put(req.getSession().getId(), new Date());
		this.getServletContext().setAttribute("last30daysSession", thisSessions);
		text = text.replace(Character.toString(toDel), "");
		
		req.setAttribute("text", text);
		RequestDispatcher reqd = this.getServletContext().getRequestDispatcher("/SecondEreaser.jsp");
		
		reqd.forward(req, resp);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}
