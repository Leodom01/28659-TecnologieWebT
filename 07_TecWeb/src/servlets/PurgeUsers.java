package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import org.json.JSONException;
import org.json.JSONObject;

public class PurgeUsers extends Thread{

	public Map<Session, Date> myMap;
	public long waitTimeMs;
	
	public PurgeUsers(Map<Session, Date> userList, long waitTime) {
		this.myMap = userList;
		this.waitTimeMs = waitTime;
	}
	
	public void run() {
		while(true) {
			try {
				this.sleep(waitTimeMs);
				
				for(Session key : myMap.keySet()) {
					System.out.println(key.getId()+ " at time: "+ myMap.get(key));
					Date thisDate = myMap.get(key);
					if(thisDate.getTime() <= System.currentTimeMillis()-waitTimeMs) {
						System.out.println("Pushing away: "+key.getId());
						JSONObject jObj = new JSONObject();
						jObj.put("result", "session reset");
						key.getBasicRemote().sendText(jObj.toString());
						//key.close();
						myMap.remove(key);
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
