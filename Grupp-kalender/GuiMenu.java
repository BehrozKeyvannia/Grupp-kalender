import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GuiMenu extends JMenuBar implements ActionListener{
	
	GuiMenu(){    
		
    JMenu menuA = new JMenu("Menu A");
    JMenuItem menuItemA1 = new JMenuItem("Menu Item A 1");
    JMenuItem menuItemA2 = new JMenuItem("Menu Item A 2");
    JMenuItem menuItemA3 = new JMenuItem("Menu Item A 3");
    menuA.add(menuItemA1);
    menuA.add(menuItemA2);
    menuA.addSeparator();
    menuA.add(menuItemA3);
     
    JMenu menuB = new JMenu("Quit...");
    JMenuItem menuItemB1 = new JMenuItem("Exit");
    menuItemB1.addActionListener(this);
    
    menuB.add(menuItemB1);

     
    this.add(menuA);
    this.add(menuB);
	
	}
    public void actionPerformed(ActionEvent e) {
    	
    }
}
