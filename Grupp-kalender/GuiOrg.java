package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Ashor, Rami
 * 
 */
@SuppressWarnings("serial")
public class GuiOrg extends JFrame implements ActionListener {

	private GuiMenu menuBarNorth = new GuiMenu();
	private Container eventCenter = new Container();
	private BorderLayout border = new BorderLayout(); // for the frame
	
	//For the eventCenter container 
	private BoxLayout box = new BoxLayout(eventCenter, BoxLayout.Y_AXIS);
	String date = null;
	int i = 0;

	/**
	 * Construct the whole GUI
	 */
	public GuiOrg() {

		this.setTitle("To do list");
		this.getContentPane();

		eventCenter.setLayout(box);

		this.add(menuBarNorth);
		this.add(eventCenter);
		border.addLayoutComponent(menuBarNorth, BorderLayout.NORTH);
		border.addLayoutComponent(eventCenter, BorderLayout.CENTER);

		this.setLayout(border);
		setVisible(true);
		pack();

		// /**
		// * Tar in dagens datum från Calender och gör om den till ett enklare
		// * format
		// */
		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, 0);
		// SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		// date = format1.format(cal.getTime());

		// setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// add(new JLabel("<html><div style=\"text-align: center;\">" + date
		// + "<html/>", JLabel.CENTER));
	}

	public void addNewEvent(GuiEvent event) {

		eventCenter.add(event);
		add(eventCenter);
		pack();

	}

	/**
	 * Skapar nytt fönster med alla inmatningsrutor för ett nytt event
	 * 
	 */
	public void newEventWindow() {
		JTextField startField = new JTextField(5);
		JTextField endField = new JTextField(5);
		JTextField titleField = new JTextField(5);
		JTextField userField = new JTextField(5);

		/**
		 * Lägger alla JLabels och inmatningsrutor i fönstert
		 * */
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("StartTime:  "));
		myPanel.add(startField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("EndTime:  "));
		myPanel.add(endField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Title:  "));
		myPanel.add(titleField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("User:  "));
		myPanel.add(userField);

		int result = JOptionPane.showConfirmDialog(null, myPanel,
				"Please Enter Starttime, Endtime and Title",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			// mang.addEvent(mang.createEvent(userField.getText(),
			// titleField.getText(), date, startField.getText(),
			// endField.getText()));

			GuiEvent event = new GuiEvent();
			this.add(event);

		}
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] arg) {
		GuiOrg org = new GuiOrg();
		GuiEvent event1 = new GuiEvent();
		GuiEvent event2 = new GuiEvent();
		GuiEvent event3 = new GuiEvent();
		org.addNewEvent(event1);
		org.addNewEvent(event2);
		org.addNewEvent(event3);
	}
}