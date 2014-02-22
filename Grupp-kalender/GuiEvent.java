package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import Engine.EventManager;

/**
 * Create a new event item
 * 
 * @author Ashor
 * 
 */
@SuppressWarnings("serial")
public class GuiEvent extends JButton {

	private JTextField titleNorth = new JTextField("noTitle");
	private JTextField dateCenter = new JTextField("noDate");
	private JTextField descriptionEast = new JTextField("noDescription");

	private JTextField startTimeWest = new JTextField("noStartTime");
	private JTextField endTimeWest = new JTextField("noEndTime");
	private GridLayout gridWest = new GridLayout(2, 1); // For start end time
	Container west = new Container();

	private JTextField joinedSouth = new JTextField("noneJoined");
	private JButton joinSouth = new JButton("Join");
	private GridLayout gridSouth = new GridLayout(1, 2); // For join and joined
	Container south = new Container();

	private BorderLayout border = new BorderLayout(); // A layout for the whole
														// GUI

	private int hashCode = 0;
	private EventManager manager;
	private int usersJoined = 0; // userJoined counter

	/**
	 * Constructor for the event
	 */
	public GuiEvent(int hashCode, EventManager manager) {

		this.manager = manager;
		this.hashCode = hashCode;
		titleNorth.setText(manager.getEventByID(hashCode).getTitle());
		dateCenter.setText(manager.getEventByID(hashCode).getDate());
		startTimeWest.setText(manager.getEventByID(hashCode).getStartTime());
		endTimeWest.setText(manager.getEventByID(hashCode).getEndTime());
		descriptionEast
				.setText(manager.getEventByID(hashCode).getDescription());

		titleNorth.setHorizontalAlignment(JTextField.CENTER);
		descriptionEast.setHorizontalAlignment(JTextField.CENTER);
		add(titleNorth);
		add(descriptionEast);
		add(dateCenter);

		AddListenerToJoin();
		south.add(joinedSouth);
		south.add(joinSouth);
		south.setLayout(gridSouth);
		add(south);

		startTimeWest.setHorizontalAlignment(JTextField.CENTER);
		endTimeWest.setHorizontalAlignment(JTextField.CENTER);
		west.setLayout(gridWest);
		west.add(startTimeWest);
		west.add(endTimeWest);
		add(west);

		// Initiate the border layout of the event button
		border.addLayoutComponent(titleNorth, BorderLayout.NORTH);
		border.addLayoutComponent(dateCenter, BorderLayout.CENTER);
		border.addLayoutComponent(descriptionEast, BorderLayout.EAST);
		border.addLayoutComponent(south, BorderLayout.SOUTH);
		border.addLayoutComponent(west, BorderLayout.WEST);

		setLayout(border);
	}

	/**
	 * Add action listener to join button and increment joined field if joined
	 * clicked. set joined editable to false
	 */
	private void AddListenerToJoin() {
		joinSouth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == joinSouth) {
					JTextField userName = new JTextField();
					int ok = JOptionPane.showConfirmDialog(null, userName,
							"Please enter your userName",
							JOptionPane.OK_CANCEL_OPTION);
					if (ok == JOptionPane.OK_OPTION) {

						// Get the event and add the user to the event
						(manager.getEventByID(hashCode)).addUser(userName
								.getText());

						// Get how many users are joined
						usersJoined = manager.getEventByID(hashCode)
								.getListOfUsers().length;
						joinedSouth.setText("" + usersJoined);
					}
				}
			}
		});

		joinedSouth.setEditable(false);
	}
}