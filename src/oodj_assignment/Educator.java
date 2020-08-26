package oodj_assignment;

import java.util.ArrayList;
import java.util.HashMap;

public class Educator extends User {

	private HashMap<String, String> intake_module;
	private String email;

	public Educator() {
	}

	public Educator(String email) {
		intake_module = new HashMap<String, String>();
		this.email = email;
	}

	public Educator(String name, String password, String ID, String email) {
		super(ID, password, name);
		intake_module = new HashMap<String, String>();
		this.email = email;
	}

	public HashMap<String, String> getIntake_module() {
		return intake_module;
	}

	public void setIntake_module(HashMap<String, String> intake_module) {
		this.intake_module = intake_module;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

