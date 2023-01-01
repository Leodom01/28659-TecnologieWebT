package it.unibo.tw.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.beans.Utente;

import java.util.*;

import java.io.*;
import java.util.*;

public class Home extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private void initGame() {
		this.getServletContext().setAttribute("dataInizio", new Date());
		ArrayList<Utente> allGiocatori = (ArrayList<Utente>)this.getServletContext().getAttribute("giocatori");
		
		//Aggiungo carte ai giocatori
		for(int i = 0; i<4; i++) {
			ArrayList<Integer> carteToAdd = new ArrayList<>(5);
			for(int j = 1; j<6; j++) {
				carteToAdd.add((5*i)+j);		//Carta in ordine, non random, faccio prima
			}
			allGiocatori.get(i).setCarte(carteToAdd);
		}
		
		this.getServletContext().setAttribute("venditaCorrente", null);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{ 
		super.init(config);
		//Aggiungo lista giovatori alla app
		ArrayList<Utente> giocatori = new ArrayList<>(4);
		this.getServletContext().setAttribute("giocatori", giocatori);
	}

	
	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	 }
	 
	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
		 String u = req.getParameter("userName");
		 String p = req.getParameter("pwd");
		 if((u.compareTo("user1")==0 && p.compareTo("pw1")==0) || 
				 (u.compareTo("user2")==0 && p.compareTo("pw2")==0) ||
				 (u.compareTo("user3")==0 && p.compareTo("pw3")==0) || 
				 (u.compareTo("user4")==0 && p.compareTo("pw4")==0))
		 {
			 //Aggiungo utente a pool giocatori
			 Utente toAdd = new Utente(req.getSession(), new ArrayList<Integer>(), 100);
			 ArrayList<Utente> allGiocatori = (ArrayList<Utente>)this.getServletContext().getAttribute("giocatori");
			 allGiocatori.add(toAdd);
			 if(allGiocatori.size() == 4) {
				 initGame();
			 }
			 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("welcome.jsp");
			 rd.forward(req, res);
			 return;
		 }
		 
		 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("login.jsp");
		 rd.forward(req, res);
		 return;
	 }
}
