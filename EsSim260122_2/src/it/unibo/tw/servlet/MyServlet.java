package it.unibo.tw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyServlet extends HttpServlet{

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
		
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}
