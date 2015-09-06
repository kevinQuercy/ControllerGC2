package core;

import java.util.logging.Logger;

import javax.xml.ws.Endpoint;

import webservice.WSConteneurImp;
import webservice.WSIlotImp;
import webservice.WSPlanificationImp;

/** @file
 * 
 * main function, starting point of the controller application
 * 
 */

public class ControllerMain {
	private static Logger LOGGER = Logger.getLogger(ControllerMain.class.getName());

	public static void main(String[] args) {
		LOGGER.info("Starting the SOAP Server on port 8081");
		Endpoint.publish("http://localhost:8081/WS/WSPlanification", new WSPlanificationImp());
		Endpoint.publish("http://localhost:8081/WS/WSIlot", new WSIlotImp());
		Endpoint.publish("http://localhost:8081/WS/WSConteneur", new WSConteneurImp());
		LOGGER.info("Starting");
		int server_port = 10000; // default
		
		if (args.length >= 1)
		{
			server_port = Integer.parseInt(args[0]);
		}
		
		SocketManager socketMan = new SocketManager(server_port);
		socketMan.run();
	}
}