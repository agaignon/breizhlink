package src.main.java.model;

import java.time.LocalDate;
import java.util.List;

public class AuthenticatedUrl extends Url {
	
	private List<String> passwordList;
	private List<Stats> statsList;
	private User user;
	private String mail;
	private LocalDate creationDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean captcha;
	
	public AuthenticatedUrl(String sourceUrl, String shortUrl, String password, List<String> passwordList,
			List<Stats> statsList, User user, String mail, LocalDate creationDate, LocalDate startDate, LocalDate endDate,
			Boolean captcha) {
		super(sourceUrl, shortUrl, password);
		this.passwordList = passwordList;
		this.statsList = statsList;
		this.user = user;
		this.mail = mail;
		this.creationDate = creationDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.captcha = captcha;
	}
	
	public AuthenticatedUrl(Long id, String sourceUrl, String shortUrl, String password, List<String> passwordList,
			List<Stats> statsList, User user, String mail, LocalDate creationDate, LocalDate startDate, LocalDate endDate,
			Boolean captcha) {
		super(id, sourceUrl, shortUrl, password);
		this.passwordList = passwordList;
		this.statsList = statsList;
		this.user = user;
		this.mail = mail;
		this.creationDate = creationDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.captcha = captcha;
	}
	
	public AuthenticatedUrl() {
		// Empty constructor
	}

	public List<String> getPasswordList() {
		return passwordList;
	}

	public void setPasswordList(List<String> passwordList) {
		this.passwordList = passwordList;
	}

	public List<Stats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<Stats> statsList) {
		this.statsList = statsList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Boolean getCaptcha() {
		return captcha;
	}

	public void setCaptcha(Boolean captcha) {
		this.captcha = captcha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((captcha == null) ? 0 : captcha.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((passwordList == null) ? 0 : passwordList.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((statsList == null) ? 0 : statsList.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticatedUrl other = (AuthenticatedUrl) obj;
		if (captcha == null) {
			if (other.captcha != null)
				return false;
		} else if (!captcha.equals(other.captcha))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (passwordList == null) {
			if (other.passwordList != null)
				return false;
		} else if (!passwordList.equals(other.passwordList))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (statsList == null) {
			if (other.statsList != null)
				return false;
		} else if (!statsList.equals(other.statsList))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "\n" 
				+ "AuthenticatedUrl [passwordList=" + passwordList + ", statsList=" + statsList + ", user=" + user
				+ ", mail=" + mail + ", creationDate=" + creationDate + ", startDate=" + startDate + ", endDate="
				+ endDate + ", captcha=" + captcha + "]";
	}	
	
	public Boolean needsPasswordCheck() {
		
	    for (String password : passwordList) {
	        if (!password.equals("")) return true;
	    }
	    
	    return false;
	}
	
	@Override
	public Boolean needsCheck() {
	    
	    return needsPasswordCheck() || !mail.equals("") || endDate != null || captcha;
	}
	
	public int passwordListSize() {
	    return passwordList.size();
	}
	
public Boolean needsFormValidation() {
        
        return needsPasswordCheck() || !mail.equals("") || captcha;
    }

}
