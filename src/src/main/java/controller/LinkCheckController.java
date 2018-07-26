package src.main.java.controller;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.captcha.botdetect.web.servlet.SimpleCaptcha;

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
		
	    HttpSession session = request.getSession();
        Url url = (Url) session.getAttribute("url");
        
        if (url instanceof AuthenticatedUrl) {
            
            if (((AuthenticatedUrl) url).getEndDate() != null && LocalDate.now().isAfter(((AuthenticatedUrl) url).getEndDate())) {
                request.setAttribute("expired_link", true);
            }
            
            if (((AuthenticatedUrl) url).getStartDate() != null && LocalDate.now().isBefore(((AuthenticatedUrl) url).getStartDate())) {
                request.setAttribute("toosoon_link", true);
            }
            
            if (!((AuthenticatedUrl) url).needsFormValidation()) {
                response.sendRedirect(url.getSourceUrl());
                return;
            }
        }
        
        request.setAttribute("urlType", url.getClass().getSimpleName());
		request.getRequestDispatcher("views/link_check.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Url url = (Url) session.getAttribute("url");
		
		// If it is an AuthenticatedUrl object
		if (url instanceof AuthenticatedUrl) {
		    
		    if (request.getParameter("password1") != null 
		            && !BCrypt.checkpw(request.getParameter("password1"), ((AuthenticatedUrl) url).getPasswordList().get(0))) {
		        
		        request.setAttribute("wrong_password1", true);
		    }
		    
		    if (request.getParameter("password2") != null 
                    && !BCrypt.checkpw(request.getParameter("password2"), ((AuthenticatedUrl) url).getPasswordList().get(1))) {
                
                request.setAttribute("wrong_password2", true);
            }
		    
		    if (request.getParameter("password3") != null 
                    && !BCrypt.checkpw(request.getParameter("password3"), ((AuthenticatedUrl) url).getPasswordList().get(2))) {
                
                request.setAttribute("wrong_password3", true);
            }
		    
		    if (request.getParameter("mail") != null && !request.getParameter("mail").equals(((AuthenticatedUrl) url).getMail())) {
		        
		        request.setAttribute("wrong_mail", true);
		    }
		    
		    if (request.getParameter("captchaCode") != null) {
		        SimpleCaptcha captcha = SimpleCaptcha.load(request, "captcha");
	            boolean captchaValidated = captcha.validate(request.getParameter("captchaCode"));
	            if (!captchaValidated) {
	                
	                request.setAttribute("captcha_failed", true);
	            }
		    }
            
            if (!request.getAttributeNames().hasMoreElements()) {
                response.sendRedirect(url.getSourceUrl());
                return;
            }
            
		} else {
			String password = request.getParameter("password");
			System.out.println("Password : '" + password + "'");
			System.out.println("Url Pass : '" + url.getPassword() + "'");
			
			if (BCrypt.checkpw(password, url.getPassword())) {
				response.sendRedirect(url.getSourceUrl());
				return;
			}
			// Failed check so re-check
	        request.setAttribute("wrong_password", true);
		}
		
		request.setAttribute("urlType", url.getClass().getSimpleName());
		request.getRequestDispatcher("/views/link_check.jsp").forward(request, response);
	}

}
