package oodj_assignment;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LoginPage extends JFrame implements ActionListener{
    private JButton login,reset,logout;
    private JLabel ID,password,type;
    private JTextField IdText;
    private JPasswordField passwordText;
    private JRadioButton adminMode,educatorMode;
    private ButtonGroup bg;
    private JPanel IdPanel,passwordPanel,typePanel,buttonPanel;

    
    public LoginPage(){
        setTitle("Grading System Login Page");
        setBounds(300,200,400,200);
        setResizable(false);
        setLayout(new GridLayout(4,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        
        
        login=new JButton("Login");
        reset=new JButton("Reset");
        logout=new JButton("Logout");
        
        login.addActionListener(this);
        reset.addActionListener(this);
        logout.addActionListener(this);
        
        ID=new JLabel("Enter your ID: ");
        password=new JLabel("    Password: ");
        type=new JLabel("User type: ");
        
        IdText=new JTextField(15);
        passwordText=new JPasswordField(15);    
        
        adminMode=new JRadioButton("Admin");      
        educatorMode=new JRadioButton("Lecturer");
        bg=new ButtonGroup();
        bg.add(educatorMode);
        bg.add(adminMode);
        educatorMode.setSelected(true);
        
        
        IdPanel=new JPanel();
        passwordPanel=new JPanel();
        typePanel=new JPanel();
        buttonPanel=new JPanel();
        
        IdPanel.add(ID);
        IdPanel.add(IdText);
        
        passwordPanel.add(password);
        passwordPanel.add(passwordText);
        
        typePanel.add(type);
        typePanel.add(adminMode);
        typePanel.add(educatorMode);
        
        buttonPanel.add(login);
        buttonPanel.add(reset);
        buttonPanel.add(logout);
        
        this.add(IdPanel);
        this.add(passwordPanel);
        this.add(typePanel);
        this.add(buttonPanel);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            setVisible(false);
            ArrayList<User> adm=new ArrayList<>();
            adm.add(new User("Ad001",1111,"Wong Yee Chung"));
            adm.add(new User("Ad002",2222,"Chew Chang Wang"));
            //String name=JOptionPane.showInputDialog(null,"Enter your ID: ");
            //int password=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter your password: "));
            Iterator<User> it=adm.iterator();
            Boolean flag=false;
            while(it.hasNext()){
                User admin=it.next();
                String myPass=String.valueOf(passwordText.getPassword());
                if(IdText.getText().equals(admin.getID()) && myPass.equals(Integer.toString(admin.getPassword()))&&adminMode.isSelected()){
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
        else if(e.getSource()==reset){
            
        }
        else if(e.getSource()==logout){
            
        }
    }
    
}
