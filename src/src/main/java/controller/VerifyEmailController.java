package src.main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.main.java.dao.UserDAO;

/**
 * Servlet implementation class VerifyEmailController
 */

public class VerifyEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_URL = "http://localhost:8080/breizhlink/verify/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyEmailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestUrl = request.getRequestURL().toString();

		System.out.println(requestUrl);

		String[] urlParts = requestUrl.split(BASE_URL);

		// If url is well formed like http://localhost:8080/breizhlink/verify/10
		// else 404
		if (urlParts.length == 2 && urlParts[1].matches("^\\d+$")) {
			
			Long userId = Long.parseLong(urlParts[1]);
			boolean success = UserDAO.activateAccount(userId);
			
			if (success) {
				response.sendRedirect(request.getContextPath() + "/account_activated");
			} else {
				System.out.println("404");
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}

		} else {
			System.out.println("404");
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
