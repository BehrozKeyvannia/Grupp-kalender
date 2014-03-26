package Com;

import java.io.*;
import java.util.*;
import Engine.Event;
import java.lang.Exception;

/**
 * Filehandler for EventManager This class is responsible for handling the
 * filewriting and filereading for the whole application. This class is only
 * used by eventManager only. It contains two simple methods which is writing to
 * a file and reading from a file.
 * 
 * @author Akar
 * @version 1.0
 */
public class FileHandler {
	/**
	 * Method writeFile: Accepts a eventList as a parameter as the object that
	 * is going to be written to a file named "events.sav".
	 * 
	 * @param eventList
	 */
	public static void writeFile(LinkedList<Event> eventList) {
		try {
			ObjectOutputStream save = new ObjectOutputStream(
					new FileOutputStream("events.sav"));
			save.writeObject(eventList);
			save.close();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method readFile: this method reads the file that is written by the
	 * "writeFile" method. It returns the eventList that is written to the
	 * "events.sav" file.
	 * 
	 */
	public static LinkedList<Event> readFile() {
		LinkedList<Event> readObject = null;
		try {
			ObjectInputStream restore = new ObjectInputStream(
					new FileInputStream("events.sav"));
			readObject = (LinkedList<Event>) restore.readObject();
			restore.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return readObject;
	}

}
