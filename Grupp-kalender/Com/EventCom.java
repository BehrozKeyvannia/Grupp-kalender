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
 *       3- IP address in the server will be 192.168.1.*
 *       4- Objects must be serializable 
 *       5- When will be close client and server sockets ? 
 */
public class EventCom {
	private static int listenerPort = 9999;
	private static String serverInetAdr = "192.168.1.4";
	private ArrayList<Socket> socketList;
	protected static LinkedList<Event> serverEventList = null; 
	
	/**
	 * Constructor
	 * @param eventList takes in the clients/servers eventList
	 */
	public EventCom(LinkedList<Event> eventList) throws IOException, Exception,
			Throwable {
		socketList = new ArrayList<Socket>();
		EventCom.serverEventList = eventList;
	}

	/**
	 * Finds out if the computer is a server and client
	 */
	public LinkedList<Event> serverOrClient() {
		try {
			if (InetAddress.getLocalHost().getHostAddress().equals(serverInetAdr)) // Is this server's socket?
				serverSide();
			else
				return clientSide(serverEventList);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ServerSide of communication. Listens to clients and adds the client to a list if it connects.
	 */
	private void serverSide() {
		try {
			ServerSocket listenerSocket = new ServerSocket(listenerPort); // Make a socket and listen to clients
			System.out.println("1 - I am now a server");
																			
			while (true) {
				Socket clientSocket = listenerSocket.accept(); // Wait to next client
				if(!(socketList.contains(clientSocket))){		//If there is not already in the list
				socketList.add(clientSocket); // Save all of client's sockets
				System.out.println("2 - I have accepte a client and save socket to my list");
				}
				new ClientHandle(clientSocket, socketList); // Make an active object of every client
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	/**
	 * Clientside of communication. Makes a connection to the predetermined host/server adress. Makes in- and outstreams
	 * and sends the eventlist to the server and receives a eventlist from the server. Finally closesall streams.
	 */
	@SuppressWarnings("unchecked")
	public LinkedList<Event> clientSide(LinkedList<Event> eventList) throws ClassNotFoundException {
		try {
			
			Socket clientSocket = new Socket(serverInetAdr, listenerPort); // Make a connection to server
			DataInputStream streamIn = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream streamOut = new DataOutputStream(clientSocket.getOutputStream());
			
			System.out.println("I have entered the clientside");
			ObjectOutputStream oos = new ObjectOutputStream(streamOut); // Send the event list to server
			oos.writeObject(eventList);															
			System.out.println("I have sent the object");
			ObjectInputStream ois = new ObjectInputStream(streamIn); // Receive the event list from server															
			System.out.println("I have recieved the object");
			LinkedList<Event> tmp = (LinkedList<Event>)ois.readObject();
			ois.close();
			oos.close();
			streamIn.close();
			streamOut.close();
			clientSocket.close();
			System.out.println("I have closed all streams");
			return tmp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
