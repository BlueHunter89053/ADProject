package adproject.Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class projectGui {
    JButton btnLogin, btnClear, btnExit;
    JPanel pnlTop, pnlMiddle, pnlBottom;
    JLabel lblStudNum, lblPassword;
    JTextField tfName;
    JPasswordField pfPassword;
    JComboBox cboRole;

    public projectGui() {
    }

    public void setGui(JFrame frame) {
        frame.setLayout(new GridLayout(3, 1));
        
        //Setting up the JPanels
        pnlTop = new JPanel();
        pnlMiddle = new JPanel();
        pnlBottom = new JPanel();
        
        //Populating top JPanel
        lblStudNum = new JLabel("Student Number");
        tfName = new JTextField(20);
        pnlTop.add(lblStudNum);
        pnlTop.add(tfName);
        
        //Populating the middle JPanel
        lblPassword = new JLabel("Password");
        pfPassword = new JPasswordField(10);
        pnlMiddle.add(lblPassword);
        pnlMiddle.add(pfPassword);
        
        //Setting up JComboBox
        String[] roles = {"--Select--", "Student", "Staff"};
        cboRole = new JComboBox<>(roles);
        cboRole.setPreferredSize(new Dimension(80, 25));
        pnlMiddle.add(cboRole);
        
        //Setting up the bottom JPanel
        btnLogin = new JButton("Login");
        btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> { //used lambda expression to add functionality to the button
            tfName.setText("");
            pfPassword.setText("");
        });
        btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> { //used lambda expression to add functionality to the button
            System.exit(0);
        });
        pnlBottom.add(btnLogin);
        pnlBottom.add(btnClear);
        pnlBottom.add(btnExit);
        
        frame.add(pnlTop);
        frame.add(pnlMiddle);
        frame.add(pnlBottom);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String roleSelected = (String) cboRole.getSelectedItem();
            if ("Staff".equals(roleSelected)) {
                
            }
        }
    }
    
    public void adminFrame() {
        //this.setVisible(false);
        
        //Create new frame
        JFrame adminFrame = new JFrame("Administrator");
        adminFrame.setSize(400, 400);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
    }
    
}