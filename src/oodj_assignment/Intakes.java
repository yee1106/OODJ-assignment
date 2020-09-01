/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodj_assignment;

import java.util.ArrayList;

/**
 *
 * @author yeech
 */

public class Intakes {
	private String Intake_code_general;
	private ArrayList<Module> module_in_intake= new ArrayList<Module>();
	private ArrayList<Student> student_in_intake= new ArrayList<Student>();;

  public Intakes() {
  }

  
	public Intakes(String Intake_code_general) {
		this.Intake_code_general = Intake_code_general;
		module_in_intake = new ArrayList<Module>();
		student_in_intake = new ArrayList<Student>();
	}

	public String getIntake_code_general() {
		return Intake_code_general;
	}

	public void setIntake_code_general(String Intake_code_general) {
		this.Intake_code_general = Intake_code_general;
	}

	public ArrayList<Module> getModule_in_intake() {
		return module_in_intake;
	}

	public void setModule_in_intake(ArrayList<Module> module_in_intake) {
		this.module_in_intake = module_in_intake;
	}

	public ArrayList<Student> getStudent_in_intake() {
		return student_in_intake;
	}

	public void setStudent_in_intake(ArrayList<Student> student_in_intake) {
		this.student_in_intake = student_in_intake;
	}

	@Override
	public String toString() {
		return "Intakes{" + "Intake_code_general=" + Intake_code_general + ", module_in_intake=" + module_in_intake + ", student_in_intake=" + student_in_intake + '}';
	}
	
	
	
	
}






