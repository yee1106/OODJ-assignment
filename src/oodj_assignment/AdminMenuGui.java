package oodj_assignment;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Wong Yee Chung
 * @author Chew chang wang
 */

/*
short-form reference
btn = button, tf =textfield, cb = combo box, ls = list, lb = label, sp = scrollpane
 */
public class AdminMenuGui extends javax.swing.JFrame {

	/**
	 * Creates new JFrame AdminMenuGui
	 */
	CardLayout cardlayout;
	Course current_course = new Course();
	public ArrayList<Student> intake_student = new ArrayList<>();
	
	//DefaultTableModel lecturer_table_m;
	 //JTable lecturer_table;

	public AdminMenuGui() {
		initComponents();
		setVisible(false);
    
    student_cancel_btn.setEnabled(false);
    Comfirm_student_btn.setEnabled(false);
		cardlayout = (CardLayout) (CardLayoutPanel_admin.getLayout());

		//add course level and month to combo box for generate intake code
		for (Integer j = 1; j <= 3; j++) {
			level_cb.addItem(j.toString());
		}

		for (Integer z = 1; z <= 12; z++) {
			String month = String.format("%02d", z);
			month_cb.addItem(month);
		}
		//new
		File file = new File("Intake_module.txt");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				Course course = new Course();
				course.setCourse_name(sc.nextLine());
				String intake = sc.nextLine();
				DefaultComboBoxModel student_intake_cb1 = (DefaultComboBoxModel) student_intake_cb.getModel();
				student_intake_cb1.addElement(intake);
				course.setShort_course_name(intake.substring(10, 12));
				course.getIntake().add(new Intakes(intake));
				String[] split_shortname_module = sc.nextLine().split(",");
				String[] split_name_module = sc.nextLine().split(",");
				ArrayList<Module> module1 = new ArrayList<>();
				for (int i = 0; i < split_shortname_module.length; i++) {
					module1.add(new Module(split_name_module[i], split_shortname_module[i]));
				}
				course.getIntake().get(0).setModule_in_intake(module1);
				sc.nextLine();
				System.out.println(course);
				Grading_System.course_list.add(course);
				Grading_System.course_available_lecturer.add(course);
				System.out.println(Grading_System.course_list);
				//System.out.println(Grading_System.course_available_lecturer);

			}
			sc.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
			comfirm_intake.setEnabled(false);
		}
		if (file.exists()) {
			comfirm_intake.setEnabled(true);
			if (file.length() == 0) {
				comfirm_intake.setEnabled(false);
			}
			DefaultComboBoxModel view_intake_cb3 = (DefaultComboBoxModel) view_intake_cb.getModel();
			//Grading_System.course_list
			//view_intake_cb3.addElement("");
			for (Course c : Grading_System.course_list) {
				String intake_code = c.getIntake().get(0).getIntake_code_general();
				view_intake_cb3.addElement(intake_code);
			}
			
		
		//int size = Grading_System.edu.size();
		
//		String[][] lecturer_data = {{null}};//new String[size][6];
//		for (int i = 0; i < size; i++) {
//			Educator lecturer = Grading_System.edu.get(i);
//			lecturer_data[i][0] = lecturer.getID();
//			lecturer_data[i][1] = lecturer.getName();
//			lecturer_data[i][2] = lecturer.getEmail();
//			lecturer_data[i][3] = lecturer.getIntake_module().get(1);
//			lecturer_data[i][4] = lecturer.getIntake_module().get(2);
//			lecturer_data[i][5] = lecturer.getIntake_module().get(3);
//		}
//		String[] columnNames = {"ID","Name","Email","Intake Module1","Intake Module2","Intake Module3"};
//		lecturer_table_m = new DefaultTableModel(lecturer_data,columnNames){
//			@Override
//			public boolean isCellEditable(int row, int column) {
//		    //all cells false
//			   return false;
//			 }
//		};
//		lecturer_table = new JTable(lecturer_table_m) {
//
//			//Implement table cell tool tips.           
//			@Override
//			public String getToolTipText(MouseEvent e) {
//				String tip = null;
//				java.awt.Point p = e.getPoint();
//				int rowIndex = rowAtPoint(p);
//				int colIndex = columnAtPoint(p);
//
//				try {
//					tip = getValueAt(rowIndex, colIndex).toString();
//				} catch (RuntimeException e1) {
//					//catch null pointer exception if mouse is over an empty line
//				}
//
//				return tip;
//			}
//		};
//		
//		lecturer_sp.getViewport ().add (lecturer_table );	
		
			
		}

		//open all student list file
		File file1 = new File("AllStudentInformation.txt");
		if (file1.exists()) {
			try {
				Scanner sc1 = new Scanner(file1);
				while (sc1.hasNext()) {
					Student stud = new Student();
					stud.setID(sc1.nextLine());
					stud.setPassword(sc1.nextLine());
					stud.setName(sc1.nextLine());
					stud.setEmail(sc1.nextLine());
					stud.setIntake_code(sc1.nextLine());
					stud.setGender(sc1.nextLine());
					stud.setNationality(sc1.nextLine());
					sc1.nextLine();
					intake_student.add(stud);
				}
				sc1.close();
				DefaultTableModel table = (DefaultTableModel) student_table.getModel();
				for (Student s : intake_student) {
					table.addRow(new Object[]{s.getID(), s.getName(), s.getIntake_code(), s.getEmail(), s.getGender()});
				}

			} catch (FileNotFoundException ex) {
				System.out.println("No student list");
			}
		} else {
			System.out.println("No record!");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    SidePanel_admin = new javax.swing.JPanel();
    AdminButton1 = new javax.swing.JButton();
    AdminButton2 = new javax.swing.JButton();
    AdminButton3 = new javax.swing.JButton();
    AdminButton4 = new javax.swing.JButton();
    AdminButton5 = new javax.swing.JButton();
    AdminButton6 = new javax.swing.JButton();
    AdminExit = new javax.swing.JButton();
    MenuPanel = new javax.swing.JPanel();
    MenuLabel = new javax.swing.JLabel();
    CardLayoutPanel_admin = new javax.swing.JPanel();
    AdminMenu_Course = new javax.swing.JPanel();
    Course_Module_lb = new javax.swing.JLabel();
    course_name_lb = new javax.swing.JLabel();
    course_name_tf = new javax.swing.JTextField();
    level_lb = new javax.swing.JLabel();
    level_cb = new javax.swing.JComboBox<>();
    year_lb = new javax.swing.JLabel();
    year_tf = new javax.swing.JTextField();
    month_lb = new javax.swing.JLabel();
    month_cb = new javax.swing.JComboBox<>();
    short_course_name_lb = new javax.swing.JLabel();
    short_course_name_tf = new javax.swing.JTextField();
    module_name_lb = new javax.swing.JLabel();
    module_name_tf = new javax.swing.JTextField();
    module_short_name_lb = new javax.swing.JLabel();
    module_short_name_tf = new javax.swing.JTextField();
    students_in_intake_sp = new javax.swing.JScrollPane();
    students_in_intake_ls = new javax.swing.JList<>();
    studetns_in_module_sp = new javax.swing.JScrollPane();
    students_in_module_ls = new javax.swing.JList<>();
    view_course_lb = new javax.swing.JLabel();
    view_intake_lb = new javax.swing.JLabel();
    view_module_lb = new javax.swing.JLabel();
    students_in_intake_lb = new javax.swing.JLabel();
    students_in_module_lb = new javax.swing.JLabel();
    add_module_btn = new javax.swing.JButton();
    edit_module_btn = new javax.swing.JButton();
    delete_module_btn = new javax.swing.JButton();
    view_course_cb = new javax.swing.JComboBox<>();
    view_intake_cb = new javax.swing.JComboBox<>();
    view_module_cb = new javax.swing.JComboBox<>();
    view_student_list_btn = new javax.swing.JButton();
    add_course_btn = new javax.swing.JButton();
    add_intake_btn = new javax.swing.JButton();
    course_list_report_btn = new javax.swing.JButton();
    confirm_all_btn = new javax.swing.JButton();
    delele_course_btn = new javax.swing.JButton();
    delete_intake_btn = new javax.swing.JButton();
    comfirm_intake = new javax.swing.JButton();
    comfirm_course_btn = new javax.swing.JButton();
    comfirm_module = new javax.swing.JButton();
    add_module_to_lb = new javax.swing.JLabel();
    intake_selected_lb = new javax.swing.JLabel();
    AdminMenu_Student = new javax.swing.JPanel();
    manage_student_lb = new javax.swing.JLabel();
    student_id_lb = new javax.swing.JLabel();
    student_id_tf = new javax.swing.JTextField();
    student_name_lb = new javax.swing.JLabel();
    student_name_tf = new javax.swing.JTextField();
    student_password_lb = new javax.swing.JLabel();
    student_password_tf = new javax.swing.JTextField();
    student_intake_lb = new javax.swing.JLabel();
    student_intake_cb = new javax.swing.JComboBox<>();
    add_student_btn = new javax.swing.JButton();
    edit_student_btn = new javax.swing.JButton();
    delete_student_btn = new javax.swing.JButton();
    generate_student_list_btn = new javax.swing.JButton();
    student_sp = new javax.swing.JScrollPane();
    student_table = new javax.swing.JTable();
    student_email_lb = new javax.swing.JLabel();
    student_email_tf = new javax.swing.JTextField();
    student_gender_lb = new javax.swing.JLabel();
    student_gender_tf = new javax.swing.JTextField();
    student_nationality_lb = new javax.swing.JLabel();
    student_nationality_tf = new javax.swing.JTextField();
    Comfirm_student_btn = new javax.swing.JButton();
    student_cancel_btn = new javax.swing.JButton();
    AdminMenu_Lecturer = new javax.swing.JPanel();
    manage_lecturer_lb = new javax.swing.JLabel();
    lecturer_id_lb = new javax.swing.JLabel();
    lecturer_id_tf = new javax.swing.JTextField();
    lecturer_name_lb = new javax.swing.JLabel();
    lecturer_name_tf = new javax.swing.JTextField();
    lecturer_password_lb = new javax.swing.JLabel();
    lecturer_password_tf = new javax.swing.JTextField();
    lecturer_intake1_lb = new javax.swing.JLabel();
    lecturer_intake1_cb = new javax.swing.JComboBox<>();
    lecturer_intake2_lb = new javax.swing.JLabel();
    lecturer_intake2_cb = new javax.swing.JComboBox<>();
    lecturer_module1_cb = new javax.swing.JComboBox<>();
    lecturer_module2_cb = new javax.swing.JComboBox<>();
    lecturer_intake3_lb = new javax.swing.JLabel();
    lecturer_intake3_cb = new javax.swing.JComboBox<>();
    lecturer_module3_cb = new javax.swing.JComboBox<>();
    add_lecturer_btn = new javax.swing.JButton();
    edit_lecturer_btn = new javax.swing.JButton();
    delete_lecturer_btn = new javax.swing.JButton();
    lecturer_sp = new javax.swing.JScrollPane();
    lecturer_table = new javax.swing.JTable();
    generate_lecturer_list_btn = new javax.swing.JButton();
    comfirm_intake_module1_btn = new javax.swing.JButton();
    comfirm_intake_module2_btn = new javax.swing.JButton();
    comfirm_intake_module3_btn = new javax.swing.JButton();
    lecturer_email_lb = new javax.swing.JLabel();
    lecturer_email_tf = new javax.swing.JTextField();
    selected_intake1_tf = new javax.swing.JTextField();
    selected_intake2_tf = new javax.swing.JTextField();
    selected_intake3_tf = new javax.swing.JTextField();
    selected_intake_lb = new javax.swing.JLabel();
    comfirm_module1_btn = new javax.swing.JButton();
    comfirm_module2_btn = new javax.swing.JButton();
    comfirm_module3_btn = new javax.swing.JButton();
    lecturer_refresh_intake_btn = new javax.swing.JButton();
    AdminMenu_log = new javax.swing.JPanel();
    ScrollPane_log = new javax.swing.JScrollPane();
    LogTable = new javax.swing.JTable();
    log = new javax.swing.JLabel();
    generate_log_btn = new javax.swing.JButton();
    AdminMenu_report = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    AdminMenu_Admin = new javax.swing.JPanel();
    manage_admin_lb = new javax.swing.JLabel();
    admin_id_lb = new javax.swing.JLabel();
    admin_id_tf = new javax.swing.JTextField();
    admin_name_lb = new javax.swing.JLabel();
    admin_name_tf = new javax.swing.JTextField();
    admin_password_lb = new javax.swing.JLabel();
    admin_password_tf = new javax.swing.JTextField();
    admin_sp = new javax.swing.JScrollPane();
    admin_table = new javax.swing.JTable();
    add_admin_btn = new javax.swing.JButton();
    edtt_admin_btn = new javax.swing.JButton();
    delete_admin_btn = new javax.swing.JButton();
    generate_admin_list_btn = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Admin Menu");
    setUndecorated(true);
    setResizable(false);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    SidePanel_admin.setBackground(new java.awt.Color(0, 0, 51));
    SidePanel_admin.setForeground(new java.awt.Color(255, 255, 255));
    SidePanel_admin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        SidePanel_adminMouseDragged(evt);
      }
    });
    SidePanel_admin.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        SidePanel_adminMousePressed(evt);
      }
    });

    AdminButton1.setBackground(new java.awt.Color(62, 128, 194));
    AdminButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
    AdminButton1.setForeground(new java.awt.Color(255, 255, 255));
    AdminButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_study_15px.png"))); // NOI18N
    AdminButton1.setText("Course and Module");
    AdminButton1.setToolTipText("Create and Modify Course and module");
    AdminButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    AdminButton1.setBorderPainted(false);
    AdminButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    AdminButton1.setMaximumSize(new java.awt.Dimension(81, 22));
    AdminButton1.setMinimumSize(new java.awt.Dimension(81, 22));
    AdminButton1.setPreferredSize(new java.awt.Dimension(81, 22));
    AdminButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AdminButton1ActionPerformed(evt);
      }
    });

    AdminButton2.setBackground(new java.awt.Color(0, 0, 51));
    AdminButton2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
    AdminButton2.setForeground(new java.awt.Color(255, 255, 255));
    AdminButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_student_male_15px.png"))); // NOI18N
    AdminButton2.setText("Student");
    AdminButton2.setToolTipText("Add and modify student account");
    AdminButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    AdminButton2.setBorderPainted(false);
    AdminButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    AdminButton2.setMaximumSize(new java.awt.Dimension(81, 22));
    AdminButton2.setMinimumSize(new java.awt.Dimension(81, 22));
    AdminButton2.setPreferredSize(new java.awt.Dimension(81, 22));
    AdminButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AdminButton2ActionPerformed(evt);
      }
    });

    AdminButton3.setBackground(new java.awt.Color(0, 0, 51));
    AdminButton3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
    AdminButton3.setForeground(new java.awt.Color(255, 255, 255));
    AdminButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_teacher_15px.png"))); // NOI18N
    AdminButton3.setText("Lecturer");
    AdminButton3.setToolTipText("Add and modify lecturer Account");
    AdminButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    AdminButton3.setBorderPainted(false);
    AdminButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    AdminButton3.setMaximumSize(new java.awt.Dimension(81, 22));
    AdminButton3.setMinimumSize(new java.awt.Dimension(81, 22));
    AdminButton3.setPreferredSize(new java.awt.Dimension(81, 22));
    AdminButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AdminButton3ActionPerformed(evt);
      }
    });

    AdminButton4.setBackground(new java.awt.Color(0, 0, 51));
    AdminButton4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
    AdminButton4.setForeground(new java.awt.Color(255, 255, 255));
    AdminButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_log_15px.png"))); // NOI18N
    AdminButton4.setText("Log");
    AdminButton4.setToolTipText("Generate and view log File");
    AdminButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    AdminButton4.setBorderPainted(false);
    AdminButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    AdminButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AdminButton4ActionPerformed(evt);
      }
    });

    AdminButton5.setBackground(new java.awt.Color(0, 0, 51));
    AdminButton5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
    AdminButton5.setForeground(new java.awt.Color(255, 255, 255));
    AdminButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_graph_report_15px.png"))); // NOI18N
    AdminButton5.setText("Report");
    AdminButton5.setToolTipText("Generate Report");
    AdminButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    AdminButton5.setBorderPainted(false);
    AdminButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    AdminButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AdminButton5ActionPerformed(evt);
      }
    });

    AdminButton6.setBackground(new java.awt.Color(0, 0, 51));
    AdminButton6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
    AdminButton6.setForeground(new java.awt.Color(255, 255, 255));
    AdminButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_admin_settings_male_15px.png"))); // NOI18N
    AdminButton6.setText("Administrator");
    AdminButton6.setToolTipText("Add and modify Administrator");
    AdminButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    AdminButton6.setBorderPainted(false);
    AdminButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    AdminButton6.setMaximumSize(new java.awt.Dimension(81, 22));
    AdminButton6.setMinimumSize(new java.awt.Dimension(81, 22));
    AdminButton6.setPreferredSize(new java.awt.Dimension(81, 22));
    AdminButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AdminButton6ActionPerformed(evt);
      }
    });

    AdminExit.setBackground(new java.awt.Color(0, 0, 51));
    AdminExit.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
    AdminExit.setForeground(new java.awt.Color(255, 255, 255));
    AdminExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_exit_15px.png"))); // NOI18N
    AdminExit.setText("Exit");
    AdminExit.setToolTipText("Exit");
    AdminExit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    AdminExit.setBorderPainted(false);
    AdminExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    AdminExit.setMaximumSize(new java.awt.Dimension(81, 22));
    AdminExit.setMinimumSize(new java.awt.Dimension(81, 22));
    AdminExit.setPreferredSize(new java.awt.Dimension(81, 22));
    AdminExit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AdminExitActionPerformed(evt);
      }
    });

    MenuPanel.setBackground(new java.awt.Color(0, 73, 102));
    MenuPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        MenuPanelMouseDragged(evt);
      }
    });
    MenuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        MenuPanelMousePressed(evt);
      }
    });

    MenuLabel.setBackground(new java.awt.Color(255, 255, 255));
    MenuLabel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
    MenuLabel.setForeground(new java.awt.Color(255, 255, 255));
    MenuLabel.setText("MENU");

    javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
    MenuPanel.setLayout(MenuPanelLayout);
    MenuPanelLayout.setHorizontalGroup(
      MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(MenuPanelLayout.createSequentialGroup()
        .addGap(47, 47, 47)
        .addComponent(MenuLabel)
        .addContainerGap(51, Short.MAX_VALUE))
    );
    MenuPanelLayout.setVerticalGroup(
      MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(MenuPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(MenuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );

    javax.swing.GroupLayout SidePanel_adminLayout = new javax.swing.GroupLayout(SidePanel_admin);
    SidePanel_admin.setLayout(SidePanel_adminLayout);
    SidePanel_adminLayout.setHorizontalGroup(
      SidePanel_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(AdminButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(AdminButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(AdminButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(AdminButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(AdminButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(AdminExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(AdminButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    SidePanel_adminLayout.setVerticalGroup(
      SidePanel_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(SidePanel_adminLayout.createSequentialGroup()
        .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(84, 84, 84)
        .addComponent(AdminButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(AdminButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(AdminButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(AdminButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(AdminButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(AdminButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(AdminExit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(81, 81, 81))
    );

    getContentPane().add(SidePanel_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 510));

    CardLayoutPanel_admin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        CardLayoutPanel_adminMouseDragged(evt);
      }
    });
    CardLayoutPanel_admin.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        CardLayoutPanel_adminMousePressed(evt);
      }
    });
    CardLayoutPanel_admin.setLayout(new java.awt.CardLayout());

    AdminMenu_Course.setBackground(new java.awt.Color(204, 253, 255));

    Course_Module_lb.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
    Course_Module_lb.setText("Course and  Module");

    course_name_lb.setText("Course Name");

    course_name_tf.setColumns(10);

    level_lb.setText("Level");

    level_cb.setMaximumRowCount(3);

    year_lb.setText("Year");

    year_tf.setColumns(5);

    month_lb.setText("Month");

    month_cb.setMaximumRowCount(12);

    short_course_name_lb.setText("Short Course Name");

    short_course_name_tf.setColumns(10);

    module_name_lb.setText("Module Name");

    module_name_tf.setColumns(10);

    module_short_name_lb.setText("Module Short Name");
    module_short_name_lb.setToolTipText("");

    module_short_name_tf.setColumns(5);

    students_in_intake_ls.setModel(new javax.swing.AbstractListModel<String>() {
      String[] strings = { " " };
      public int getSize() { return strings.length; }
      public String getElementAt(int i) { return strings[i]; }
    });
    students_in_intake_sp.setViewportView(students_in_intake_ls);

    studetns_in_module_sp.setViewportView(students_in_module_ls);

    view_course_lb.setText("Course");

    view_intake_lb.setText("Intake");

    view_module_lb.setText("Module in intake");

    students_in_intake_lb.setText("Students  in intake ");

    students_in_module_lb.setText("Student in Module");

    add_module_btn.setText("Add");
    add_module_btn.setEnabled(false);
    add_module_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        add_module_btnActionPerformed(evt);
      }
    });

    edit_module_btn.setText("Edit");
    edit_module_btn.setEnabled(false);
    edit_module_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        edit_module_btnActionPerformed(evt);
      }
    });

    delete_module_btn.setText("Delete");
    delete_module_btn.setEnabled(false);
    delete_module_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delete_module_btnActionPerformed(evt);
      }
    });

    view_course_cb.setMaximumRowCount(50);

    view_intake_cb.setMaximumRowCount(9);

    view_module_cb.setMaximumRowCount(5);
    view_module_cb.setToolTipText("");

    view_student_list_btn.setText(" View student list");
    view_student_list_btn.setEnabled(false);
    view_student_list_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        view_student_list_btnActionPerformed(evt);
      }
    });

    add_course_btn.setText("Add Course");
    add_course_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        add_course_btnActionPerformed(evt);
      }
    });

    add_intake_btn.setText("Add new intake");
    add_intake_btn.setEnabled(false);
    add_intake_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        add_intake_btnActionPerformed(evt);
      }
    });

    course_list_report_btn.setText("Generate Course List Report");
    course_list_report_btn.setEnabled(false);
    course_list_report_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        course_list_report_btnActionPerformed(evt);
      }
    });

    confirm_all_btn.setText("Comfirm All Changes");
    confirm_all_btn.setToolTipText("Comfirm all course modification");
    confirm_all_btn.setEnabled(false);
    confirm_all_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        confirm_all_btnActionPerformed(evt);
      }
    });

    delele_course_btn.setText("Delete course");
    delele_course_btn.setEnabled(false);
    delele_course_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delele_course_btnActionPerformed(evt);
      }
    });

    delete_intake_btn.setText("Delete Intake");
    delete_intake_btn.setEnabled(false);
    delete_intake_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delete_intake_btnActionPerformed(evt);
      }
    });

    comfirm_intake.setText("Comfirm");
    comfirm_intake.setToolTipText("Comfirm Intake Selection");
    comfirm_intake.setEnabled(false);
    comfirm_intake.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comfirm_intakeActionPerformed(evt);
      }
    });

    comfirm_course_btn.setText("Comfirm");
    comfirm_course_btn.setToolTipText("Comfirm Coourse Selection");
    comfirm_course_btn.setEnabled(false);
    comfirm_course_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comfirm_course_btnActionPerformed(evt);
      }
    });

    comfirm_module.setText("Comfirm");
    comfirm_module.setToolTipText("Comfirm Module");
    comfirm_module.setEnabled(false);
    comfirm_module.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comfirm_moduleActionPerformed(evt);
      }
    });

    add_module_to_lb.setText("Add modules to ");

    intake_selected_lb.setBackground(new java.awt.Color(255, 0, 51));
    intake_selected_lb.setForeground(new java.awt.Color(255, 0, 51));

    javax.swing.GroupLayout AdminMenu_CourseLayout = new javax.swing.GroupLayout(AdminMenu_Course);
    AdminMenu_Course.setLayout(AdminMenu_CourseLayout);
    AdminMenu_CourseLayout.setHorizontalGroup(
      AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
            .addComponent(course_name_lb)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(course_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(short_course_name_lb)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(short_course_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(add_course_btn)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
            .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(view_course_lb)
              .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                .addComponent(view_course_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comfirm_course_btn))
              .addComponent(Course_Module_lb)
              .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                .addComponent(add_module_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_module_btn))
              .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                .addComponent(module_name_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(module_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(module_short_name_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(module_short_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(students_in_intake_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(students_in_intake_lb)
                  .addComponent(view_intake_lb))
                .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(students_in_module_lb)
                      .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                        .addComponent(studetns_in_module_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addComponent(course_list_report_btn)
                          .addComponent(confirm_all_btn)
                          .addComponent(view_student_list_btn)))))
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminMenu_CourseLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(delele_course_btn)
                    .addGap(154, 154, 154))
                  .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(delete_intake_btn)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminMenu_CourseLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(view_module_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110))
                      .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                            .addComponent(comfirm_module)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(delete_module_btn))
                          .addComponent(view_module_lb))
                        .addGap(0, 0, Short.MAX_VALUE))))))
              .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                .addComponent(view_intake_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comfirm_intake)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
            .addComponent(level_lb)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(level_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(year_lb)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(year_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(month_lb)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(month_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(add_intake_btn)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
            .addComponent(add_module_to_lb)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(intake_selected_lb)
            .addGap(0, 0, Short.MAX_VALUE))))
    );
    AdminMenu_CourseLayout.setVerticalGroup(
      AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
        .addGap(27, 27, 27)
        .addComponent(Course_Module_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(course_name_lb)
          .addComponent(course_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(short_course_name_lb)
          .addComponent(short_course_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(add_course_btn))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(level_lb)
          .addComponent(level_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(year_lb)
          .addComponent(year_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(month_lb)
          .addComponent(month_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(add_intake_btn))
        .addGap(24, 24, 24)
        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(add_module_to_lb)
          .addComponent(intake_selected_lb))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(module_name_lb)
          .addComponent(module_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(module_short_name_lb)
          .addComponent(module_short_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(add_module_btn)
          .addComponent(edit_module_btn))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(view_course_lb)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(view_course_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comfirm_course_btn)
          .addComponent(delele_course_btn))
        .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdminMenu_CourseLayout.createSequentialGroup()
            .addGap(111, 111, 111)
            .addComponent(view_student_list_btn)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(confirm_all_btn)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(course_list_report_btn))
          .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
            .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminMenu_CourseLayout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(view_intake_lb)
                  .addComponent(view_module_lb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(view_intake_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(view_module_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(comfirm_intake)
                  .addComponent(delete_intake_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(students_in_intake_lb)
                  .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comfirm_module)
                    .addComponent(delete_module_btn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
              .addGroup(AdminMenu_CourseLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(students_in_module_lb)
                .addGap(8, 8, 8)))
            .addGroup(AdminMenu_CourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(students_in_intake_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
              .addComponent(studetns_in_module_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))))
        .addContainerGap())
    );

    CardLayoutPanel_admin.add(AdminMenu_Course, "1");

    AdminMenu_Student.setBackground(new java.awt.Color(204, 253, 255));

    manage_student_lb.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
    manage_student_lb.setText("Manage Student");

    student_id_lb.setText("Student ID");

    student_id_tf.setColumns(6);

    student_name_lb.setText("Name");

    student_name_tf.setColumns(10);

    student_password_lb.setText("Default Password");

    student_password_tf.setColumns(15);

    student_intake_lb.setText("Intake");

    student_intake_cb.setMaximumRowCount(9);
    student_intake_cb.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        student_intake_cbActionPerformed(evt);
      }
    });

    add_student_btn.setText("Add");
    add_student_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        add_student_btnActionPerformed(evt);
      }
    });

    edit_student_btn.setText("Edit");
    edit_student_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        edit_student_btnActionPerformed(evt);
      }
    });

    delete_student_btn.setText("Delete");
    delete_student_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delete_student_btnActionPerformed(evt);
      }
    });

    generate_student_list_btn.setText("Generate student list");
    generate_student_list_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        generate_student_list_btnActionPerformed(evt);
      }
    });

    student_table.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "ID", "Name", "Intake", "Email", "Gender", "Nationality"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    student_table.setRowSelectionAllowed(false);
    student_table.getTableHeader().setResizingAllowed(false);
    student_table.getTableHeader().setReorderingAllowed(false);
    student_sp.setViewportView(student_table);

    student_email_lb.setText("Email");

    student_email_tf.setColumns(10);

    student_gender_lb.setText("Gender");

    student_gender_tf.setColumns(6);

    student_nationality_lb.setText("Nationality");

    student_nationality_tf.setColumns(10);

    Comfirm_student_btn.setText("Comfirm");
    Comfirm_student_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        Comfirm_student_btnActionPerformed(evt);
      }
    });

    student_cancel_btn.setText("Cancel");
    student_cancel_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        student_cancel_btnActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout AdminMenu_StudentLayout = new javax.swing.GroupLayout(AdminMenu_Student);
    AdminMenu_Student.setLayout(AdminMenu_StudentLayout);
    AdminMenu_StudentLayout.setHorizontalGroup(
      AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_StudentLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(student_sp)
          .addGroup(AdminMenu_StudentLayout.createSequentialGroup()
            .addGroup(AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(student_id_lb)
              .addComponent(student_email_lb))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(AdminMenu_StudentLayout.createSequentialGroup()
                .addComponent(student_id_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(student_name_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(student_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(student_password_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(student_password_tf))
              .addGroup(AdminMenu_StudentLayout.createSequentialGroup()
                .addComponent(student_email_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(student_gender_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(student_gender_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(student_nationality_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(student_nationality_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))))
          .addGroup(AdminMenu_StudentLayout.createSequentialGroup()
            .addGroup(AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(manage_student_lb)
              .addGroup(AdminMenu_StudentLayout.createSequentialGroup()
                .addComponent(student_intake_lb)
                .addGap(29, 29, 29)
                .addComponent(student_intake_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(AdminMenu_StudentLayout.createSequentialGroup()
                .addComponent(add_student_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_student_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(student_cancel_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Comfirm_student_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_student_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generate_student_list_btn)))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    AdminMenu_StudentLayout.setVerticalGroup(
      AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_StudentLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(manage_student_lb)
        .addGap(25, 25, 25)
        .addGroup(AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(student_id_lb)
          .addComponent(student_id_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(student_name_lb)
          .addComponent(student_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(student_password_lb)
          .addComponent(student_password_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(student_email_lb)
          .addComponent(student_email_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(student_gender_lb)
          .addComponent(student_gender_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(student_nationality_lb)
          .addComponent(student_nationality_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(student_intake_lb)
          .addComponent(student_intake_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
        .addGroup(AdminMenu_StudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(add_student_btn)
          .addComponent(edit_student_btn)
          .addComponent(delete_student_btn)
          .addComponent(generate_student_list_btn)
          .addComponent(Comfirm_student_btn)
          .addComponent(student_cancel_btn))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(student_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    CardLayoutPanel_admin.add(AdminMenu_Student, "2");

    AdminMenu_Lecturer.setBackground(new java.awt.Color(204, 253, 255));

    manage_lecturer_lb.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
    manage_lecturer_lb.setText("Manage Lecturer");

    lecturer_id_lb.setText("Lecturer ID");

    lecturer_id_tf.setColumns(6);

    lecturer_name_lb.setText("Name");

    lecturer_name_tf.setColumns(10);

    lecturer_password_lb.setText("Default password");

    lecturer_password_tf.setColumns(15);

    lecturer_intake1_lb.setText("Intake 1");

    lecturer_intake1_cb.setMaximumRowCount(10);
    lecturer_intake1_cb.setToolTipText((String)this.lecturer_intake1_cb.getSelectedItem());

    lecturer_intake2_lb.setText("Intake 2");

    lecturer_intake2_cb.setMaximumRowCount(10);
    lecturer_intake2_cb.setToolTipText((String)this.lecturer_intake2_cb.getSelectedItem());

    lecturer_intake3_lb.setText("Intake 3");

    lecturer_intake3_cb.setMaximumRowCount(10);
    lecturer_intake3_cb.setToolTipText((String)this.lecturer_intake3_cb.getSelectedItem());

    add_lecturer_btn.setText("Add");
    add_lecturer_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        add_lecturer_btnActionPerformed(evt);
      }
    });

    edit_lecturer_btn.setText("Edit");
    edit_lecturer_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        edit_lecturer_btnActionPerformed(evt);
      }
    });

    delete_lecturer_btn.setText("Delete");
    delete_lecturer_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delete_lecturer_btnActionPerformed(evt);
      }
    });

    lecturer_table.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "ID", "Name", "Email", "Intake Module1", "Intake Module2", "Intake Module3"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    lecturer_table.getTableHeader().setResizingAllowed(false);
    lecturer_table.getTableHeader().setReorderingAllowed(false);
    lecturer_sp.setViewportView(lecturer_table);

    generate_lecturer_list_btn.setText("Generate Lecturer list");
    generate_lecturer_list_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        generate_lecturer_list_btnActionPerformed(evt);
      }
    });

    comfirm_intake_module1_btn.setText("Comfirm");
    comfirm_intake_module1_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comfirm_intake_module1_btnActionPerformed(evt);
      }
    });

    comfirm_intake_module2_btn.setText("Comfirm");
    comfirm_intake_module2_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comfirm_intake_module2_btnActionPerformed(evt);
      }
    });

    comfirm_intake_module3_btn.setText("Comfirm");
    comfirm_intake_module3_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comfirm_intake_module3_btnActionPerformed(evt);
      }
    });

    lecturer_email_lb.setText("Email");

    lecturer_email_tf.setColumns(10);

    selected_intake1_tf.setEditable(false);
    selected_intake1_tf.setColumns(10);

    selected_intake2_tf.setEditable(false);
    selected_intake2_tf.setColumns(10);

    selected_intake3_tf.setEditable(false);
    selected_intake3_tf.setColumns(10);

    selected_intake_lb.setText("Selected intakes and modules");

    comfirm_module1_btn.setText("Comfirm");
    comfirm_module1_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comfirm_module1_btnActionPerformed(evt);
      }
    });

    comfirm_module2_btn.setText("Comfirm");
    comfirm_module2_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comfirm_module2_btnActionPerformed(evt);
      }
    });

    comfirm_module3_btn.setText("Comfirm");
    comfirm_module3_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comfirm_module3_btnActionPerformed(evt);
      }
    });

    lecturer_refresh_intake_btn.setText("refresh");
    lecturer_refresh_intake_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        lecturer_refresh_intake_btnActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout AdminMenu_LecturerLayout = new javax.swing.GroupLayout(AdminMenu_Lecturer);
    AdminMenu_Lecturer.setLayout(AdminMenu_LecturerLayout);
    AdminMenu_LecturerLayout.setHorizontalGroup(
      AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
            .addComponent(lecturer_sp)
            .addContainerGap())
          .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
            .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(manage_lecturer_lb)
              .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
                .addComponent(lecturer_password_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lecturer_password_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
                .addComponent(lecturer_id_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lecturer_id_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lecturer_name_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lecturer_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lecturer_email_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lecturer_email_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
                .addComponent(add_lecturer_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit_lecturer_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_lecturer_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generate_lecturer_list_btn))
              .addComponent(selected_intake_lb)
              .addComponent(lecturer_refresh_intake_btn))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminMenu_LecturerLayout.createSequentialGroup()
            .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
                .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                  .addComponent(selected_intake3_tf, javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(selected_intake2_tf, javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(selected_intake1_tf, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, Short.MAX_VALUE))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdminMenu_LecturerLayout.createSequentialGroup()
                .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                  .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
                    .addComponent(lecturer_intake3_lb)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lecturer_intake3_cb, 0, 103, Short.MAX_VALUE))
                  .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdminMenu_LecturerLayout.createSequentialGroup()
                    .addComponent(lecturer_intake2_lb)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lecturer_intake2_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdminMenu_LecturerLayout.createSequentialGroup()
                    .addComponent(lecturer_intake1_lb)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lecturer_intake1_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
                    .addComponent(comfirm_intake_module3_btn)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lecturer_module3_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(comfirm_module3_btn))
                  .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
                    .addComponent(comfirm_intake_module1_btn)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lecturer_module1_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(comfirm_module1_btn))
                  .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
                    .addComponent(comfirm_intake_module2_btn)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lecturer_module2_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(comfirm_module2_btn)))))
            .addGap(74, 74, 74))))
    );
    AdminMenu_LecturerLayout.setVerticalGroup(
      AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_LecturerLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(manage_lecturer_lb)
        .addGap(4, 4, 4)
        .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lecturer_id_lb)
          .addComponent(lecturer_id_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lecturer_name_lb)
          .addComponent(lecturer_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lecturer_email_lb)
          .addComponent(lecturer_email_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lecturer_password_lb)
          .addComponent(lecturer_password_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(7, 7, 7)
        .addComponent(lecturer_refresh_intake_btn)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lecturer_intake1_lb)
          .addComponent(lecturer_intake1_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comfirm_intake_module1_btn)
          .addComponent(lecturer_module1_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comfirm_module1_btn))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lecturer_intake2_lb)
          .addComponent(lecturer_intake2_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comfirm_intake_module2_btn)
          .addComponent(lecturer_module2_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comfirm_module2_btn))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lecturer_intake3_lb)
          .addComponent(lecturer_intake3_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comfirm_intake_module3_btn)
          .addComponent(lecturer_module3_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(comfirm_module3_btn))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(selected_intake_lb)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(selected_intake1_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(selected_intake2_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(selected_intake3_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(AdminMenu_LecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(add_lecturer_btn)
          .addComponent(edit_lecturer_btn)
          .addComponent(delete_lecturer_btn)
          .addComponent(generate_lecturer_list_btn))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(lecturer_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    CardLayoutPanel_admin.add(AdminMenu_Lecturer, "3");

    AdminMenu_log.setBackground(new java.awt.Color(204, 253, 255));

    ScrollPane_log.setBackground(new java.awt.Color(204, 253, 255));
    ScrollPane_log.setForeground(new java.awt.Color(204, 253, 255));

    LogTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "ID", "Name", "Mode", "Log In time", "Log Out  time"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    LogTable.setRowSelectionAllowed(false);
    LogTable.getTableHeader().setResizingAllowed(false);
    LogTable.getTableHeader().setReorderingAllowed(false);
    ScrollPane_log.setViewportView(LogTable);

    log.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
    log.setText("User Log");

    generate_log_btn.setText("Generate Log Report");
    generate_log_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        generate_log_btnActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout AdminMenu_logLayout = new javax.swing.GroupLayout(AdminMenu_log);
    AdminMenu_log.setLayout(AdminMenu_logLayout);
    AdminMenu_logLayout.setHorizontalGroup(
      AdminMenu_logLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(ScrollPane_log, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
      .addGroup(AdminMenu_logLayout.createSequentialGroup()
        .addGroup(AdminMenu_logLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(AdminMenu_logLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(log, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(AdminMenu_logLayout.createSequentialGroup()
            .addGap(213, 213, 213)
            .addComponent(generate_log_btn)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    AdminMenu_logLayout.setVerticalGroup(
      AdminMenu_logLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_logLayout.createSequentialGroup()
        .addGap(35, 35, 35)
        .addComponent(log)
        .addGap(58, 58, 58)
        .addComponent(ScrollPane_log, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(generate_log_btn)
        .addContainerGap(94, Short.MAX_VALUE))
    );

    CardLayoutPanel_admin.add(AdminMenu_log, "4");

    AdminMenu_report.setBackground(new java.awt.Color(204, 253, 255));

    jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
    jLabel1.setText("Other Reports");

    javax.swing.GroupLayout AdminMenu_reportLayout = new javax.swing.GroupLayout(AdminMenu_report);
    AdminMenu_report.setLayout(AdminMenu_reportLayout);
    AdminMenu_reportLayout.setHorizontalGroup(
      AdminMenu_reportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_reportLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addContainerGap(451, Short.MAX_VALUE))
    );
    AdminMenu_reportLayout.setVerticalGroup(
      AdminMenu_reportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_reportLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addContainerGap(480, Short.MAX_VALUE))
    );

    CardLayoutPanel_admin.add(AdminMenu_report, "5");

    AdminMenu_Admin.setBackground(new java.awt.Color(204, 253, 255));

    manage_admin_lb.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
    manage_admin_lb.setText("Manage Administrator");

    admin_id_lb.setText("Administrator ID");

    admin_id_tf.setColumns(6);

    admin_name_lb.setText("Name");

    admin_name_tf.setColumns(10);

    admin_password_lb.setText("Password");

    admin_password_tf.setColumns(10);

    admin_table.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "ID", "Name"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    admin_sp.setViewportView(admin_table);

    add_admin_btn.setText("Add");
    add_admin_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        add_admin_btnActionPerformed(evt);
      }
    });

    edtt_admin_btn.setText("Edit");
    edtt_admin_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        edtt_admin_btnActionPerformed(evt);
      }
    });

    delete_admin_btn.setText("Delete");
    delete_admin_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delete_admin_btnActionPerformed(evt);
      }
    });

    generate_admin_list_btn.setText("Generate Administrator List");
    generate_admin_list_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        generate_admin_list_btnActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout AdminMenu_AdminLayout = new javax.swing.GroupLayout(AdminMenu_Admin);
    AdminMenu_Admin.setLayout(AdminMenu_AdminLayout);
    AdminMenu_AdminLayout.setHorizontalGroup(
      AdminMenu_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_AdminLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(AdminMenu_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(admin_sp)
          .addGroup(AdminMenu_AdminLayout.createSequentialGroup()
            .addGroup(AdminMenu_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(manage_admin_lb)
              .addGroup(AdminMenu_AdminLayout.createSequentialGroup()
                .addComponent(admin_id_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_id_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_name_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_password_lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_password_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(AdminMenu_AdminLayout.createSequentialGroup()
                .addComponent(add_admin_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtt_admin_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_admin_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generate_admin_list_btn)))
            .addGap(0, 41, Short.MAX_VALUE)))
        .addContainerGap())
    );
    AdminMenu_AdminLayout.setVerticalGroup(
      AdminMenu_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AdminMenu_AdminLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(manage_admin_lb)
        .addGap(18, 18, 18)
        .addGroup(AdminMenu_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(admin_id_lb)
          .addComponent(admin_id_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(admin_name_lb)
          .addComponent(admin_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(admin_password_lb)
          .addComponent(admin_password_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(62, 62, 62)
        .addGroup(AdminMenu_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(add_admin_btn)
          .addComponent(edtt_admin_btn)
          .addComponent(delete_admin_btn)
          .addComponent(generate_admin_list_btn))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(admin_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        .addContainerGap())
    );

    CardLayoutPanel_admin.add(AdminMenu_Admin, "6");

    getContentPane().add(CardLayoutPanel_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 580, 510));

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void AdminButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminButton1ActionPerformed
		// TODO add your handling code here:
		SetColor(AdminButton1);
		ResetColor(AdminButton2);
		ResetColor(AdminButton3);
		ResetColor(AdminButton4);
		ResetColor(AdminButton5);
		ResetColor(AdminButton6);

		cardlayout.show(CardLayoutPanel_admin, "1");
  }//GEN-LAST:event_AdminButton1ActionPerformed

  private void AdminButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminButton2ActionPerformed
		// TODO add your handling code here:
		ResetColor(AdminButton1);
		SetColor(AdminButton2);
		ResetColor(AdminButton3);
		ResetColor(AdminButton4);
		ResetColor(AdminButton5);
		ResetColor(AdminButton6);

		cardlayout.show(CardLayoutPanel_admin, "2");
  }//GEN-LAST:event_AdminButton2ActionPerformed

  private void AdminButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminButton3ActionPerformed
		// TODO add your handling code here:
		ResetColor(AdminButton1);
		ResetColor(AdminButton2);
		SetColor(AdminButton3);
		ResetColor(AdminButton4);
		ResetColor(AdminButton5);
		ResetColor(AdminButton6);

		cardlayout.show(CardLayoutPanel_admin, "3");
		
		
		
		
  }//GEN-LAST:event_AdminButton3ActionPerformed

  private void AdminButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminButton4ActionPerformed
		// TODO add your handling code here:
		ResetColor(AdminButton1);
		ResetColor(AdminButton2);
		ResetColor(AdminButton3);
		SetColor(AdminButton4);
		ResetColor(AdminButton5);
		ResetColor(AdminButton6);

		cardlayout.show(CardLayoutPanel_admin, "4");
  }//GEN-LAST:event_AdminButton4ActionPerformed

  private void AdminButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminButton6ActionPerformed
		// TODO add your handling code here:
		ResetColor(AdminButton1);
		ResetColor(AdminButton2);
		ResetColor(AdminButton3);
		ResetColor(AdminButton4);
		ResetColor(AdminButton5);
		SetColor(AdminButton6);

		cardlayout.show(CardLayoutPanel_admin, "6");

  }//GEN-LAST:event_AdminButton6ActionPerformed

  private void AdminExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminExitActionPerformed
		// TODO add your handling code here:
		setVisible(false);
		Grading_System.lg.setVisible(true);
		Date date = new Date();
		Grading_System.currentUser.setLogout(date);
		Grading_System.logFile();
		//System.exit(0);
		
  }//GEN-LAST:event_AdminExitActionPerformed


  private void add_course_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_course_btnActionPerformed
		// TODO add your handling code here:
		if (evt.getSource() == add_course_btn) {
			//check invalid empty input.
			if (course_name_tf.getText().equals("")) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Empty course name!", "Manage Course", JOptionPane.WARNING_MESSAGE);
			} else if (short_course_name_tf.getText().equals("")) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Empty short course name!", "Manage Course", JOptionPane.WARNING_MESSAGE);
				//Check occurence of digit in course name.
			} else if (course_name_tf.getText().matches(".*\\d.*") || short_course_name_tf.getText().matches(".*\\d.*")) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Invalid course name!", "Manage Course", JOptionPane.WARNING_MESSAGE);

			} else if (course_name_tf.getText().equals(short_course_name_tf.getText())) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Full course name and short course name cannot be same!", "Manage Course", JOptionPane.WARNING_MESSAGE);

			} else {

				//Create a course object
				//Course current_course = new Course(course_name_tf.getText(),short_course_name_tf.getText());
				//System.out.print(current_course.toString());//for checking, will delete later
//				view_course_cb.addItem(course_name_tf.getText());
				current_course = new Course(course_name_tf.getText(), short_course_name_tf.getText());
				DefaultComboBoxModel view_course_cb1 = (DefaultComboBoxModel) view_course_cb.getModel();
				DefaultComboBoxModel view_intake_cb1 = (DefaultComboBoxModel) view_intake_cb.getModel();
				view_intake_cb1.removeAllElements();
				view_course_cb1.addElement(course_name_tf.getText());
				add_course_btn.setEnabled(false);
				course_name_tf.setEditable(false);
				short_course_name_tf.setEditable(false);
				JOptionPane.showMessageDialog(AdminMenu_Course, "New Course added!, press ok to continue with intake");
				delele_course_btn.setEnabled(false);
				add_intake_btn.setEnabled(true);
				comfirm_intake.setEnabled(false);
				level_cb.setEnabled(true);
				month_cb.setEnabled(true);
				year_tf.setEditable(true);
			}
		}
  }//GEN-LAST:event_add_course_btnActionPerformed

  private void delele_course_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delele_course_btnActionPerformed
		// TODO add your handling code here:


  }//GEN-LAST:event_delele_course_btnActionPerformed

  private void add_intake_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_intake_btnActionPerformed
		// TODO add your handling code here:student_intake_cb
		DefaultComboBoxModel view_intake_cb1 = (DefaultComboBoxModel) view_intake_cb.getModel();
    DefaultComboBoxModel student_intake_cb1 = (DefaultComboBoxModel) student_intake_cb.getModel();
		if (evt.getSource() == add_intake_btn) {

			String intake_code;
			String intake_level = String.valueOf(level_cb.getSelectedItem());
			String intake_year = year_tf.getText();
			String intake_month = String.valueOf(month_cb.getSelectedItem());
			boolean found = false;

			if (intake_year.equals("")) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Empty year!", "Manage Course", JOptionPane.WARNING_MESSAGE);

			} else if (!intake_year.matches("[0-9]+") || Integer.parseInt(intake_year) < 2000 || Integer.parseInt(intake_year) > 2100) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Invalid year!", "Manage Course", JOptionPane.WARNING_MESSAGE);

			} else {

				intake_code = "UC" + intake_level + "L" + intake_year + intake_month + short_course_name_tf.getText();
				/*for(int x=0 ; x<current_course.getIntake().size() ; x++){
					if(intake_code.equals(current_course.getIntake().get(x).getIntake_code_general())){
						
						found = true;
						break;
						
					}
				}*/
				for (Course c : Grading_System.course_list) {
					if (c.getIntake().get(0).getIntake_code_general().equals(intake_code)) {
						found = true;
						break;
					}
				}
				if (!found) {
					//view_intake_cb1.insertElementAt(intake_code, 0);
					//view_intake_cb1.setSelectedItem(intake_code);
					view_intake_cb1.addElement(intake_code);
          student_intake_cb1.addElement(intake_code);
					view_intake_cb1.setSelectedItem(intake_code);
					Intakes current_intakes = new Intakes(intake_code);
					current_course.getIntake().add(current_intakes);
					JOptionPane.showMessageDialog(AdminMenu_Course, "New intake added!");
					System.out.println(current_course.toString());//for checking, will delete later
					add_intake_btn.setEnabled(false);
					year_tf.setEditable(false);
					level_cb.setEnabled(false);
					month_cb.setEnabled(false);
					add_module_btn.setEnabled(true);
					delete_intake_btn.setEnabled(true);
					delete_module_btn.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(AdminMenu_Course, "Intake exist!", "Manage Course", JOptionPane.WARNING_MESSAGE);
					originPage();
				}

			}

		}

  }//GEN-LAST:event_add_intake_btnActionPerformed

  private void delete_intake_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_intake_btnActionPerformed
		// TODO add your handling code here:
		DefaultComboBoxModel view_intake_cb2 = (DefaultComboBoxModel) view_intake_cb.getModel();
    DefaultComboBoxModel student_intake_cb1 = (DefaultComboBoxModel) student_intake_cb.getModel();
		int isDelete = JOptionPane.showConfirmDialog(null, "Delete " + view_intake_cb2.getSelectedItem(), "Warning", JOptionPane.YES_NO_CANCEL_OPTION);
		if (view_intake_cb2.getSelectedItem() == null) {

			JOptionPane.showMessageDialog(AdminMenu_Course, "Intake cannot be empty! Please add intake!", "Manage Course", JOptionPane.WARNING_MESSAGE);

		} else if (isDelete == JOptionPane.YES_OPTION) {
			if (evt.getSource() == delete_intake_btn) {
				String intake_delete = (String) view_intake_cb2.getSelectedItem();
				view_intake_cb2.removeElement(intake_delete);
        student_intake_cb1.removeElement(intake_delete);
				view_module_cb.removeAllItems();
				add_intake_btn.setEnabled(true);
				year_tf.setEditable(true);
				level_cb.setEnabled(true);
				month_cb.setEnabled(true);
				/*for (int i=0 ; i<current_course.getIntake().size();i++){

            if(intake_delete.equals(current_course.getIntake().get(i).getIntake_code_general())){

              current_course.getIntake().remove(i);
              System.out.println(current_course.toString());//for checking, will delete later
              break;

            }
			}*/
				for (Course c : Grading_System.course_list) {
					if (c.getIntake().get(0).getIntake_code_general().equals(intake_delete)) {
						Grading_System.course_list.remove(c);
						break;
					}
				}
				originPage();
				saveFile();
				JOptionPane.showMessageDialog(null, "Delete Success");
			}

		}

  }//GEN-LAST:event_delete_intake_btnActionPerformed

  private void add_module_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_module_btnActionPerformed
		// TODO add your handling code here:
		DefaultComboBoxModel view_intake_cb3 = (DefaultComboBoxModel) view_intake_cb.getModel();
		DefaultComboBoxModel view_module_cb1 = (DefaultComboBoxModel) view_module_cb.getModel();
		boolean found1 = false;
		boolean found2 = false;
		String intake_select = (String) view_intake_cb3.getSelectedItem();
		if (evt.getSource() == add_module_btn) {
			//check invalid empty input.
			if (module_name_tf.getText().equals("")) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Empty module name!", "Manage Course", JOptionPane.WARNING_MESSAGE);
			} else if (module_short_name_tf.getText().equals("")) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Empty short module name!", "Manage Course", JOptionPane.WARNING_MESSAGE);
				//Check occurence of digit in module name.
			} else if (module_name_tf.getText().matches(".*\\d.*") || module_short_name_tf.getText().matches(".*\\d.*")) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Invalid module name!", "Manage Course", JOptionPane.WARNING_MESSAGE);

			} else if (module_name_tf.getText().equals(module_short_name_tf.getText())) {

				JOptionPane.showMessageDialog(AdminMenu_Course, "Full module name and short module name cannot be same!", "Manage Course", JOptionPane.WARNING_MESSAGE);

			} else {

				for (int q = 0; q < current_course.getIntake().size(); q++) {

					if (current_course.getIntake().get(q).getIntake_code_general().equals(intake_select)) {

						for (int e = 0; e < current_course.getIntake().get(q).getModule_in_intake().size(); e++) {

							if (module_name_tf.getText().equals(current_course.getIntake().get(q).getModule_in_intake().get(e).getModuleName()) && module_short_name_tf.getText().equals(current_course.getIntake().get(q).getModule_in_intake().get(e).getShortModuleName())) {

								found1 = true;
								break;

							}
							//if(module_short_name_tf.getText().equals(current_course.getIntake().get(q).getModule_in_intake().get(e).getShortModuleName())){

							//	found2= true;
							//	break;
							//}
						}
						if (!found1) {
							confirm_all_btn.setEnabled(true);
							view_module_cb1.addElement(module_name_tf.getText());
							//Intakes current_intakes = new Intakes(intake_code);
							Module current_modules = new Module(module_name_tf.getText(), module_short_name_tf.getText());
							current_course.getIntake().get(q).getModule_in_intake().add(current_modules);
							JOptionPane.showMessageDialog(AdminMenu_Course, "New module added!");
							module_name_tf.setText("");
							module_short_name_tf.setText("");
							System.out.println(current_course.toString());//for checking, will delete later
							break;

							//						JOptionPane.showMessageDialog(AdminMenu_Course, "New Course added!");
							//						System.out.println(current_course.toString());//for checking, will delete later
							//						break;
						} else {

							JOptionPane.showMessageDialog(AdminMenu_Course, "Course exist!", "Manage Course", JOptionPane.WARNING_MESSAGE);
							break;

						}
//					

					}
//					for(int e =0 ; e<current_course.getIntake().get(q).getModule_in_intake() .size(); e++){
//						
//						if(module_name_tf.getText().equals(current_course.getIntake().get(q).getModule_in_intake().get(e).getModuleName())&&module_short_name_tf.getText().equals(current_course.getIntake().get(q).getModule_in_intake().get(e).getShortModuleName())){
//							
//							found1= true;
//							break;
//						
//						}
//						//if(module_short_name_tf.getText().equals(current_course.getIntake().get(q).getModule_in_intake().get(e).getShortModuleName())){
//						
//						//	found2= true;
//						//	break;
//						
//						//}
//						
//					}
//					if(!found1==false){
//					
//						//view_module_cb1.addElement(module_name_tf.getText());
//						//Intakes current_intakes = new Intakes(intake_code);
//						Module current_modules = new Module(module_name_tf.getText(),module_short_name_tf.getText());
//						
//						
//						
//				 	     current_course.getIntake().get(q).getModule_in_intake().add(current_modules);
//						JOptionPane.showMessageDialog(AdminMenu_Course, "New module added!");
//						System.out.println(current_course.toString());//for checking, will delete later
//						break;
//						
//						
////						JOptionPane.showMessageDialog(AdminMenu_Course, "New Course added!");
////						System.out.println(current_course.toString());//for checking, will delete later
////						break;
//			
//					}else{
//						
//						JOptionPane.showMessageDialog(AdminMenu_Course, "Course exist!","Manage Course",JOptionPane.WARNING_MESSAGE);
//						break;
//						
//					}
//					

				}

			}
		}
