package es2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class TaskThread extends Thread{

	public Socket openSocket;
	
	public TaskThread(Socket openSocket) {
		this.openSocket = openSocket;
	}
	
	//Returns null if the file has already been written or if it's being written rn
	private synchronized FileOutputStream getWriterOrNull(String received) {
		if(! Files.exists(Path.of(received))) {
			try {
				return new FileOutputStream(received);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void run() {
		
		System.out.println(this.getId()+" new Thread running");
		String received;
		DataInputStream socketIn = null;
		DataOutputStream socketOut = null;
		FileOutputStream fileWriter = null;
		
		try {
			socketIn = new DataInputStream(openSocket.getInputStream());
			socketOut = new DataOutputStream(openSocket.getOutputStream());
		} catch (IOException e) {
			System.err.println(this.getId()+":Error while creating DataStream: "+e.getMessage());
			e.printStackTrace();
		}
		
		try {
			while((received = socketIn.readUTF()) != null){
				//TODO mi raisa EOFException quando finisce lo stream, devo catcharlo e gestirlo normalemente o posso evitare?
				if((fileWriter = this.getWriterOrNull(received)) != null) {
					socketOut.writeUTF("file accepted");
					System.out.println(this.getId()+"Server: accepted file: "+received);
					//Here the code to copy and get all the files
					long fileLength = socketIn.readLong();	
					// TODO: dovrei fare un loop in cui tutte le volte chiamo readNbytes con il max int
					// questo finchè non raggiungo la dimensione di fileLength (Serve solo nel caso in cui fileLength sia 
					// maggiore di MAX_INT
					fileWriter.write(socketIn.readNBytes((int)fileLength));
					fileWriter.close();
					System.out.println(this.getId()+"Server: scritto file: "+received);
				}else {
					socketOut.writeUTF("file not accepted");
					System.out.println(this.getId()+"Server: not accepted file: "+received);
				}
			
			}
		} catch (EOFException e) {
			System.out.println(this.getId()+"Server: Ho finito di leggere (o scartare) il file");
			try {
				openSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
