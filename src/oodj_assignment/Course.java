package oodj_assignment;

import java.util.ArrayList;

public class Course {
	private String course_name;
	private Level course_level;
	private ArrayList<Module>Module_offer;

	public Course(String course_name, Level course_level, ArrayList<Module> Module_offer) {
		this.course_name = course_name;
		this.course_level = course_level;
		this.Module_offer = Module_offer;
	}

	public String getCourse_name() {
		return course_name;
	}

	public Level getCourse_level() {
		return course_level;
	}

	public ArrayList<Module> getModule_offer() {
		return Module_offer;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public void setCourse_level(Level course_level) {
		this.course_level = course_level;
	}

	public void setModule_offer(ArrayList<Module> Module_offer) {
		this.Module_offer = Module_offer;
	}

	
	
	
}




