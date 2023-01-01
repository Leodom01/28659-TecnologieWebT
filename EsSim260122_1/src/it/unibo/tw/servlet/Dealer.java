package it.unibo.tw.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.unibo.tw.beans.Asta;


public class Dealer extends HttpServlet{

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
		List<Asta> allAste = (List<Asta>)this.getServletContext().getAttribute("allAste");
		String buyer = (String)req.getSession().getAttribute("name");
		String itemName = (String)req.getParameter("item");
		System.out.println((String)req.getParameter("price"+itemName));
		Float price = Float.parseFloat((String)req.getParameter("price"+itemName));
		

		for(Asta temp : allAste) {
			if(temp.name.equals(itemName) && 
				temp.endDate.getTime()>=System.currentTimeMillis() &&
				temp.price < price) {
				temp.price = price;
				temp.winner = buyer;
				temp.users.add(req.getSession());
			}
		}
		JSONObject toAdd = new JSONObject();
		List<String> winnerList = new ArrayList<>();
		List<String> itemList = new ArrayList<>();
		List<String> priceList = new ArrayList<>();
		
		//Check update on aste
		for(Asta temp : allAste) {
			if(temp.users.contains(req.getSession()) && temp.endDate.getTime()>System.currentTimeMillis()) {
				winnerList.add(temp.winner);
				itemList.add(temp.name);
				priceList.add(Double.toString(temp.price));
				temp.users.remove(req.getSession());
			}
		}
		try {
			toAdd.put("items", String.join(",", itemList));
			toAdd.put("winners", String.join(",", winnerList));
			toAdd.put("prices", String.join(",", priceList));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("result", toAdd.toString());
		RequestDispatcher rqd = this.getServletContext().getRequestDispatcher("/welcome.jsp");
		
		rqd.forward(req, resp);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}
