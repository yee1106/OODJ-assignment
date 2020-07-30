package oodj_assignment;

import java.util.ArrayList;
import java.util.HashMap;

public class Educator extends User {

	private HashMap<String, String> intake_module;
	private String email;

	public Educator() {
	}

	public Educator(HashMap<String, String> intake_module, String email) {
		this.intake_module = intake_module;
		this.email = email;
	}

	public Educator(String name, String password, String ID, String email, HashMap<String, String> intake_module) {
		super(ID, password, name);
		this.intake_module = intake_module;
		this.email = email;
	}

	public HashMap<String, String> getIntake_module() {
		return intake_module;
	}

	public void setModules(HashMap<String, String> intake_module) {
		this.intake_module = intake_module;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