//		intake_code = "UC"+"L"+intake_level+intake_year+intake_month+short_course_name_tf.getText();
//				for(int x=0 ; x<current_course.getIntake().size() ; x++){
//					
//					
//					
//					if(intake_code.equals(current_course.getIntake().get(x).getIntake_code_general())){
//						
//						found = true;
//						break;
//						
//					}
//				}
//				if(!found){
//					
//					view_intake_cb1.addElement(intake_code);
//					Intakes current_intakes = new Intakes(intake_code);
//					current_course.getIntake().add(current_intakes);
//					JOptionPane.showMessageDialog(AdminMenu_Course, "New intake added!");
//					System.out.println(current_course.toString());//for checking, will delete later
//			
//				}else{
//					JOptionPane.showMessageDialog(AdminMenu_Course, "Intake exist!","Manage Course",JOptionPane.WARNING_MESSAGE);
//				}


  }//GEN-LAST:event_add_module_btnActionPerformed

  private void edit_module_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_module_btnActionPerformed
		// TODO add your handling code here:


  }//GEN-LAST:event_edit_module_btnActionPerformed

  private void delete_module_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_module_btnActionPerformed
		// TODO add your handling code here:
		DefaultComboBoxModel view_module_cb1 = (DefaultComboBoxModel) view_module_cb.getModel();
		System.out.println("sd");
		if (view_module_cb1.getSelectedItem() == null) {

			JOptionPane.showMessageDialog(AdminMenu_Course, "Module already empty!", "Manage Course", JOptionPane.WARNING_MESSAGE);

		} else {
			for (Module item : current_course.getIntake().get(0).getModule_in_intake()) {
				if (view_module_cb1.getSelectedItem().equals(item.getModuleName())) {
					current_course.getIntake().get(0).getModule_in_intake().remove(item);
					view_module_cb1.removeElement(view_module_cb.getSelectedItem());
					System.out.println(current_course.toString());//for checking, will delete later        
					break;
				}
			}
		}
  }//GEN-LAST:event_delete_module_btnActionPerformed

  private void view_student_list_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_student_list_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_view_student_list_btnActionPerformed

  private void confirm_all_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirm_all_btnActionPerformed
		// TODO add your handling code here:
		DefaultComboBoxModel view_module_cb1 = (DefaultComboBoxModel) view_module_cb.getModel();
		DefaultComboBoxModel view_intake_cb1 = (DefaultComboBoxModel) view_intake_cb.getModel();
		

		if (view_intake_cb1.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Must has intake or delete existing module", "Manage module", JOptionPane.WARNING_MESSAGE);
		} else {
			current_course.setCourse_name(course_name_tf.getText());
			current_course.setShort_course_name(short_course_name_tf.getText());
			current_course.getIntake().get(0).setIntake_code_general("UC" + String.valueOf(level_cb.getSelectedItem()) + "L" + year_tf.getText() + String.valueOf(month_cb.getSelectedItem()) + short_course_name_tf.getText());
			System.out.println(Grading_System.course_list);
			for (Course co : Grading_System.course_list) {
				if (co.getIntake().get(0).getIntake_code_general().equals(current_course.getIntake().get(0).getIntake_code_general())) {
					Grading_System.course_list.remove(co);
					break;
				}
			}
			Grading_System.course_list.add(current_course);
			Grading_System.course_available_lecturer.add(current_course);
			System.out.println(Grading_System.course_list);
			originPage();
			saveFile();
			System.out.println("1");
//			for(int i=0 ; i<current_course.getIntake().size() ; i++){
//			
//				lecturer_intake1_cb1.addElement(current_course.getIntake().get(i).getIntake_code_general());
//				lecturer_intake2_cb1.addElement(current_course.getIntake().get(i).getIntake_code_general()) ;
//				lecturer_intake3_cb1.addElement(current_course.getIntake().get(i).getIntake_code_general()) ;
//			
//			}
		}
		

  }//GEN-LAST:event_confirm_all_btnActionPerformed

  private void course_list_report_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_course_list_report_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_course_list_report_btnActionPerformed

  private void add_student_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_student_btnActionPerformed
		// TODO add your handling code here:
		DefaultComboBoxModel student_intake_cb1 = (DefaultComboBoxModel) student_intake_cb.getModel();
		if (student_intake_cb1.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Intake not Exist", "Manage Student", JOptionPane.WARNING_MESSAGE);
		} else if (student_id_tf.getText().equals("") || student_name_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Empty Id or name!", "Manage Student", JOptionPane.WARNING_MESSAGE);
		} else if (student_password_tf.getText().equals("") ||student_email_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Empty password or email!", "Manage Student", JOptionPane.WARNING_MESSAGE);
		} else if (student_gender_tf.getText().equals("") || student_nationality_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Empty gender or nationality!", "Manage Student", JOptionPane.WARNING_MESSAGE);
		} else if (student_gender_tf.getText().matches(".*\\d.*") || student_nationality_tf.getText().matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Invalid  gender or nationality!", "Manage Student", JOptionPane.WARNING_MESSAGE);
		} else if (student_name_tf.getText().matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Empty Id or name!", "Manage Student", JOptionPane.WARNING_MESSAGE);
		} 
		else {
			Boolean flag = false;
			Student stu = new Student();
			stu.setIntake_code(String.valueOf(student_intake_cb1.getSelectedItem()));
			stu.setID(student_id_tf.getText());
			stu.setName(student_name_tf.getText());
			stu.setPassword(student_password_tf.getText());
			stu.setEmail(student_email_tf.getText());
			stu.setGender(student_gender_tf.getText());
			stu.setNationality(student_nationality_tf.getText());
			for (Student s : intake_student) {
				if (s.getID().toUpperCase().equals(stu.getID().toUpperCase())) {
					flag = true;
				}
			}
			if (flag == false) {
				DefaultTableModel table = (DefaultTableModel) student_table.getModel();
				table.addRow(new Object[]{student_id_tf.getText(), student_name_tf.getText(), student_intake_cb1.getSelectedItem(), student_email_tf.getText(), student_gender_tf.getText()});
				intake_student.add(stu);
				JOptionPane.showMessageDialog(AdminMenu_Course, "New student Added!", "Manage Student", JOptionPane.INFORMATION_MESSAGE);
				saveStudent(intake_student, new File("AllStudentInformation.txt"));
				try {
					saveStudent(stu.getIntake_code().toUpperCase(), stu);
				} catch (Exception ex) {
					System.out.println("File error");
				}
			} else {
				JOptionPane.showMessageDialog(AdminMenu_Course, "Student already exist!", "Manage Student", JOptionPane.WARNING_MESSAGE);
			}
			student_id_tf.setText("");
			student_name_tf.setText("");
			student_password_tf.setText("");
			student_email_tf.setText("");
			student_gender_tf.setText("");
			student_nationality_tf.setText("");

		}


  }//GEN-LAST:event_add_student_btnActionPerformed

  private void edit_student_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_student_btnActionPerformed
		// TODO add your handling code here:
    String intake1 = JOptionPane.showInputDialog(null, "Delete Student's intake_code");
		String studentID = JOptionPane.showInputDialog(null, "Delete Student's ID ");
		boolean flag = false;
		boolean flag1 = false;
    if (studentID != null && (studentID.length() > 0) && intake1 != null && (intake1.length() > 0)) {
      
      
      
      
    } else {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Intake or ID error!!", "Manage Student", JOptionPane.WARNING_MESSAGE);
		}
  }//GEN-LAST:event_edit_student_btnActionPerformed

  private void delete_student_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_student_btnActionPerformed
		// TODO add your handling code here:
		String intake1 = JOptionPane.showInputDialog(null, "Delete Student's intake_code");
		String studentID = JOptionPane.showInputDialog(null, "Delete Student's ID ");
		boolean flag = false;
		boolean flag1 = false;
		if (studentID != null && (studentID.length() > 0) && intake1 != null && (intake1.length() > 0)) {
      deleteStudent(intake1,studentID);
			/*File file = new File(intake1.toUpperCase() + "StudentList.txt");
			if (file.exists() && file != null) {
				ArrayList<Student> studentIntake = new ArrayList<>();
				try {
					Scanner sc = new Scanner(file);
					while (sc.hasNext()) {
						Student stud = new Student();
						stud.setID(sc.nextLine());
						stud.setPassword(sc.nextLine());
						stud.setName(sc.nextLine());
						stud.setEmail(sc.nextLine());
						stud.setIntake_code(sc.nextLine());
						stud.setGender(sc.nextLine());
						stud.setNationality(sc.nextLine());
						sc.nextLine();
						studentIntake.add(stud);
					}
					sc.close();
					for (Student s : studentIntake) {
						if (s.getID().toUpperCase().equals(studentID.toUpperCase())) {
							studentIntake.remove(s);
							flag1 = true;
							break;
						}
					}
					saveStudent(studentIntake, file);
				} catch (FileNotFoundException ex) {
					System.out.println("File not found");
				}
				if (flag1 == true) {
					for (Student s : intake_student) {
						if (s.getID().toUpperCase().equals(studentID.toUpperCase())) {
							intake_student.remove(s);
							saveStudent(intake_student, new File("AllStudentInformation.txt"));
							flag = true;
							break;
						}
					}
				}
				if (flag == true) {
					DefaultTableModel table = (DefaultTableModel) student_table.getModel();
					for (int i = 0; i < table.getRowCount(); i++) {
						if (String.valueOf(table.getValueAt(i, 0)).toUpperCase().equals(studentID.toUpperCase())) {
							table.removeRow(i);
							break;
						}
					}
				} else {
					JOptionPane.showMessageDialog(AdminMenu_Course, "Student not exist!", "Manage Student", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(AdminMenu_Course, "Intake not exist or don't have record!!", "Manage Student", JOptionPane.WARNING_MESSAGE);
			}*/
		} else {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Intake or ID error!!", "Manage Student", JOptionPane.WARNING_MESSAGE);
		}
  }//GEN-LAST:event_delete_student_btnActionPerformed

  private void generate_student_list_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_student_list_btnActionPerformed
		// TODO add your handling code here:
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AllStudentDetail.pdf"));
			document.open();

			PdfPTable table = new PdfPTable(6); // 3 columns.
			table.setWidthPercentage(100); //Width 100%
			table.setSpacingBefore(10f); //Space before table
			table.setSpacingAfter(10f); //Space after table

			//Set Column widths
			float[] columnWidths = {0.5f, 1f, 1.5f, 0.7f, 0.4f, 0.6f};
			table.setWidths(columnWidths);

			PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
			//cell1.setBorderColor(BaseColor.BLACK);
			cell1.setPaddingLeft(10);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell2 = new PdfPCell(new Paragraph("Name"));
			//cell2.setBorderColor(BaseColor.BLACK);
			cell2.setPaddingLeft(10);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell3 = new PdfPCell(new Paragraph("Email"));
			//cell3.setBorderColor(BaseColor.RED);
			cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell4 = new PdfPCell(new Paragraph("Intake-Code"));
			//cell3.setBorderColor(BaseColor.RED);
			cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell5 = new PdfPCell(new Paragraph("Gender"));
			//cell3.setBorderColor(BaseColor.RED);
			cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell6 = new PdfPCell(new Paragraph("Nationality"));
			//cell3.setBorderColor(BaseColor.RED);
			cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			//To avoid having the cell border and the content overlap, if you are having thick cell borders
			//cell1.setUserBorderPadding(true);
			//cell2.setUserBorderPadding(true);
			//cell3.setUserBorderPadding(true);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			for (Student s : intake_student) {
				table.addCell(s.getID());
				table.addCell(s.getName());
				table.addCell(s.getEmail());
				table.addCell(s.getIntake_code());
				table.addCell(s.getGender());
				table.addCell(s.getNationality());
			}
			document.add(new Paragraph("All student Information"));
			document.add(new Paragraph(" "));
			document.add(table);

			JOptionPane.showMessageDialog(AdminMenu_Course, "All Student Information Report Generated", "Manage Student", JOptionPane.WARNING_MESSAGE);
			document.close();
			writer.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Close your Report then can generate new One", "Manage Student", JOptionPane.WARNING_MESSAGE);
		}
  }//GEN-LAST:event_generate_student_list_btnActionPerformed

  private void add_lecturer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_lecturer_btnActionPerformed
		// TODO add your handling code here:
		DefaultComboBoxModel _intake_cb1 = (DefaultComboBoxModel) student_intake_cb.getModel();
		
		
		
		if(evt.getSource()==add_lecturer_btn){
				
				 if (lecturer_id_tf.getText().equals("") || lecturer_name_tf.getText().equals("")) {
					JOptionPane.showMessageDialog(AdminMenu_Course, "Empty Id or name!", "Manage Lecturer", JOptionPane.WARNING_MESSAGE);
				} else if (lecturer_password_tf.getText().equals("") ||lecturer_email_tf.getText().equals("")) {
					JOptionPane.showMessageDialog(AdminMenu_Course, "Empty password or email!", "Manage Student", JOptionPane.WARNING_MESSAGE);
				} else if (lecturer_password_tf.getText().equals("") ||lecturer_email_tf.getText().equals("")) {
					JOptionPane.showMessageDialog(AdminMenu_Course, "Empty password or email!", "Manage Student", JOptionPane.WARNING_MESSAGE);
				} else if(lecturer_name_tf.getText().matches(".*\\d.*")){
			
					JOptionPane.showMessageDialog(AdminMenu_Course, "Invalid name!", "Manage Lecturer", JOptionPane.WARNING_MESSAGE);
			
				} else {
					
					Boolean flag = false;
					Educator current_lecturer = new Educator();
					current_lecturer.setID(lecturer_id_tf.getText());
					current_lecturer.setPassword(lecturer_password_tf.getText());
					current_lecturer.setEmail(lecturer_email_tf.getText());
					//current_lecturer.getIntake_module().add(selected_intake1_tf.getText());
					//current_lecturer.getIntake_module().add(selected_intake2_tf.getText());
					//current_lecturer.getIntake_module().add(selected_intake3_tf.getText());
					for (Educator e : Grading_System.edu) {
						if (e.getID().toUpperCase().equals(e.getID().toUpperCase())) {
							flag = true;
						}
					}
					if(!flag){
						DefaultTableModel table = (DefaultTableModel) lecturer_table.getModel();
						table.addRow(new Object[]{lecturer_id_tf.getText(), lecturer_name_tf.getText(), lecturer_email_tf.getText(), selected_intake1_tf.getText(),selected_intake2_tf.getText(), selected_intake3_tf.getText()});
						Grading_System.edu.add(current_lecturer);
						JOptionPane.showMessageDialog(AdminMenu_Lecturer, "New Lecturer Added!", "Manage Lecturer", JOptionPane.INFORMATION_MESSAGE);
						saveLecturer(Grading_System.edu, new File("AllLecturerInformation.txt"));
						
						
					} else{
						
						JOptionPane.showMessageDialog(AdminMenu_Course, "Lecturer exists!", "Manage Lecturer", JOptionPane.INFORMATION_MESSAGE);
						
					}
					lecturer_id_tf.setText("");
					lecturer_name_tf.setText("");
					lecturer_password_tf.setText("");
					lecturer_email_tf.setText("");	
					selected_intake1_tf.setText("");
					selected_intake2_tf.setText("");
					selected_intake3_tf.setText("");
						
						
					


				
			} 
		}
  }//GEN-LAST:event_add_lecturer_btnActionPerformed

  private void edit_lecturer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_lecturer_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_edit_lecturer_btnActionPerformed

  private void delete_lecturer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_lecturer_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_delete_lecturer_btnActionPerformed

  private void generate_lecturer_list_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_lecturer_list_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_generate_lecturer_list_btnActionPerformed

  private void generate_log_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_log_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_generate_log_btnActionPerformed

  private void add_admin_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_admin_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_add_admin_btnActionPerformed

  private void edtt_admin_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtt_admin_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_edtt_admin_btnActionPerformed

  private void delete_admin_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_admin_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_delete_admin_btnActionPerformed

  private void generate_admin_list_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_admin_list_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_generate_admin_list_btnActionPerformed

  private void AdminButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminButton5ActionPerformed
		// TODO add your handling code here:
		ResetColor(AdminButton1);
		ResetColor(AdminButton2);
		ResetColor(AdminButton3);
		ResetColor(AdminButton4);
		SetColor(AdminButton5);
		ResetColor(AdminButton6);

		cardlayout.show(CardLayoutPanel_admin, "5");
  }//GEN-LAST:event_AdminButton5ActionPerformed

  private void comfirm_course_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comfirm_course_btnActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_comfirm_course_btnActionPerformed

  private void comfirm_intakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comfirm_intakeActionPerformed
		// TODO add your handling code here:
		DefaultComboBoxModel view_intake_cb3 = (DefaultComboBoxModel) view_intake_cb.getModel();
		DefaultComboBoxModel view_module_cb1 = (DefaultComboBoxModel) view_module_cb.getModel();
		DefaultComboBoxModel view_level_cb1 = (DefaultComboBoxModel) level_cb.getModel();
		DefaultComboBoxModel view_month_cb1 = (DefaultComboBoxModel) month_cb.getModel();
		String intake_text = (String) view_intake_cb3.getSelectedItem();
		if (evt.getSource() == comfirm_intake) {
			comfirm_intake.setEnabled(false);
			add_module_btn.setEnabled(true);
			add_course_btn.setEnabled(false);
			view_intake_cb3.removeAllElements();
			view_intake_cb3.setSelectedItem(intake_text);
			//add_intake_btn.setEnabled(false);
			intake_selected_lb.setText(intake_text);
			//view_module_cb1.removeAllElements();
			for (Course c : Grading_System.course_list) {
				if (c.getIntake().get(0).getIntake_code_general().equals(intake_text)) {
					delete_intake_btn.setEnabled(true);
					delete_module_btn.setEnabled(true);
					confirm_all_btn.setEnabled(true);
					/*current_course.setCourse_name(c.getCourse_name());
          current_course.setShort_course_name(c.getShort_course_name());*/
					current_course = new Course();
					System.out.println(current_course);
					current_course.setIntake(c.getIntake());
					System.out.println(current_course);
					course_name_tf.setText(c.getCourse_name());
					short_course_name_tf.setText(c.getShort_course_name());
					String intake = c.getIntake().get(0).getIntake_code_general();
					view_level_cb1.setSelectedItem(intake.substring(2, 3));
					view_month_cb1.setSelectedItem(intake.substring(8, 10));
					year_tf.setText(intake.substring(4, 8));
					for (Module m : c.getIntake().get(0).getModule_in_intake()) {
						view_module_cb1.addElement(m.getModuleName());
					}
					break;
				}
			}

		}


  }//GEN-LAST:event_comfirm_intakeActionPerformed

  private void comfirm_moduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comfirm_moduleActionPerformed
		// TODO add your handling code here:
  }//GEN-LAST:event_comfirm_moduleActionPerformed

	int xx, xy;
  private void MenuPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuPanelMouseDragged
		// TODO add your handling code here:
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xx, y - xy);

  }//GEN-LAST:event_MenuPanelMouseDragged

  private void MenuPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuPanelMousePressed
		// TODO add your handling code here:
		xx = evt.getX();
		xy = evt.getY();


  }//GEN-LAST:event_MenuPanelMousePressed

  private void SidePanel_adminMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SidePanel_adminMousePressed
		// TODO add your handling code here:
		xx = evt.getX();
		xy = evt.getY();


  }//GEN-LAST:event_SidePanel_adminMousePressed

  private void SidePanel_adminMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SidePanel_adminMouseDragged
		// TODO add your handling code here:
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xx, y - xy);


  }//GEN-LAST:event_SidePanel_adminMouseDragged

  private void CardLayoutPanel_adminMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CardLayoutPanel_adminMousePressed
		// TODO add your handling code here:
		xx = evt.getX();
		xy = evt.getY();


  }//GEN-LAST:event_CardLayoutPanel_adminMousePressed

  private void CardLayoutPanel_adminMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CardLayoutPanel_adminMouseDragged
		// TODO add your handling code here:
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xx, y - xy);


  }//GEN-LAST:event_CardLayoutPanel_adminMouseDragged

  private void Comfirm_student_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comfirm_student_btnActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_Comfirm_student_btnActionPerformed

  private void comfirm_intake_module1_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comfirm_intake_module1_btnActionPerformed

		DefaultComboBoxModel comfirm_intake1 =(DefaultComboBoxModel) lecturer_intake1_cb.getModel();
		DefaultComboBoxModel comfirm_intake2 =(DefaultComboBoxModel) lecturer_intake2_cb.getModel();
		DefaultComboBoxModel comfirm_intake3 =(DefaultComboBoxModel) lecturer_intake3_cb.getModel();
		DefaultComboBoxModel comfirm_module1 =(DefaultComboBoxModel) lecturer_module1_cb.getModel();
		String intake1=(String)comfirm_intake1.getSelectedItem();
		
		if(evt.getSource()==comfirm_intake_module1_btn){
			
			comfirm_module1.removeAllElements();
			
			for (Course c : Grading_System.course_list) {
				if (c.getIntake().get(0).getIntake_code_general().equals(intake1)) {
					
					for (Module m : c.getIntake().get(0).getModule_in_intake()) {
						comfirm_module1.addElement(m.getModuleName());
					}
					break;
				}
			}
			comfirm_intake_module1_btn.setEnabled(false);
			comfirm_intake2 .removeElement(intake1);
			comfirm_intake3 .removeElement(intake1);
			
			
		}
				
  }//GEN-LAST:event_comfirm_intake_module1_btnActionPerformed

  private void comfirm_intake_module2_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comfirm_intake_module2_btnActionPerformed

		DefaultComboBoxModel comfirm_intake2 =(DefaultComboBoxModel) lecturer_intake2_cb.getModel();
		DefaultComboBoxModel comfirm_intake1 =(DefaultComboBoxModel) lecturer_intake1_cb.getModel();
		DefaultComboBoxModel comfirm_intake3 =(DefaultComboBoxModel) lecturer_intake3_cb.getModel();
		DefaultComboBoxModel comfirm_module2 =(DefaultComboBoxModel) lecturer_module2_cb.getModel();
		String intake2=(String)comfirm_intake2.getSelectedItem();
		
		if(evt.getSource()==comfirm_intake_module2_btn){
			
			comfirm_module2.removeAllElements();
			
			for (Course c : Grading_System.course_list) {
				if (c.getIntake().get(0).getIntake_code_general().equals(intake2)) {
					
					for (Module m : c.getIntake().get(0).getModule_in_intake()) {
						comfirm_module2.addElement(m.getModuleName());
					}
					break;
				}
			}
			comfirm_intake_module2_btn.setEnabled(false);
			comfirm_intake1 .removeElement(intake2);
			comfirm_intake3 .removeElement(intake2);
		}
	
  }//GEN-LAST:event_comfirm_intake_module2_btnActionPerformed

  private void comfirm_intake_module3_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comfirm_intake_module3_btnActionPerformed
   
		DefaultComboBoxModel comfirm_intake3 =(DefaultComboBoxModel) lecturer_intake3_cb.getModel();
		DefaultComboBoxModel comfirm_intake2 =(DefaultComboBoxModel) lecturer_intake2_cb.getModel();
		DefaultComboBoxModel comfirm_intake1 =(DefaultComboBoxModel) lecturer_intake1_cb.getModel();
		DefaultComboBoxModel comfirm_module3 =(DefaultComboBoxModel) lecturer_module3_cb.getModel();
		String intake3=(String)comfirm_intake3.getSelectedItem();
		
		if(evt.getSource()==comfirm_intake_module3_btn){
			
			comfirm_module3.removeAllElements();
			
			for (Course c : Grading_System.course_list) {
				if (c.getIntake().get(0).getIntake_code_general().equals(intake3)) {
					
					for (Module m : c.getIntake().get(0).getModule_in_intake()) {
						comfirm_module3.addElement(m.getModuleName());
					}
					break;
				}
			}
			comfirm_intake_module3_btn.setEnabled(false);
			comfirm_intake1 .removeElement(intake3);
			comfirm_intake2 .removeElement(intake3);
			
		}
	
  }//GEN-LAST:event_comfirm_intake_module3_btnActionPerformed

  private void comfirm_module1_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comfirm_module1_btnActionPerformed
    // TODO add your handling code here:
		DefaultComboBoxModel comfirm_intake1 =(DefaultComboBoxModel) lecturer_intake1_cb.getModel();
		DefaultComboBoxModel comfirm_module1 =(DefaultComboBoxModel) lecturer_module1_cb.getModel();
		String module1= (String)comfirm_module1.getSelectedItem();
		String intake_module = (String)comfirm_intake1.getSelectedItem()+"_"+module1;
		
