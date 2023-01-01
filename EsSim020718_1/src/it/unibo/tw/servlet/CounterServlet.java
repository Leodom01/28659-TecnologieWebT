package it.unibo.tw.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CounterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Cookie getCookie(HttpServletRequest req, String cookieName) {
		for(Cookie temp : req.getCookies()) {
			if(temp.getName().equals(cookieName)) {
				return temp;
			}
		}
		return null;
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] file = req.getParameter("files").split("/");
		long startTime = System.currentTimeMillis();
		int charCounter = 0;
		for(int i = 0; i<3; i++){
			try {
				BufferedReader fr = new BufferedReader(new FileReader(file[i]));
				String line; 
				while((line = fr.readLine()) != null){
					for(Character currentChar : line.toCharArray()){
						if(Character.isUpperCase(currentChar)){
							charCounter++;
						}
					}
				}
				fr.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		resp.getWriter().append(Integer.toString(charCounter)+","+Long.toString(System.currentTimeMillis()-startTime));
		return;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
