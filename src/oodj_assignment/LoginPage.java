package oodj_assignment;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {

	private JButton login, reset, logout;
	private JLabel ID, password, type;
	private JTextField IdText;
	private JPasswordField passwordText;
	private JRadioButton adminMode, educatorMode;
	private ButtonGroup bg;
	private JPanel IdPanel, passwordPanel, typePanel, buttonPanel;

	public LoginPage() {
		setTitle("Grading System Login Page");
		setBounds(800, 400, 350, 200);
		setResizable(false);//fix the frame size
		setLayout(new GridLayout(4, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		login = new JButton("Login");//Button for user login
		reset = new JButton("Reset");//reset the ID and password button
		logout = new JButton("Logout");//logout the login page button

		login.addActionListener(this);
		reset.addActionListener(this);
		logout.addActionListener(this);


		ID = new JLabel("Enter your ID: ");
		password = new JLabel("    Password: ");
		type = new JLabel("User type: ");
		

		IdText = new JTextField(15);//for accept ID
		passwordText = new JPasswordField(15);//for accept password


		adminMode = new JRadioButton("Admin");   //choose admin or lecturer   
		educatorMode = new JRadioButton("Lecturer");
		bg = new ButtonGroup();//choose either one for admin and lecturer
		bg.add(educatorMode);
		bg.add(adminMode);
		educatorMode.setSelected(true);//set the default choose educator

		IdPanel = new JPanel();
		passwordPanel = new JPanel();

		typePanel = new JPanel();
		buttonPanel = new JPanel();

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
		if (e.getSource() == login) {
			setVisible(false);
			Boolean flag = false;
			Boolean flag1 = false;
			Boolean flag2 = false;
			if (adminMode.isSelected()) {
				Grading_System.adm.add(new User("Ad001", "1111", "Wong Yee Chung"));
				Grading_System.adm.add(new User("Ad002", "2222", "Chew Chang Wang"));
				//String name=JOptionPane.showInputDialog(null,"Enter your ID: ");
				//int password=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter your password: "));
				Iterator<User> it = Grading_System.adm.iterator();
				while (it.hasNext()) {
					User admin = it.next();
					//String myPass=String.valueOf(passwordText.getPassword());//change the JPassword field to string
					if (IdText.getText().equalsIgnoreCase(admin.getID()) && new String(passwordText.getPassword()).equals(admin.getPassword())) {//check whether ID, Password and the type or user correct or not   
            Grading_System.currentUser.setName(admin.getName());
            Grading_System.currentUser.setID(IdText.getText());
            Grading_System.currentUser.setMode("Admin");
						flag = true;
						flag1 = true;
            break;
					}
				}
			}
			if (educatorMode.isSelected()) {
				Iterator<Educator> educator = Grading_System.edu.iterator();
				while (educator.hasNext()) {
					Educator edu = educator.next();
					if (IdText.getText().equalsIgnoreCase(edu.getID()) && new String(passwordText.getPassword()).equals(edu.getPassword())) {
            Grading_System.currentUser.setName(edu.getName());
            Grading_System.currentUser.setID(IdText.getText());
            Grading_System.currentUser.setMode("Educator");
						flag = true;
						flag2 = true;
            break;
					}
				}
			}
			if (flag == true) {
        Date date=new Date();	
        Grading_System.currentUser.setLogin(date);
				setVisible(false);
				if (flag1 == true) {
//					Grading_System.adMenu.setVisible(true);//display admin menu page
          JOptionPane.showMessageDialog(null, "Hello "+Grading_System.currentUser.getName());
					Grading_System.adminmenu.setVisible(true);
				}
				if (flag2 == true) { //display educator menu
          JOptionPane.showMessageDialog(null, "Hello "+Grading_System.currentUser.getName()); 
					Grading_System.lm1.setVisible(true);
				}
        
        
        
			} else {
				JOptionPane.showMessageDialog(null, "Wrong ID or password!!");
				setVisible(true);//go back to login page
				
			}    
      clear();




		} else if (e.getSource() == reset) {
			clear();
		} else if (e.getSource() == logout) {


			System.exit(0);
		}
	}


	public void clear() {//for clear the ID and password text field
		IdText.setText("");
		passwordText.setText("");
	}


}










