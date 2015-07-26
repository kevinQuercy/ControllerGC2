package data;

import graph.Graph;
import graph.GraphNode;

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
	
	private ContainerSystem() {
		super();
		containerSets = new ArrayList<>();
		containerById = new HashMap<>();
		depot = null;
		nbVehicles = 0;
		collectRoutes = null;
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
		// get container sets to collect
		List<ContainerSet> containersToCollect = readyForCollect();
		
		// determine circuits
		Graph graph = buildGraph(containersToCollect);
		List<List<GraphNode>> routes = graph.solveVehicleRouting(nbVehicles);
		
		// store result in collectRoutes
		collectRoutes = new ArrayList<>();
		for (List<GraphNode> route: routes) {
			List<ContainerSet> collectRoute = new ArrayList<>();
			collectRoutes.add(collectRoute);
			
			for (GraphNode node: route) {
				ContainerSetGraphNode csNode = (ContainerSetGraphNode) node;
				if (csNode.containerSet != null)
					collectRoute.add(csNode.containerSet);
			}
		}
	}
	
	// Get the list of ContainerSet ready for collect
	private List<ContainerSet> readyForCollect() {
		List<ContainerSet> result = new ArrayList<>();
		for (ContainerSet containerSet: containerSets) {
			if (containerSet.isReadyForCollect())
				result.add(containerSet);
		}
		return result;
	}
	
	private static class ContainerSetGraphNode extends GraphNode {
		public ContainerSet containerSet;
		public GeoCoordinate location;
		
		@Override
		public double getWeight(GraphNode destNode) {
			return location.distanceTo(((ContainerSetGraphNode)destNode).location);
		}
	}
	
	// generate graph for this list of ContainerSet, adding depot
	private Graph buildGraph(List<ContainerSet> containerSets) {
		Graph graph = new Graph();
		
		// create nodes for depot and all container sets
		ContainerSetGraphNode depotNode = new ContainerSetGraphNode();
		depotNode.containerSet = null;
		depotNode.location = depot;
		graph.nodes.add(depotNode);
		graph.startNode = depotNode;
		
		for (ContainerSet cs: containerSets) {
			ContainerSetGraphNode csNode = new ContainerSetGraphNode();
			csNode.containerSet = cs;
			csNode.location = cs.getLocation();
			graph.nodes.add(csNode);
		}
		
		// build edges
		graph.buildEdges();
		
		return graph;
	}
}
