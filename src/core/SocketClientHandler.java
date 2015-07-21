package core;

import java.net.Socket;
import java.util.logging.Logger;

import org.jdom2.Document;

/** @file
 * 
 * Manages socket connected to one client
 * Inspired from: http://cs.lmu.edu/~ray/notes/javanetexamples/#capitalize
 *
 * Start a new thread to deal with the connected client
 * 
 * Messages are sent in XML format, on one or several lines; an empty line is used
 * as a separator to know that the full XML message has been received.
 * (if socket stream directly provided to SAXBuilder().build(), SAXbuilder waits for EOF).
 */

public class SocketClientHandler extends Thread {
	private static Logger LOGGER = Logger.getLogger(SocketClientHandler.class.getName());

	XMLSocket xmlsocket;
	int clientNumber;
	
	public SocketClientHandler(Socket socket, int clientNumber) {
		super();
		this.xmlsocket = new XMLSocket(socket);
		this.clientNumber = clientNumber;
		LOGGER.info("New client connected: #"+clientNumber+", "+socket);
	}
	
	public void run() {
    	RequestHandler reqHandler = new RequestHandler(clientNumber);
    	
		// process all requests from client
    	while(!xmlsocket.isClosed()) {
    		// get XML request
    		Document request = xmlsocket.read();
    		if (request == null)
    				break;
    		
    		// process it and get associated response
    		Document response = reqHandler.handle(request);
    		
    		// send XML response
    		xmlsocket.write(response);
        }
    	xmlsocket.close();
    }
}
