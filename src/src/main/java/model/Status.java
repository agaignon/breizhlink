package src.main.java.model;

public enum Status {
	
	INDIVIDUAL(1),
	COMPANY(2),
	ASSOCIATION(3);
	
	private int id;
	
	Status(int id) {
		this.id = id;
	}	
	
	public int getId() {
		return id;
	}

}
