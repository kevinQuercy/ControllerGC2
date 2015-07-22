package core;

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
		
		if (args.length == 1)
		{
			// override with parameters from command line
			server_port = Integer.parseInt(args[0]);
		}
		
		createContainerSystem();
		
		SocketManager socketMan = new SocketManager(server_port);
		socketMan.run();
	}

	private static class ContainerSetDesc {
		public GeoCoordinate location;
		public int containersId[];
		public ContainerSetDesc(GeoCoordinate location, int[] containersId) {
			super();
			this.location = location;
			this.containersId = containersId;
		}
	}
	
	// describe a ContainerSet to ease build of ContainerSystem
	private static ContainerSetDesc csDescs[] = {
		new ContainerSetDesc(new GeoCoordinate(43.662093, 1.429904), new int[]{1,2,3,4}),
		new ContainerSetDesc(new GeoCoordinate(43.642684, 1.427742), new int[]{5,6}),
		new ContainerSetDesc(new GeoCoordinate(43.640468, 1.465994), new int[]{7,8,9}),
	};
	
	private static void createContainerSystem() {
		ContainerSystem cs = ContainerSystem.getContainerSystem();
		
		for (ContainerSetDesc csDesc: csDescs) {
			ContainerSet containerSet = new ContainerSet(csDesc.location);
			for (int id: csDesc.containersId) {
				containerSet.addContainer(new Container(id));
			}
			cs.addContainerSet(containerSet);
		}
	}
}
