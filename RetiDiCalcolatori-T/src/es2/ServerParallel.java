package es2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerParallel {

	// Expected parameters:
	// Server listening_port
	
	public static void main(String[] args) {
		
		int listeningPort = Integer.parseInt(args[0]);
		ServerSocket socket = null;
		Socket openSocket = null;
		
		try {
			socket = new ServerSocket(listeningPort);
		} catch (IOException e) {
			System.err.println("Errore in fase di creazione della socket...");
			e.printStackTrace();
		}
		
		//Loop di accetazione, scambio dati e accettazione (bene per server seriale)
		while(true) {
			System.out.println("Server pronto per nuove connessioni");
			try {
				openSocket = socket.accept();
				TaskThread toRun = new TaskThread(openSocket);
				toRun.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
