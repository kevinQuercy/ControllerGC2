package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @file
 * 
 * Manages server socket
 * Inspired from: http://cs.lmu.edu/~ray/notes/javanetexamples/#capitalize
 * 
 * Start a socket server, accept connections and delegates management of this
 * client socket to SocketClientHandler
 */

public class SocketManager {
	private static Logger LOGGER = Logger.getLogger(SocketManager.class.getName());
	
	private int port;

	public SocketManager(int port) {
		super();
		this.port = port;
	}
	
	public void run() {
	    int clientNumber = 0;
	    ServerSocket listener = null;
        try {
        	LOGGER.info("Opening server socket on port #"+port);
			listener = new ServerSocket(port);
			while(true) {
				new SocketClientHandler(listener.accept(), clientNumber++).start();
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error during server socket management", e);
		} finally {
			if (listener != null)
				try {
					listener.close();
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "Error while closing server socket", e);
				}
		}
	}
}
