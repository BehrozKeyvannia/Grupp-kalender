package gruppKalender;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;

/*
 * Author: Sou
 * Version: 0.0
 * Date: 2014-02-19
 * InAndOut: LinkedList<Event>
 * OBS ! 1-  Port in the server will be 9999
 *       2- Port in clients will be 9963
 *       3- IP address in the server will be 69.122.0.1
 *       4- Objects must be serializable 
 *       5- When will be close client and server sockets ? 
 */
public class EventCom{
	private static int listenerPort = 9999;
	private static String serverInetAdr = "69.122.0.1";
	private ArrayList<Socket> socketList;
	private LinkedList<Event> eventList;
	
	//Constructors
    public EventCom(LinkedList<Event> eventList) throws IOException, Exception, Throwable{  
    	socketList = new ArrayList<Socket>();
    	this.eventList = eventList;
    	serverOrClient();
    }
    
    //Choose between server and client side
    private void serverOrClient(){
    	try{
	    	if(InetAddress.getLocalHost().getHostAddress().equals(serverInetAdr))	//Is this server's socket? 
					serverSide();
			else
	    			clientSide(eventList);
    	}catch (Throwable e) {e.printStackTrace();}
    }
    
    //Server side method 
	private void serverSide(){
		try{
		ServerSocket listenerSocket = new ServerSocket(listenerPort); //Make a socket and listen to clients
			while(true){
	    	   Socket clientSocket = listenerSocket.accept(); 			//Wait to next client
	    	   socketList.add(clientSocket); 							//Save all of client's sockets 
	    	   new clientHandle(clientSocket, socketList);				//Make an active object from client
	    	   //????  clientSocket.close();
			}
		}
		catch(Throwable e){e.printStackTrace();}
					
     }
	
	//Client side method
    private LinkedList<Event> clientSide(LinkedList<Event> eventList) {
    	LinkedList<Event> newEventList = new LinkedList<Event>();		
    	try {
			Socket clientSo = new Socket(serverInetAdr, listenerPort);			//Make a connection to server
			DataInputStream streamIn = new DataInputStream (clientSo.getInputStream()); 
			DataOutputStream streamOut = new DataOutputStream(clientSo.getOutputStream());
			while(true){
				ObjectOutputStream oos = new ObjectOutputStream(streamOut);		//Send the event list to server
				oos.writeObject(eventList);
				ObjectInputStream ois = new ObjectInputStream(streamIn);		//Receive the event list from server
				newEventList = (LinkedList<Event>)ois.readObject();
			}
		} catch (IOException|ClassNotFoundException e) {e.printStackTrace();}
		return newEventList;
   	}
}

