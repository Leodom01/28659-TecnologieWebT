package it.unibo.tw.servlet;

import java.io.IOException;
import it.unibo.tw.beans.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PriceRequest extends HttpServlet{

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
		
		int date1 = Integer.parseInt(req.getParameter("date_start"));
		int date2 = Integer.parseInt(req.getParameter("date_end"));
		String id = req.getParameter("albergo");
		
		DatiReader myReader = this.getServletContext().getAttribute("datiReader");
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}
