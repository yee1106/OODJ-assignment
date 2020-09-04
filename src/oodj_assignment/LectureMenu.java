package oodj_assignment;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LectureMenu extends JFrame implements ActionListener {
	//private JTextField jf1,jf2;

	private JLabel menu;
	private JPanel jp, jp1;//, jp2, jp3, jp4, jp5;
	private JButton first, second,/* third, fourth, fifth,*/ exit;

	public LectureMenu() {
		setTitle("Lecture Menu");
		setBounds(800, 400, 250, 190);//position, lenght, breadth
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(new GridLayout(5,1,5)); //row and column
		setLayout(new FlowLayout(FlowLayout.CENTER));

		menu = new JLabel("  ------- MENU --------  ");
		menu.setForeground(Color.blue);//set font's color
		jp = new JPanel();
		jp.add(menu);
		add(jp);

		first = new JButton("*** 1)Key in result ***");
		first.addActionListener(this);
		jp1 = new JPanel();
		jp1.setLayout(new GridLayout(6, 1));
		jp1.add(first);
		//add(jp1);

		second = new JButton("*** 2)Generate report ***");
		second.addActionListener(this);
		//jp2=new JPanel();
		jp1.add(second);
		//add(jp2);

		/*third = new JButton("***  3)Generate report  ***");
		third.addActionListener(this);
		//jp3=new JPanel();
		jp1.add(third);
		//add(jp3);

		fourth = new JButton("*** 4)Generate report ***");
		fourth.addActionListener(this);
		//jp4=new JPanel();
		jp1.add(fourth);
		//add(jp4);

		fifth = new JButton("*** 5)Other ***");
		fifth.addActionListener(this);
		//jp5=new JPanel();
		jp1.add(fifth);
		//add(jp5);*/

		exit = new JButton("Exit");
		exit.addActionListener(this);
		jp1.add(exit);
		add(jp1);
		setVisible(false);
		setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == first) {
      keyIn_mark kim = new keyIn_mark(); 
		} /*else if (e.getSource() == second) {
			  
		} else if (e.getSource() == third) {

		}*/ else if (e.getSource() == second) {
       boolean flag=false;  
       String intakeCode = JOptionPane.showInputDialog("Enter intake code eg:[UC2L202006CS]:");
       String moduleName = JOptionPane.showInputDialog("Enter module short name eg:[OODJ]:");
      try {
       File file= new File(intakeCode.toUpperCase()+moduleName.toUpperCase()+".txt");
       if(file.exists()){
         flag=true; 
       }
      }catch(Exception ee){
        flag=false;
      }
      if(flag==true){
        Document document = new Document();
      try {
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(intakeCode.toUpperCase()+moduleName.toUpperCase()+" StudentMark Report"+".pdf"));
        document.open();

        PdfPTable table = new PdfPTable(6); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        //Set Column widths
        float[] columnWidths = {1f, 1f, 0.6f, 0.6f, 0.6f, 0.6f};
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

        PdfPCell cell3 = new PdfPCell(new Paragraph("Quiz Mark(10%)"));
        //cell3.setBorderColor(BaseColor.RED);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell4 = new PdfPCell(new Paragraph("Labtest Mark(40%)"));
        //cell3.setBorderColor(BaseColor.RED);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell5 = new PdfPCell(new Paragraph("Assignment Mark(50%)"));
        //cell3.setBorderColor(BaseColor.RED);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell6 = new PdfPCell(new Paragraph("Total Mark(100%)"));
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
        
        Scanner scan=new Scanner(new File(intakeCode.toUpperCase()+moduleName.toUpperCase()+".txt"));
        while(scan.hasNext()){
          table.addCell(scan.nextLine());
          table.addCell(scan.nextLine());
          scan.nextLine();
          scan.nextLine();
          table.addCell(scan.nextLine());
          table.addCell(scan.nextLine());
          table.addCell(scan.nextLine());
          table.addCell(scan.nextLine());
          scan.nextLine();
        }
        scan.close();
        
        document.add(new Paragraph(intakeCode.toUpperCase()+moduleName.toUpperCase()+" StudentMark Transcipt"));
        document.add(new Paragraph(" "));
        document.add(table);
       
        JOptionPane.showMessageDialog(null, "Student transcipt generate", "Generate report", JOptionPane.INFORMATION_MESSAGE);
        document.close();
        writer.close();
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Close your Report then can generate new One", "Generate report", JOptionPane.WARNING_MESSAGE);
      }
    }else{
        JOptionPane.showMessageDialog(null, "Student List not found", "Generate report", JOptionPane.WARNING_MESSAGE);
      }
		} else if (e.getSource() == exit) {
			setVisible(false);
      Date date=new Date();
      Grading_System.currentUser.setLogout(date);
      Grading_System.logFile();
      System.exit(0);
			//Grading_System.lg.setVisible(true);
		}
		//setVisible(false);
	}
}


