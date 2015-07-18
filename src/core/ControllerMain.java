package core;

import java.util.logging.Logger;

/** @file
 * 
 * main function, starting point of the controller application
 *
 */

public class ControllerMain {
	private static Logger LOGGER = Logger.getLogger(ControllerMain.class.getName());

	public static void main(String[] args) {
		LOGGER.info("Starting");
		SocketManager socketMan = new SocketManager(10000);
		socketMan.run();
	}

}
