package oodj_assignment;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {

	private String intake_code;
	private String email;
	private ArrayList<Module> module_taken = new ArrayList<>();
	private HashMap<String, String> module_grade;
	

	public Student(){
	}

	public Student(String intake_code, String email) {
		this.email = email;
		this.intake_code = intake_code;
		module_taken = new ArrayList<Module>();
		module_grade = new HashMap<String, String>();
	}

	public Student(String intake_code, String ID, String password, String name, String email) {
		super(ID, password, name);
		this.intake_code = intake_code;
		this.email = email;
		module_taken = new ArrayList<Module>();
		module_grade = new HashMap<String, String>();
	}

	public String getIntake_code() {
		return intake_code;
	}

	public void setIntake_code(String intake_code) {
		this.intake_code = intake_code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Module> getModule_taken() {
		return module_taken;
	}

	public void setModule_taken(ArrayList<Module> module_taken) {
		this.module_taken = module_taken;
	}

	public HashMap<String, String> getModule_grade() {
		return module_grade;
	}

	public void setModule_grade(HashMap<String, String> module_grade) {
		this.module_grade = module_grade;
	}

	@Override
	public String toString() {
		return "Student{" + "intake_code=" + intake_code + ", email=" + email + ", module_taken=" + module_taken + ", module_grade=" + module_grade + '}';
	}

}



