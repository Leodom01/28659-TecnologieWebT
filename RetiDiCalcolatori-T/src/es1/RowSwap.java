package es1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class RowSwap extends Thread{

	public int port;
	public String fileName;
	
	public RowSwap(int port, String fileName) {
		this.port = port;
		this.fileName = fileName;
	}
	
	public void run() {
		
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		
		try {
			socket = new DatagramSocket(port);
			byte [] buf = new byte[1024];
			packet = new DatagramPacket(buf, buf.length);
			
			System.out.println("Partito thread per porta "+port);
			
			while(true) {
				//Wait to get request
				packet.setData(buf);
				socket.receive(packet);
				String request = new String(packet.getData());
				String receivedIp = new String(packet.getAddress().getAddress());
				int receivedPort = packet.getPort();
				System.out.println("Just recieved "+request+" from IP and port "+receivedIp+":"+receivedPort);
				buf = new String("200").getBytes();
				packet.setData(buf);
				socket.send(packet);
				System.out.println("Just sent back data to the latest requester...");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
