package src.main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.main.java.dao.UrlDAO;
import src.main.java.model.Url;

/**
 * Servlet implementation class LinkRedirectController
 */
public class LinkRedirectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_URL = "http://localhost:8080/Breizhlink/y/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LinkRedirectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUrl = request.getRequestURL().toString();		
		String[] urlParts = requestUrl.split(BASE_URL);
		
		// If url is well formed like http://localhost:8080/Breizhlink/y/Bi6oR
		// else 404
		if (urlParts.length == 2 && urlParts[1].length() == 5) {
			
			String shortUrl = urlParts[1];
			Url url = UrlDAO.getUrlWithShortUrl(shortUrl);			
			HttpSession session = request.getSession();
			session.setAttribute("url", url);			
			
			// If Url or AuthenticatedUrl needs check i.e. password(s), captcha, date, etc
			if (url.needsCheck()) {
				// TODO
			} else {				
				response.sendRedirect(url.getSourceUrl());
			}
			
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}