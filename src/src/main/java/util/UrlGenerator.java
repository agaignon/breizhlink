package src.main.java.util;

import org.apache.commons.lang.RandomStringUtils;

import src.main.java.dao.UrlDAO;

public class UrlGenerator {	
	
	public static String generateShortUrl() {
		String generatedShortUrl;
		do {
			generatedShortUrl = RandomStringUtils.randomAlphanumeric(5);			
		} while (UrlDAO.shortUrlExists(generatedShortUrl));		
	   
	    return generatedShortUrl;
	}	
}
