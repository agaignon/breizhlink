package src.main.java.model;

public class Url {
	
	private String originalUrl;
	private String shortenedUrl;
	private String password;
	
	public Url(String originalUrl, String shortenedUrl, String password) {
		this.originalUrl = originalUrl;
		this.shortenedUrl = shortenedUrl;
		this.password = password;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public String getShortenedUrl() {
		return shortenedUrl;
	}

	public String getPassword() {
		return password;
	}
}
