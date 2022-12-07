package it.unibo.tw.web;

import it.tecnologieweb.app.HelloWorld;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HelloWorldServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	doGetWithCookies(request, response);
    	//doGetWithSessions(request, response);

    }
    
	public void doGetWithCookies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    	
	    	// some delay... (as it usually happens in complex Web apps)
	    	try {
				Thread.sleep(2000);
			}
	    	catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	
			// retrieve former values, if any
	    	String name = null;
	    	for(Cookie temp : request.getCookies()) {
	    		if(temp.getName().equals("02UserName")) {
	    			name = temp.getValue();
	    			break;
	    		}
	    	}
	    	
	    	int pluto = Integer.parseInt("pippo");
	    	pluto = pluto +5;
	    	PrintWriter out = response.getWriter();
	
	        out.println("<html>");
	        
		        out.println("<head>");
		
	            // title
			    out.println("<title>Hello Wolrd Servlet</title>");
		
			    // style
			    out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
		
			    out.println("</head>");
		
			    out.println("<body>");
	
		        out.println("This is the servlet output.<br/><br/>");
		     
		        out.println("<form method=\"post\">");
		        if(name == null) {
		        	System.out.println("Name was null");
		        	out.println("<input type=\"text\" name=\"name\" value=\"Ora manda\"/>");
		        }else {
		        	System.out.println("Name was not null");
		        	out.println("<input type=\"text\" name=\"name\" value=\"Manda nuovo nome "+name+"\"/>");
		        }
		        out.println("<input type=\"submit\" name=\"post\" value=\"Invia nome!\"/>");
			    out.println("</form>");   
		        out.println("</body>");
		        
	        out.println("</html>");
	
	    }
    
	public void doGetWithSessions(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	// some delay... (as it usually happens in complex Web apps)
    	try {
			Thread.sleep(2000);
		}
    	catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
		// retrieve former values, if any
    	String name = (String) request.getSession(true).getAttribute("02UserName");
    	

    	PrintWriter out = response.getWriter();

        out.println("<html>");
        
	        out.println("<head>");
	
            // title
		    out.println("<title>Hello Wolrd Servlet</title>");
	
		    // style
		    out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
	
		    out.println("</head>");
	
		    out.println("<body>");

	        out.println("This is the servlet output.<br/><br/>");
	     
	        out.println("<form method=\"post\">");
	        if(name == null) {
	        	System.out.println("Name was null");
	        	out.println("<input type=\"text\" name=\"name\" value=\"Manda\"/>");
	        }else {
	        	System.out.println("Name was not null");
	        	out.println("<input type=\"text\" name=\"name\" value=\"Manda nuovo nome "+name+"\"/>");
	        }
	        out.println("<input type=\"submit\" name=\"post\" value=\"Invia nome!\"/>");
		    out.println("</form>");   
	        out.println("</body>");
	        
        out.println("</html>");

    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//doPostWithCookies(request, response);
		doPostWithSessions(request, response);
		
    }

	public void doPostWithSessions(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			String name = request.getParameter("name");
			System.out.println("Ho ricevuto "+name+" come parametro");
			PrintWriter out = response.getWriter();
			System.out.println("Setto il nuovo nome come sessione");
	    	
	    	request.getSession().setAttribute("02UserName", name);
			System.out.println("Ecco il nuovo nome dalla session var: "+(String) request.getSession().getAttribute("02UserName"));
	    	
	        out.println("<html>");
	        System.out.println("Iniziato a scrivere file");
		        out.println("<head>");
		
	            // title
			    out.println("<title>Hello Wolrd Servlet</title>");
		
			    // style
			    out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
		
			    out.println("</head>");
		
			    out.println("<body>");
	
			    
			    
		        out.println("<br/>");
		        out.println("<br/>");
		        out.println("<hr/>");
		        out.println("<br/>");
		        System.out.println("Stampo p");
		        out.println("<p>Look my friend, io ho ricevuto: "+name+", ora esco ciao ciao!</p>");
			        
		        out.println("</body>");
		        
	        out.println("</html>");
	    }
	
	public void doPostWithCookies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String name = request.getParameter("name");
		System.out.println("Ho ricevuto "+name+" come parametro");
		PrintWriter out = response.getWriter();
		System.out.println("Ottenuto il writer");
    	
    	boolean updatedCookie = false;
    	for(Cookie temp : request.getCookies()) {
    		System.out.println("Trovato cookie "+temp.getName()+" con valore settato a: "+temp.getValue());
    		if(temp.getName().equals("02UserName")) {
    			temp.setValue(name);
    			updatedCookie = true;
    		}
    	}
    	System.out.println("Finito loop cookies");
    	if(!updatedCookie) {
    		System.out.println("Aggiungo nuovo cookie con nome: "+name);
    		Cookie newCook = new Cookie("02UserName", name);
    		newCook.setSecure(true);
    		newCook.setMaxAge(-1);
    		newCook.setPath("/");
    		System.out.println("Sto per aggiugnere cookie");
    		response.addCookie(newCook);
    		System.out.println("Creato nuovo cookie");
    	}
    	System.out.println("Finito di aggiungere cookie o non aggiunto");
        out.println("<html>");
        System.out.println("Iniziato a scrivere file");
	        out.println("<head>");
	
            // title
		    out.println("<title>Hello Wolrd Servlet</title>");
	
		    // style
		    out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
	
		    out.println("</head>");
	
		    out.println("<body>");

		    
		    
	        out.println("<br/>");
	        out.println("<br/>");
	        out.println("<hr/>");
	        out.println("<br/>");
	        System.out.println("Stampo p");
	        out.println("<p>Look my friend, io ho ricevuto: "+name+", ora esco ciao ciao!</p>");
		        
	        out.println("</body>");
	        
        out.println("</html>");
    }
		
}
