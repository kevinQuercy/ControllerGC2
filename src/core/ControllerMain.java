package core;

import java.util.logging.Logger;

/** @file
 * 
 * main function, starting point of the controller application
 *
 *todel
 */

public class ControllerMain {
	private static Logger LOGGER = Logger.getLogger(ControllerMain.class.getName());

	public static void main(String[] args) {
		LOGGER.info("Starting");
		int server_port = 10000; // default
		
		if (args.length == 1)
		{
			// override with parameters from command line
			server_port = Integer.parseInt(args[0]);
		}
		
		SocketManager socketMan = new SocketManager(server_port);
		socketMan.run();
	}

}
