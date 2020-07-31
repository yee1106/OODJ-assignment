package oodj_assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Grading_System {
    
    public static LoginPage lg = new LoginPage();   
    public static AdminMenu adMenu=new AdminMenu();
    public static addLecture al=new addLecture();
    public static ArrayList<Educator> edu=new ArrayList<>();
    
    public static void main(String[] args) {     
            
            /*HashMap<String,String> hm=new HashMap<>();
            hm.put("oodj","uc2006cs");
            edu.add(new Educator("Chew Chang Wang","111","ED001","chewchangwang@gmail.com",hm));*/
        try {
            Scanner s=new Scanner (new File("EducatorInformation.txt"));   
            while(s.hasNext()){ 
                Educator educator=new Educator();
                educator.setID(s.nextLine());
                //System.out.println(educator.getID());
                educator.setPassword(s.nextLine());
                //System.out.println(educator.getPassword());
                educator.setName(s.nextLine());
                educator.setEmail(s.nextLine());
                String[] line=s.nextLine().split(" ");
                for(String inta_modu:line){
                    String[] str=inta_modu.split("_");
                    HashMap<String,String> hm1=new HashMap<>();
                    hm1.put(str[0], str[1]);
                    educator.setIntake_module(hm1);
                }
                s.nextLine();
                edu.add(educator);         
            }
        } catch (FileNotFoundException ex) { }
 
    }
	
}





