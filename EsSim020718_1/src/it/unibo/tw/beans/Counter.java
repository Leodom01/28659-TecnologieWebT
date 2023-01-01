package it.unibo.tw.beans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Counter {

	public Counter(){

	}

	//Ritorna <numero maiuscole>,<exec time in ms>
	public String fileToCount(String[] file){
		long startTime = System.currentTimeMillis();
		int charCounter = 0;
		for(int i = 0; i<3; i++){
			try {
				BufferedReader fr = new BufferedReader(new FileReader(file[i]));
				String line; 
				while((line = fr.readLine()) != null){
					for(Character currentChar : line.toCharArray()){
						if(Character.isUpperCase(currentChar)){
							charCounter++;
						}
					}
				}
				fr.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Integer.toString(charCounter)+","+Long.toString(System.currentTimeMillis()-startTime);
	}

	
}
