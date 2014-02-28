package Com;

import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;

import Engine.Event;

/*
 * Author: Sou
 * Version: 0.0
 * Date: 2014-02-19
 * InAndOut: LinkedList<Event>
 * OBS ! 1-  Port in the server will be 9999
 *       2- Port in clients can be whatever
 *       3- IP address in the server will be 192.168.1.1
 *       4- Objects must be serializable 
 *       5- When will be close client and server sockets ? 
 */
public class EventCom {
	private static int listenerPort = 9999;
	private static String serverInetAdr = "192.168.1.6";
	private ArrayList<Socket> socketList;
	private LinkedList<Event> eventList; 
	
	// Constructors
	public EventCom(LinkedList<Event> eventList) throws IOException, Exception,
			Throwable {
		socketList = new ArrayList<Socket>();
		this.eventList = eventList;
	}

	// Choose between server and client side
	public LinkedList<Event> serverOrClient() {
		try {
			if (InetAddress.getLocalHost().getHostAddress().equals(serverInetAdr)) // Is this server's socket?
				serverSide();
			else
				return clientSide(eventList);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	// Server side method
	private void serverSide() {
		try {
			ServerSocket listenerSocket = new ServerSocket(listenerPort); // Make a socket and listen to clients
																			
			while (true) {
				Socket clientSocket = listenerSocket.accept(); // Wait to next client			
				socketList.add(clientSocket); // Save all of client's sockets
				new ClientHandle(clientSocket, socketList); // Make an active object of every client
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	// Client side method
	@SuppressWarnings("unchecked")
	public LinkedList<Event> clientSide(LinkedList<Event> eventList) throws ClassNotFoundException {
		try {
			
			Socket clientSo = new Socket(serverInetAdr, listenerPort); // Make a connection to server
			DataInputStream streamIn = new DataInputStream(clientSo.getInputStream());
			DataOutputStream streamOut = new DataOutputStream(clientSo.getOutputStream());
			
			System.out.println("I have entered the clientside (1)");
				ObjectOutputStream oos = new ObjectOutputStream(streamOut); // Send the event list to server
				oos.writeObject(eventList);															
				System.out.println("I have sent the object");
																			
				
				ObjectInputStream ois = new ObjectInputStream(streamIn); // Receive the event list from server															
				return (LinkedList<Event>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
