package es0;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

// Produttore NON e' un filtro
public class ProduttorePro {
	public static void main(String[] args) {	
		
		if(args.length != 1) {
			System.out.println("Wowowiwa!\nDevi usare il programma: java Produttore.java <targetFile>");
			System.exit(3);
		}
		
		BufferedReader in = null;
	
		in = new BufferedReader(new InputStreamReader(System.in));
		
		List<FileWriter> outputList = new ArrayList<FileWriter>();
			
		//Filling all my output FileWriter
		for(int i = 1; i<args.length; i++) {
			try {
				outputList.add(new FileWriter(args[i]));
			}catch(IOException e) {
				e.printStackTrace();
				System.exit(2); // uscita con errore, intero positivo a livello di sistema Unix
			}
		}
		
		System.out.println("Benvenuto caro utente, scriva pure...");
		try {
			String newLine;
			while((newLine = in.readLine()) != null) {
				int fileNo = Integer.parseInt(newLine.split(":")[0]);
				int indexOfContent = newLine.indexOf(":");
				String stringToAdd = newLine.substring(indexOfContent);
				outputList.get(fileNo).append(stringToAdd);
			}
			//Here I add everything, it should be done, just need to close every file
			in.close();
			for(int i = 0; i<outputList.size(); i++) {
				outputList.get(i).close();
			}
		} 
		catch (NumberFormatException nfe) { 
			nfe.printStackTrace(); 
			System.exit(1); // uscita con errore, intero positivo a livello di sistema Unix
		}
	    catch (IOException e) { 
			e.printStackTrace();
			System.exit(2); // uscita con errore, intero positivo a livello di sistema Unix
		}
		
		
		
		System.out.println("Tutto letto e scrito, ciao ciao...");
		
	}
}

