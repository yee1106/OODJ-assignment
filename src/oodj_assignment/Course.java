package oodj_assignment;

import java.util.ArrayList;

public class Course {

	private String course_name;
	private String short_course_name;  //use for generate intake code
	private ArrayList<Module> Module_offer = new ArrayList<Module>();
	private ArrayList<Intakes> Intake= new 	ArrayList<Intakes>();

  public Course() {
    Intake = new 	ArrayList<Intakes>();
    Module_offer = new ArrayList<Module>();
  }
	
	

	public Course(String course_name, String short_course_name) {
		this.course_name = course_name;
		this.short_course_name= short_course_name;
		Module_offer = new ArrayList<Module>();
		Intake = new 	ArrayList<Intakes>();
	}

	public String getCourse_name() {
		return course_name;
	}

	public ArrayList<Module> getModule_offer() {
		return Module_offer;
	}

	public ArrayList<Intakes> getIntake() {
		return Intake;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getShort_course_name() {
		return short_course_name;
	}

	public void setShort_course_name(String short_course_name) {
		this.short_course_name = short_course_name;
	}

	

	public void setModule_offer(ArrayList<Module> Module_offer) {
		this.Module_offer = Module_offer;
	}

	public void setIntake(ArrayList<Intakes> Intake) {
		this.Intake = Intake;
	}

	@Override
	public String toString() {
		return "Course{" + "course_name=" + course_name + ", short_course_name=" + short_course_name + ", Module_offer=" + Module_offer + ", Intake=" + Intake + '}';
	}

	

}








