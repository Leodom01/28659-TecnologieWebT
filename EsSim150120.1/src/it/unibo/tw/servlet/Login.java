package it.unibo.tw.servlet;

import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import it.unibo.tw.beans.GruppoUtenti;
import it.unibo.tw.beans.Utente;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7406323049563558798L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String group = req.getParameter("group");
		Utente testUser;
		GruppoUtenti utenti = (GruppoUtenti) this.getServletContext().getAttribute("gruppoUtenti");
		
		
		//Check and update group pw expiration
		if(utenti.getUtenteByName(username) != null) {
			int expPwInGroup = 0;
			for(Utente temp : utenti.utenti) {
				if(temp.gruppo.equals(group) && !temp.isStillValid()) {
					expPwInGroup++;
				}
			}
			if(expPwInGroup > 3) {
				for(Utente temp : utenti.utenti) {
					if(temp.gruppo.equals(group)) {
						temp.zeroTime = new Date(temp.zeroTime.getTime()-(60*24*60*2000));
					}
				}
			}
		}
			
		//Login
		if((testUser = utenti.getUtenteByName(username)) != null && testUser.password.equals(password) && testUser.gruppo.equals(group) && testUser.isStillValid() && testUser.pwStrike<=3) {
			
				if(testUser.isAdmin) {
					//Admin Login
					req.setAttribute("loginOutcome", "success");
					RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/admin.jsp");
					rd.forward(req, res);
					return;
				}else {
					//User login
					req.setAttribute("loginOutcome", "success");
					RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loggedUser.jsp");
					rd.forward(req, res);
					return;
				}
		}else if((testUser = utenti.getUtenteByName(username)) != null && !testUser.password.equals(password)) {
		//Wrong password
			testUser.pwStrike++;
			req.setAttribute("loginOutcome", "wrongPassword");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}else if((testUser = utenti.getUtenteByName(username)) != null && testUser.password.equals(password) && testUser.gruppo.equals(group) && !testUser.isStillValid()) {
		//Expired pw
			req.setAttribute("loginOutcome", "pwExpired");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}else if((testUser = utenti.getUtenteByName(username)) == null) {
		//Non existing user	
			req.setAttribute("loginOutcome", "userNotFound");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}else if((testUser = utenti.getUtenteByName(username)) != null && testUser.password.equals(password) && testUser.gruppo.equals(group) && testUser.isStillValid() && testUser.pwStrike>3) {
		//Correct info but too many errors	
			req.setAttribute("loginOutcome", "tooManyAttempts");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}else {
		//Wrong group or else
			req.setAttribute("loginOutcome", "wrongGroupOrOther");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}

	}
}
