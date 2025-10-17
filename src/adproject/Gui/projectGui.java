package adproject.Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class projectGui {

    JButton btnLogin, btnClear, btnExit;
    JPanel pnlTop, pnlMiddle, pnlBottom;
    JLabel lblEmail, lblPassword;
    JTextField tfName;
    JPasswordField pfPassword;
    JComboBox cboRole;
    private JFrame loginFrame;

    public projectGui() {
    }

    public void setGui(JFrame frame) {
        this.loginFrame = frame;
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
            if ("Admin".equals(roleSelected)) {
                frame.setVisible(false);
                adminFrame();
<<<<<<< Updated upstream
            } else if ("Student".equals(roleSelected)){
=======
            } else if ("Student".equals(roleSelected)) {
>>>>>>> Stashed changes
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
        adminFrame.setSize(800, 400);
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
        JLabel lblSlotAvailable = new JLabel("Slots Available:");
        JTextField tfSlotAvailable = new JTextField(5);
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
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            adminFrame.dispose();
            loginFrame.setVisible(true);
        });
        //Add components to gridPanel
        gridPanel.add(lblCourseName);
        gridPanel.add(tfCourseName);
        gridPanel.add(lblCourseCode);
        gridPanel.add(tfCourseCode);
        gridPanel.add(lblSlotAvailable);
        gridPanel.add(tfSlotAvailable);
        //Add components to flowPanel
        flowPanel.add(btnSubmit);
        flowPanel.add(btnClear);
        flowPanel.add(btnBack);
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
        JButton btnBack2 = new JButton("Back");
        btnBack2.addActionListener(e -> {
            adminFrame.dispose();
            loginFrame.setVisible(true);
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
        flowPanel2.add(btnBack2);
        //Add flow & grid panel to main panel
        mainPanel2.add(gridPanel2, BorderLayout.CENTER);
        mainPanel2.add(flowPanel2, BorderLayout.SOUTH);

        //Setting up the enrollment requests tab
        JPanel pnlRequest = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("Student Number");
        model.addColumn("Email");
        model.addColumn("Course");
        model.addColumn("Course Code");
        model.addColumn("Slots Available");
        model.addColumn("Request Date");

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        //Setting up buttons
        JPanel pnlButtons = new JPanel(new FlowLayout());
        JButton btnApprove = new JButton("Approve");
        btnApprove.addActionListener(e -> {
            //Add code here that will enroll student in a course!!!
        });
        JButton btnReject = new JButton("Reject");
        btnReject.addActionListener(e -> {
            //Add code here that will reject the student from that course!!!
        });
        JButton btnBack3 = new JButton("Back");
        btnBack3.addActionListener(e -> {
            adminFrame.dispose();
            loginFrame.setVisible(true);
        });
        //Adding scroll pane to requests panel
        pnlButtons.add(btnApprove);
        pnlButtons.add(btnReject);
        pnlButtons.add(btnBack3);
        //Setting up display options
        JPanel pnlDisplayOptions = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton btnShowCourse = new JButton("Show Course");
        JComboBox<String> cboShowCourse = new JComboBox<>();
        cboShowCourse.addItem("--Select--");
        JButton btnShowStudent = new JButton("Show Student");
        JComboBox<String> cboShowStudent = new JComboBox<>();
        cboShowStudent.addItem("--Select--");
        //Add functionality
        btnShowCourse.addActionListener(e -> {
            //Add code here that will show courses filtered by JComboBox
        });
        btnShowStudent.addActionListener(e -> {
            //Add code here that will show course filtered by JComboBox
        });
        //Add components to pnlDisplayOptions
        pnlDisplayOptions.add(btnShowCourse);
        pnlDisplayOptions.add(cboShowCourse);
        pnlDisplayOptions.add(btnShowStudent);
        pnlDisplayOptions.add(cboShowStudent);
        //Add components to pnlRequest
        pnlRequest.add(pnlDisplayOptions, BorderLayout.NORTH);
        pnlRequest.add(scrollPane, BorderLayout.CENTER);
        pnlRequest.add(pnlButtons, BorderLayout.SOUTH);
        //Add the main panel to the tab
        tab.addTab("Add Course", mainPanel);
        tab.addTab("Add Student", mainPanel2);
        tab.addTab("Enrollment requests", pnlRequest);
        //Add panels to frame
        adminFrame.add(tab);
        //Make frame visible
        adminFrame.setVisible(true);
    }

    public void studentFrame() {
        //Setting up frame
        JFrame studentFrame = new JFrame("Student");
        studentFrame.setSize(400, 400);
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentFrame.setLocationRelativeTo(null);
        studentFrame.setVisible(true);
<<<<<<< Updated upstream
        
        //Setting up JTable for students to view courses
        
=======

        //Create tabbed pane
        JTabbedPane tab = new JTabbedPane();

        //Setting up student Frame
        JPanel pnlStudent = new JPanel(new BorderLayout());
        //Setting up north panel
        JPanel pnlNorth = new JPanel(new GridLayout(1, 3, 5, 5));
        JLabel lblCourse = new JLabel("Choose course:");
        JButton btnShowCourses = new JButton("Show courses");
        JComboBox<String> cboCourses = new JComboBox<>();
        cboCourses.addItem("--Select--");
        //Setting up the JTable
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Course");
        model.addColumn("Course Code");
        model.addColumn("Slots available");
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        //Setting up south panel (buttons)
        JPanel pnlSouth = new JPanel(new FlowLayout());
        JButton btnEnroll = new JButton("Enroll");
        btnEnroll.addActionListener(e -> {
            //Add code to enroll student into DB
        });
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> {
            cboCourses.setSelectedIndex(0);
            model.setRowCount(0);
        });
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            studentFrame.dispose();
            loginFrame.setVisible(true);
        });
        //Adding components to north panel
        pnlNorth.add(lblCourse);
        pnlNorth.add(btnShowCourses);
        pnlNorth.add(cboCourses);
        //Adding componnets to south panel
        pnlSouth.add(btnEnroll);
        pnlSouth.add(btnClear);
        pnlSouth.add(btnBack);
        //Adding panels to student panel
        pnlStudent.add(pnlNorth, BorderLayout.NORTH);
        pnlStudent.add(scrollPane, BorderLayout.CENTER);
        pnlStudent.add(pnlSouth, BorderLayout.SOUTH);

        JPanel pnlStudCourses = new JPanel(new BorderLayout());
        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("Name");
        model2.addColumn("Surname");
        model2.addColumn("Email");
        model2.addColumn("Course");
        model2.addColumn("Course Code");
        JTable table2 = new JTable(model2);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        JPanel pnlDisplay = new JPanel(new FlowLayout());
        JButton btnDisplayCourses = new JButton("Display Courses");
        btnDisplayCourses.addActionListener(e -> {
            //Add code here that will show courses in the JTable
        });
        JButton btnBack2 = new JButton("Back");
        btnBack2.addActionListener(e -> {
            studentFrame.dispose();
            loginFrame.setVisible(true);
        });
        //Add components to pnlDisplay
        pnlDisplay.add(btnDisplayCourses);
        pnlDisplay.add(btnBack2);
        //Add components to pnlStudCourses
        pnlStudCourses.add(scrollPane2, BorderLayout.CENTER);
        pnlStudCourses.add(pnlDisplay, BorderLayout.SOUTH);
        //Adding student panel to tab
        tab.addTab("Course Enrollment", pnlStudent);
        tab.addTab("Enrolled Courses", pnlStudCourses);
        //Adding student panel to frame
        studentFrame.add(tab);
>>>>>>> Stashed changes
    }
}
