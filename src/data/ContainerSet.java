package data;

import java.util.ArrayList;
import java.util.List;

/** @file
 * 
 * Set of containers, in the same building
 *
 */

public class ContainerSet {
	private List<Container> containers;
	private GeoCoordinate location;
	
	public ContainerSet(GeoCoordinate location) {
		super();
		this.location = location;
		containers = new ArrayList<>();
	}
	
	public List<Container> getContainers() {
		return containers;
	}

	public GeoCoordinate getLocation() {
		return location;
	}

	public void addContainer(Container container) {
		containers.add(container);
	}
	
	public boolean isReadyForCollect() {
		// check how many containers are full
		int containersFull = 0;
		for (Container container: containers) {
			if (container.isReadyForCollect())
				containersFull++;
		}
		return containersFull*100/containers.size() >= 60; // if 60% or more containers are full, need to collect the set
	}
}
