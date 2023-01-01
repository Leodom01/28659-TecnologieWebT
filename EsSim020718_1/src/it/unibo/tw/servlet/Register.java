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
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String group = req.getParameter("group");
		Utente testUser;
		GruppoUtenti utenti = (GruppoUtenti) this.getServletContext().getAttribute("gruppoUtenti");
		
		if(utenti.getUtenteByName(username) == null) {
			//New registration
			Utente toAdd = new Utente(username, newPassword, group, false);
			utenti.utenti.add(toAdd);
			req.setAttribute("registerOutcome", "newSuccessFullRegistration");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}else if((testUser = utenti.getUtenteByName(username)) != null && testUser.password.equals(oldPassword) && testUser.gruppo.equals(group) && !testUser.isStillValid()){
			//Replace pw correct
			Utente toAdd = new Utente(username, newPassword, group, false);
			utenti.removeUtente(testUser);
			utenti.utenti.add(toAdd);
			req.setAttribute("registerOutcome", "newSuccessPwReplacement");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}else if((testUser = utenti.getUtenteByName(username)) != null && !testUser.password.equals(oldPassword) && testUser.gruppo.equals(group) && !testUser.isStillValid()){
			//Replace password, wrong pw provided
			req.setAttribute("registerOutcome", "failedBecauseOfWrongPw");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}else if(((testUser = utenti.getUtenteByName(username)) != null && testUser.password.equals(oldPassword) && testUser.gruppo.equals(group) && testUser.isStillValid())){
			//Repalce pw but no need to 
			req.setAttribute("registerOutcome", "failedBecauseNoNeedToReplace");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}else {
			//Wrong group or other
			req.setAttribute("registerOutcome", "failedWrongGroupOrOther");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			rd.forward(req, res);
			return;
		}
			
	}
}