//		File file =new File("AllLecturerInformation.txt");
//		if (file.exists()) {
//			try {
//				Scanner sc = new Scanner(file);
//				while (sc.hasNext()) {
//					Student stud = new Student();
//					stud.setID(sc1.nextLine());
//					stud.setPassword(sc1.nextLine());
//					stud.setName(sc1.nextLine());
//					stud.setEmail(sc1.nextLine());
//					stud.setIntake_code(sc1.nextLine());
//					stud.setGender(sc1.nextLine());
//					stud.setNationality(sc1.nextLine());
//					sc1.nextLine();
//					intake_student.add(stud);
//				}
//				sc1.close();
//				DefaultTableModel table = (DefaultTableModel) student_table.getModel();
//				for (Student s : intake_student) {
//					table.addRow(new Object[]{s.getID(), s.getName(), s.getIntake_code(), s.getEmail(), s.getGender()});
//				}
//
//			} catch (FileNotFoundException ex) {
//				System.out.println("No student list");
//			}
//		} else {
//			System.out.println("No record!");
//		}
		
		
		selected_intake1_tf.setText(intake_module);
		
		comfirm_module1_btn.setEnabled(false);
		
		
		
		
  }//GEN-LAST:event_comfirm_module1_btnActionPerformed

  private void comfirm_module2_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comfirm_module2_btnActionPerformed
    // TODO add your handling code here:
		DefaultComboBoxModel comfirm_intake2 =(DefaultComboBoxModel) lecturer_intake2_cb.getModel();
		DefaultComboBoxModel comfirm_module2 =(DefaultComboBoxModel) lecturer_module2_cb.getModel();
		String module2= (String)comfirm_module2.getSelectedItem();
		
		
		selected_intake2_tf.setText((String)comfirm_intake2.getSelectedItem()+"_"+module2);
		
		comfirm_module2_btn.setEnabled(false);
		
		
		
		
		
		
		
		
  }//GEN-LAST:event_comfirm_module2_btnActionPerformed

  private void comfirm_module3_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comfirm_module3_btnActionPerformed
    // TODO add your handling code here:
		DefaultComboBoxModel comfirm_intake3=(DefaultComboBoxModel) lecturer_intake3_cb.getModel();
		DefaultComboBoxModel comfirm_module3 =(DefaultComboBoxModel) lecturer_module3_cb.getModel();
		String module3= (String)comfirm_module3.getSelectedItem();
		
		
		selected_intake3_tf.setText((String)comfirm_intake3.getSelectedItem()+"_"+module3);
		
		comfirm_module3_btn.setEnabled(false);
		
		
		
		
		
		
		
		
		
  }//GEN-LAST:event_comfirm_module3_btnActionPerformed

  private void student_cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_cancel_btnActionPerformed
    // TODO add your handling code here:
		student_id_tf.setText("");
		student_name_tf.setText("");
		student_email_tf.setText("");
		student_password_tf.setText("");
		student_gender_tf.setText("");
		student_nationality_tf.setText("");
		
  }//GEN-LAST:event_student_cancel_btnActionPerformed

  private void lecturer_refresh_intake_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lecturer_refresh_intake_btnActionPerformed
    // TODO add your handling code here:
		DefaultComboBoxModel lecturer_intake1= (DefaultComboBoxModel) lecturer_intake1_cb.getModel();
		DefaultComboBoxModel lecturer_intake2= (DefaultComboBoxModel) lecturer_intake2_cb.getModel();
		DefaultComboBoxModel lecturer_intake3= (DefaultComboBoxModel) lecturer_intake3_cb.getModel();
		DefaultComboBoxModel lecturer_module1= (DefaultComboBoxModel) lecturer_module1_cb.getModel();
		DefaultComboBoxModel lecturer_module2= (DefaultComboBoxModel) lecturer_module2_cb.getModel();
		DefaultComboBoxModel lecturer_module3= (DefaultComboBoxModel) lecturer_module3_cb.getModel();
		
		lecturer_intake1.removeAllElements();
		lecturer_intake2.removeAllElements();
		lecturer_intake3.removeAllElements();
		
		for (Course c : Grading_System.course_list) {
			String intake_code = c.getIntake().get(0).getIntake_code_general();
			 lecturer_intake1.addElement(intake_code);
			 lecturer_intake2.addElement(intake_code);
			 lecturer_intake3.addElement(intake_code);
		}
