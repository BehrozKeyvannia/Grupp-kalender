package Engine;

/**
* Create an event and its' contends.
*
* @author (Ashor, Akar, Ali och Rami)
* @version 1
*/

public class Event
{
    String userNames;
    String title;
    String date; //"yyyy-MM-dd"
    String startTime;
    String endTime;
    
    /**
     * 
     * @param ID
     * @param userName
     * @param title
     * @param date
     * @param startTime
     * @param endTime
     */
    public Event(String userName, String title, String date, String startTime, String endTime)
    {
       this.userNames = userName;
       this.title = title;
       this.date = date;
       this.startTime = startTime;
       this.endTime = endTime;

    }

    public String[] getListOfUsers()
    {
        String[] listOfUsers = userNames.split(",");
        return listOfUsers;
    }
    public void setTitle(String newTitle)
    {
       title = newTitle;
    }

    public void setDate(String newDate)
    {
       date = newDate;
    }

    public void setStartTime(String newStartTime)
    {
       startTime = newStartTime;
    }

    public void setEndTime(String newStartTime)
    {
       endTime = newStartTime;
    }

    public String getUserName()
    {
       return userNames;
    }

    public String getTitle()
    {
       return title;
    }

    public String getDate()
    {
       return date;
    }
    public String getStartTime()
    {
       return startTime;
    }

    public String getEndTime()
    {
       return endTime;
    }
    public String getHTMLString(){
    	
    	return "<html> "+ startTime+ "  " + endTime+"<br /> "+title+ "<br /> "+userNames+"</html>";
    }
    //Must fix specialcase here: If the userToRemove is first in userNames 
    public void removeUser(String userToRemove){
    	userNames.replace(","+userToRemove, "");
    	userNames.replace(userToRemove+",", "");
    }
    //Must fix specialcase here: If the list is empty
    public void addUser(String userToAdd){
    	userNames += ("," + userToAdd);
    }

}
