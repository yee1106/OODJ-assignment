package oodj_assignment;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminMenu extends JFrame implements ActionListener{
    //private JTextField jf1,jf2;
    private JLabel menu;
    private JPanel jp,jp1,jp2,jp3,jp4,jp5;
    private JButton first,second,third,fourth,fifth;
    
    public AdminMenu(){
        setTitle("Admin Menu");
        setBounds(800,400,350,250);//position, lenght, breadth
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new GridLayout(5,1,5));//row and column
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        menu = new JLabel("  ------- MENU --------  ");
        menu.setForeground(Color.blue);//set font's color
        jp=new JPanel();
        jp.add(menu);
        add(jp);
                
        first=new JButton("*** 1)Generate log file ***");
        first.addActionListener(this);
        jp1=new JPanel();
        jp1.setLayout(new GridLayout(6,1));
        jp1.add(first);
        //add(jp1);
        
        second=new JButton("*** 2)Add student Account ***");
        second.addActionListener(this);
        //jp2=new JPanel();
        jp1.add(second);
        //add(jp2);
        
        third=new JButton("***  3)Add lecturer Account ***");
        third.addActionListener(this);
        //jp3=new JPanel();
        jp1.add(third);
        //add(jp3);
    
        fourth=new JButton("*** 4)Create and modify course and module ***");
        fourth.addActionListener(this);
        //jp4=new JPanel();
        jp1.add(fourth);
        //add(jp4);
        
        fifth=new JButton("*** 5)View educator and student information ***");
        fifth.addActionListener(this);
        //jp5=new JPanel();
        jp1.add(fifth);
        //add(jp5);
        add(jp1);
        
        
        setVisible(false);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==first){
            
        }else if(e.getSource()==second){
            
        }
        else if(e.getSource()==third){
            
        }
        else if(e.getSource()==fourth){
            
        }
        else if(e.getSource()==fifth){
            
        }
    }
}