package oodj_assignment;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class keyIn_mark extends JFrame implements ActionListener {

	private JLabel IdLabel, nameLabel, emailLabel, intakeLabel, moduleLabel, quizLabel, labTestLabel, assignmentLabel, overallMarkLabel;
	private JTextField IdText, nameText, emailText, intakeText, moduleText, quizText, labTestText, assignmentText, overallMarkText;
	private JButton confirmButton, cancelButton, exitButton, previousButton, nextButton, editButton, searchButton;
	private JPanel IdPanel, namePanel, emailPanel, intakePanel, buttonPanel, modulePanel, quiztPanel, labTestPanel, assignmentPanel, overallMarkPanel;
	private int currentRow;

	public keyIn_mark() {

		setTitle("Key in student's marks");
		setBounds(600, 400, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(11, 2));

		//JLabel
		IdLabel = new JLabel("Id:");
		nameLabel = new JLabel("Name:");
		emailLabel = new JLabel("Email:");
		intakeLabel = new JLabel("Intake:");
		moduleLabel = new JLabel("Module:");
		quizLabel = new JLabel("Quiz Mark:");
		labTestLabel = new JLabel("LabTest Mark");
		assignmentLabel = new JLabel("Assignement Mark:");
		overallMarkLabel = new JLabel("Overall Mark:");

		//JTextField
		IdText = new JTextField(40);
		nameText = new JTextField(40);
		emailText = new JTextField(40);
		intakeText = new JTextField(40);
		moduleText = new JTextField(40);
		quizText = new JTextField(40);
		labTestText = new JTextField(40);
		assignmentText = new JTextField(40);
		overallMarkText = new JTextField(40);

		//intakeText.setEditable(false);
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		previousButton = new JButton("Previous");
		previousButton.addActionListener(this);
		nextButton = new JButton("Next");
		nextButton.addActionListener(this);
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		editButton = new JButton("Edit");
		editButton.addActionListener(this);

		IdPanel = new JPanel();
		IdPanel.add(IdLabel);
		IdPanel.add(IdText);
		IdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		namePanel = new JPanel();
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		emailPanel = new JPanel();
		emailPanel.add(emailLabel);
		emailPanel.add(emailText);
		emailPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		intakePanel = new JPanel();
		intakePanel.add(intakeLabel);
		intakePanel.add(intakeText);
		intakePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		modulePanel = new JPanel();
		modulePanel.add(moduleLabel);
		modulePanel.add(moduleText);
		modulePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		quiztPanel = new JPanel();
		quiztPanel.add(quizLabel);
		quiztPanel.add(quizText);
		quiztPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		labTestPanel = new JPanel();
		labTestPanel.add(labTestLabel);
		labTestPanel.add(labTestText);
		labTestPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		assignmentPanel = new JPanel();
		assignmentPanel.add(assignmentLabel);
		assignmentPanel.add(assignmentText);
		assignmentPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		overallMarkPanel = new JPanel();
		overallMarkPanel.add(overallMarkLabel);
		overallMarkPanel.add(overallMarkText);
		overallMarkPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		buttonPanel = new JPanel();
		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(editButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		buttonPanel.add(exitButton);

		add(IdPanel);
		add(namePanel);
		add(emailPanel);
		add(intakePanel);
		add(modulePanel);
		add(quiztPanel);
		add(labTestPanel);
		add(assignmentPanel);
		add(overallMarkPanel);
		add(buttonPanel);

		setResizable(false);
		boolean flag;
    boolean flag4=false;
    Grading_System.stu1=new ArrayList<>();
     Grading_System.stu=new ArrayList<>();
    String intakeCode =JOptionPane.showInputDialog(null,"Enter intake code eg:[UC2L202006CS]:");//enter existing intake and module short name
		String moduleName = JOptionPane.showInputDialog(null, "Enter module short name eg:[OODJ]:");
		do {
			flag = true;
      try {
        Scanner sca=new Scanner(new File("Intake_module.txt"));//checking has this intake code or not
        while(sca.hasNext()){
          sca.nextLine();
          if(intakeCode!=null&&moduleName!=null&&moduleName.length()>0&&intakeCode.length()>0){
            if(intakeCode.toUpperCase().equals(sca.nextLine().toUpperCase())){
              String[] split_shortname_module = sca.nextLine().split(",");
              for(String shortName:split_shortname_module){
                if(shortName.toUpperCase().equals(moduleName.toUpperCase())){
                  flag4=true;
                  break;
                }
              }
            }
            sca.nextLine();
            sca.nextLine();
          }
       } 
        sca.close();
      } catch (FileNotFoundException ex) {
        flag=false;
      }catch(Exception ee){
        flag=true;
      }
     if(flag4==true){ 
      if(intakeCode!=null&&moduleName!=null&&moduleName.length()>0&&intakeCode.length()>0){
        Grading_System.intakeCode = intakeCode;
        Grading_System.currentModule = moduleName;
        moduleText.setText(moduleName);
        moduleText.setEditable(false);
        File f = new File(Grading_System.intakeCode.toUpperCase() + "StudentList.txt");//checking has this intake student list or not
        if (f.exists()) {
          Scanner file;
          try {
            file = new Scanner(f);
            while (file.hasNext()) {
              Student student = new Student();
              student.setID(file.nextLine());
              student.setPassword(file.nextLine());
              student.setName(file.nextLine());
              student.setEmail(file.nextLine());
              student.setIntake_code(file.nextLine());
              file.nextLine();
              file.nextLine();
              file.nextLine();
              Grading_System.stu.add(student);
            }
            file.close();
          } catch (FileNotFoundException ex) {
          }
          File f2 = new File(Grading_System.intakeCode.toUpperCase() + Grading_System.currentModule.toUpperCase() + ".txt");//checking that intake and module has student list or not
          if (f2.exists()) {
            try {
              Scanner file1 = new Scanner(f2);
              while (file1.hasNext()) {
                Student student1 = new Student();
                student1.setID(file1.nextLine());
                student1.setName(file1.nextLine());
                student1.setEmail(file1.nextLine());
                student1.setIntake_code(file1.nextLine());
                int quiz = Integer.parseInt(file1.nextLine());
                int labtest = Integer.parseInt(file1.nextLine());
                int assignment = Integer.parseInt(file1.nextLine());
                int overallMark = Integer.parseInt(file1.nextLine());
                file1.nextLine();
              	student1.getModule_taken().add(new Module(Grading_System.currentModule, overallMark, quiz, labtest, assignment));
                Grading_System.stu1.add(student1);
              }
              file1.close();
            } catch (FileNotFoundException ex) {
            }
            for (Student s : Grading_System.stu) {
              boolean flag1 = false;
              for (Student s1 : Grading_System.stu1) {
                if (s.getID().equals(s1.getID())) {
                  flag1 = true;//mean this student isn't new student
                }
              }
              if (flag1 == false) {
                Grading_System.stu1.add(s);//if has new student added by admin 
              }
            }
            //****************  if has a student already change class need to delete the information
            for(Student s:Grading_System.stu1){
              boolean flag1=false;
              for(Student s1:Grading_System.stu){
                if(s.getID().equals(s1.getID())){
                  flag1=true;//mean this student isn't new student
                }               
              }
              if(flag1==false){
                Grading_System.stu1.remove(s); //if has new student added by admin 
              }
             }          
             for(Student s:Grading_System.stu1){
              for(Student s1:Grading_System.stu){
                if(s.getID().equals(s1.getID())){
                  s.setName(s1.getName());
                  s.setEmail(s1.getEmail());
                }               
              }
             } 
             
          } else {
            Grading_System.stu1 = new ArrayList<>(Grading_System.stu);
            for (int i = 0; i < Grading_System.stu1.size(); i++) {
        			Grading_System.stu1.get(i).getModule_taken().add(new Module(Grading_System.currentModule, 0, 0, 0, 0));
            }
          }
          
        } else {
          flag = false;
          JOptionPane.showMessageDialog(null, "Error enter intake or don't have this class");
        }
      }
      else{
        JOptionPane.showMessageDialog(null, "No enter intake or module");
      }
    }else{
        JOptionPane.showMessageDialog(null, "Wrong Intake or module");
        
      }
     
		} while (flag == false);
		if (Grading_System.stu1.size() > 0) {//if has student mark list, then display the student mark 
			currentRow = 0;
			setTitle("Key in student's marks" + "  Student" + currentRow);
			getData(currentRow);
			setVisible(true);
			cancelButton.setEnabled(false);
			confirmButton.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null, "!! No record !!", "Student List Not Found", JOptionPane.WARNING_MESSAGE);
      //Grading_System.lg.setVisible(true);
		}
		//put Grading_System.stu1 to intake+module file 
	}

	public JTextField getIntakeText() {
		return intakeText;
	}

	public void setIntakeText(String intakeText) {
		this.intakeText.setText(intakeText);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == confirmButton) {		//comfirm change the student marks
      boolean flag3=false;
      try{//checking invalid input to the marks 
        Integer.parseInt(quizText.getText());
        Integer.parseInt(labTestText.getText());
        Integer.parseInt(assignmentText.getText());
      }catch(NumberFormatException ex){
        JOptionPane.showMessageDialog(null, "Quiz, labtest or Assignment mark error, must be number！", "Student List Record", JOptionPane.WARNING_MESSAGE);
        flag3=true;
        setTest();
      }
      if(flag3==false){
        if(Integer.parseInt(quizText.getText())>10||Integer.parseInt(quizText.getText())<0){
          JOptionPane.showMessageDialog(null, "Quiz larger than 10 marks or less than 0！", "Student List Record", JOptionPane.WARNING_MESSAGE);
          setTest();
        }
        else if(Integer.parseInt(labTestText.getText())>40||Integer.parseInt(labTestText.getText())<0){
          JOptionPane.showMessageDialog(null, "Labtest larger than 40 marks or less than 0！", "Student List Record", JOptionPane.WARNING_MESSAGE);
          setTest();
        }
        else if(Integer.parseInt(assignmentText.getText())>50||Integer.parseInt(assignmentText.getText())<0){
          JOptionPane.showMessageDialog(null, "Assignment larger than 50 marks or less than 0！", "Student List Record", JOptionPane.WARNING_MESSAGE);
          setTest();
        }
        else{
          System.out.println(Grading_System.stu1);
          saveDetail();
          cancelButton.setEnabled(false);
          confirmButton.setEnabled(false);
          setEdit1(true);
        }
      }
		} else if (e.getSource() == cancelButton) {//discrad change the student marks
			boolean flag = false;
      setTest();
			setEdit1(true);
			cancelButton.setEnabled(false);
			confirmButton.setEnabled(false);
			setEdit(false);
		} else if (e.getSource() == exitButton) {//exit the key in mark page and back to lecturer menu
			saveFile();
			setVisible(false);
			Grading_System.lm1.setVisible(true);
		} else if (e.getSource() == previousButton) {//checking the previous student
			if (currentRow >= 1) {
				currentRow = currentRow - 1;
				//setTitle("Key in student's marks"+"  Student"+currentRow);
				getData(currentRow);
			} else {
				JOptionPane.showMessageDialog(null, "Already First Student！", "Student List Record", JOptionPane.WARNING_MESSAGE);
			}
		} else if (e.getSource() == nextButton) { //checking the next student
			if (currentRow < Grading_System.stu.size() - 1) {
				currentRow = currentRow + 1;

				getData(currentRow);
			} else {
				JOptionPane.showMessageDialog(null, "Already Last Student！", "Student List Record", JOptionPane.WARNING_MESSAGE);
			}
		} else if (e.getSource() == editButton) { //edit the student mark
			setEdit(true);
			setEdit1(false);
			cancelButton.setEnabled(true);
			confirmButton.setEnabled(true);
		} else if (e.getSource() == searchButton) { //searching the specific student mark
			boolean flag = false;
			String tpNumber = JOptionPane.showInputDialog("Enter the student' ID: ");
			for (int i = 0; i < Grading_System.stu1.size(); i++) {
				if (tpNumber.toLowerCase().equalsIgnoreCase(Grading_System.stu1.get(i).getID())) {
					getData(i);
          currentRow=i;
					flag = true;
				}
			}
			if (flag == false) {
				JOptionPane.showMessageDialog(null, "Student not found");
			}
		}

	}

	public void getData(int currentRow) { //get the particular student mark
		if (currentRow >= 0) {
			setTitle("Key in student's marks" + "  Student" + currentRow);
			IdText.setText(Grading_System.stu1.get(currentRow).getID());
			nameText.setText(Grading_System.stu1.get(currentRow).getName());
			emailText.setText(Grading_System.stu1.get(currentRow).getEmail());
			intakeText.setText(Grading_System.stu1.get(currentRow).getIntake_code());
			IdText.setEditable(false);
			nameText.setEditable(false);
			emailText.setEditable(false);
			intakeText.setEditable(false);
			overallMarkText.setEditable(false);
			//moduleText.setText(Grading_System.stu.get(currentRow).getModule_taken().get(0).);
			boolean flag = false;
			if (!Grading_System.stu1.get(currentRow).getModule_taken().equals(null)) {
				for (Module x : Grading_System.stu1.get(currentRow).getModule_taken()) {
					if (x.getModuleName().toLowerCase().equalsIgnoreCase(Grading_System.currentModule)) {//if lecture already key in mark
						quizText.setText(String.valueOf(x.getQuiz_mark()));
						labTestText.setText(String.valueOf(x.getLab_test_mark()));
						assignmentText.setText(String.valueOf(x.getAssignment_mark()));
						overallMarkText.setText(String.valueOf(x.getOverall_mark()));
						flag = true;
					}
				}
			}
			if (flag == false) {
				quizText.setText("0");
				labTestText.setText("0");
				assignmentText.setText("0");
				overallMarkText.setText("0");
			}
			setEdit(false);
		}
	}

	public void setEdit(boolean flag) { //set the textfield status
		quizText.setEditable(flag);
		labTestText.setEditable(flag);
		assignmentText.setEditable(flag);
	}

	public void setEdit1(boolean flag) {
		editButton.setEnabled(flag);
		previousButton.setEnabled(flag);
		nextButton.setEnabled(flag);
		searchButton.setEnabled(flag);
	}

	public void saveFile() { //save the student mark into file
		try {
      System.out.println(Grading_System.stu1);
      System.out.println(Grading_System.stu1.get(0).getModule_taken());
			PrintWriter pw = new PrintWriter(Grading_System.intakeCode.toUpperCase() + Grading_System.currentModule.toUpperCase() + ".txt");
			for (int i = 0; i < Grading_System.stu1.size(); i++) {
				//System.out.println(Grading_System.stu1.get(i));
				Student s = Grading_System.stu1.get(i);
				pw.println(s.getID());
				pw.println(s.getName());
				pw.println(s.getEmail());
				pw.println(s.getIntake_code());
				if (!Grading_System.stu1.get(i).getModule_taken().equals(null)&&Grading_System.stu1.get(i).getModule_taken().size()>0) {
					pw.println(s.getModule_taken().get(0).getQuiz_mark());
					pw.println(s.getModule_taken().get(0).getLab_test_mark());
					pw.println(s.getModule_taken().get(0).getAssignment_mark());
					pw.println(s.getModule_taken().get(0).getOverall_mark());
				}

				pw.println();
			}
			pw.close();
		} catch(Exception ex){
      Logger.getLogger(keyIn_mark.class.getName()).log(Level.SEVERE, null, ex);
    }
	}

	public void saveDetail() { //save the student mark to array list and save to file
		setEdit(false);
		boolean flag = false;
		int count = 0;
		int overallMark = 0;
		for (Module module : Grading_System.stu1.get(currentRow).getModule_taken()) {
			if (module.getModuleName().toLowerCase().equalsIgnoreCase(moduleText.getText())) {
				Grading_System.stu1.get(currentRow).getModule_taken().get(count).setQuiz_mark(Integer.parseInt(quizText.getText()));
				Grading_System.stu1.get(currentRow).getModule_taken().get(count).setLab_test_mark(Integer.parseInt(labTestText.getText()));
				Grading_System.stu1.get(currentRow).getModule_taken().get(count).setAssignment_mark(Integer.parseInt(assignmentText.getText()));
				overallMark = Integer.parseInt(quizText.getText()) + Integer.parseInt(labTestText.getText()) + Integer.parseInt(assignmentText.getText());
				Grading_System.stu1.get(currentRow).getModule_taken().get(count).setOverall_mark(overallMark);
				overallMarkText.setText(String.valueOf(overallMark));
				flag = true;
			}
			count++;
		}
		if (flag == false) {
			overallMark = Integer.parseInt(quizText.getText()) + Integer.parseInt(labTestText.getText()) + Integer.parseInt(assignmentText.getText());
			overallMarkText.setText(String.valueOf(overallMark));
			Grading_System.stu1.get(currentRow).getModule_taken().add(new Module(Grading_System.currentModule, overallMark,
				Integer.parseInt(quizText.getText()), Integer.parseInt(labTestText.getText()), Integer.parseInt(assignmentText.getText())));
		}
		saveFile();
	}
  private void setTest(){//set the mark textfield
    boolean flag = false;
			if (!Grading_System.stu1.get(currentRow).getModule_taken().equals(null)) {
				for (Module module : Grading_System.stu1.get(currentRow).getModule_taken()) {
					if (module.getModuleName().equalsIgnoreCase(moduleText.getText())) {
						quizText.setText(String.valueOf(module.getQuiz_mark()));
						labTestText.setText(String.valueOf(module.getLab_test_mark()));
						assignmentText.setText(String.valueOf(module.getAssignment_mark()));
						overallMarkText.setText(String.valueOf(module.getOverall_mark()));
						flag = true;
					}
				}
			}
			if (flag == false) { //if student has not marks set the default value
				quizText.setText("0");
				labTestText.setText("0");
				assignmentText.setText("0");
				overallMarkText.setText("0");
			}
  }

  
}









