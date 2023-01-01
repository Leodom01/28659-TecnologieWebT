package es2.EXT;

import java.io.FileWriter;
import java.io.IOException;

public class ClientEXT {

	// Expected parameters:
	// Client Server_port
	
	public static String MY_FOLDER = "src/es2/EXT/Client_folder/";

	public static void main(String[] args) throws IOException{
	
		FileWriter fw = new FileWriter(MY_FOLDER+"Client");
		fw.append("Ciao");
		fw.close();
		
	}
}
