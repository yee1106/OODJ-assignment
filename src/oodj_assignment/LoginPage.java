package oodj_assignment;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class LoginPage extends JFrame implements ActionListener{
    private Button admin,lecturer,logout;
    
    public LoginPage(){
        setBounds(300,100,300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        
        admin=new Button("Admin");
        admin.addActionListener(this);
        add(admin);
        
        lecturer=new Button("Lecturer");
        lecturer.addActionListener(this);
        add(lecturer);
        
        logout=new Button("Lecturer");
        logout.addActionListener(this);
        add(logout);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==admin){
            setVisible(false);
            ArrayList<User> adm=new ArrayList<>();
            adm.add(new User("Ad001",1111,"Wong Yee Chung"));
            adm.add(new User("Ad002",2222,"Chew Chang Wang"));
            String name=JOptionPane.showInputDialog(null,"Enter your ID: ");
            int password=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter your password: "));
            Iterator<User> it=adm.iterator();
            Boolean flag=false;
            while(it.hasNext()){
                User admin=it.next();
                if(name.equals(admin.getID())&&password==admin.getPassword()){
                    flag=true;
                }
            }
            if(flag==true){
                JOptionPane.showMessageDialog(null,"Login Success!!");
                
            }else{
                JOptionPane.showMessageDialog(null,"Wrong ID or password!!");
            }
            setVisible(true);
        }
        else if(e.getSource()==lecturer){
            
        }
        else if(e.getSource()==logout){
            
        }
    }
    
}
