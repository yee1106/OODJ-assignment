package oodj_assignment;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==admin){
            
        }
        else if(e.getSource()==lecturer){
            
        }
        else if(e.getSource()==logout){
            
        }
    }
    
}
