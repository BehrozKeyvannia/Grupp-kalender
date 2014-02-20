import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GuiMenu extends JMenuBar implements ActionListener{
	
	GuiMenu(){    
		
    JMenu File = new JMenu("File");
    JMenuItem menuItemA1 = new JMenuItem("Create new Event");
    JMenuItem menuItemA2 = new JMenuItem("");
    JMenuItem menuItemA3 = new JMenuItem("Menu Item A 3");
    File.add(menuItemA1);
    File.add(menuItemA2);
    File.addSeparator();
    File.add(menuItemA3);
     
    JMenu menuB = new JMenu("Quit...");
    JMenuItem menuItemB1 = new JMenuItem("Exit");
    menuItemB1.addActionListener(this);
    
    menuB.add(menuItemB1);

     
    this.add(File);
    this.add(menuB);
	
	}
    public void actionPerformed(ActionEvent e) {
 
    }
}
