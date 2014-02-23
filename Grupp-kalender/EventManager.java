package Engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Manage the events
 * 
 * @author (Ashor, Akar, Ali och Rami)
 * @version 1
 */
public class EventManager {
	private HashMap<String, LinkedList<Event>> hMap;
	private LinkedList<Event> eventList;

	/**
	 * Constructor for objects of class EventManager
	 */
	public EventManager() {
		hMap = new HashMap<String, LinkedList<Event>>();
		eventList = new LinkedList<Event>();
	}

	/**
	 * Creates and returns a Event with the specified info
	 * 
	 * @param description
	 */
	public Event createNewEvent(String userNames, String title, String date,
			String startTime, String endTime, String description) {
		Event temp = new Event(userNames, title, date, startTime, endTime,
				description);
		return temp;
	}

	/**
	 * Adds an event to a user's Event list
	 * 
	 * @param event
	 *            The event that will be added
	 */
	public void addEventList(LinkedList<Event> list) {
		Iterator<Event> tempIterator = list.iterator();
		while (tempIterator.hasNext()) {
			addEvent(tempIterator.next());
		}
	}

	public void sortEventList() {
		Collections.sort(eventList);
	}

	public Event addEvent(Event event) {
		if (!eventList.contains(event))
			eventList.addLast(event);
		String[] list = event.getListOfUsers();
		for (int i = 0; i < list.length; i++) {
			if (!hMap.containsKey(list[i])) {
				hMap.put(list[i], new LinkedList<Event>());
			}
			hMap.get(list[i]).add(eventList.getLast());
		}
		return event;
	}

	/**
	 * 
	 * @param user
	 *            Vilken användares lista som kmr sökas
	 * @param id
	 *            Eventets ID
	 * @return Eventet som söks
	 */
	public Event getEvent(String user, int hashCode) {
		// Gå igenom användares lista o hitta rätt event id
		return searchEvent(hMap.get(user), hashCode);

	}

	public Event getEventByID(int hashCode) {
		return searchEvent(eventList, hashCode);
	}

	private Event searchEvent(LinkedList<Event> list, int hashCode) {
		Event tempEvent;
		Iterator<Event> tempIterator = list.iterator();
		while (tempIterator.hasNext()) {
			tempEvent = tempIterator.next();
			if (tempEvent.hashCode() == hashCode)
				return tempEvent;
		}
		// Inget event hittas returnas null
		return null;
	}

	public void removeUserFromEvent(String user, int hashCode) {
		if (!hMap.containsKey(user))
			if (hMap.get(user).remove(getEvent(user, hashCode)))
				getEventByID(hashCode).removeUser(user);
	}

	public void removeEvent(int hashCode) {
		Event temp = getEventByID(hashCode);
		if (temp != null) {
			String[] list = temp.getListOfUsers();
			for (int i = 0; i < list.length; i++)
				removeUserFromEvent(list[i], hashCode);
			if (!eventList.contains(temp))
				eventList.remove(temp);
		}
	}

	public static void main(String[] arg) {
		EventManager e = new EventManager();
		e.addEvent(e.createNewEvent("Rami,Behroz,Akar", "Go fuck yourself",
				"2014-05-14", "20:00", "21:00", "Bajsa"));
		e.addEvent(e.createNewEvent("Rami", "Shittin", "2014-05-13", "20:00",
				"21:00", "Promenad"));
		e.addEvent(e.createNewEvent("Behroz", "pppfffffft", "2014-05-15",
				"20:00", "21:00", "Hälsa"));
		e.addEvent(e.createNewEvent("Ashor,Stefan", "Haj Haj", "2014-05-12",
				"20:30", "21:00", "Plugga"));
		int hash = e.addEvent(
				e.createNewEvent("Josef Stalin,Hitler,Mao", "Conquering world",
						"2014-05-12", "20:20", "21:00",
						"Sending people to Gulag")).hashCode();
		e.sortEventList();
		e.removeEvent(hash);
	}
}