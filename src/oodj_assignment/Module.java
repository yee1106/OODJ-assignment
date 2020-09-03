package oodj_assignment;

import java.util.ArrayList;

public class Module {

	private String ModuleName;
	private String ShortModuleName;
	private int overall_mark;
	private int quiz_mark;
	private int lab_test_mark;
	private int assignment_mark;
	private ArrayList<Student> StudentInModule= new ArrayList<Student>();

	public Module(String ModuleName, String ShortModuleName) {
		this.ModuleName = ModuleName;
		this.ShortModuleName = ShortModuleName;
	}

	public Module(String ModuleName, String ShortModuleName,int overall_mark, int quiz_mark, int lab_test_mark, int assignment_mark) {
		this.ModuleName = ModuleName;
		this.ShortModuleName = ShortModuleName;
		this.overall_mark = overall_mark;
		this.quiz_mark = quiz_mark;
		this.lab_test_mark = lab_test_mark;
		this.assignment_mark = assignment_mark;
		StudentInModule= new ArrayList<Student>();
	}

	public String getModuleName() {
		return ModuleName;
	}

	public void setModuleName(String moduleName) {
		ModuleName = moduleName;
	}

	public int getOverall_mark() {
		return overall_mark;
	}

	public void setOverall_mark(int overall_mark) {
		this.overall_mark = overall_mark;
	}

	public int getQuiz_mark() {
		return quiz_mark;
	}

	public void setQuiz_mark(int quiz_mark) {
		this.quiz_mark = quiz_mark;
	}

	public int getLab_test_mark() {
		return lab_test_mark;
	}

	public void setLab_test_mark(int lab_test_mark) {
		this.lab_test_mark = lab_test_mark;
	}

	public int getAssignment_mark() {
		return assignment_mark;
	}

	public void setAssignment_mark(int assignment_mark) {
		this.assignment_mark = assignment_mark;
	}

	public String getShortModuleName() {
		return ShortModuleName;
	}

	public void setShortModuleName(String ShortModuleName) {
		this.ShortModuleName = ShortModuleName;
	}

	public ArrayList<Student> getStudentInModule() {
		return StudentInModule;
	}

	public void setStudentInModule(ArrayList<Student> StudentInModule) {
		this.StudentInModule = StudentInModule;
	}
	
	
	
	public int CalcOverallMark(){
		return this.overall_mark=this.assignment_mark+this.lab_test_mark+this.quiz_mark;
		
	}

	@Override
	public String toString() {
		return "Module{" + "ModuleName=" + ModuleName + ", ShortModuleName=" + ShortModuleName + ", overall_mark=" + overall_mark + ", quiz_mark=" + quiz_mark + ", lab_test_mark=" + lab_test_mark + ", assignment_mark=" + assignment_mark + ", StudentInModule=" + StudentInModule + '}';
	}
	

	
	

}








