package src.main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.main.java.dao.UrlDAO;
import src.main.java.model.Url;
import src.main.java.util.UrlGenerator;

/**
 * Servlet implementation class Controller
 */

public class ShortenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_URL = "http://localhost:8080/breizhlink/y/";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    
		request.getRequestDispatcher("views/shortener.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sourceUrl = request.getParameter("sourceUrl");
        String shortUrl = UrlGenerator.generateShortUrl();
        
        String pwdCheck = request.getParameter("pwdCheck");
        String password = "";
        
        if (pwdCheck != null) {
            password = request.getParameter("password");
        }

        System.out.println("'" + password + "'");

        Url url = new Url(sourceUrl, shortUrl, password);
        UrlDAO.insertUrl(url);
        request.setAttribute("fullShortUrl", BASE_URL + shortUrl);

        request.getRequestDispatcher("views/result_link.jsp").forward(request, response);
    }

}
