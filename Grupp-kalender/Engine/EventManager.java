package Engine;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import Com.FileHandler;

/**
 * Manages the events and handles every operation that has to do with Event
 * outside of the Event class. This class creates, finds, saves, sorts, adds,
 * organizes,and removes Events.
 * 
 * @author (Ashor, Akar, Ali och Rami)
 * @version 1
 */
public class EventManager extends Observable {
	private HashMap<String, LinkedList<Event>> hMap;
	private LinkedList<Event> eventList;

	/**
	 * Constructor for objects of class EventManager
	 */
	public EventManager() {
		hMap = new HashMap<String, LinkedList<Event>>();
		eventList = new LinkedList<Event>();
		readEvents();
	}

	/**
	 * 
	 * @return The eventList
	 */
	public LinkedList<Event> getEventList() {
		return eventList;
	}

	/**
	 * Creates and returns a Event with the specified info
	 * 
	 * @param userNames
	 * @param title
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param description
	 * @return the event
	 */
	public Event createNewEvent(String userNames, String title, String date,
			String startTime, String endTime, String description) {
		Event temp = new Event(userNames, title, date, startTime, endTime,
				description);
		return temp;
	}

	/**
	 * Adds all the events from a list to eventList
	 * 
	 * @param list
	 *            The list of events that will be added
	 */
	public void addEventList(LinkedList<Event> list) {
		Iterator<Event> tempIterator = list.iterator();
		while (tempIterator.hasNext()) {
			addEvent(tempIterator.next());
		}
	}

	/**
	 * Sorts all events in Event list in chronological order.
	 */
	public void sortEventList() {
		Collections.sort(eventList);
	}

	/**
	 * 
	 * Adds a event if it isn't already in eventList and adds the event in all
	 * the participants list in hMap. Notifies Observers
	 * 
	 * @param event
	 *            The event that will be added
	 * 
	 */
	public Event addEvent(Event event) {
		if (!eventList.contains(event)) {
			eventList.addLast(event);
			setChanged();
			notifyObservers();
		}

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
	 * @param hashCode
	 * @return the event
	 */
	public Event getEvent(String user, int hashCode) {
		return searchEvent(hMap.get(user), hashCode);

	}

	/**
	 * Searches for an Event in eventList and returnes the event with the
	 * specified hashCode
	 * 
	 * @param hashCode
	 *            hashCode to be searched
	 * @return Event If Event found it is returned else returns null.
	 * 
	 */
	public Event getEventByID(int hashCode) {
		return searchEvent(eventList, hashCode);
	}

	/**
	 * Searches through a list(param) for a Event with the specified
	 * hashCode(param)
	 * 
	 * @param list
	 *            List to be searched
	 * @param hashCode
	 *            hashCode to search after.
	 * 
	 * @return Event if found else null.
	 */
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

	/**
	 * Removes the EventReference with the specified hashCode in a user list and
	 * removes the users name in the event's userNames.
	 * 
	 * @param user
	 *            user to remove
	 * @param hashCode
	 *            Events hashcode which the user is removed from.
	 * 
	 */
	public void removeUserFromEvent(String user, int hashCode) {
		if (!hMap.containsKey(user))
			if (hMap.get(user).remove(getEvent(user, hashCode)))
				getEventByID(hashCode).removeUser(user);
	}

	/**
	 * Removes all users from the event (using removeUserFromEvent method) and
	 * then removes/deletes the Event. Notifies Observers
	 * 
	 * @param The
	 *            Event's hashCode to be removed
	 */
	public void removeEvent(int hashCode) {
		Event temp = getEventByID(hashCode);
		if (temp != null) {
			String[] list = temp.getListOfUsers();
			for (int i = 0; i < list.length; i++) {
				removeUserFromEvent(list[i], hashCode);
			}
			if (eventList.contains(temp)) {
				eventList.remove(temp);
				setChanged();
				notifyObservers();
			}

		}
	}

	/**
	 * Removes all events and notifies observers
	 * 
	 */
	public void removeAllEvents() {
		for (LinkedList<Event> list : hMap.values()) {
			list.clear();
		}
		eventList.clear();
		setChanged();
		notifyObservers();
	}

	/**
	 * Removes all Event with dates that are before todays date
	 */
	public void removePastEvents() {
		Iterator<Event> iterator = getEventListIterator();
		Date date = new Date();
		while (iterator.hasNext()) {
			Event temp = iterator.next();
			if (date.compareTo(temp.getDate2()) < 0)
				break;
			String[] x = temp.getListOfUsers();
			for (int i = 0; i < x.length; i++)
				removeUserFromEvent(x[i], temp.hashCode());
			iterator.remove();

		}

	}

	/**
	 * Sorts eventList and returns a Iterator of eventlist
	 */
	public Iterator<Event> getEventListIterator() {
		sortEventList();
		return eventList.iterator();
	}

	/**
	 * Saves all events to a local Event.sav file
	 */
	public void saveEvents() {
		FileHandler.writeFile(eventList);
	}

	/**
	 * Reads from local Event.sav file and adds all events that are not already
	 * there. If it doesn't exist throws/catches IOexception Is only called once
	 * during initation in constuctor.
	 */
	public void readEvents() {
		LinkedList<Event> control = FileHandler.readFile();
		if (control != null)
			addEventList(control); // FÅR ENDAST ANVÄNDAS VID APPLIKATIONENS
									// START
	}
}
