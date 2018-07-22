package src.main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.main.java.dao.UserDAO;
import src.main.java.model.Status;
import src.main.java.model.User;
import src.main.java.service.EmailService;

/**
 * Servlet implementation class CreateAccountController
 */

public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("views/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password");
		String mail = request.getParameter("mail").trim();
		String status = request.getParameter("status");
		
		System.out.println(username);
		System.out.println(password);
		System.out.println(mail);
		System.out.println(status);
		
		if ((username.equals("") || username == null) || (password.equals("") || password == null)
				|| (mail.equals("") || mail == null) || (status.equals("") || status == null)) {
			
			request.setAttribute("error", true);
			request.getRequestDispatcher("views/signup.jsp").forward(request, response);
			return;
		}
		
		User user = new User(username, password, mail, Status.valueOf(status));
		user = UserDAO.insertUser(user);
		EmailService.sendMail(user);
		request.getRequestDispatcher("views/check_email.jsp").forward(request, response);
		
		
	}

}
