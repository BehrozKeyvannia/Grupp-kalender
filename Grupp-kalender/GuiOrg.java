package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import Engine.Event;
import Engine.EventManager;


@SuppressWarnings("serial")
public class GuiOrg extends JFrame{
	/*
	 * @Author
	 */

	// Create the menu bar.
	private GuiMenu menu = new GuiMenu();
	private EventManager mang;
	String date = null;

	public GuiOrg(EventManager e) {
		/**
         * 
         */
		setTitle("GUI");

		mang = e;

		/**
		 * Tar in dagens datum från Calender och gör om den till ett enklare
		 * format
		 */

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		date = format1.format(cal.getTime());

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel("<html><div style=\"text-align: center;\">" + date
				+ "<html/>", JLabel.CENTER));
		Container c = getContentPane();
		c.add(menu, BorderLayout.NORTH);
		c.add(this);
		pack();
		setVisible(true);
		c.setSize(300, 300);
	}

	/**
	 * Skapar nytt fönster med alla inmatningsrutor för ett nytt event
	 * 
	 */
	public void newEventWindow() {
		Event temp;
		JTextField startField = new JTextField(5);
		JTextField endField = new JTextField(5);
		JTextField titleField = new JTextField(5);
		JTextField descField = new JTextField(5);
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
		myPanel.add(new JLabel("Description:  "));
		myPanel.add(descField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("User:  "));
		myPanel.add(userField);

		int result = JOptionPane.showConfirmDialog(null, myPanel,
				"Please Enter Starttime, Endtime, Title, Description and User(s)",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			temp = mang.createEvent(userField.getText(),
					titleField.getText(), date, startField.getText(),
					endField.getText());
			mang.addEvent(temp);
			
	//		newEventJButton(mang.getEvent(userField.getText(),
	//				mang.geteventID()));
			/**
			 * Skapar ny Knapp med Eventet som vi just skapade som inmatning
			 * Eventet hittas genom user infon som angavs och eventets hashCode.
			 */
		}
	}


	public static void main(String[] arg) {
		new GuiOrg(new EventManager());
	}
}