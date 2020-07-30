package oodj_assignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grading_System {
    
    public static LoginPage lg = new LoginPage();   
    public static AdminMenu adMenu=new AdminMenu();
    public static addLecture al=new addLecture();
    public static ArrayList<Educator> edu=new ArrayList<>();
    
    public static void main(String[] args) {     
        
            /*HashMap<String,String> hm=new HashMap<>();
            hm.put("oodj","uc2006cs");
            edu.add(new Educator("Chew Chang Wang","111","Ed001","chewchangwang@gmail.com",hm));*/
        try {
            Scanner s=new Scanner (new File("EducatorInformation"));
            while(s.hasNext()){
                Educator edu=new Educator();
                edu.setID(s.next());
                edu.setPassword(s.next());
                edu.setName(s.next());
                edu.setEmail(s.next());
                String[] line=s.next().split(" ");
                for(String inta_modu:line){
                    String[] str=inta_modu.split("_");
                    
                }
            }
        } catch (FileNotFoundException ex) { }
        
    }
	
}



