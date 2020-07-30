package oodj_assignment;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
	private String intake_code; 
	private ArrayList<Module>module_taken;
	private HashMap<String, String>module_grade;

        public Student() {
        }

	public Student(String intake_code) {
		this.intake_code = intake_code;
		module_taken = new ArrayList<Module>();
		module_grade = new HashMap<String, String>();
	}

	public Student(String intake_code, String ID, String password, String name) {
		super(ID, password, name);
		this.intake_code = intake_code;
		module_taken = new ArrayList<Module>();
		module_grade = new HashMap<String, String>();
	}

	

	

	
	

	
	
}








