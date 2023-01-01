package it.unibo.tw.servlet;

import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import it.unibo.tw.beans.GruppoUtenti;
import it.unibo.tw.beans.Matrix;
import it.unibo.tw.beans.Utente;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;

public class Calculator extends HttpServlet {

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

		int matRows = Integer.parseInt(req.getParameter("rows"));
		int matCols = Integer.parseInt(req.getParameter("cols"));
		
		Matrix a = new Matrix(matRows,matCols);
		Matrix b = new Matrix(matRows,matCols);
		
		for(int row = 0; row<matRows; row++) {
			for(int col = 0; col<matCols; col++) {
				String cellCode = new Integer(row+1).toString()+ new Integer(col+1).toString();
				a.add(col, row, Integer.parseInt(req.getParameter(cellCode+"A")));
				b.add(col, row, Integer.parseInt(req.getParameter(cellCode+"B")));
			}
		}
		
		Matrix result = a.sum(b);
		
		for(int row = 0; row<result.matArray.length; row++) {
			for(int col = 0; col<result.matArray[0].length; col++) {
				String cellCode = new Integer(row+1).toString()+ new Integer(col+1).toString();
				req.setAttribute(cellCode, result.matArray[row][col]);
			}
		}
		req.setAttribute("result", "ready");
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/welcome.jsp");
		disp.forward(req, res);
		return;
			
	}
}

