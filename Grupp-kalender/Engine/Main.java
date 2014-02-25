/**
 * 
 */
package Engine;

import java.io.IOException;

import Com.ClientHandle;
import Com.EventCom;
import Com.FileHandler;
import GUI.GuiOrg;

/**
 * @author root
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws Throwable 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception, Throwable {
		
		try{
			//ClientHandle handle = new ClientHandle();
			FileHandler fileHandler = new FileHandler();
			EventManager manager = new EventManager();
			//GuiOrg org = new GuiOrg(manager);
			
			EventCom com = new EventCom(manager.getEventList());
			
		} catch(IOException e )
		
		{
			System.out.println(e.getMessage());
		}
		

	}

}
