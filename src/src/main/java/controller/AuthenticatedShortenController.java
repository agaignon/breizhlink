package src.main.java.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.main.java.dao.UrlDAO;
import src.main.java.model.AuthenticatedUrl;
import src.main.java.model.User;
import src.main.java.util.UrlGenerator;

/**
 * Servlet implementation class AuthenticatedShortenController
 */

public class AuthenticatedShortenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_URL = "http://localhost:8080/breizhlink/y/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticatedShortenController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    request.getRequestDispatcher("/views/auth_shorten.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String sourceUrl = request.getParameter("sourceUrl");
        String shortUrl = UrlGenerator.generateShortUrl();
        
        String pwdCheck = request.getParameter("pwdCheck");
        
        ArrayList<String> passwordList = new ArrayList<>();
        
        if (pwdCheck != null) {
            passwordList.add(request.getParameter("password1"));
            passwordList.add(request.getParameter("password2"));
            passwordList.add(request.getParameter("password3"));
        }
        
        System.out.println(pwdCheck);
        
        Boolean captcha = request.getParameter("captcha") != null;
        String mail = request.getParameter("mail").trim();
        
        String date = request.getParameter("date");
        
        LocalDate startDate = null;
        LocalDate endDate = null;
        
        if (date != null) {
            
            if (date.equals("fromto")) {
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                startDate = LocalDate.parse(request.getParameter("from"), formatter);
                endDate = LocalDate.parse(request.getParameter("to"), formatter);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                endDate = LocalDate.parse(request.getParameter("until"), formatter);
            }
        }
        
        User user = (User) request.getSession().getAttribute("user");
        
        AuthenticatedUrl authenticatedUrl = new AuthenticatedUrl(sourceUrl, shortUrl, "", passwordList, null, user, mail, LocalDate.now(), startDate, endDate, captcha);
        UrlDAO.insertAuthenticatedUrl(authenticatedUrl);
        request.setAttribute("fullShortUrl", BASE_URL + shortUrl);

        request.getRequestDispatcher("/views/result_link.jsp").forward(request, response);
	}

}
