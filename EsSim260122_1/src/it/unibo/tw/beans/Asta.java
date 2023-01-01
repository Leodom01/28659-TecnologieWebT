package it.unibo.tw.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

public class Asta {

	public String name; 
	public double price;
	public String winner;
	public Date endDate;
	public Set<HttpSession> users;
	
	
	public Asta(String name, double price, String winner, Date endDate) {
		super();
		this.name = name;
		this.price = price;
		this.winner = winner;
		this.endDate = endDate;
		this.users = new HashSet<HttpSession>();
	}

	public boolean equals(Object obj) {
		if(obj instanceof Asta) {
			//TODO Add additional check
			
			return true;
		}else {
			return false; 
		}
	}
	
	public String toString() {
		return "Instance of BeanItem";
	}
	
}
