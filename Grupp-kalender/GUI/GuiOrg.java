package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Observer;
import java.util.Observable;

import javax.swing.*;

import Engine.*;

// import Engine.Event;
// import Engine.EventManager;

/**
 * 
 * @author Ashor, Rami
 * 
 */
@SuppressWarnings("serial")
public class GuiOrg extends JFrame implements ActionListener, Observer {

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

		this.manager = manager;
		this.setTitle("To do list");
		this.getContentPane();
		this.setLayout(border);

		createMenu();

		eventCenter.setLayout(box);
		this.add(eventCenter);

		border.addLayoutComponent(menuBarNorth, BorderLayout.NORTH);
		border.addLayoutComponent(eventCenter, BorderLayout.CENTER);

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

		menuItemA3 = new JMenuItem("Remove all events");
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
		this.pack();
	}

	/**
	 * Remove all the GUI Events from the screen
	 */
	private void removeAllEvents() {

		eventCenter.removeAll();
		this.pack();
	}

	/**
	 * Create new GUI events for a collection of Iterator
	 * 
	 * @param eventIterator
	 *            The iterator that will be used to create the new events
	 */
	public void createEventsIterator(Iterator<Event> eventIterator) {
		while (eventIterator.hasNext()) {
			addNewEvent(new GuiEvent(eventIterator.next().hashCode(), manager));
		}
	}

	// /**
	// * Create new GUI events for a collection of Iterator
	// *
	// * @param eventIterator
	// * The iterator that will be used to create the new events
	// */
	// public void createEventsIterator(Iterator<Event> eventIterator) {
	//
	// Event tempEvent = eventIterator.next();
	// while (eventIterator.hasNext()) {
	// GuiEvent guiEvent = new GuiEvent(tempEvent.hashCode(), manager);
	// addNewEvent(guiEvent);
	// }
	// }

	/**
	 * Create an input row to get the new event information from the user
	 */
	private void newEventWindow() {

		JTextField userField = new JTextField(8);
		userField.setToolTipText("Enter user name");

		JTextField titleField = new JTextField(6);
		titleField.setToolTipText("Enter title");

		JTextField dateField = new JTextField(8);
		dateField.setToolTipText("Enter yyyy-mm-dd");

		JTextField startField = new JTextField(6);
		startField.setToolTipText("Enter hh:mm");

		JTextField endField = new JTextField(6);
		endField.setToolTipText("Enter hh:mm");

		JTextField descriptionField = new JTextField(10);
		descriptionField.setToolTipText("Describe you event please");

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

		while (true) {

			// Show Confirm dialog window
			int result = JOptionPane
					.showConfirmDialog(null, myPanel,
							"Please enter what is needed",
							JOptionPane.OK_CANCEL_OPTION);

			// Check that user name is entered
			if (result == JOptionPane.OK_OPTION
					&& userField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a user name");

			// Check for date format input is correct entered
			if (result == JOptionPane.OK_OPTION
					&& !dateField.getText().matches(
							("(\\d{4}+)(-{1}+)(\\d{2}+)(-{1}+)(\\d{2}+)")))
				JOptionPane.showMessageDialog(null,
						"Please enter day as yyyy-mm-dd format");

			// Check to see that time format is correct
			else if (result == JOptionPane.OK_OPTION
					&& (!startField.getText().matches(
							("(\\d{2}+)(:{1}+)(\\d{2}+)")) || !endField
							.getText().matches(("(\\d{2}+)(:{1}+)(\\d{2}+)"))))
				JOptionPane.showMessageDialog(null,
						"Please enter time as hh-mm format");

			// If canceled then exit
			else if (result == JOptionPane.CANCEL_OPTION)
				break;

			else {

				// int tempHashCode =
				manager.addEvent(
						manager.createNewEvent(userField.getText(),
								titleField.getText(), dateField.getText(),
								startField.getText(), endField.getText(),
								descriptionField.getText())).hashCode();
				// Create GuiEvent and pass hash code to it
				// GuiEvent guiEvent = new GuiEvent(tempHashCode, manager);

				// this.addNewEvent(guiEvent); // Add this event to GUI
				break;
			}
		}
	}

	/**
	 * Doing different actions according to what menu item is been chosen
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == menuItemA1)
			newEventWindow();

		else if (e.getSource() == menuItemA3)
			removeAllEvents();

		else if (e.getSource() == menuItemB1)
			System.out.println("You want to do exit");
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs == manager) {
			removeAllEvents();
			createEventsIterator(manager.getEventListIterator());
		}
	}
	/*
	 * public static void main(String[] arg) { EventManager manager = new
	 * EventManager(); GuiOrg org = new GuiOrg(manager);
	 * 
	 * manager.addObserver(org);
	 * 
	 * }
	 */
}