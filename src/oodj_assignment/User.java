package oodj_assignment;

public class User {

	private String ID;
	private String password;
	private String name;

	public User() {
	}

	public User(String ID, String password, String name) {
		this.ID = ID;
		this.password = password;
		this.name = name;
	}

	public void setID(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

