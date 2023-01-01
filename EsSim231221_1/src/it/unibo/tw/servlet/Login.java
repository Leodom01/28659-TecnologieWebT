package it.unibo.tw.servlet;

import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import it.unibo.tw.beans.GruppoUtenti;
import it.unibo.tw.beans.Utente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.servlet.*;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7406323049563558798L;

	public static List<JSONObject> readJsonFile(String filePath) {
        JSONParser parser = new JSONParser();
        List<JSONObject> jsonObjects = new ArrayList<>();
        try {
            FileReader reader = new FileReader(filePath);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                jsonObjects.add(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObjects;
    }
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ArrayList<String[]> userAndPw = new ArrayList<>();
		this.getServletContext().setAttribute("usrPwUsr", userAndPw);
		JsonArray jArr = new JsonArray();
		
		List<JSONObject> jObjects = readJsonFile("/gruppi.json");
		for(JSONObject temp : jObjects) {
			String group = (String) temp.get("group");
			String[] utenti = ((String)temp.get("users")).split(",");
			for(String curr : utenti) {
				String[] strList = new String[3];
				strList[0] = curr;
				strList[1] = "password";
				strList[2] = group;
				userAndPw.add(strList);
			}
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		ArrayList<String[]> userAndPw = (ArrayList<String[]>)this.getServletContext().getAttribute("usrPwUsr");
		for(String[] temp : userAndPw) {
			if(username.equals(temp[0]) && password.equals(temp[1])) {
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/welcome.jsp");
				rd.forward(req, res);
				return;
			}
		}
		
		//Login failed
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
		rd.forward(req, res);
		return;

	}
}
