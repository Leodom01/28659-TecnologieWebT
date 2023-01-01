package it.unibo.tw.webSockServers;

import java.io.IOException;
import java.util.Date;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class WebSocketServer{
	
	static int connectedUser;
	static Date startDate = null;
	
	@OnOpen
	public void open(Session session) {
		connectedUser++;
		if(connectedUser == 2 && startDate == null) {
			startDate = new Date();
			System.out.println("Data settata");
		}
	}
	
	@OnClose
	public void close(Session session) {
		connectedUser--;
	}
	
	@OnError
	public void error(Throwable error) {
		
	}
	
	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("Ricevuto "+message);
		if(System.currentTimeMillis() > startDate.getTime()+60*1000) {
			System.out.println("Chiudo tutto");
			for(Session temp : session.getOpenSessions()) {
				try {
					temp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(message.charAt(0) != 'A' && message.charAt(0) != 'S') {
			for(Session temp : session.getOpenSessions()) {
				try {
					temp.getBasicRemote().sendText(message+" clienti:"+connectedUser);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("Messaggio rejectato: "+message);
		}
	}
	
}