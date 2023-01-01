package es2.EXT;

import java.io.FileWriter;
import java.io.IOException;

public class ServerSequentialEXT {

	// Expected parameters:
	// Server listening_port
	
	public static String MY_FOLDER = "src/es2/EXT/Server_folder/";
	
	public static void main(String[] args) throws IOException {
		
		FileWriter fw = new FileWriter(MY_FOLDER+"Server");
		fw.append("Ciao");
		fw.close();
		
	}
	
}
