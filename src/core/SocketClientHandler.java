package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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

	Socket socket;
	int clientNumber;
	
	public SocketClientHandler(Socket socket, int clientNumber) {
		super();
		this.socket = socket;
		this.clientNumber = clientNumber;
		LOGGER.info("New client connecter: #"+clientNumber+", "+socket);
	}
	
	public void run() {
        try {
        	SAXBuilder sxb = new SAXBuilder();
        	RequestHandler reqHandler = new RequestHandler(clientNumber);
        	
    		// process all requests from client
        	while(true) {
        		// get XML request (buffer lines until an empty line is found)
        		BufferedReader buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        		StringBuilder xmlRequest = new StringBuilder();
        		while (true)
        		{
        			String line = buffRead.readLine();
        			if (line == null || line.isEmpty())
        				break;
        			xmlRequest.append(line);
        		}
        		if (xmlRequest.length() == 0)
        			break; // no request, close socket
        		Document request = sxb.build(new StringReader(xmlRequest.toString()));
        		
        		// process it
        		Document response = reqHandler.handle(request);
        		
        		// send response
        		XMLOutputter xmlOutput = new XMLOutputter(Format.getCompactFormat());
        		xmlOutput.output(response, socket.getOutputStream());
        		socket.getOutputStream().write('\n'); // empty line to indicate end of response
            }
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "Client #"+clientNumber+" disconnected", e);
		} catch (JDOMException e) {
			LOGGER.log(Level.SEVERE, "Client #"+clientNumber+": invalid XML", e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Error while closing socket", e);
			}
		}
    }
}
