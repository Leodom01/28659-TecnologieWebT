package it.unibo.tw.servlet;

import java.io.IOException;
import it.unibo.tw.beans.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Book extends HttpServlet{

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
		
		int date1 = (int) req.getSession().getAttribute("day1");
		int date2 = (int) req.getSession().getAttribute("day2");
		String id = (String) req.getSession().getAttribute("id");
		
		DatiReader myReader = (DatiReader) this.getServletContext().getAttribute("datiReader");
		
		if(myReader.bookRoom(id)) {
			req.setAttribute("booking", "successful");
		}else {
			req.setAttribute("booking", "unsuccessful");
		}

		
		RequestDispatcher reqd = this.getServletContext().getRequestDispatcher("/welcome.jsp");
		
		reqd.forward(req, resp);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}
