package it.unibo.tw.beans;

import java.util.*;

public class GruppoUtenti {

	public List<Utente> utenti;

	public GruppoUtenti(List<Utente> utenti) {
		super();
		this.utenti = utenti;
	}

	public GruppoUtenti() {
		super();
		utenti = new ArrayList<Utente>();
		Utente toAdd = new Utente("leo", "password", "fiki", false);
		utenti.add(toAdd);
		toAdd = new Utente("admin", "admin", "admin", true);
		utenti.add(toAdd);
		toAdd = new Utente("test", "password", "fiki", false);
		utenti.add(toAdd);
	}
	

	public void removeUtente(Utente u) {
		this.utenti.remove(u);
	}

	public boolean containsUser(Utente u) {
		return this.utenti.contains(u);
	}

	public Utente getUtenteByName(String userName) {
		if (userName.isEmpty() || userName == null)
			return null;
		for (Utente u : this.utenti) {
			if (u.getNomeUtente().compareTo(userName) == 0)
				return u;
		}
		return null;
	}

	public int checkValidity() {
		int result = 0;
		for (Utente u : this.utenti) {
			if (!u.isStillValid()) {
				result++;
			}
		}
		return result;
	}

}
