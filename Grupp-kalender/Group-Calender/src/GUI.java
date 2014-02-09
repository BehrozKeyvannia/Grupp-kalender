import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GUI extends JFrame implements ActionListener {
    /*
     * @Author  Rami Jabor
     */

    //Create the menu bar.
    private JMenuItem menuItem = new JMenuItem("Create new eventuuu!");
    private JMenuBar menuBar  = new JMenuBar();
    private JPanel pan = new JPanel();
    private JButton[] b = new JButton[15];
    int i=0;
    public GUI() {
        /**
         * @param lolol
         */
        setTitle("Meny");

        //Build the first menu.
        JMenu menu = new JMenu("Meny");
        menu.setMnemonic(KeyEvent.VK_A);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(cal.getTime());

        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setToolTipText("Create new event!");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuBar.add(menu);
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.add(new JLabel("<html><div style=\"text-align: center;\">" + formatted + "<html/>" ,JLabel.CENTER));
        Container c = getContentPane();
        c.add(menuBar, BorderLayout.NORTH);
        c.add(pan);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem) 
            newEventWindow();
        else{
            for(int index=0;index<i;index++){
                if (e.getSource() == b[index]){
                    System.out.println(b[index].getText());
                    break;
                }      
            }
        }
    }

    public void newEventWindow(){
        JTextField startField = new JTextField(5);
        JTextField endField = new JTextField(5);
        JTextField titleField = new JTextField(5);
        JTextField userField = new JTextField(5);

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
                "Please Enter Starttime, Endtime and Title", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            newEventJButton("<html> "+ startField.getText()+ "  " + endField.getText()+"<br /> "+titleField.getText()+ "<br /> "+userField.getText()+"</html>");
        }
    }

    public void newEventJButton(String text){
        b[i] = new JButton();
        b[i].setText(text);
        b[i].addActionListener(this);
        pan.add(b[i++]);
        pack();
    }

    public static void main(String[] arg) {
        GUI s = new GUI();
    }
}
