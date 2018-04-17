package src.main.java.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;

public class UrlGenerator {	
	
	private static Set<String> usedUrls = new HashSet<>();	
	
	public static void setUsedUrls(Set<String> usedUrls) {
		UrlGenerator.usedUrls = usedUrls;
	}
	
	public static String generateUrl() {
		String generatedUrl;
		do {
			generatedUrl = RandomStringUtils.randomAlphanumeric(5);			
		} while (usedUrls.contains(generatedUrl));		
	    usedUrls.add(generatedUrl);
	    return generatedUrl;
	}	
}
