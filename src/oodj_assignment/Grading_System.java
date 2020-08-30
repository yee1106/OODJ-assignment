package oodj_assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Grading_System {

	public static LoginPage lg = new LoginPage();
//	public static AdminMenu adMenu = new AdminMenu();
	public static AdminMenuGui adminmenu= new AdminMenuGui();
	public static addLecture al = new addLecture();
	public static ArrayList<Educator> edu = new ArrayList<>();
	public static ArrayList<Student> stu = new ArrayList<>();
	public static ArrayList<Student> stu1 = new ArrayList<>();
	public static addStudent as = new addStudent();
	public static String currentIntakeCode;
	public static String currentModule;
	public static String intakeCode;
	//public static keyIn_mark kim=new keyIn_mark();
	public static LectureMenu lm1 = new LectureMenu();
	public static ArrayList<Course>course_list = new ArrayList<>();
	

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
					educator.setIntake_module(hm1);
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

}







