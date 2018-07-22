package src.main.java.model;

public class User {
	
	private Long id;
	private String username;
	private String password;
	private String mail;
	private Status status;
	private Boolean accountActivated;
	
	public User(Long id, String username, String password, String mail, Status status, Boolean accountActivated) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.status = status;
		this.accountActivated = accountActivated;
	}
	
	public User(String username, String password, String mail, Status status) {
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.status = status;
	}
	
	public User() {
		// Empty constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Boolean getAccountActivated() {
		return accountActivated;
	}

	public void setAccountActivated(Boolean accountActivated) {
		this.accountActivated = accountActivated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountActivated == null) ? 0 : accountActivated.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (accountActivated == null) {
			if (other.accountActivated != null)
				return false;
		} else if (!accountActivated.equals(other.accountActivated))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (status != other.status)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", mail=" + mail + ", status="
				+ status + ", accountActivated=" + accountActivated + "]";
	}

}
