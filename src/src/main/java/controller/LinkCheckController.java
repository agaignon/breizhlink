package src.main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.main.java.model.AuthenticatedUrl;
import src.main.java.model.Url;
import src.main.java.util.BCrypt;

/**
 * Servlet implementation class LinkCheckController
 */

public class LinkCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LinkCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = "views/link_check.jsp";
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Url url = (Url) session.getAttribute("url");
		
		// If it is an AuthenticatedUrl object
		if (url instanceof AuthenticatedUrl) {
			// TODO
		} else {
			String password = request.getParameter("password");
			System.out.println("Password : '" + password + "'");
			System.out.println("Url Pass : '" + url.getPassword() + "'");
			
			if (BCrypt.checkpw(password, url.getPassword())) {
				response.sendRedirect(url.getSourceUrl());
				return;
			}
		}
		
		// Failed check so re-check
		request.setAttribute("error", true);
		request.getRequestDispatcher("views/link_check.jsp").forward(request, response);
	}

}
