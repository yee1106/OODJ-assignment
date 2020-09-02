package oodj_assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grading_System {
  public static ArrayList<User> adm = new ArrayList<>();;//for adding new admin and for admin login
  public static User currentUser=new User();//for current login user detail
	
//	public static AdminMenu adMenu = new AdminMenu();
	public static ArrayList<Course> course_list = new ArrayList<>();
	public static ArrayList<Course> course_available_lecturer= new ArrayList<>();
	public static AdminMenuGui adminmenu= new AdminMenuGui();
	public static addLecture al = new addLecture();
	public static ArrayList<Educator> edu = new ArrayList<>();//for adding new lecturer and for lecturer login
	public static LoginPage lg = new LoginPage();
	public static ArrayList<Student> stu = new ArrayList<>();
	public static ArrayList<Student> stu1 = new ArrayList<>();
	public static addStudent as = new addStudent();
	public static String currentIntakeCode;
	public static String currentModule;
	public static String intakeCode;
	//public static keyIn_mark kim=new keyIn_mark();
	public static LectureMenu lm1 = new LectureMenu();
	
	//new

	public static void main(String[] args) {
		/*HashMap<String,String> hm=new HashMap<>();
            hm.put("oodj","uc2006cs");
            edu.add(new Educator("Chew Chang Wang","111","ED001","chewchangwang@gmail.com",hm));*/
		
		//Change the UI style to Windows
//		try {
//			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//				if ("Windows".equals(info.getName())) {
//					javax.swing.UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		} catch (ClassNotFoundException ex) {
//			java.util.logging.Logger.getLogger(AdminMenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (InstantiationException ex) {
//			java.util.logging.Logger.getLogger(AdminMenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (IllegalAccessException ex) {
//			java.util.logging.Logger.getLogger(AdminMenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
//			java.util.logging.Logger.getLogger(AdminMenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		}
		
		//Initailizing lecturers data to memory
		try {
			Scanner s = new Scanner(new File("EducatorInformation.txt"));
			while (s.hasNext()) {
				Educator educator = new Educator();
				educator.setID(s.nextLine());
				//System.out.println(educator.getID());
				educator.setPassword(s.nextLine());
				//System.out.println(educator.getPassword());
				educator.setName(s.nextLine());
				educator.setEmail(s.nextLine());
				String[] line = s.nextLine().split(" ");
				for (String inta_modu : line) {
					String[] str = inta_modu.split("_");
					HashMap<String, String> hm1 = new HashMap<>();
					hm1.put(str[0], str[1]);
//					educator.setIntake_module(hm1);
				}
				s.nextLine();
				edu.add(educator);
			}
			s.close();
		} catch (FileNotFoundException ex) {
		}
		/*try{
          Scanner file=new Scanner(new File("StudentList.txt"));
          while(file.hasNext()){ 
            Student student=new Student();
            student.setID(file.nextLine());
            student.setPassword(file.nextLine());
            student.setName(file.nextLine());
            student.setEmail(file.nextLine());
            student.setIntake_code(file.nextLine());
            file.nextLine();
            stu.add(student);
          }
          file.close();

        }    
        catch(FileNotFoundException ex){}*/
	}

  public static void logFile(){
    try {
      BufferedWriter bw=new BufferedWriter(new FileWriter("LogFile.txt",true));
      bw.append(Grading_System.currentUser.getID()+"\r\n");
      bw.append(Grading_System.currentUser.getName()+"\r\n");
      bw.append(Grading_System.currentUser.getMode()+"\r\n");
      bw.append(Grading_System.currentUser.getLogin()+"\r\n");
      bw.append(Grading_System.currentUser.getLogout()+"\r\n");
      bw.append("\r\n");
      bw.close();
    } catch (IOException ex) {
      System.out.println("File not found");
    }
  }
}










