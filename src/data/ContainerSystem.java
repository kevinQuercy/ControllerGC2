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
	private GeoCoordinate depot;
	private int nbVehicles;
	private List<List<ContainerSet>> collectRoutes;
	private List<ContainerSet> notCollected;
	private List<Truck> trucks;
	
	private ContainerSystem() {
		super();
		containerSets = new ArrayList<>();
		containerById = new HashMap<>();
		depot = null;
		nbVehicles = 0;
		collectRoutes = null;
		notCollected = null;
		trucks = null;
	}
	
	public List<ContainerSet> getContainerSets() {
		return containerSets;
	}

	public GeoCoordinate getDepot() {
		return depot;
	}

	public void setDepot(GeoCoordinate depot) {
		this.depot = depot;
	}

	public int getNbVehicles() {
		return nbVehicles;
	}

	public void setNbVehicles(int nbVehicles) {
		this.nbVehicles = nbVehicles;
	}

	public List<List<ContainerSet>> getCollectRoutes() {
		return collectRoutes;
	}

	public List<ContainerSet> getNotCollected() {
		return notCollected;
	}

	public List<Truck> getTrucks() {
		return trucks;
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
	
	public synchronized void trigCircuitComputation() {
		notCollected = new ArrayList<>();

		// get container sets to collect
		List<ContainerSet> containersToCollect = new ArrayList<>();
		for (ContainerSet containerSet: containerSets) {
			if (containerSet.isReadyForCollect())
				containersToCollect.add(containerSet);
			else
				notCollected.add(containerSet);
		}
		
		// prepare trucks
		trucks = new ArrayList<>();
		for (int i = 0; i < nbVehicles; i++) {
			Truck truck = new Truck();
			truck.setDepot(depot);
			trucks.add(truck);
		}
		
		// construct problem
		CircuitSolution problem = new CircuitSolution();
		problem.setContainerSetList(containersToCollect);
		problem.setVehicleList(trucks);
		
		// solve problem
		CircuitSolver solver = new CircuitSolver();
		CircuitSolution solution = solver.solve(problem);
		
		// store result in collectRoutes
		collectRoutes = new ArrayList<>();
		for (Truck truck: solution.getTruckList()) {
			List<ContainerSet> collectRoute = new ArrayList<>();
			collectRoutes.add(collectRoute);
			
			ContainerSet containerSet = truck.getNextContainerSet();
			while(containerSet != null)
			{
				collectRoute.add(containerSet);
				containerSet = containerSet.getNextContainerSet();
			}
		}
	}
}
