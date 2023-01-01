package es1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class DiscoveryServer {
	
	public static final int DATA_PACKET_SIZE = 1024;

	public static void main(String [] args) {
		
		DatagramSocket port = null;
		DatagramPacket packet = null;
		byte[] inBuffer = new byte[DATA_PACKET_SIZE];
		ArrayList<Thread> myThreads = new ArrayList<>();
		
		//Check args
		if(args.length%2 == 0) {
			System.err.println("Run code as:\nDiscoveryServer DS_port filename1 file1_port filename2 file2_port ... filenamen filen_port");
			System.exit(1);
		}
		//No check on filename
		//Checking port values
		for(int i = 1; i<args.length; i=i+2) {
			int currentPort = Integer.parseInt(args[i+1]);
			if(currentPort <= 1024 || currentPort >= 64000) {
				System.err.println("Port "+currentPort+" cannot be used. See ya :)");
				System.exit(2);
			}
		}
		
		for(int i = 1; i<args.length; i=i+2) {
			RowSwap toCreate = new RowSwap(Integer.parseInt(args[i+1]), args[i]);
			myThreads.add(toCreate);
			toCreate.start();
		}
		
		//Open port on args[1]
		try {
			System.out.println("Trying to open my port on port: "+Integer.parseInt(args[0]));
			port = new DatagramSocket(Integer.parseInt(args[0]));
		}catch(Exception e) {
			System.err.println("Error while opening my listening port: "+e.getMessage());
			System.exit(3);
		}
		
		packet = new DatagramPacket(inBuffer, inBuffer.length);
		
		//Now start listening on port
		try {
			while(true) {
				packet.setData(inBuffer); //TODO perchè mi serve?
				port.receive(packet);
				String request = new String(packet.getData());
				int portToReturn = -1;
				for(int i = 1; i<args.length && portToReturn == -1; i=i+2) {
					if(args[i].equals(request)) {
						portToReturn = i+1;
					}
				}
				inBuffer = Integer.toString(portToReturn).getBytes();
				packet.setData(inBuffer);
				String debuggerResponse = new String(packet.getData());
				System.out.println("Sending data:"+portToReturn);
				port.send(packet);
			}
		}catch(Exception e) {
			//TODO Add exception handling here
		}
		
		port.close();
		
	}
	
}
