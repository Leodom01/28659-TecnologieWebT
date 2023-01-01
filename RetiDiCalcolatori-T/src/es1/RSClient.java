package es1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class RSClient {

	public static void main(String [] args) {
		
		//Check args number
		if(args.length != 3) {
			System.err.println("Invoke the program with: RSClient IPDS portDS fileName");
			System.exit(1);
		}
		
		//TODO: Check port value
		//Open the socket port
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket();
			socket.setSoTimeout(30000);
			byte[] buf = new byte[1024];
			
			//Should check args[2] size first but smeh
			buf = args[2].getBytes();
			InetAddress addr = InetAddress.getByName(args[0]);
			DatagramPacket packet = new DatagramPacket(buf, buf.length, addr, Integer.parseInt(args[1]));
			socket.send(packet);
			
			//Just sent the request packet to DNS
			buf = new byte[1024];
			packet.setData(buf);
			socket.receive(packet);
			
			//Now parse the server response
			String response = new String(packet.getData());
			System.out.println("I got back from server: "+response);
			int rowSwapperPort = Integer.parseInt(response);
			if(rowSwapperPort == -1) {
				System.err.println("DiscoverySystem said that there's no such file!\nBye bye...:)");
				System.exit(2);
			}
			
			//Set the packet socket to the rowSwap server
			packet.setPort(rowSwapperPort);
			
			//Get input lines to swap from user
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Type the rows to swap, space separated values:");
			String readLine = reader.readLine();
			//TODO Should check the input syntax
			buf = readLine.getBytes();
			packet.setData(buf);
			socket.send(packet);
			//Just sent the packet to the RowSwapper
			
			//Now wait for the row swapper outcome
			packet.setData(buf);
			socket.receive(packet);
			System.out.println("Got "+packet.getData().toString()+" from the server.\nNow closing...");
			System.exit(0);
		} catch (Exception e) {
			System.err.println("Some sort of exception catched, err message: "+e.getMessage()+" and now the stacktrace: ");
			e.printStackTrace();
		}finally {
			socket.close();
		}
		
		
	}
	
}
