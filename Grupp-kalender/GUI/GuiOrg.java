package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Observer;
import java.util.Observable;

import javax.swing.*;

import Com.EventCom;
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
	private JMenuItem menuItemA4;
	private JMenuItem menuItemB1;
	private EventCom  com;

	// For the eventCenter container
	private BoxLayout box = new BoxLayout(eventCenter, BoxLayout.Y_AXIS);
	int i = 0;
	private Date date;

	/**
	 * Construct the whole GUI
	 */
	public GuiOrg(EventManager manager) {


		this.manager = manager;
		try {
			this.com = new EventCom(manager.getEventList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setTitle("To do list");
		this.getContentPane();
		this.setLayout(border);

		createMenu();

		date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String datum = format1.format(date);

		eventCenter.setLayout(box);
		this.add(eventCenter);

		border.addLayoutComponent(menuBarNorth, BorderLayout.NORTH);
		border.addLayoutComponent(eventCenter, BorderLayout.CENTER);

		setVisible(true);
		setPreferredSize(new Dimension(500,500));
		pack();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		update(manager,null);
	}

	/**
	 * Create the main menu bar and add the action listener on its items. After
	 * creation adds to frame
	 */
	private void createMenu() {

		JMenu File = new JMenu("File");

		menuItemA1 = new JMenuItem("Create new Event");
		menuItemA1.addActionListener(this);

		menuItemA2 = new JMenuItem("Share events!");
		menuItemA2.addActionListener(this);

		menuItemA3 = new JMenuItem("Remove all past events");
		menuItemA3.addActionListener(this);
		
		menuItemA4 = new JMenuItem("Remove all events");
		menuItemA4.addActionListener(this);

		File.add(menuItemA1);
		File.add(menuItemA2);
		File.addSeparator();
		File.add(menuItemA3);
		File.add(menuItemA4);

		menuBarNorth.add(File);

		JMenu menuB = new JMenu("Save File");
		menuItemB1 = new JMenuItem("Save");
		menuItemB1.addActionListener(this);
		menuB.add(menuItemB1);

		menuBarNorth.add(menuB);
		add(menuBarNorth);
	}

	/**
	 * Add a new event to the GUI
	 * 
	 * @param event
	 *            The event that will be added
	 */
	public void addNewEvent(GuiEvent event) {

		eventCenter.add(event);
		add(eventCenter);
		pack();
	}

	/**
	 * Remove all the GUI Events from the screen
	 */
	private void clear() {

		eventCenter.removeAll();
		pack();
	}

	/**
	 * Create new GUI events for a collection of Iterator
	 * 
	 * @param eventIterator
	 *            The iterator that will be used to create the new events
	 */
	public void createEventsIterator(Iterator<Event> eventIterator) {
		while (eventIterator.hasNext()) {
			Event temp = eventIterator.next();
			if(date.compareTo(temp.getDate2())<0)
				addNewEvent(new GuiEvent(temp.hashCode(), manager));
		}
	}


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

			if (result == JOptionPane.OK_OPTION){
				// Check that user name is entered
				if (userField.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter a user name");

				// Check for date format input is correct entered
				else if (!dateField.getText().matches(
						("(\\d{4}+)(-{1}+)(\\d{2}+)(-{1}+)(\\d{2}+)")))
					JOptionPane.showMessageDialog(null,
							"Please enter day as yyyy-mm-dd format");

				// Check to see that time format is correct
				else if (!startField.getText().matches(
						("(\\d{2}+)(:{1}+)(\\d{2}+)")) || !endField
						.getText().matches(("(\\d{2}+)(:{1}+)(\\d{2}+)")))
					JOptionPane.showMessageDialog(null,
							"Please enter time as hh-mm format");
				else {
					manager.addEvent(
							manager.createNewEvent(userField.getText(),
									titleField.getText(), dateField.getText(),
									startField.getText(), endField.getText(),
									descriptionField.getText())).hashCode();
					break;
				}
			}
			// If canceled then exit
			else if (result == JOptionPane.CANCEL_OPTION)
				break;
			else
				break;
			
		}
	}

	/**
	 * Doing different actions according to what menu item is been chosen
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == menuItemA1)
			newEventWindow();

		else if (e.getSource() == menuItemA2)
			manager.addEventList(com.serverOrClient());
		else if (e.getSource() == menuItemA3)
			manager.removePastEvents();
		else if (e.getSource() == menuItemA4)
			manager.removeAllEvents();
		else if (e.getSource() == menuItemB1)
			manager.saveEvents();
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs == manager) {
			clear();
			createEventsIterator(manager.getEventListIterator());
		}
	}
}
