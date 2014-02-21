package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

/**
 * Create a new event item
 * 
 * @author Ashor
 * 
 */
@SuppressWarnings("serial")
public class GuiEvent extends JButton {

	private JTextField titleNorth = new JTextField("getTitle");
	private JTextField descriptionCenter = new JTextField("getDescription");

	private JTextField startTime = new JTextField("getStartTime");
	private JTextField endTime = new JTextField("getEndTime");
	private GridLayout gridWest = new GridLayout(2, 1); // For start end time
	Container west = new Container();

	private JTextField joined = new JTextField("getJoined");
	private JButton join = new JButton("Join");
	private GridLayout gridSouth = new GridLayout(1, 2); // For join and joined
	Container south = new Container();

	private BorderLayout border = new BorderLayout();
	private int i = 1;

	private int hashCode = 0;

	/**
	 * Constructor for the event
	 */
	public GuiEvent(int hashCode) {

		this.hashCode = hashCode;

		titleNorth.setHorizontalAlignment(JTextField.CENTER);
		descriptionCenter.setHorizontalAlignment(JTextField.CENTER);
		add(titleNorth);
		add(descriptionCenter);

		AddListenerToJoin();
		south.add(joined);
		south.add(join);
		south.setLayout(gridSouth);
		add(south);

		startTime.setHorizontalAlignment(JTextField.CENTER);
		endTime.setHorizontalAlignment(JTextField.CENTER);
		west.setLayout(gridWest);
		west.add(startTime);
		west.add(endTime);
		add(west);

		// Initiate the border layout of the event button
		border.addLayoutComponent(titleNorth, BorderLayout.NORTH);
		border.addLayoutComponent(descriptionCenter, BorderLayout.CENTER);
		border.addLayoutComponent(south, BorderLayout.SOUTH);
		border.addLayoutComponent(west, BorderLayout.WEST);

		setLayout(border);
	}

	/**
	 * Add action listener to join button and increment joined field if joined
	 * clicked. set joined editable to false
	 */
	private void AddListenerToJoin() {
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == join) {
					joined.setText("" + i);
					i++;
				}
			}
		});

		joined.setEditable(false);
	}
}