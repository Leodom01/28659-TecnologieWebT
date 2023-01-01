package es0;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

// Produttore NON e' un filtro
public class Produttore {
	public static void main(String[] args) {	
		
		for(int i = 0; i<args.length; i++) {
			System.out.println("Elemento di input "+i+" : "+args[i]);
		}
		
		if(args.length != 1) {
			System.out.println("Wowowiwa!\nDevi usare il programma: java Produttore.java <targetFile>");
			System.exit(3);
		}
		
		BufferedReader in = null;
	
		in = new BufferedReader(new InputStreamReader(System.in));
			
		FileWriter fout;
		System.out.println("Inserire la prima riga:");
		try {
			fout = new FileWriter(args[0]);
			int newChar;
			while((newChar = in.read()) >= 0) {
				fout.write(newChar);
			}
			fout.close();
			in.close();
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

