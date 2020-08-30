package oodj_assignment;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addStudent extends JFrame implements ActionListener {

	private JLabel IdLabel, nameLabel, passwordLabel, emailLabel, intakeLabel;
	private JTextField IdText, nameText, passwordText, emailText, intakeText;
	private JButton confirmButton, resetButton, exitButton;
	private JPanel IdPanel, namePanel, passwordPanel, emailPanel, intakePanel, buttonPanel;

	public addStudent() {

		setTitle("Add student's information");
		setBounds(800, 400, 320, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(6, 2));

		//JLabel
		IdLabel = new JLabel("Id:");
		nameLabel = new JLabel("Name:");
		passwordLabel = new JLabel("Password:");
		emailLabel = new JLabel("Email:");
		intakeLabel = new JLabel("Intake:");

		//JTextField
		IdText = new JTextField(20);
		nameText = new JTextField(20);
		passwordText = new JTextField(20);
		emailText = new JTextField(20);
		intakeText = new JTextField(20);

		//intakeText.setEditable(false);
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(this);
		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);

		IdPanel = new JPanel();
		IdPanel.add(IdLabel);
		IdPanel.add(IdText);
		IdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		namePanel = new JPanel();
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		passwordPanel = new JPanel();
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordText);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		emailPanel = new JPanel();
		emailPanel.add(emailLabel);
		emailPanel.add(emailText);
		emailPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		intakePanel = new JPanel();
		intakePanel.add(intakeLabel);
		intakePanel.add(intakeText);
		intakePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(exitButton);

		add(IdPanel);
		add(namePanel);
		add(passwordPanel);
		add(emailPanel);
		add(intakePanel);
		add(buttonPanel);
		setResizable(false);
		setVisible(false);
	}

	public JTextField getIntakeText() {
		return intakeText;
	}

	public void setIntakeText(String intakeText) {
		this.intakeText.setText(intakeText);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == confirmButton) {
			if (IdText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "** Empty ID!! **", "Student's information", JOptionPane.WARNING_MESSAGE);
			} else if (nameText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "** Empty Name!! **", "Student's information", JOptionPane.WARNING_MESSAGE);
			} else if (passwordText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "** Empty Password!! **", "Student's information", JOptionPane.WARNING_MESSAGE);
			} else if (emailText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "** Empty Email!! **", "Student's information", JOptionPane.WARNING_MESSAGE);
			} else if (intakeText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "** Empty Intake Code!! **", "Student's information", JOptionPane.WARNING_MESSAGE);
			} else if (nameText.getText().matches(".*\\d.*")) {
				JOptionPane.showMessageDialog(null, "** Invalid Name **", "Student's information", JOptionPane.WARNING_MESSAGE);
			}
			
			
			
			else {
				/*HashMap<String,String> inta_mod = new HashMap<String,String>();
                inta_mod.put(moduleText.getText(), intakeText.getText());
                Educator ed=new Educator(IdText.getText(),passwordText.getText(),nameText.getText(),inta_mod,emailText.getText());*/
				File f = new File(Grading_System.currentIntakeCode + "StudentList.txt");
				if (f.exists()) {
					try {
						Scanner file = new Scanner(f);
						while (file.hasNext()) {
							Student student = new Student();
							student.setID(file.nextLine());
							student.setPassword(file.nextLine());
							student.setName(file.nextLine());
							student.setEmail(file.nextLine());
							student.setIntake_code(file.nextLine());
							file.nextLine();
							Grading_System.stu.add(student);
						}
						file.close();

					} catch (FileNotFoundException ex) {
					}
				}
				Grading_System.stu.add(new Student(intakeText.getText(), IdText.getText(), passwordText.getText(), nameText.getText(), emailText.getText()));
//				clear();
				intakeText.setText(Grading_System.currentIntakeCode);
				JOptionPane.showMessageDialog(null, "new student added! press ok to continue");
				try {
					PrintWriter pw = new PrintWriter(Grading_System.currentIntakeCode + "StudentList.txt");
					for (int i = 0; i < Grading_System.stu.size(); i++) {
						Student student = Grading_System.stu.get(i);
						pw.println(student.getID());
						pw.println(student.getPassword());
						pw.println(student.getName());
						pw.println(student.getEmail());
						pw.println(student.getIntake_code());
						pw.println();
					}
					pw.close();
				} catch (FileNotFoundException ex) {
				}
			}
		} else if (e.getSource() == resetButton) {
//			clear();
		} else if (e.getSource() == exitButton) {

		}

	}

//	public void clear() {//for clear the ID and password text field
//		IdText.setText("");
//		nameText.setText("");
//		passwordText.setText("");
//		emailText.setText("");
//		intakeText.setText("");
//	}

}


