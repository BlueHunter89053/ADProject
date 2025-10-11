package adproject.Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class projectGui {

    JButton btnLogin, btnClear, btnExit;
    JPanel pnlTop, pnlMiddle, pnlBottom;
    JLabel lblEmail, lblPassword;
    JTextField tfName;
    JPasswordField pfPassword;
    JComboBox cboRole;

    public projectGui() {
    }

    public void setGui(JFrame frame) {
        JPanel mainPanel0 = new JPanel(new BorderLayout());
        //Creating panel objects
        pnlTop = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlBottom = new JPanel(new FlowLayout());
        //Populating top JPanel
        lblEmail = new JLabel("Email:");
        tfName = new JTextField(14);
        pnlTop.add(lblEmail);
        pnlTop.add(tfName);
        //Populating the middle JPanel
        lblPassword = new JLabel("Password:");
        pfPassword = new JPasswordField(10);
        pnlTop.add(lblPassword);
        pnlTop.add(pfPassword);
        //Setting up JComboBox
        JLabel lblRole = new JLabel("Role:");
        String[] roles = {"--Select--", "Student", "Admin"};
        cboRole = new JComboBox<>(roles);
        cboRole.setPreferredSize(new Dimension(80, 25));
        pnlTop.add(lblRole);
        pnlTop.add(cboRole);
        //Setting up the bottom JPanel
        btnLogin = new JButton("Login");
        btnLogin.addActionListener((ActionEvent e) -> { //Used lambda expression to add functionality login button
            String roleSelected = (String) cboRole.getSelectedItem();
            if ("Staff".equals(roleSelected)) {
                frame.setVisible(false);
                adminFrame();
            } else {
                frame.setVisible(false);
                studentFrame();
            }
        });
        btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> { //used lambda expression to add functionality to clear button
            tfName.setText("");
            pfPassword.setText("");
        });
        btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> { //used lambda expression to add functionality to exit button
            System.exit(0);
        });
        //Adding components to bottom panel
        pnlBottom.add(btnLogin);
        pnlBottom.add(btnClear);
        pnlBottom.add(btnExit);
        //Add panels to main panel
        mainPanel0.add(pnlTop, BorderLayout.CENTER);
        mainPanel0.add(pnlBottom, BorderLayout.SOUTH);
        //Add panels to frame
        frame.add(mainPanel0);
    }

    public void adminFrame() {
        //Create new frame
        JFrame adminFrame = new JFrame("Administrator");
        adminFrame.setSize(400, 400);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setLocationRelativeTo(null);

        //Create tabbed pane
        JTabbedPane tab = new JTabbedPane();

        //Main panel object
        JPanel mainPanel = new JPanel(new BorderLayout());
        //gridPanel object
        JPanel gridPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //Components of gridPanel
        JLabel lblCourseName = new JLabel("Course name:");
        JTextField tfCourseName = new JTextField(10);
        tfCourseName.setPreferredSize(new Dimension(25, 25));
        JLabel lblCourseCode = new JLabel("Course code:");
        JTextField tfCourseCode = new JTextField(10);
        tfCourseCode.setPreferredSize(new Dimension(25, 25));
        //flowPanel object used for JButtons
        JPanel flowPanel = new JPanel(new FlowLayout());
        //Submit button functionality
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(e -> {
            //This code must send the data to the database. 
        });
        //Clear button functionality
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> {
            tfCourseName.setText("");
            tfCourseCode.setText("");
        });
        //Exit button functionality
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> {
            System.exit(0);
        });
        //Add components to gridPanel
        gridPanel.add(lblCourseName);
        gridPanel.add(tfCourseName);
        gridPanel.add(lblCourseCode);
        gridPanel.add(tfCourseCode);
        //Add components to flowPanel
        flowPanel.add(btnSubmit);
        flowPanel.add(btnClear);
        flowPanel.add(btnExit);
        //Add gridPanel & flowPanel to mainPanel
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(flowPanel, BorderLayout.SOUTH);

        //Create the student panel object
        JPanel mainPanel2 = new JPanel(new BorderLayout());
        //Components of student panel
        JPanel gridPanel2 = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel lblName = new JLabel("Name:");
        JTextField tfName = new JTextField(15);
        JLabel lblSname = new JLabel("Surname:");
        JTextField tfSname = new JTextField(15);
        JLabel lblEmail = new JLabel("Email:");
        JTextField tfEmail = new JTextField(15);
        JLabel lblStudNum = new JLabel("Student Number:");
        JTextField tfStudNum = new JTextField(15);
        //Flow panel object
        JPanel flowPanel2 = new JPanel(new FlowLayout());
        //Submit button functionality
        JButton btnSubmit2 = new JButton("Submit");
        btnSubmit2.addActionListener(e -> {
            //Add code that adds the student details to the database
        });
        //Clear button functionality
        JButton btnClear2 = new JButton("Clear");
        btnClear2.addActionListener(e -> {
            tfName.setText("");
            tfSname.setText("");
            tfEmail.setText("");
            tfStudNum.setText("");
        });
        //Exit button functionality
        JButton btnExit2 = new JButton("Exit");
        btnExit2.addActionListener(e -> {
            System.exit(0);
        });
        //Add components to grid panel
        gridPanel2.add(lblName);
        gridPanel2.add(tfName);
        gridPanel2.add(lblSname);
        gridPanel2.add(tfSname);
        gridPanel2.add(lblEmail);
        gridPanel2.add(tfEmail);
        gridPanel2.add(lblStudNum);
        gridPanel2.add(tfStudNum);
        //Add components to flow panel
        flowPanel2.add(btnSubmit2);
        flowPanel2.add(btnClear2);
        flowPanel2.add(btnExit2);
        //Add flow & grid panel to main panel
        mainPanel2.add(gridPanel2, BorderLayout.CENTER);
        mainPanel2.add(flowPanel2, BorderLayout.SOUTH);
        //Add the main panel to the tab
        tab.addTab("Add Course", mainPanel);
        tab.addTab("Add Student", mainPanel2);
        //Add panels to frame
        adminFrame.add(tab);
        //Make frame visible
        adminFrame.setVisible(true);
    }

    public void studentFrame() {
        //Create new frame
        JFrame studentFrame = new JFrame("Student");
        studentFrame.setSize(400, 400);
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentFrame.setLocationRelativeTo(null);
        studentFrame.setVisible(true);

    }
}
