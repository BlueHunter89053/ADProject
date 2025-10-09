package adproject.Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class projectGui {
    JButton btnLogin, btnClear, btnExit;
    JPanel pnlTop, pnlMiddle, pnlBottom;
    JLabel lblName, lblPassword;
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
        lblName = new JLabel("Name");
        tfName = new JTextField(20);
        pnlTop.add(lblName);
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
        btnClear.addActionListener(e -> {
            tfName.setText("");
            pfPassword.setText("");
        });
        btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> {
            System.exit(0);
        });
        pnlBottom.add(btnLogin);
        pnlBottom.add(btnClear);
        pnlBottom.add(btnExit);
        
        frame.add(pnlTop);
        frame.add(pnlMiddle);
        frame.add(pnlBottom);
    }
    
    
}