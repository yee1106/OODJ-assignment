package oodj_assignment;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminMenu extends JFrame implements ActionListener{
    private JTextField jf1,jf2;
    private JLabel jlb;
    private JPanel jp,jp1;
    private JButton first,second,third,fourth,fifth;
    
    public AdminMenu(){
        setTitle("Admin Menu");
        setBounds(300,300,300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        first=new JButton("***  Add student Account");
        first.addActionListener(this);
        jp1=new JPanel();
        jp1.add(first);
        add(jp1);
        setVisible(false);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
