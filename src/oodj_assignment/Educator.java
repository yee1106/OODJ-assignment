package oodj_assignment;

import java.util.ArrayList;


public class Educator extends User {

	private ArrayList<String>intake_module= new ArrayList<>();
	private String email;

	public Educator() {
    
	}

	public Educator(String email) {
		
		intake_module = new ArrayList<>();
		this.email = email;
	}

	public Educator(String name, String password, String ID, String email) {
		super(ID, password, name);
		
		intake_module = new ArrayList<>();
		this.email = email;
	}



	public ArrayList<String> getIntake_module() {
		return intake_module;
	}

	public void setIntake_module(ArrayList<String> intake_module) {
		this.intake_module = intake_module;
	}

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		
		return "Educator{" + "intake_module=" + intake_module + ", email=" + email + ".ID"+super.getID();
	
	}

	

}








