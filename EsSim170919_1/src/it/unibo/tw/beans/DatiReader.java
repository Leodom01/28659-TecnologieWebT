package it.unibo.tw.beans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatiReader {
	
	public BufferedReader fr;
	
	public Map<String, Integer> lookingAt
	
	public DatiReader() {
		try {
			fr = new BufferedReader(new FileReader("Dati.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Errore in fase di apertura");
			e.printStackTrace();
		}
	}
	
	public List<String> getIDs() {
		String line;
		List<String> toReturn = new ArrayList<>();
		try {
			while((line = fr.readLine()) != null) {
				toReturn.add(line.split(",")[0]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public Map<String, Integer> getCamere(){
		String line;
		Map<String, Integer> toReturn = new HashMap<>();
		try {
			while((line = fr.readLine()) != null) {
				toReturn.put(line.split(",")[0], Integer.parseInt(line.split(",")[1]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public Map<String, Integer> getPrezzo(){
		String line;
		Map<String, Integer> toReturn = new HashMap<>();
		try {
			while((line = fr.readLine()) != null) {
				toReturn.put(line.split(",")[0], Integer.parseInt(line.split(",")[2]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public synchronized boolean bookRoom(String id) {
		Map<String, Integer> camere = this.getCamere();
		Map<String, Integer> prezzi = this.getPrezzo();
		if(camere.get(id) > 1) {
			try {
				fr.close();
				
				new FileWriter("Dati.txt", false).close();
				FileWriter write = new FileWriter("Dati.txt");
				for(String key : camere.keySet()) {
					if(key.equals(id)) {
						write.append(key+","+Integer.toString(camere.get(key)-1)+","+Integer.toString(prezzi.get(key))+"\n");
					}else {
						write.append(key+","+Integer.toString(camere.get(key))+","+Integer.toString(prezzi.get(key))+"\n");
					}
					
				}
				write.close();
				fr = new BufferedReader(new FileReader("Dati.txt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}else {
			return false;
		}
		
	}
	
	public String toString() {
		return "Instance of BeanItem";
	}
	
}
