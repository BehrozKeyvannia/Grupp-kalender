package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * @author Rami, Ashor
 * 
 */
@SuppressWarnings("serial")
public class GuiMenu extends JMenuBar implements ActionListener {

	private JMenuItem menuItemA1;
	private JMenuItem menuItemA2;
	private JMenuItem menuItemA3;
	private JMenuItem menuItemB1;

	/**
	 * Create the main menu bar and add the action listener on its items
	 */
	GuiMenu() {

		JMenu File = new JMenu("File");

		menuItemA1 = new JMenuItem("Create new Event");
		menuItemA1.addActionListener(this);

		menuItemA2 = new JMenuItem("");

		menuItemA3 = new JMenuItem("Menu Item A3");
		menuItemA3.addActionListener(this);

		File.add(menuItemA1);
		File.add(menuItemA2);
		File.addSeparator();
		File.add(menuItemA3);

		JMenu menuB = new JMenu("Quit...");
		menuItemB1 = new JMenuItem("Exit");
		menuItemB1.addActionListener(this);
		menuB.add(menuItemB1);

		this.add(File);
		this.add(menuB);

	}

	/**
	 * Doing different actions according to what menu item is been chosen 
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == menuItemA1)
			System.out.println("You want to create event.");
		
		else if (e.getSource() == menuItemA3)
			System.out.println("You want to do something");
		
		else if (e.getSource() == menuItemB1)
			System.out.println("You want to do exit");
	}
}