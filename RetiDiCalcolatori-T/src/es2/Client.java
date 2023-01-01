package es2;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class Client {

	// Expected parameters:
	// Client directory_to_copy Server_port max_size_transfer
	
	public static void main(String[] args) {
		
		int serverPort = Integer.parseInt(args[1]);
		int maxFileTransfer = Integer.parseInt(args[2]);
		String directory = args[0];
		
		Socket socket = null;
		DataInputStream socketIn = null;
		DataOutputStream socketOut = null;
		String received;
		BufferedInputStream fileReader = null;
		
		if(!Path.of(directory).toFile().isDirectory()) {
			System.out.println("The provided directory is not a directory!");
			System.exit(-3);
		}
		
		try {
			socket = new Socket(InetAddress.getLocalHost(), serverPort);
			socketIn = new DataInputStream(socket.getInputStream());
			socketOut = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("Errore in fase di creazione della socket...");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Errore in fase di creazione della socket...");
			e.printStackTrace();
		}
		
		System.out.println("Lookin at files in: "+directory);
		
		File[] listFiles = (new File(directory)).listFiles();
		
		System.out.println("No of file found: "+listFiles.length);
		
		for(int i = 0; i<listFiles.length; i++) {
			try {
				long fileSize = listFiles[i].length();
				System.out.println("The filesize is: "+fileSize);
				if(fileSize <= maxFileTransfer) {
					System.out.println("FileSize accettabile, mando nome file al server.");
				}else {
					System.out.println("FileSize non accettabile, cambio file.");
					continue;
				}
				socketOut.writeUTF(listFiles[i].getName());
				received = socketIn.readUTF();
				if(received.equals("file accepted")) {
					System.out.println("Server said accept so starting transfer");
					
					fileReader = new BufferedInputStream(new FileInputStream(listFiles[i]));
					socketOut.writeLong(fileSize);
					byte[] byteBuffer = new byte[4096];
					int readByte;
					while((readByte = fileReader.read(byteBuffer, 0, 4096)) != -1) {
						socketOut.write(byteBuffer, 0, readByte);
					}
					System.out.println("Moved the whole file to the server");
				}else {
					System.out.println("Server didn't say accept so not starting transfer");
					continue;
				}
			} catch (IOException e) {
				System.err.println("Errore in fase di comunicazione con la socket...");
				e.printStackTrace();
			}
		}
		System.out.println("No more files to read.\nClosing my stream.");
		try {
			socketIn.close();
			if(fileReader != null) {
				fileReader.close();
			}
			socketOut.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error while closing the stream.");
			e.printStackTrace();
		}
		System.exit(0);
	}
	
}
