/**
 * 
 */
package Engine;
import Com.EventCom;
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
		// new EventCom(manager.getEventList());
	}

}
