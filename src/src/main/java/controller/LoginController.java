package src.main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.main.java.dao.UserDAO;
import src.main.java.model.User;

/**
 * Servlet implementation class LoginController
 */

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    if (request.getSession().getAttribute("user") == null) {
	        request.getRequestDispatcher("views/login.jsp").forward(request, response);
	    } else {
	        response.sendRedirect(request.getContextPath() + "/auth/shorten");
	    }
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    
	    if (UserDAO.userExists(username, password)) {
	        if (UserDAO.isAccountActivated(username)) {
	            
	            User user = UserDAO.findByUsername(username);
	            
	            System.out.println(user);
	            
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);
	            
	            response.sendRedirect(request.getContextPath() + "/auth/shorten");
	            return;
	            
	        } else {
	            request.setAttribute("account_not_activated", true);
	            request.getRequestDispatcher("views/login.jsp").forward(request, response);
	            return;
	        }
	    } else {
	        request.setAttribute("error", true);
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
	    }
	}

}
