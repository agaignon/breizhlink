package src.main.java.model;

public class Url {
	
	private Long id;
	private String sourceUrl;
	private String shortUrl;
	private String password;
	
	public Url(Long id, String sourceUrl, String shortUrl, String password) {
		this.id = id;
		this.sourceUrl = sourceUrl;
		this.shortUrl = shortUrl;
		this.password = password;
	}
	
	public Url() {
		// Empty constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((shortUrl == null) ? 0 : shortUrl.hashCode());
		result = prime * result + ((sourceUrl == null) ? 0 : sourceUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Url other = (Url) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (shortUrl == null) {
			if (other.shortUrl != null)
				return false;
		} else if (!shortUrl.equals(other.shortUrl))
			return false;
		if (sourceUrl == null) {
			if (other.sourceUrl != null)
				return false;
		} else if (!sourceUrl.equals(other.sourceUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", sourceUrl=" + sourceUrl + ", shortUrl=" + shortUrl + ", password=" + password + "]";
	}	
	
}
