package it.unibo.tw.webSockServers;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echoServer")
public class WebSocketServer{
	
	@OnOpen
	public void open(Session session) {
		
	}
	
	@OnClose
	public void close(Session session) {
		
	}
	
	@OnError
	public void error(Throwable error) {
		
	}
	
	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("Stampo risposta echo");
		try {
			session.getBasicRemote().sendText(message+"echoed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}