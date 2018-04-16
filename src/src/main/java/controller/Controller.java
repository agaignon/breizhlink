package src.main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.main.java.dao.DAO;
import src.main.java.model.Url;
import src.main.java.util.UrlGenerator;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();        
        DAO.loadUsedUrls();
        System.out.println("Loaded used URLs");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String page = null;
		String action = request.getParameter("action");

		if (action == null) {
			action = "showShortener";
		}

		switch (action) {
			case "showShortener":
				page = "views/shortener.jsp";
				break;

		}

		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String page = null;
		String action = request.getParameter("action");

		if (action == null) {
			page = "views/shortener.jsp";
		}
		
		switch (action) {
			case "doShorten":
				String originalUrl = request.getParameter("urlToShorten");
				String shortenedUrl = UrlGenerator.generateUrl();
				String password = request.getParameter("password");
				Url url = new Url(originalUrl, shortenedUrl, password);
				DAO.insertUrl(url);
				request.setAttribute("shortenedUrl", shortenedUrl);
				page = "views/result.jsp";
				break;
	
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

}
