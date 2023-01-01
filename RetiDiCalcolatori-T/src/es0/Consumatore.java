package es0;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

// Consumatore e' un filtro
public class Consumatore {
	public static void main(String[] args) {
		Reader r = null;
		char ch;
		int x;
		
		Set<Character> forbiddenChar = new HashSet<Character>();
		
		if (args.length == 2){
			try {
				r = new FileReader(args[1]);
			} catch(FileNotFoundException e){
				System.out.println("File non trovato");
				System.exit(1);
			}
		}
		if (args.length == 1) {
			r = new InputStreamReader(System.in);
		}
		
		
		//Filling my forbiddenChar set
		for(int i = 0; i<args[0].length(); i++) {
			forbiddenChar.add(args[0].charAt(i));
		}
		
		try {
			while ((x = r.read()) >= 0) { 
				ch = (char) x;
				if(!forbiddenChar.contains(ch)) {
					System.out.print(ch);
				}
			}
			r.close();
		} catch(IOException ex){
			System.out.println("Errore di input");
			System.exit(2);
		}
}}
