package it.unibo.tw.beans;

import java.util.*;

public class Vendita {

	private int carta;
	private Utente owner;
	private int prezzoCorrente;
	private Utente currentWinner;
	private Date inizioVendita;
	
	public Vendita(int carta, Utente owner, int prezzoCorrente, Utente currentWinner) {
		super();
		this.carta = carta;
		this.owner = owner;
		this.prezzoCorrente = prezzoCorrente;
		this.currentWinner = currentWinner;
		this.inizioVendita = new Date();
	}

	public int getCarta() {
		return carta;
	}

	public void setCarta(int carta) {
		this.carta = carta;
	}

	public Utente getOwner() {
		return owner;
	}

	public void setOwner(Utente owner) {
		this.owner = owner;
	}

	public int getPrezzoCorrente() {
		return prezzoCorrente;
	}

	public void setPrezzoCorrente(int prezzoCorrente) {
		this.prezzoCorrente = prezzoCorrente;
	}

	public Utente getCurrentWinner() {
		return currentWinner;
	}

	public void setCurrentWinner(Utente currentWinner) {
		this.currentWinner = currentWinner;
	}

	public Date getInizioVendita() {
		return inizioVendita;
	}

	public void setInizioVendita(Date inizioVendita) {
		this.inizioVendita = inizioVendita;
	}
	
	

}
