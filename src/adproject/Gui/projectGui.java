package adproject.Gui;

import java.awt.*;
import javax.swing.*;

public class projectGui {
	JPanel pnlTop, pnlBottom;
	JLabel lblName, lblPassword;
	JTextField tfName;
	JPasswordField pfPassword;
	JComboBox cboRole;
	
	public projectGui() {
	}
	
	public void setGui(JFrame frame) {
		pnlTop = new JPanel();
		pnlBottom = new JPanel();
		lblName = new JLabel("Name");
		tfName = new JTextField(20);
		lblPassword = new JLabel("Password");
		pfPassword = new JPasswordField(20);
		pnlTop.add(lblName);
		pnlTop.add(tfName);
		pnlBottom.add(lblPassword);
		pnlBottom.add(pfPassword);
		pnlBottom.setLayout(new BorderLayout());
		frame.add(pnlTop);
		frame.add(pnlBottom);
	}
}
