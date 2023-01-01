package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/actionsCollab")
public class CalcServerCollab{
	
	public Float firstItem = (float) 1; 
	public Float secondItem = (float) 1,
			result = (float) 1;
	public String operand = "*";
	public Map<Session, Date> connectedUsers = new TreeMap<>();
	
	private PurgeUsers myPurger = new PurgeUsers(connectedUsers, 30000);
	
	public synchronized float calculate() {
		Float result;
		if(this.operand.equals("+")) {
			result = firstItem+secondItem;
		}else if(this.operand.equals("-")) {
			result = firstItem-secondItem;
		}else if(this.operand.equals("*")) {
			result = firstItem*secondItem;
		}else if(this.operand.equals("/")) {
			result = firstItem/secondItem;
		}else {
			result = null;
		}
		return result;
	}
	
	public synchronized void setFirstItem(Float firstItem) {
		this.firstItem = firstItem;
	}

	public synchronized void setSecondItem(Float secondItem) {
		this.secondItem = secondItem;
	}

	public synchronized void setOperand(String operand) {
		this.operand = operand;
	}

	@OnOpen
	public void open(Session session) throws JSONException, IOException {
		if(!myPurger.isAlive()) {
			myPurger.start();
		}
		System.out.println(session.getId()+" opened and added0");
		JSONObject jObj = new JSONObject();
		jObj.put("firstItem", firstItem.toString());
		jObj.put("secondItem", secondItem.toString());
		jObj.put("operand", operand);
		jObj.put("result", new Float(calculate()).toString());
		session.getBasicRemote().sendText(jObj.toString());
		System.out.println("Now adding to map"+session.getId());
		//connectedUsers.put(session, new Date(System.currentTimeMillis()));
		System.out.println(session.getId()+" opened and added5FINAL");
	}
	
	@OnClose
	public void close(Session session) {
		//connectedUsers.remove(session);
		System.out.println(session.getId()+" session closed");
	}
	
	@OnError
	public void error(Throwable error) {
		
	}
		
	@OnMessage
	public synchronized void handleMessage(String message, Session session) {
		//Should also catch parsing errors but nevermind
		try {
			System.out.println("WebSockserver ricevuto: "+message);
			JSONObject jObj = new JSONObject(message);
			Float firstItem = Float.parseFloat(jObj.getString("firstItem"));
			String operation = jObj.getString("operator");
			Float secondItem = Float.parseFloat(jObj.getString("secondItem"));
			
			if(firstItem != null) {
				setFirstItem(firstItem);
			}
			if(secondItem != null) {
				setSecondItem(secondItem);
			}
			if(operation != null) {
				setOperand(operation);
			}
			result = calculate();
			
			jObj = new JSONObject();
			
			jObj.put("firstItem", firstItem.toString());
			jObj.put("secondItem", secondItem.toString());
			jObj.put("operand", operation);
			if(result != null) {
				jObj.put("result", result.toString());
			}else {
				jObj.put("result", "invalid request");
			}
			System.out.println("Sto per mandare a "+session.getOpenSessions().size()+" sessioni");
			
			for(Session temp : session.getOpenSessions()) {
				temp.getBasicRemote().sendText(jObj.toString());
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