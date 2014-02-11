import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
* Manage the events
*
* @author (Ashor, Akar och Ali)
* @version whogivesashit?
*/
public class EventManager
{
    private HashMap<String, LinkedList<Event>> hMap;
    private LinkedList<Event> eventList;
    int eventID = 0;

    /**
    * Constructor for objects of class EventManager
    */
    public EventManager()
    {
       hMap = new HashMap<String, LinkedList<Event>>();
       eventList = new LinkedList<Event>();
    }
   
    /**
    * Adds an event to a user's Eventlist
    * @param event, the event that will be added
    */
    public Event createEvent(String userName, String title, String date, String startTime, String endTime){
    	return new Event(eventID, userName, title, date, startTime, endTime);	
    }
    public void addEvent(Event event)
    {
       hMap.put(event.getUserName(),eventList);
       hMap.get(event.getUserName()).add(event);
    }
 /*   public Event getEvent(String user,int id){
    	
    }*/

}
