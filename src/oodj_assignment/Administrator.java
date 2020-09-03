package oodj_assignment;

import java.util.Date;


public class Administrator extends User {

	public Administrator() {
	}

	public Administrator(String ID, String name, Date login, Date logout, String mode) {
		super(ID, name, login, logout, mode);
	}

	public Administrator(String ID, String password, String name) {
		super(ID, password, name);
	}
	
}




