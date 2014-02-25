/**
* Create an event and its' contends.
*
* @author (Ashor, Akar och Ali)
* @version 1
*/

public class Event
{
    final int ID;
    String userName;
    String title;
    String date;
    String startTime;
    String endTime;

    /**
    * Constractor for an event
    * @param ID an unique id for event
    *        username of the one who created the event
    *        title of the event
    *        date when the event will be created
    *        start of the event
    *        end time of the event
    */
    public Event(int ID, String userName, String title, String date, String startTime, String endTime)
    {
       this.ID = ID;
       this.userName = userName;
       this.title = title;
       this.date = date;
       this.startTime = startTime;
       this.endTime = endTime;

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

    public int getID()
    {
       return ID;
    }

    public String getUserName()
    {
       return userName;
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
    	
    	return "<html> "+ startTime+ "  " + endTime+"<br /> "+title+ "<br /> "+userName+"</html>";
    }

}
