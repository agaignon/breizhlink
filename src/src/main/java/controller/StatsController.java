package src.main.java.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.main.java.dao.StatsDAO;

/**
 * Servlet implementation class StatsController
 */

public class StatsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String urlIdString = request.getParameter("id");
	    Long urlId;
	    
	    System.out.println(urlIdString);
	    
	    if (urlIdString != null && !urlIdString.equals("") && urlIdString.matches("^\\d+$")) {
	        
	        urlId = Long.parseLong(urlIdString);
	        Map<String, Integer> dataMap = StatsDAO.findDateAndSumNbClickByUrlId(urlId);
	        
	        StringBuilder jsonString = new StringBuilder();
	        boolean isFirst = true;
	        jsonString.append("[");
	        for (String date : dataMap.keySet()) {
	            if (!isFirst) {
	                jsonString.append(",");
	            }
	            jsonString.append("{\"date\":\"" + date + "\", \"nbClick\":" + dataMap.get(date) + "}");
	            
	            isFirst = false;
	        }
	        jsonString.append("]");
	        
	        request.setAttribute("jsonString", jsonString);
	        request.getRequestDispatcher("/views/stats.jsp").forward(request, response);
	        
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