//		lecturer_intake1.addAll( temp_intake);
//		lecturer_intake2.addAll( temp_intake);
//		lecturer_intake3.addAll( temp_intake);
		this.lecturer_name_tf.setText("");
		this.lecturer_id_tf.setText("");
		this.lecturer_password_tf.setText("");
		this.lecturer_email_tf.setText("");
		lecturer_module1.removeAllElements();
		lecturer_module2.removeAllElements();
		lecturer_module3.removeAllElements();
		comfirm_intake_module1_btn.setEnabled(true);
		comfirm_intake_module2_btn.setEnabled(true);
		comfirm_intake_module3_btn.setEnabled(true);
		comfirm_module1_btn.setEnabled(true);
		comfirm_module2_btn.setEnabled(true);
		comfirm_module3_btn.setEnabled(true);
		selected_intake1_tf.setText("");
		selected_intake2_tf.setText("");
		selected_intake3_tf.setText("");
		
  }//GEN-LAST:event_lecturer_refresh_intake_btnActionPerformed

  private void student_intake_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_intake_cbActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_student_intake_cbActionPerformed



	private void originPage() {
		DefaultComboBoxModel level_cb1 = (DefaultComboBoxModel) level_cb.getModel();
		DefaultComboBoxModel month_cb1 = (DefaultComboBoxModel) month_cb.getModel();
		DefaultComboBoxModel view_module_cb1 = (DefaultComboBoxModel) view_module_cb.getModel();
		DefaultComboBoxModel view_intake_cb1 = (DefaultComboBoxModel) view_intake_cb.getModel();
		DefaultComboBoxModel view_course_cb1 = (DefaultComboBoxModel) view_course_cb.getModel();
		course_name_tf.setText(null);
		course_name_tf.setEditable(true);
		short_course_name_tf.setText(null);
		short_course_name_tf.setEditable(true);
		year_tf.setText(null);
		module_name_tf.setText(null);
		module_short_name_tf.setText(null);
		intake_selected_lb.setText(null);
		level_cb1.setSelectedItem(1);
		month_cb1.setSelectedItem("01");
		view_module_cb1.removeAllElements();
		view_intake_cb1.removeAllElements();
		view_course_cb1.removeAllElements();
		add_intake_btn.setEnabled(false);
		add_module_btn.setEnabled(false);
		delete_intake_btn.setEnabled(false);
		delete_module_btn.setEnabled(false);
		add_course_btn.setEnabled(true);
		comfirm_intake.setEnabled(true);
		confirm_all_btn.setEnabled(false);
		current_course = null;
		DefaultComboBoxModel view_intake_cb3 = (DefaultComboBoxModel) view_intake_cb.getModel();
		boolean flag = false;
		for (Course c : Grading_System.course_list) {
			String intake_code = c.getIntake().get(0).getIntake_code_general();
			view_intake_cb3.addElement(intake_code);
			flag = true;
		}
		if (flag == false) {
			comfirm_intake.setEnabled(false);
		}
	}

	private void saveFile() {
		try {
			if (!Grading_System.course_list.equals(null)) {
				PrintWriter pw = new PrintWriter("Intake_module.txt");
				for (int x = 0; x < Grading_System.course_list.size(); x++) {
					Course course = Grading_System.course_list.get(x);
					pw.println(course.getCourse_name());
					pw.println(course.getIntake().get(0).getIntake_code_general());
					for (Module module : course.getIntake().get(0).getModule_in_intake()) {
						pw.print(module.getShortModuleName() + ",");
					}
					pw.println();
					for (Module module : course.getIntake().get(0).getModule_in_intake()) {
						pw.print(module.getModuleName() + ",");
					}
					pw.println();
					pw.println();
				}
				pw.close();
				JOptionPane.showMessageDialog(AdminMenu_Course, "Already add to file!", "Manage Course", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(AdminMenu_Course, "Empty course!", "Manage Course", JOptionPane.WARNING_MESSAGE);
				PrintWriter pw = new PrintWriter("Intake_module.txt");
				pw.print("");
				pw.close();
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found");
		}
	}

	private void saveStudent(ArrayList<Student> intake_student, File file) {
		try {
			if (!intake_student.equals(null)) {
				PrintWriter pw = new PrintWriter(file);//AllStudentInformation
				for (Student s : intake_student) {
					pw.println(s.getID());
					pw.println(s.getPassword());
					pw.println(s.getName());
					pw.println(s.getEmail());
					pw.println(s.getIntake_code());
					pw.println(s.getGender());
					pw.println(s.getNationality());
					pw.println();
				}
				pw.close();
			} else {
				JOptionPane.showMessageDialog(AdminMenu_Course, "Empty student!", "Manage student", JOptionPane.WARNING_MESSAGE);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}
	}

	
	
	private void saveLecturer(ArrayList<Educator> edu, File file) {
		try {
			if (!edu.equals(null)) {
				PrintWriter pw = new PrintWriter(file);//AllLecturerInformation
				for (Educator e : edu) {
					pw.println(e.getID());
					pw.println(e.getPassword());
					pw.println(e.getName());
					pw.println(e.getEmail());
					pw.println(e.getIntake_module().get(0));
					pw.println(e.getIntake_module().get(1));
					pw.println(e.getIntake_module().get(2));
					pw.println();
				}
				pw.close();
			} else {
				JOptionPane.showMessageDialog(AdminMenu_Course, "Empty lecterert!", "Manage lecturer", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}
	}
	private void saveStudent(String intake, Student stu)  {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(intake + "StudentList.txt", true));
			bw.append(stu.getID() + "\r\n");
			bw.append(stu.getPassword() + "\r\n");
			bw.append(stu.getName() + "\r\n");
			bw.append(stu.getEmail() + "\r\n");
			bw.append(stu.getIntake_code() + "\r\n");
			bw.append(stu.getGender() + "\r\n");
			bw.append(stu.getNationality() + "\r\n");
			bw.append("\r\n");
			bw.close();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(AdminMenu_Course, "Empty student!", "Manage student", JOptionPane.WARNING_MESSAGE);
		} catch (IOException ex) {
      Logger.getLogger(AdminMenuGui.class.getName()).log(Level.SEVERE, null, ex);
    }
	}

  private void deleteStudent(String intake1,String studentID){
    boolean flag = false;
		boolean flag1 = false;
    File file = new File(intake1.toUpperCase() + "StudentList.txt");
			if (file.exists() && file != null) {
				ArrayList<Student> studentIntake = new ArrayList<>();
				try {
					Scanner sc = new Scanner(file);
					while (sc.hasNext()) {
						Student stud = new Student();
						stud.setID(sc.nextLine());
						stud.setPassword(sc.nextLine());
						stud.setName(sc.nextLine());
						stud.setEmail(sc.nextLine());
						stud.setIntake_code(sc.nextLine());
						stud.setGender(sc.nextLine());
						stud.setNationality(sc.nextLine());
						sc.nextLine();
						studentIntake.add(stud);
					}
					sc.close();
					for (Student s : studentIntake) {
						if (s.getID().toUpperCase().equals(studentID.toUpperCase())) {
							studentIntake.remove(s);
							flag1 = true;
							break;
						}
					}
					saveStudent(studentIntake, file);
				} catch (FileNotFoundException ex) {
					System.out.println("File not found");
				}
				if (flag1 == true) {
					for (Student s : intake_student) {
						if (s.getID().toUpperCase().equals(studentID.toUpperCase())) {
							intake_student.remove(s);
							saveStudent(intake_student, new File("AllStudentInformation.txt"));
							flag = true;
							break;
						}
					}
				}
				if (flag == true) {
					DefaultTableModel table = (DefaultTableModel) student_table.getModel();
					for (int i = 0; i < table.getRowCount(); i++) {
						if (String.valueOf(table.getValueAt(i, 0)).toUpperCase().equals(studentID.toUpperCase())) {
							table.removeRow(i);
							break;
						}
					}
				} else {
					JOptionPane.showMessageDialog(AdminMenu_Course, "Student not exist!", "Manage Student", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(AdminMenu_Course, "Intake not exist or don't have record!!", "Manage Student", JOptionPane.WARNING_MESSAGE);
			}
  }
  
  
  
  

//	public static void main(String args[]) {
//		//		/* Set the Nimbus look and feel */
//		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//		 */
//		try {
//			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//				if ("Windows".equals(info.getName())) {
//					javax.swing.UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		} catch (ClassNotFoundException ex) {
//			java.util.logging.Logger.getLogger(AdminMenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (InstantiationException ex) {
//			java.util.logging.Logger.getLogger(AdminMenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (IllegalAccessException ex) {
//			java.util.logging.Logger.getLogger(AdminMenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
//			java.util.logging.Logger.getLogger(AdminMenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		}
//		//</editor-fold>
//
//
//
//
//		/* Create and display the form */
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new AdminMenuGui().setVisible(true);
//			}
//		});
//	}
	public void SetColor(JButton button) {
		button.setBackground(new java.awt.Color(62, 128, 194));
	}

	public void ResetColor(JButton button) {
		button.setBackground(new java.awt.Color(0, 0, 51));
	}


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton AdminButton1;
  private javax.swing.JButton AdminButton2;
  private javax.swing.JButton AdminButton3;
  private javax.swing.JButton AdminButton4;
  private javax.swing.JButton AdminButton5;
  private javax.swing.JButton AdminButton6;
  private javax.swing.JButton AdminExit;
  private javax.swing.JPanel AdminMenu_Admin;
  private javax.swing.JPanel AdminMenu_Course;
  private javax.swing.JPanel AdminMenu_Lecturer;
  private javax.swing.JPanel AdminMenu_Student;
  private javax.swing.JPanel AdminMenu_log;
  private javax.swing.JPanel AdminMenu_report;
  private javax.swing.JPanel CardLayoutPanel_admin;
  private javax.swing.JButton Comfirm_student_btn;
  private javax.swing.JLabel Course_Module_lb;
  private javax.swing.JTable LogTable;
  private javax.swing.JLabel MenuLabel;
  private javax.swing.JPanel MenuPanel;
  private javax.swing.JScrollPane ScrollPane_log;
  private javax.swing.JPanel SidePanel_admin;
  private javax.swing.JButton add_admin_btn;
  private javax.swing.JButton add_course_btn;
  private javax.swing.JButton add_intake_btn;
  private javax.swing.JButton add_lecturer_btn;
  private javax.swing.JButton add_module_btn;
  private javax.swing.JLabel add_module_to_lb;
  private javax.swing.JButton add_student_btn;
  private javax.swing.JLabel admin_id_lb;
  private javax.swing.JTextField admin_id_tf;
  private javax.swing.JLabel admin_name_lb;
  private javax.swing.JTextField admin_name_tf;
  private javax.swing.JLabel admin_password_lb;
  private javax.swing.JTextField admin_password_tf;
  private javax.swing.JScrollPane admin_sp;
  private javax.swing.JTable admin_table;
  private javax.swing.JButton comfirm_course_btn;
  private javax.swing.JButton comfirm_intake;
  private javax.swing.JButton comfirm_intake_module1_btn;
  private javax.swing.JButton comfirm_intake_module2_btn;
  private javax.swing.JButton comfirm_intake_module3_btn;
  private javax.swing.JButton comfirm_module;
  private javax.swing.JButton comfirm_module1_btn;
  private javax.swing.JButton comfirm_module2_btn;
  private javax.swing.JButton comfirm_module3_btn;
  private javax.swing.JButton confirm_all_btn;
  private javax.swing.JButton course_list_report_btn;
  private javax.swing.JLabel course_name_lb;
  private javax.swing.JTextField course_name_tf;
  private javax.swing.JButton delele_course_btn;
  private javax.swing.JButton delete_admin_btn;
  private javax.swing.JButton delete_intake_btn;
  private javax.swing.JButton delete_lecturer_btn;
  private javax.swing.JButton delete_module_btn;
  private javax.swing.JButton delete_student_btn;
  private javax.swing.JButton edit_lecturer_btn;
  private javax.swing.JButton edit_module_btn;
  private javax.swing.JButton edit_student_btn;
  private javax.swing.JButton edtt_admin_btn;
  private javax.swing.JButton generate_admin_list_btn;
  private javax.swing.JButton generate_lecturer_list_btn;
  private javax.swing.JButton generate_log_btn;
  private javax.swing.JButton generate_student_list_btn;
  private javax.swing.JLabel intake_selected_lb;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel lecturer_email_lb;
  private javax.swing.JTextField lecturer_email_tf;
  private javax.swing.JLabel lecturer_id_lb;
  private javax.swing.JTextField lecturer_id_tf;
  private javax.swing.JComboBox<String> lecturer_intake1_cb;
  private javax.swing.JLabel lecturer_intake1_lb;
  private javax.swing.JComboBox<String> lecturer_intake2_cb;
  private javax.swing.JLabel lecturer_intake2_lb;
  private javax.swing.JComboBox<String> lecturer_intake3_cb;
  private javax.swing.JLabel lecturer_intake3_lb;
  private javax.swing.JComboBox<String> lecturer_module1_cb;
  private javax.swing.JComboBox<String> lecturer_module2_cb;
  private javax.swing.JComboBox<String> lecturer_module3_cb;
  private javax.swing.JLabel lecturer_name_lb;
  private javax.swing.JTextField lecturer_name_tf;
  private javax.swing.JLabel lecturer_password_lb;
  private javax.swing.JTextField lecturer_password_tf;
  private javax.swing.JButton lecturer_refresh_intake_btn;
  private javax.swing.JScrollPane lecturer_sp;
  private javax.swing.JTable lecturer_table;
  private javax.swing.JComboBox<String> level_cb;
  private javax.swing.JLabel level_lb;
  private javax.swing.JLabel log;
  private javax.swing.JLabel manage_admin_lb;
  private javax.swing.JLabel manage_lecturer_lb;
  private javax.swing.JLabel manage_student_lb;
  private javax.swing.JLabel module_name_lb;
  private javax.swing.JTextField module_name_tf;
  private javax.swing.JLabel module_short_name_lb;
  private javax.swing.JTextField module_short_name_tf;
  private javax.swing.JComboBox<String> month_cb;
  private javax.swing.JLabel month_lb;
  private javax.swing.JTextField selected_intake1_tf;
  private javax.swing.JTextField selected_intake2_tf;
  private javax.swing.JTextField selected_intake3_tf;
  private javax.swing.JLabel selected_intake_lb;
  private javax.swing.JLabel short_course_name_lb;
  private javax.swing.JTextField short_course_name_tf;
  private javax.swing.JButton student_cancel_btn;
  private javax.swing.JLabel student_email_lb;
  private javax.swing.JTextField student_email_tf;
  private javax.swing.JLabel student_gender_lb;
  private javax.swing.JTextField student_gender_tf;
  private javax.swing.JLabel student_id_lb;
  private javax.swing.JTextField student_id_tf;
  private javax.swing.JComboBox<String> student_intake_cb;
  private javax.swing.JLabel student_intake_lb;
  private javax.swing.JLabel student_name_lb;
  private javax.swing.JTextField student_name_tf;
  private javax.swing.JLabel student_nationality_lb;
  private javax.swing.JTextField student_nationality_tf;
  private javax.swing.JLabel student_password_lb;
  private javax.swing.JTextField student_password_tf;
  private javax.swing.JScrollPane student_sp;
  private javax.swing.JTable student_table;
  private javax.swing.JLabel students_in_intake_lb;
  private javax.swing.JList<String> students_in_intake_ls;
  private javax.swing.JScrollPane students_in_intake_sp;
  private javax.swing.JLabel students_in_module_lb;
  private javax.swing.JList<String> students_in_module_ls;
  private javax.swing.JScrollPane studetns_in_module_sp;
  private javax.swing.JComboBox<String> view_course_cb;
  private javax.swing.JLabel view_course_lb;
  private javax.swing.JComboBox<String> view_intake_cb;
  private javax.swing.JLabel view_intake_lb;
  private javax.swing.JComboBox<String> view_module_cb;
  private javax.swing.JLabel view_module_lb;
  private javax.swing.JButton view_student_list_btn;
  private javax.swing.JLabel year_lb;
  private javax.swing.JTextField year_tf;
  // End of variables declaration//GEN-END:variables
}
