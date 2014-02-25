package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class GuiEvent extends JPanel {

	private JTextField titleNorth = new JTextField("noTitle");
	private JTextField dateEast = new JTextField("noDate");
	private JTextField descriptionCenter = new JTextField("noDescription");

	private JTextField startTimeWest = new JTextField("noStartTime");
	private JTextField endTimeWest = new JTextField("noEndTime");
	private GridLayout gridWest = new GridLayout(2, 1); // For start end time
	Container west = new Container();

	private JTextField joinedSouth = new JTextField("noneJoined");
	private JButton joinSouth = new JButton("Join");
	private GridLayout gridSouth = new GridLayout(1, 2); // For join and joined
	Container south = new Container();

	private BorderLayout border = new BorderLayout(); // A layout for the whole
														// GUI event

	private int hashCode = 0;
	private EventManager manager;
	private int usersJoined = 0; // userJoined counter

	/**
	 * Constructor for the event
	 */
	public GuiEvent(int hashCode, EventManager manager) {

		this.setLayout(border);

		this.manager = manager;
		this.hashCode = hashCode;

		titleNorth.setText(manager.getEventByID(hashCode).getTitle());
		dateEast.setText(manager.getEventByID(hashCode).getDate());
		startTimeWest.setText(manager.getEventByID(hashCode).getStartTime());
		endTimeWest.setText(manager.getEventByID(hashCode).getEndTime());
		descriptionCenter.setText(manager.getEventByID(hashCode)
				.getDescription());

		titleNorth.setEditable(false);
		dateEast.setEditable(false);
		startTimeWest.setEditable(false);
		endTimeWest.setEditable(false);
		descriptionCenter.setEditable(false);

		titleNorth.setHorizontalAlignment(JTextField.CENTER);
		descriptionCenter.setHorizontalAlignment(JTextField.CENTER);
		dateEast.setHorizontalAlignment(JTextField.CENTER);
		add(titleNorth);
		add(descriptionCenter);
		add(dateEast);

		AddListenerToJoin();
		south.setLayout(gridSouth);
		south.add(joinedSouth);
		south.add(joinSouth);
		add(south);

		startTimeWest.setHorizontalAlignment(JTextField.CENTER);
		endTimeWest.setHorizontalAlignment(JTextField.CENTER);
		west.setLayout(gridWest);
		west.add(startTimeWest);
		west.add(endTimeWest);
		add(west);

		// Initiate the border layout of the event button
		border.addLayoutComponent(titleNorth, BorderLayout.NORTH);
		border.addLayoutComponent(dateEast, BorderLayout.EAST);
		border.addLayoutComponent(descriptionCenter, BorderLayout.CENTER);
		border.addLayoutComponent(south, BorderLayout.SOUTH);
		border.addLayoutComponent(west, BorderLayout.WEST);

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
					while (true) {
						int ok = JOptionPane.showConfirmDialog(null, userName,
								"Please enter your userName",
								JOptionPane.OK_CANCEL_OPTION);

						if (ok == JOptionPane.CANCEL_OPTION)
							break;

						else if (ok == JOptionPane.OK_OPTION
								&& !userName.getText().equals("")) {

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
			}
		});

		joinedSouth.setEditable(false);
	}
}