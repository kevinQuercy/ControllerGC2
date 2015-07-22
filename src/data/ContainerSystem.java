package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @file
 * 
 * Complete container system management
 *
 */

public class ContainerSystem {
	// singleton
	private static ContainerSystem singleton = null;
	
	public static ContainerSystem getContainerSystem() {
		if (singleton == null)
			singleton = new ContainerSystem();
		return singleton;
	}
	
	private List<ContainerSet> containerSets;
	private Map<Integer,Container> containerById;
	
	private ContainerSystem() {
		super();
		containerSets = new ArrayList<>();
		containerById = new HashMap<>();
	}
	
	/** add containerSet to the system
	 * @note containerSet shall already contains its containers
	 */ 
	public void addContainerSet(ContainerSet containerSet) {
		containerSets.add(containerSet);
		for (Container container: containerSet.getContainers()) {
			assert !containerById.containsKey(container.getContainerId()): "duplicated container with same id";
			containerById.put(container.getContainerId(), container);
		}
	}
	
	// Access container by Id
	public Container getContainer(int containerId) {
		return containerById.get(containerId);
	}
	
	// Get the list of ContainerSet ready for collect
	public List<ContainerSet> readyForCollect() {
		List<ContainerSet> result = new ArrayList<>();
		for (ContainerSet containerSet: containerSets) {
			if (containerSet.isReadyForCollect())
				result.add(containerSet);
		}
		return result;
	}
}
