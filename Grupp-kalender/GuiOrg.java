package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import Engine.Event;
import Engine.EventManager;


@SuppressWarnings("serial")
public class GuiOrg extends JFrame implements ActionListener {
	/*
	 * @Author
	 */

	// Create the menu bar.
	private GuiMenu menu = new GuiMenu();
	private JPanel pan = new JPanel();
	private JButton[] b = new JButton[15];
	int i = 0;
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

		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.add(new JLabel("<html><div style=\"text-align: center;\">" + date
				+ "<html/>", JLabel.CENTER));
		Container c = getContentPane();
		c.add(menu, BorderLayout.NORTH);
		c.add(pan);
		pack();
		setVisible(true);
		c.setSize(300, 300);
	}

	/**
	 * Om nåt klickas
	 */
	public void actionPerformed(ActionEvent e) {
		for (int index = 0; index < i; index++) {
			if (e.getSource() == b[index]) {

				// Har ett problem här! Behöver assiocera knappen till ett event
				// så jag kan ta själva eventets variabler istället för knappens
				// text
				// Antingen så kan man ta knappens text, hämta infot därifrån
				// genom att separerare HTML koden och sen leta upp eventet i
				// Hashmappen hMap
				// Eller så kan eventID och användaren sparas på nåt annat sätt
				// i själva JButtonen. Vet inte hur
				// Eller så gör vi en tabell som håller reda på vilken knapp har
				// vilket event?
				// Need help!
				System.out.println(b[index].getText());
				break;
			}
		}
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
			mang.addEvent(mang.createEvent(userField.getText(),
					titleField.getText(), date, startField.getText(),
					endField.getText()));

			newEventJButton(mang.getEvent(userField.getText(),
					mang.geteventID()));
			/**
			 * Skapar ny Knapp med Eventet som vi just skapade som inmatning
			 * Eventet hittas genom user infon som angavs och mang's
			 * eventvärde.Låter detta bra?
			 */
		}
	}

	/**
	 * Metod som tar in ett Event och skapar en aktivt lyssnade JButton på
	 * fönster med Eventets info Vet inte varför jag har dem i en array än men
	 * kan vara användbart
	 * */
	public void newEventJButton(Event DasEvent) {
		b[i] = new JButton();
		b[i].setText(DasEvent.getHTMLString());
		b[i].addActionListener(this);
		pan.add(b[i++]);
		pack();
	}

	public static void main(String[] arg) {
		new GuiOrg(new EventManager());
	}
}