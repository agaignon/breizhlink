package src.main.java.model;

import java.util.Date;

public class Stats {
	
	private int nbClick;
	private Date date;
	private String ipAddress;
	
	public Stats(int nbClick, Date date, String ipAddress) {
		this.nbClick = nbClick;
		this.date = date;
		this.ipAddress = ipAddress;
	}
	
	public Stats() {
		// Empty constructor
	}

	public int getNbClick() {
		return nbClick;
	}

	public void setNbClick(int nbClick) {
		this.nbClick = nbClick;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + nbClick;
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
		Stats other = (Stats) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (nbClick != other.nbClick)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stats [nbClick=" + nbClick + ", date=" + date + ", ipAddress=" + ipAddress + "]";
	}	

}
