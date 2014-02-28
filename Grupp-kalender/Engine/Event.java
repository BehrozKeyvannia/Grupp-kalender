package Engine;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;


/**
 * Create an event and its' contends.
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
	 * Create an event by user inputs
	 * @param userName
	 * @param title
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param description
	 * @throws ParseException 
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

 /*
     * A method for getting the users
     * 
     * @return an array of Strings that represent the users
     */
	public String[] getListOfUsers() {
		String[] listOfUsers = userNames.split(",");
		return listOfUsers;
	}
	
   /*
     * a method that returns a list of the users
     * 
     * @return the list to be returned
     */	
	public java.util.List<String> List(){
		return Arrays.asList(getListOfUsers());
	}

 /*
     * a method to change the title
     * 
     * @param newTitle the new title 
     */
	public void setTitle(String newTitle) {
		title = newTitle;
	}

 /*
     * a method to change the date
     * 
     * @param newDate the new date
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

/*
     * a method to change the start time
     * 
     * @param newStartTime the new start time
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

 /*
     * a method to change the end time
     * 
     * @param newStartTime the new end time
     */
	public void setEndTime(String newStartTime) {
		endTime = newStartTime;
	}
	
 /*
     * a method to change the description
     * 
     * @param newStartTime the new description
     */
	public void setDescription(String newDescription) {
		description = newDescription;
	}

   /*
     * a method to get the user names of the event
     * 
     * @return a string of user names
     */
	public String getUserNames() {
		return userNames;
	}

   /*
     * a method to get the title of the event
     * 
     * @return a string of the title
     */
	public String getTitle() {
		return title;
	}

   /*
     * a method to get the user date of the event
     * 
     * @return a string of the date
     */
	public String getDate() {
		return date;
	}

   /*
     * a method to get the date of the event
     * 
     * @return a Date-object(not string) of date
     */
	public Date getDate2(){
		return date2;
	}

   /*
     * a method to get the start time of the event
     * 
     * @return a string of the start time
     */
	public String getStartTime() {
		return startTime;
	}

   /*
     * a method to get the uend time of the event
     * 
     * @return a string of the end time
     */
	public String getEndTime() {
		return endTime;
	}
	
   /*
     * a method to get the description of the event
     * 
     * @return a string of the description
     */
	public String getDescription() {
		return description;
	}

   /*
     * a method to get info about start time, end time, title and user names of the event
     * 
     * @return a string of the info in HTML format
     */
	public String getHTMLString() {

		return "<html> " + startTime + "  " + endTime + "<br /> " + title
				+ "<br /> " + userNames + "</html>";
	}
	
   /*
     * a method that removes users from an event
     * 
     * @param userToRemove the users to remove
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

   /*
     * a method that adds users to an event
     * 
     * @param userToAdd the users to add
     */
	public void addUser(String userToAdd) {
		if(userNames==null || userNames.equals(""))
			userNames=userToAdd;
		else
			userNames += ("," + userToAdd);
	}

   /*
     * a method that compares this event's date with the given event's date
     * 
     * @param the event to be compared
     * @return an int that reflects the result of the comparison	
     */
	@Override
	public int compareTo(Event o) {
		return getDate2().compareTo(o.getDate2());
	}
}
