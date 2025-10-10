package adproject;
import adproject.Gui.projectGui;
import javax.swing.*;

import javax.swing.JOptionPane;

public class ADProject {
	
    public static void main(String[] args) {
	    JFrame frame = new JFrame("LOGIN");
            frame.setSize(500, 200);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
            projectGui gui = new projectGui();
	    gui.setGui(frame);
	    
	    frame.setVisible(true);
    }
    
}
