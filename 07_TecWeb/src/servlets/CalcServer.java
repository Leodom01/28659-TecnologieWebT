package servlets;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/actions")
public class CalcServer{
	
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
		//Should also catch parsing errors but nevermind
		try {
			System.out.println("WebSockserver ricevuto: "+message);
			JSONObject jObj = new JSONObject(message);
			Float firstItem = Float.parseFloat(jObj.getString("firstItem"));
			String operation = jObj.getString("operator");
			Float secondItem = Float.parseFloat(jObj.getString("secondItem"));
			
			Float result;
			if(operation.equals("+")) {
				result = firstItem+secondItem;
			}else if(operation.equals("-")) {
				result = firstItem-secondItem;
			}else if(operation.equals("*")) {
				result = firstItem*secondItem;
			}else if(operation.equals("/")) {
				result = firstItem/secondItem;
			}else {
				result = null;
			}
			
			jObj = new JSONObject();
			
			if(result != null) {
				session.getUserProperties().put("lastValidOperationRes", result);
				jObj.put("result", result.toString());
				session.getBasicRemote().sendText(jObj.toString());
			}else {
				jObj.put("result", "invalid oeprator");
				session.getBasicRemote().sendText(jObj.toString());
			}
			
		} catch (JSONException e) {
			System.err.println("Errore dentro handleMessage!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Errore dentro handleMessage!");
			e.printStackTrace();
		}
	}
	
}