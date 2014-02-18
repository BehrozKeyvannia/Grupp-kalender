import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

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
     * Creates and returns a Event with the specified info
     */
    public Event createEvent(String userName, String title, String date, String startTime, String endTime){
    	eventID++;
    	return new Event(eventID, userName, title, date, startTime, endTime);	
    }
    /**
    * Adds an event to a user's Eventlist
    * @param event, the event that will be added
    */
    public void addEvent(Event event)
    {
       hMap.put(event.getUserName(),eventList);
       hMap.get(event.getUserName()).add(event);
    }
    
    /**
     * 
     * @param user Vilken användares lista som kmr sökas
     * @param id Eventets ID
     * @return Eventet som söks
     */
    public Event getEvent(String user,int id){
    	
    	LinkedList<Event> temp = hMap.get(user);
    	Event tempEvent;
    	
    	//Gå igenom användares lista o hitta rätt event id
    	Iterator<Event> tempIterator = temp.iterator();
    	 while (tempIterator.hasNext()) 
         {
    		 tempEvent=tempIterator.next();
             if(tempEvent.getID()==id)   
            	 return tempEvent;
         }
    	 //Om inget event hittas returnas null
    	return null;
    }
    
    public int geteventID(){
    	return eventID;
    }
    
}
