package it.unibo.tw.servlet;

import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import it.unibo.tw.beans.GruppoUtenti;
import it.unibo.tw.beans.Utente;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;

public class Register extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8803894900349864836L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String username = req.getParameter("username");
		String newPassword = req.getParameter("newPassword");
		
		ArrayList<String[]> userAndPw = (ArrayList<String[]>)this.getServletContext().getAttribute("usrPwUsr");
		
		String[] toAdd = new String[3];
		toAdd[0] = username; 
		toAdd[1] = newPassword;
		toAdd[2] = "no group";
		userAndPw.add(toAdd);
		
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/welcome.jsp");
	}
}

