package Engine;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;


/**
 * Represents a singular event and contains info of the event and the users attending the event.
 * 
 * @author (Ashor, Akar, Ali och Rami)
 * @version 1
 */

public class Event implements Comparable<Event>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userNames;
	private String title;
	private String date;
	private Date date2;
	private String startTime;
	private String endTime;
	private String description;
	private DateFormat formatter;

	/**
	 * Create an event with parameter values. Formats the yyyy-MM-dd date and HH:mm startTime as a Date object.
	 * @param userName
	 * @param title
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param description
	 * @throws ParseException
	 * 
	 */
	public Event(String userNames, String title, String date, String startTime,
			String endTime, String description) {
		formatter =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			this.date2 = (Date) formatter.parse(date+" "+startTime);
		} catch (ParseException e) {
			System.out.println("Wrong Format on date!");
		}
		this.date = date;
		this.userNames = userNames;
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}
	/**
	 * Create a String array of the userNames splitted by comma ',' and returns it
	 */
	public String[] getListOfUsers() {
		String[] listOfUsers = userNames.split(",");
		return listOfUsers;
	}
	/**
	 * Returns the string array provided by 'getListOfUsers' method as a Arraylist
	 */
	public java.util.List<String> List(){
		return Arrays.asList(getListOfUsers());
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	/**
	 * Formats the String newDate to Date object and sets it to the events date
	 */
	public void setDate(String newDate) {
		formatter =  new SimpleDateFormat("yyyy-MM-dd");
		try {
			date2 =(Date) formatter.parse(newDate);
		} catch (ParseException e) {
			System.out.println("Wrong Format on Date!");
		}
		date=newDate;
	}
	/**
	 * Formats the String newStartTime to Date object and sets it to Event date
	 */
	public void setStartTime(String newStartTime) {
		formatter =  new SimpleDateFormat("HH:mm");
		try {
			date2 =(Date) formatter.parse(newStartTime);
		} catch (ParseException e) {
			System.out.println("Wrong Format on Time!");
		}
		startTime = newStartTime;
	}

	public void setEndTime(String newStartTime) {
		endTime = newStartTime;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}

	public String getUserNames() {
		return userNames;
	}

	public String getTitle() {
		return title;
	}

	public String getDate() {
		return date;
	}

	public Date getDate2(){
		return date2;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
	
	public String getDescription() {
		return description;
	}
	/**
	 * Returns a String with HTML code for JComponents. /n doesnt work with JComponents. Instead we use <br />
	 */
	public String getHTMLString() {

		return "<html> " + startTime + "  " + endTime + "<br /> " + title
				+ "<br /> " + userNames + "</html>";
	}
	/**
	 * Removes the User from userNames
	 */
	public void removeUser(String userToRemove) {
		if(List().contains(userToRemove)){
			if(List().size()==1)
				userNames = userNames.replace( userToRemove, "");
			else if(List().indexOf(userToRemove)==List().size()-1)
				userNames = userNames.replace("," + userToRemove, "");
			else
				userNames = userNames.replace(userToRemove + ",", "");
		}
	}
	/**
	 * Adds the user to userNames
	 */
	public void addUser(String userToAdd) {
		if(userNames==null || userNames.equals(""))
			userNames=userToAdd;
		else
			userNames += ("," + userToAdd);
	}
	
	/**
	 * Compares current and parameter Event by Dates. If o is past <0 is returned. If o is equal 0 is returned. If o is future date >0 is returned.
	 */
	@Override
	public int compareTo(Event o) {
		return getDate2().compareTo(o.getDate2());
	}
	@Override
	public int hashCode() {
        return title.hashCode()+
            date.hashCode()+
            startTime.hashCode()+
            endTime.hashCode()+
            description.hashCode();
    }
    /**
     * Title, date, startTime, endTime and descprition are compared.
     */
	@Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Event))
            return false;

        Event rhs = (Event) obj;
        return 
            (title.equals(rhs.title)&&
            date.equals(rhs.date)&&
            startTime.equals(rhs.startTime)&&
            endTime.equals(rhs.endTime)&&
            description.equals(rhs.description));
    }
}
