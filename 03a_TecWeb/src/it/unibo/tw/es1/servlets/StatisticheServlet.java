
package it.unibo.tw.es1.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.es1.beans.Articolo;
import it.unibo.tw.es1.beans.InsiemeDiArticoli;


public class StatisticheServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		InsiemeDiArticoli merceVenduta = (InsiemeDiArticoli) this.getServletContext().getAttribute("merceVenduta");
		
		float guadagnoRichiestaAttuale = 0;
		String id = req.getParameter("id");
		Integer firstDay = Integer.parseInt(req.getParameter("firstDay"));
		Integer lastDay = Integer.parseInt(req.getParameter("lastDay"));
		boolean checkId = (id.equals("")) ? false : true;
		
		for(Articolo temp : merceVenduta.getMerce()) {
			System.out.println("CheckId: "+checkId+" and day: "+temp.getDay());
			if(temp.getDay() >= firstDay && temp.getDay() <= lastDay && (!checkId || temp.getId().equals(id))) {
				guadagnoRichiestaAttuale += temp.getAmount()*temp.getPrice();
				System.out.println("Aggiungo articolo id: "+temp.getId()+" prezzo: "+temp.getPrice()+" quantity: "+temp.getAmount());
			}
		}
		System.out.println("Ritorno: "+guadagnoRichiestaAttuale);
		
		//Fill cookies
		resp.addCookie(new Cookie("id", "None"));
		resp.addCookie(new Cookie("firstDay", firstDay.toString()));
		resp.addCookie(new Cookie("lastDay", lastDay.toString()));
		resp.addCookie(new Cookie("guadagno", Float.toString(guadagnoRichiestaAttuale)));
		
		req.setAttribute("guadagnoRichiestaAttuale", guadagnoRichiestaAttuale);
		
		RequestDispatcher reDisp = this.getServletContext().getRequestDispatcher("/statistiche.jsp");
		reDisp.forward(req, resp);
	}
	
}
