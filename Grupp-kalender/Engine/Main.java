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
		
		EventManager manager = new EventManager();
		GuiOrg org = new GuiOrg(manager);
		manager.addObserver(org);
	}

}
