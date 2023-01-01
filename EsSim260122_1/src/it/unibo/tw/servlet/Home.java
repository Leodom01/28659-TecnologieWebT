package it.unibo.tw.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.beans.Asta;

import java.util.*;

import java.io.*;
import java.util.*;

public class Home extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{ 
		super.init(config);
		//Instantiate and add to the servlet context attribute all of the nedded vars (the one you read with jsp useBeans
		List<Asta> allAste = new ArrayList<Asta>();
		Asta toAdd = new Asta("Occhiali Andrea Diprè", 100.99, null, new Date(System.currentTimeMillis()+(1000*60*5)));
		allAste.add(toAdd);
		toAdd = new Asta("Il mio bicchiere", 32.88, null, new Date(System.currentTimeMillis()+(1000*60*3)));
		allAste.add(toAdd);
		toAdd = new Asta("Filtro ben fatto", 0.99, null, new Date(System.currentTimeMillis()+(1000*60*10)));
		allAste.add(toAdd);
		this.getServletContext().setAttribute("allAste", allAste);
		
	}
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	 }
	 
	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
		 String u = req.getParameter("userName");
		 String p = req.getParameter("pwd");
		 if(u.compareTo("admin")==0 && p.compareTo("admin")==0){
			 req.getSession().setAttribute("name" , u);
			 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/admin.jsp");
			 rd.forward(req, res);
			 return;
		 }else if(u.compareTo("utente1")==0 && p.compareTo("password1")==0){
			 req.getSession().setAttribute("name" , u);
			 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/welcome.jsp");
			 rd.forward(req, res);
			 return;
		 }
		 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
		 rd.forward(req, res);
		 return;
	 }
}
