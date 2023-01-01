package it.unibo.tw.webSockServers;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

@ServerEndpoint("/tris")
public class WebSocketServer{
	
	//Player 1 X, 2 O
	static int turn; //0 if waiting player, 1 if player 1 turn, 2 if player turn 2, -1 player 1 won, -2 player 2 won
	static int connectedPlayers;
	static char table[][] = new char[3][3];
	static Session players[] = new Session[2];

	public boolean winnerAvailable(){
		boolean winner = false;
		//Check rows
		for(int row = 0; row<3; row++){
			winner = winner || (table[row][0] == table[row][1] && table[row][1] == table[row][2] && (table[row][1] == 'X' || table[row][1] == 'O'));
		}
		//Check cols
		for(int col = 0; col<3; col++){
			winner = winner || (table[0][col] == table[1][col] && table[1][col] == table[2][col] && (table[2][col] == 'X' || table[2][col] == 'O'));
		}
		//Check diagonal
		winner = winner || (table[0][0] == table[1][1] && table[1][1] == table[2][2] && (table[1][1] == 'X' || table[1][1] == 'O'));
		winner = winner || (table[0][2] == table[1][1] && table[1][1] == table[2][0] && (table[1][1] == 'X' || table[1][1] == 'O'));

		return winner;
	}

	public boolean isTie(){
		for(int row = 0; row<3; row++){
			for(int col = 0; col<3; col++){
				if(table[row][col] != 'X' && table[row][col] != 'O'){
					return false;
				}
			}
		}
		return true;
	}

	public String tableToString(){
		return table[0][0]+","+table[0][1]+","+table[0][2]+","+table[1][0]+","+table[1][1]+","+table[1][2]+","+table[2][0]+","+table[2][1]+","+table[2][2];

	}

	@OnOpen
	public void open(Session session) {
		System.out.println("Utente connesso!");
		if(connectedPlayers<2){
			connectedPlayers++;
			JSONObject jObj = new JSONObject();
			if(connectedPlayers == 1) {
				try {
					players[0] = session;
					turn = 0;
					jObj.put("turn", "no"); //no, yes, won, lost
					jObj.put("table", this.tableToString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				players[1] = session;
				turn = 1;
				try {
					jObj.put("turn", "no");
					jObj.put("table", this.tableToString());
					session.getBasicRemote().sendText(jObj.toString());
					
					jObj.put("turn", "yes");
					jObj.put("table", this.tableToString());
					players[0].getBasicRemote().sendText(jObj.toString());
				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
			
			return;
		}else{
			try {
				session.getBasicRemote().sendText("Troppi utenti connessi");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@OnClose
	public void close(Session session) {
		connectedPlayers--;
	}
	
	@OnError
	public void error(Throwable error) {
		
	}
	
	//Mi aspetto due numeri separati da una virgola, il primo la riga, il secondo la colonna
	@OnMessage
	public void handleMessage(String message, Session session) {
		int row = Integer.parseInt(message.split(",")[0]);
		int col = Integer.parseInt(message.split(",")[1]);
		char toAdd = (session.equals(players[0])) ? 'X' : 'O';

		int player = (session.equals(players[0])) ? 0 : 1;
		int otherPlayer = Math.abs(player-1);

		table[row][col] = toAdd;

		if(winnerAvailable()){
			
			if(player == 1){
				turn = -1;
			}else{
				turn = -2;
			}
			JSONObject jObj = new JSONObject();
			try {
				jObj.put("turn", "won");
				jObj.put("table", this.tableToString());
				players[player].getBasicRemote().sendText(jObj.toString());
				jObj.put("turn", "lost");
				jObj.put("table", this.tableToString());
				players[otherPlayer].getBasicRemote().sendText(jObj.toString());
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(!isTie()){
			if(player == 1){
				turn = 2;
			}else{
				turn = 1;
			}
			JSONObject jObj = new JSONObject();
			try {
				jObj.put("turn", "no");
				jObj.put("table", this.tableToString());
				players[player].getBasicRemote().sendText(jObj.toString());
				jObj.put("turn", "yes");
				jObj.put("table", this.tableToString());
				players[otherPlayer].getBasicRemote().sendText(jObj.toString());
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			JSONObject jObj = new JSONObject();
			try {
				jObj.put("turn", "tie");
				jObj.put("table", this.tableToString());
				players[0].getBasicRemote().sendText(jObj.toString());
				players[1].getBasicRemote().sendText(jObj.toString());
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}