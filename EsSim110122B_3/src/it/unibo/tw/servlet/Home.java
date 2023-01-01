package it.unibo.tw.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	}
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	 }
	 
	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
		 String u = req.getParameter("userName");
		 String p = req.getParameter("pwd");
		 if(u.compareTo("admin")==0 && p.compareTo("admin")==0)
		 {
			 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("admin.jsp");
			 rd.forward(req, res);
			 return;
		 }
		 
		 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("welcome.jsp");
		 rd.forward(req, res);
		 return;
	 }
}
