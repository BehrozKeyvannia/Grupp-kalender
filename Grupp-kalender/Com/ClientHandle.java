package Com;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Engine.Event;

public class ClientHandle implements Runnable {
	public Thread aktivitet = new Thread(this);
	Socket clientSocket;
	DataInputStream streamIn;
	DataOutputStream streamOut;
	private ArrayList<Socket> socketList;

	public ClientHandle(Socket s, ArrayList<Socket> socketList)
			throws IOException { // Contractor
		this.clientSocket = s;
		this.socketList = socketList;
		streamIn = new DataInputStream(clientSocket.getInputStream());
		//streamOut = new DataOutputStream(clientSocket.getOutputStream());
		aktivitet.start();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(streamIn); // Receive
																			// a
																			// list
																			// from
																			// a
																			// client
				LinkedList<Event> eventList = (LinkedList<Event>) ois
						.readObject();
				if (eventList == null)
					break;
				for (Socket s : socketList) { // Send it to all of clients which
					streamOut = new DataOutputStream(s.getOutputStream());							// is connected
					ObjectOutputStream oos = new ObjectOutputStream(streamOut);
					oos.writeObject(eventList);
					oos.close();
				}
				ois.close();
				streamIn.close();
				streamOut.close();
			} catch (IOException e) {
				break;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * The private method change one LinkedList<Event> to bytes private byte[]
	 * serialize(LinkedList<Event> eventList) throws IOException {
	 * ByteArrayOutputStream out = new ByteArrayOutputStream();
	 * ObjectOutputStream os = new ObjectOutputStream(out);
	 * os.writeObject(eventList); return out.toByteArray(); } //The private
	 * method change bytes to LinkedList<Event> private LinkedList<Event>
	 * deserialize(byte[] data) throws IOException, ClassNotFoundException {
	 * ByteArrayInputStream in = new ByteArrayInputStream(data);
	 * ObjectInputStream is = new ObjectInputStream(in); return
	 * (LinkedList<Event>) is.readObject(); }
	 */
}