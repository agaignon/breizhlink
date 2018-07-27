package src.main.java.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.main.java.dao.StatsDAO;
import src.main.java.dao.UrlDAO;
import src.main.java.model.Stats;
import src.main.java.model.Url;

/**
 * Servlet implementation class LinkRedirectController
 */

public class LinkRedirectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_URL = "http://localhost:8080/breizhlink/y/";
	
	private static final String[] IP_HEADER_CANDIDATES = { 
	        "X-Forwarded-For",
	        "Proxy-Client-IP",
	        "WL-Proxy-Client-IP",
	        "HTTP_X_FORWARDED_FOR",
	        "HTTP_X_FORWARDED",
	        "HTTP_X_CLUSTER_CLIENT_IP",
	        "HTTP_CLIENT_IP",
	        "HTTP_FORWARDED_FOR",
	        "HTTP_FORWARDED",
	        "HTTP_VIA",
	        "REMOTE_ADDR"
	};
       
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
		
		System.out.println(requestUrl);
		
		String[] urlParts = requestUrl.split(BASE_URL);
		
		// If url is well formed like http://localhost:8080/breizhlink/y/Bi6oR
		// else 404
		if (urlParts.length == 2 && urlParts[1].length() == 5) {
			
			String shortUrl = urlParts[1];
			Url url = UrlDAO.getUrlWithShortUrl(shortUrl);			
			HttpSession session = request.getSession();
			session.setAttribute("url", url);	
			
			Stats stats = new Stats();
			stats.setDate(LocalDate.now());
			stats.setIpAddress(getClientIpAddress(request));
			StatsDAO.insertOrIncrementStats(url.getId(), stats);
			
			System.out.println(url);
			
			// If Url or AuthenticatedUrl needs check i.e. password(s), captcha, date, etc
			if (url.needsCheck()) {
				System.out.println(request.getContextPath());
				response.sendRedirect(request.getContextPath() + "/check");
				
			} else {				
				response.sendRedirect(url.getSourceUrl());
				// Invalidate session
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
	
    private static String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

}
