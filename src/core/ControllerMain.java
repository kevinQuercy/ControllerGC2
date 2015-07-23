package core;

import graph.Graph;

import java.util.Random;
import java.util.logging.Logger;

import data.Container;
import data.ContainerSet;
import data.ContainerSystem;
import data.GeoCoordinate;

/** @file
 * 
 * main function, starting point of the controller application
 * 
 */

public class ControllerMain {
	private static Logger LOGGER = Logger.getLogger(ControllerMain.class.getName());

	public static void main(String[] args) {
		LOGGER.info("Starting");
		int server_port = 10000; // default
		int nbContainers = 50; // default
		
		if (args.length >= 1)
		{
			// override with parameters from command line
			nbContainers = Integer.parseInt(args[0]);
			if (args.length >= 2)
			{
				server_port = Integer.parseInt(args[1]);
			}
		}
		
		createContainerSystem(nbContainers);
		
		SocketManager socketMan = new SocketManager(server_port);
		socketMan.run();
	}
	
	private static final GeoCoordinate reference = new GeoCoordinate(43.602704, 1.441745); // Toulouse center
	private static final double referenceLatSpan = 0.039;
	private static final double referenceLongSpan = 0.054;
	private static final double radius = 4500; // metres
	
	private static void createContainerSystem(int nbContainers) {
		ContainerSystem cs = ContainerSystem.getContainerSystem();
		Random random = new Random(1); // fix seed to reproduce same values
		
		int containerId = 0;
		while (containerId < nbContainers) {
			// choose a random location,  inside radius
			GeoCoordinate location = new GeoCoordinate(0, 0);
			do {
				location.setLatitude(reference.getLatitude()+random.nextDouble()*2*referenceLatSpan-referenceLatSpan);
				location.setLongitude(reference.getLongitude()+random.nextDouble()*2*referenceLongSpan-referenceLongSpan);
			} while(location.distanceTo(reference) > radius);
			
			int n = 1+random.nextInt(5);
			if (n > nbContainers-containerId)
				n = nbContainers-containerId;
			
			ContainerSet containerSet = new ContainerSet(location);
			for (int i = containerId; i < containerId+n; i++) {
				containerSet.addContainer(new Container(i));
			}
			cs.addContainerSet(containerSet);
			containerId += n;
		}
		
		cs.setDepot(reference);
		
		// code to be moved: should be triggered by an XML message
		Graph graph = cs.buildGraph(cs.getContainerSets());
		graph.solveVehicleRouting(3);
	}
}
