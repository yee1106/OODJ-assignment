package oodj_assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Grading_System {

	public static ArrayList<Administrator> adm = new ArrayList<>();//for adding new admin and for admin login
	public static User currentUser = new User();//for current login user detail
	public static ArrayList<Course> course_list = new ArrayList<>();
	public static ArrayList<Course> course_available_lecturer = new ArrayList<>();
	public static ArrayList<Educator> edu = new ArrayList<>();//for adding new lecturer and for lecturer login
	public static addLecture al = new addLecture();
	
	public static AdminMenuGui adminmenu = new AdminMenuGui();
	public static LoginPage lg = new LoginPage();
	public static ArrayList<Student> stu = new ArrayList<>();
	public static ArrayList<Student> stu1 = new ArrayList<>();
	public static addStudent as = new addStudent();
	public static String currentIntakeCode;
	public static String currentModule;
	public static String intakeCode;
	//public static keyIn_mark kim=new keyIn_mark();
	public static LectureMenu lm1 = new LectureMenu();

	
	public static void main(String[] args) {

	}

	public static void logFile() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("LogFile.txt", true));
			bw.append(Grading_System.currentUser.getID() + "\r\n");
			bw.append(Grading_System.currentUser.getName() + "\r\n");
			bw.append(Grading_System.currentUser.getMode() + "\r\n");
			bw.append(Grading_System.currentUser.getLogin() + "\r\n");
			bw.append(Grading_System.currentUser.getLogout() + "\r\n");
			bw.append("\r\n");
			bw.close();
		} catch (IOException ex) {
			System.out.println("File not found");
		}
	}
}







