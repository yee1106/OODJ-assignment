package oodj_assignment;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {

	private String intake_code;
	private String gender;
	private String nationality;
	private String email;
	private ArrayList<Module> module_taken = new ArrayList<>();
	private HashMap<String, String> module_grade;
	

	public Student(){
	}

	public Student(String intake_code, String email, String gender, String nationality) {
		this.email = email;
		this.intake_code = intake_code;
		this.gender = gender;
		this.nationality = nationality;
		module_taken = new ArrayList<Module>();
		module_grade = new HashMap<String, String>();
	}

	public Student(String intake_code, String ID, String password, String name, String email , String gender, String nationality) {
		super(ID, password, name);
		this.gender = gender;
		this.nationality = nationality;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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
		return "Student{" + "intake_code=" + intake_code + ", gender=" + gender + ", nationality=" + nationality + ", email=" + email + ", module_taken=" + module_taken + ", module_grade=" + module_grade + '}';
	}

	
}




