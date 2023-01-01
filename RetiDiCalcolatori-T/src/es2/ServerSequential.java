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

public class ServerSequential {

	// Expected parameters:
	// Server listening_port
	
	public static void main(String[] args) {
		
		int listeningPort = Integer.parseInt(args[0]);
		ServerSocket socket = null;
		DataInputStream socketIn = null;
		DataOutputStream socketOut = null;
		String received;
		FileOutputStream fileWriter = null;
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
				socketIn = new DataInputStream(openSocket.getInputStream());
				socketOut = new DataOutputStream(openSocket.getOutputStream());
				
				//Accettato e aperto connessione su socketIn
				while((received = socketIn.readUTF()) != null){
					//TODO mi raisa EOFException quando finisce lo stream, devo catcharlo e gestirlo normalemente o posso evitare?
					if(! Files.exists(Path.of(received))) {
						socketOut.writeUTF("file accepted");
						System.out.println("Server: accepted file: "+received);
						//Here the code to copy and get all the files
						long fileLength = socketIn.readLong();
						fileWriter = new FileOutputStream(received);	
						// TODO: dovrei fare un loop in cui tutte le volte chiamo readNbytes con il max int
						// questo finchè non raggiungo la dimensione di fileLength (Serve solo nel caso in cui fileLength sia 
						// maggiore di MAX_INT
						fileWriter.write(socketIn.readNBytes((int)fileLength));
						fileWriter.close();
						System.out.println("Server: scritto file: "+received);
					}else {
						socketOut.writeUTF("file not accepted");
						System.out.println("Server: not accepted file: "+received);
						//Here to continue to the next file
						continue;
					}
				}
				System.out.println("Ho terminato la lettura da un client");
			} catch (EOFException e) {
				System.out.println("Server: Ho finito di leggere (o scartare) il file");
				try {
					openSocket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
