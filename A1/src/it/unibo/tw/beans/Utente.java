package it.unibo.tw.beans;

import java.util.*;

import javax.servlet.http.HttpSession;

public class Utente {
	
	private HttpSession sessione;
	private ArrayList<Integer> carte;
	private int denari;
	
	public Utente(HttpSession sessione, ArrayList<Integer> carte, int denari) {
		super();
		this.sessione = sessione;
		this.carte = carte;
		this.denari = denari;
	}
	
	public HttpSession getSessione() {
		return sessione;
	}
	public void setSessione(HttpSession sessione) {
		this.sessione = sessione;
	}
	public ArrayList<Integer> getCarte() {
		return carte;
	}
	public void setCarte(ArrayList<Integer> carte) {
		this.carte = carte;
	}
	public int getDenari() {
		return denari;
	}
	public void setDenari(int denari) {
		this.denari = denari;
	}
	
	void vendiCarta(Integer number) {
		this.carte.remove(number);
	}

}
