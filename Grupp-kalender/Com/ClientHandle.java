package Com;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Engine.Event;

	/**
	 * ClientHandle - a class that is used by EventCom to make a profile for every client that connects to the server. 
	 * This class is build to every client and is keeping them updated on which events that is on the eventlist that is shared with all of the clients.
	 */
public class ClientHandle implements Runnable {
	public Thread aktivitet = new Thread(this);
	Socket clientSocket;
	DataInputStream streamIn;
	DataOutputStream streamOut;
	private ArrayList<Socket> socketList;
	
	/**
	 * Constructor takes the socket of the client and a socketlist as parameters and the socketlist is a list of all of the sockets which are active.
	 * This is used to update every socket that is in the list which gives the result that every client can see one common eventlist.
	 * 
	 * @param s the clients socket to recieve from.
	 * @param socketlist clientlist of sockets to send to.
	 */
	public ClientHandle(Socket s, ArrayList<Socket> socketList)
			throws IOException { 
		this.clientSocket = s;
		this.socketList = socketList;
		streamIn = new DataInputStream(clientSocket.getInputStream());
		streamOut = new DataOutputStream(clientSocket.getOutputStream());
		aktivitet.start();
	}

	/**
	 * Clienthandle thread code. Server recieves a list from the client. Server sends the same list + all of the previous lists it has recieved
	 * + the servers own starting list and sends that to all saved clientsockets in socketList. Finally closes all streams
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(streamIn); // Receive a list from the client
				LinkedList<Event> newEventList = (LinkedList<Event>) ois.readObject();
				if (newEventList == null)
					break;
			    for(Event newEvent : newEventList) { //Update the list with new events 
			        if(!(EventCom.serverEventList.contains(newEvent))){
			                EventCom.serverEventList.add(newEvent);
			            }
			        }
				System.out.println("3 - I have managed a InputStream and read an object");
				for (Socket s : socketList) { // Send it to all of clients which is connected					
					ObjectOutputStream oos = new ObjectOutputStream(streamOut);
					oos.writeObject(EventCom.serverEventList);
					System.out.println(" 4- I have written event list to outputStream");
					oos.close();
					s.close();
					
				}
				ois.close();
				streamIn.close();
				streamOut.close();
				clientSocket.close();
				System.out.println("5 - Finish !");
			} catch (IOException e) {
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
