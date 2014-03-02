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

	public String[] getListOfUsers() {
		String[] listOfUsers = userNames.split(",");
		return listOfUsers;
	}
	public java.util.List<String> List(){
		return Arrays.asList(getListOfUsers());
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setDate(String newDate) {
		formatter =  new SimpleDateFormat("yyyy-MM-dd");
		try {
			date2 =(Date) formatter.parse(newDate);
		} catch (ParseException e) {
			System.out.println("Wrong Format on Date!");
		}
		date=newDate;
	}

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

	public String getHTMLString() {

		return "<html> " + startTime + "  " + endTime + "<br /> " + title
				+ "<br /> " + userNames + "</html>";
	}

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

	public void addUser(String userToAdd) {
		if(userNames==null || userNames.equals(""))
			userNames=userToAdd;
		else
			userNames += ("," + userToAdd);
	}

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
