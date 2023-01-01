package it.unibo.tw.beans;

import java.util.*;

public class Utente {
	public String nomeUtente;
	public String password;
	public String gruppo;
	public Date zeroTime;
	public boolean isAdmin;
	public int pwStrike; 
	
	public final int MS_BEFORE_EXPIRING = 60*24*60*60*1000;	//60 Days
	
	
	public Utente(String nomeUtente, String password, String gruppo, boolean isAdmin) {
		super();
		this.nomeUtente = nomeUtente;
		this.password = password;
		this.gruppo = gruppo;
		this.zeroTime = new Date(System.currentTimeMillis());
		this.isAdmin = isAdmin;
		this.pwStrike = 0;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGruppo() {
		return gruppo;
	}
	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
	}
	public Date getZeroTime() {
		return zeroTime;
	}
	public void setZeroTime(Date zeroTime) {
		this.zeroTime = zeroTime;
	}

	@Override
	public String toString() {
		return "Utente [nomeUtente=" + nomeUtente + ", gruppo=" + gruppo + ", valid=" + this.isStillValid() + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Utente altroU = (Utente) obj;
		if(this.nomeUtente.compareTo(altroU.getNomeUtente())==0 && this.gruppo.compareTo(altroU.getGruppo())==0)
			return true;
		else
			return false;
	}

	public boolean isStillValid() {
		if ((new Date().getTime() - this.getZeroTime().getTime()) >= MS_BEFORE_EXPIRING) {
			return false;
		} else {
			return true;
		}
	}
	
	public int daysBeforeExp() {
		if(!isStillValid()) {
			return -1;
		}else {
			return (int)(MS_BEFORE_EXPIRING - (new Date().getTime() - this.getZeroTime().getTime()))/1000/60/60/24;
		}
	}

}
