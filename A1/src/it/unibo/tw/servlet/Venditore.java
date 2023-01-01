package it.unibo.tw.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.beans.Utente;
import it.unibo.tw.beans.Vendita;


public class Venditore extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Cookie getCookie(HttpServletRequest req, String cookieName) {
		for(Cookie temp : req.getCookies()) {
			if(temp.getName().equals(cookieName)) {
				return temp;
			}
		}
		return null;
	}
	
	public void init() {
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Utente currentUser = null;
		for(Utente temp : (ArrayList<Utente>)this.getServletContext().getAttribute("giocatori")){
			if(temp.getSessione().equals(req.getSession())){
				currentUser = temp;
				break;
			}
		}
		//Check fine vendita, nel caso metti a nulla la vendita nella application scope
		if( this.getServletContext().getAttribute("venditaCorrente") != null &&
				System.currentTimeMillis()-60*1000 <= ((Vendita)this.getServletContext().getAttribute("venditaCorrente")).getInizioVendita().getTime()) {
			Vendita currentVendita =(Vendita)this.getServletContext().getAttribute("venditaCorrente");
			currentVendita.getCurrentWinner().getCarte().add(currentVendita.getCarta());
			currentVendita.getOwner().getCarte().remove(currentVendita.getCarta());
			currentVendita.getCurrentWinner().setDenari(currentVendita.getCurrentWinner().getDenari()-currentVendita.getPrezzoCorrente());
		}
		
		//Se vuole vendere
		if(req.getParameter("carta") != null) {
			if( this.getServletContext().getAttribute("venditaCorrente") == null) {
				//Crico vendita
				Vendita toAdd = new Vendita(Integer.parseInt(req.getParameter("carta")), currentUser, 0, null);
				this.getServletContext().setAttribute("venditaCorrente", toAdd);
			}
		}else if(req.getParameter("compraPrezzo") != null) {
			//Se vuole comprare
			if( this.getServletContext().getAttribute("venditaCorrente") != null) {
				Vendita currentVendita =(Vendita)this.getServletContext().getAttribute("venditaCorrente");
				if(currentVendita.getPrezzoCorrente() < Integer.parseInt(req.getParameter("compraPrezzo")) &&
						currentUser.getDenari() >= Integer.parseInt(req.getParameter("compraPrezzo"))) {
					currentVendita.setCurrentWinner(currentUser);
					currentVendita.setPrezzoCorrente(Integer.parseInt(req.getParameter("compraPrezzo")));
					currentVendita.setInizioVendita(new Date());
					
				}
			}
		}
		
		RequestDispatcher rqd = this.getServletContext().getRequestDispatcher("/welcome.jsp");
		rqd.forward(req, resp);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
