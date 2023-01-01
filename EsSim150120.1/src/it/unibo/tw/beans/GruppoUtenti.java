package it.unibo.tw.beans;

import java.util.*;

public class GruppoUtenti {

	public List<Utente> utenti;
	public List<String> allowedGroup = new ArrayList<>();

	public GruppoUtenti(List<Utente> utenti) {
		super();
		this.utenti = utenti;
		this.allowedGroup.add("A");
		this.allowedGroup.add("B");
		this.allowedGroup.add("C");
	}

	public GruppoUtenti() {
		super();
		utenti = new ArrayList<Utente>();
		Utente toAdd = new Utente("leo", "password", "B", false);
		utenti.add(toAdd);
		toAdd = new Utente("admin", "admin", "A", true);
		utenti.add(toAdd);
		toAdd = new Utente("test", "password", "B", false);
		utenti.add(toAdd);
		this.allowedGroup.add("A");
		this.allowedGroup.add("B");
		this.allowedGroup.add("C");
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
	
	public List<String> getGroups() {
		List<String> toRet = new ArrayList<>();
		for(Utente temp : this.utenti) {
			if(!toRet.contains(temp.gruppo)) {
				toRet.add(temp.gruppo);
			}
		}
		return toRet;
	}

}
