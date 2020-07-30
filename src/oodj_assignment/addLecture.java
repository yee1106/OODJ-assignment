package oodj_assignment;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class addLecture extends JFrame implements ActionListener {
    private JLabel IdLabel,nameLabel,passwordLabel,emailLabel,moduleLabel,intakeLabel;
    private JTextField IdText,nameText,passwordText,emailText,moduleText,intakeText;
    private JButton confirmButton, resetButton, exitButton;
    private JPanel IdPanel,namePanel,passwordPanel,emailPanel,modulePanel,intakePanel,buttonPanel;
    
    public addLecture(){
        
        setTitle("Register Educator");
        setBounds(800,400,320,320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7,2));
        
        //JLabel
        IdLabel=new JLabel("Id:");
        nameLabel=new JLabel("Name:");
        passwordLabel=new JLabel("Password:");
        emailLabel=new JLabel("Email:");
        moduleLabel=new JLabel("Modules:");
        intakeLabel=new JLabel("Intake:");
        
        //JTextField
        IdText=new JTextField(20);
        nameText=new JTextField(20);
        passwordText=new JTextField(20);
        emailText=new JTextField(20);
        moduleText=new JTextField(20);
        intakeText= new JTextField(20);
        
        confirmButton=new JButton("Confirm");
        confirmButton.addActionListener(this);
        resetButton=new JButton("Reset");
        resetButton.addActionListener(this);
        exitButton=new JButton("Exit");
        exitButton.addActionListener(this);
        
        IdPanel=new JPanel();
        IdPanel.add(IdLabel);
        IdPanel.add(IdText);
        IdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        namePanel=new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameText);
        namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        passwordPanel=new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);
        passwordPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        emailPanel=new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailText);
        emailPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        modulePanel=new JPanel();
        modulePanel.add(moduleLabel);
        modulePanel.add(moduleText);
        modulePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        intakePanel=new JPanel();
        intakePanel.add(intakeLabel);
        intakePanel.add(intakeText);
        intakePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        buttonPanel=new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);
        
        add(IdPanel);
        add(namePanel);
        add(passwordPanel);
        add(emailPanel);
        add(modulePanel);
        add(intakePanel);
        add(buttonPanel);
        
        setVisible(false);
    }
     @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==confirmButton){
            if(IdText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"** Empty ID!! **", "Lecture's information", JOptionPane.WARNING_MESSAGE);
            }
            else if(nameText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"** Empty Name!! **", "Lecture's information", JOptionPane.WARNING_MESSAGE);
            }
            else if(passwordText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"** Empty Password!! **", "Lecture's information", JOptionPane.WARNING_MESSAGE);
            }
            else if(emailText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"** Empty Email!! **", "Lecture's information", JOptionPane.WARNING_MESSAGE);
            }
            else if(moduleText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"** Empty Module!! **", "Lecture's information", JOptionPane.WARNING_MESSAGE);
            }
            else if(intakeText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"** Empty Name!! **", "Lecture's information", JOptionPane.WARNING_MESSAGE);
            }
            else{
                HashMap<String,String> inta_mod = new HashMap<String,String>();
                inta_mod.put(intakeText.getText(),moduleText.getText());
                Educator ed=new Educator(nameText.getText(),passwordText.getText(),IdText.getText(),emailText.getText(),inta_mod);
                Grading_System.edu.add(ed);
                clear();
            }
        }
        else if(e.getSource()==resetButton){
            clear();
        }
        else if(e.getSource()==exitButton){
            
            
            
            Grading_System.lg.setVisible(true);
            setVisible(false);
        }
        
    }
    public void clear(){//for clear the ID and password text field
        IdText.setText("");
        nameText.setText("");
        passwordText.setText("");
        emailText.setText("");
        moduleText.setText("");
        intakeText.setText("");
    }
}