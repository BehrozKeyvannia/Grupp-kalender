package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Engine.Event;
import Engine.EventManager;

/**
 * 
 * @author Ashor, Rami
 * 
 */
@SuppressWarnings("serial")
public class GuiOrg extends JFrame implements ActionListener {

	private EventManager manager;
	private JMenuBar menuBarNorth = new JMenuBar();
	private Container eventCenter = new Container();
	private BorderLayout border = new BorderLayout(); // for the frame

	// For menu bar
	private JMenuItem menuItemA1;
	private JMenuItem menuItemA2;
	private JMenuItem menuItemA3;
	private JMenuItem menuItemB1;

	// For the eventCenter container
	private BoxLayout box = new BoxLayout(eventCenter, BoxLayout.Y_AXIS);
	int i = 0;

	/**
	 * Construct the whole GUI
	 */
	public GuiOrg(EventManager manager) {

		this.setTitle("To do list");
		this.getContentPane();

		createMenu();

		eventCenter.setLayout(box);
		this.add(eventCenter);

		border.addLayoutComponent(menuBarNorth, BorderLayout.NORTH);
		border.addLayoutComponent(eventCenter, BorderLayout.CENTER);

		this.setLayout(border);
		setVisible(true);
		pack();
	}

	/**
	 * Create the main menu bar and add the action listener on its items. After
	 * creation adds to frame
	 */
	private void createMenu() {

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

		menuBarNorth.add(File);

		JMenu menuB = new JMenu("Quit...");
		menuItemB1 = new JMenuItem("Exit");
		menuItemB1.addActionListener(this);
		menuB.add(menuItemB1);

		menuBarNorth.add(menuB);
		this.add(menuBarNorth);
	}

	/**
	 * Add a new event to the GUI
	 * 
	 * @param event
	 *            The event that will be added
	 */
	public void addNewEvent(GuiEvent event) {

		eventCenter.add(event);
		this.add(eventCenter);
		this.revalidate();
		this.pack();
		this.repaint();
	}

	/**
	 * Create an input row to get the new event information from the user
	 */
	private void newEventWindow() {

		JTextField userField = new JTextField(5);
		JTextField titleField = new JTextField(5);
		JTextField dateField = new JTextField(5);
		JTextField startField = new JTextField(5);
		JTextField endField = new JTextField(5);
		JTextField descriptionField = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("userName:  "));
		myPanel.add(userField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("title:  "));
		myPanel.add(titleField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("date:  "));
		myPanel.add(dateField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("startTime:  "));
		myPanel.add(startField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("endTime:  "));
		myPanel.add(endField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("description:  "));
		myPanel.add(descriptionField);

		// Show Confirm dialog window
		int result = JOptionPane.showConfirmDialog(null, myPanel,
				"Please enter what is needed", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

//			int tempHashCode = manager.addEvent(
//					manager.createNewEvent(userField.getText(),
//							titleField.getText(), dateField.getText(),
//							startField.getText(), endField.getText(),
//							descriptionField.getText())).hashCode();

			GuiEvent guiEvent = new GuiEvent(/*change this later*/0, userField.getText(),
					titleField.getText(), dateField.getText(),
					startField.getText(), endField.getText(),
					descriptionField.getText()); // Create
													// GuiEvent
													// and
													// pass
													// hash
													// code
													// to it
			this.addNewEvent(guiEvent); // Add this event to GUI
		}
	}

	/**
	 * Doing different actions according to what menu item is been chosen
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == menuItemA1)
			newEventWindow();

		else if (e.getSource() == menuItemA3)
			System.out.println("You want to do something");

		else if (e.getSource() == menuItemB1)
			System.out.println("You want to do exit");
	}

	public static void main(String[] arg) {
		EventManager manager = new EventManager();
		GuiOrg org = new GuiOrg(manager);
	}
}