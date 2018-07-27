package src.main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.main.java.dao.UrlDAO;
import src.main.java.model.AuthenticatedUrl;
import src.main.java.model.User;
import src.main.java.util.Pagination;

/**
 * Servlet implementation class UrlListController
 */

public class UrlListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UrlListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String pageString = request.getParameter("page");
	    int page = 0;
	    
	    if (pageString != null && !pageString.equals("") && pageString.matches("^\\d+$")) {
            page = Integer.parseInt(pageString);
	    }
	    
	    Pagination<AuthenticatedUrl> pagination = new Pagination<>(page, 10, UrlDAO.findAllByUser((User) request.getSession().getAttribute("user")));
	    
	    request.setAttribute("pagination", pagination);
        request.getRequestDispatcher("/views/urls.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
