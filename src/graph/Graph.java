package graph;

import java.util.ArrayList;
import java.util.List;

/** @file
 * 
 * Full graph container
 *
 */

public class Graph {
	public List<GraphNode> nodes;
	public GraphNode startNode;

	public Graph() {
		nodes = new ArrayList<>();
	}
	
	// build all edges
	public void buildEdges() {
		for (int i = 0; i < nodes.size(); i++)
		{
			GraphNode node_i = nodes.get(i);
			for (int j = i+1; j < nodes.size(); j++)
			{
				GraphNode node_j = nodes.get(j);
				double weight = node_i.getWeight(node_j);
				node_i.edges.add(new GraphEdge(weight, node_j));
				node_j.edges.add(new GraphEdge(weight, node_i));
			}
		}
	}
	
	/** solve vehicle routing problem
	 * 
	 * @param nbVehicles number of vehicles to use
	 * @return ordered list of nodes to visit, for each vehicle
	 */
	public List<List<GraphNode>> solveVehicleRouting(int nbVehicles) {
		// dummy implementation: create routes with provided nodes, in order
		
		// create result
		List<List<GraphNode>> result = new ArrayList<>();
		for (int i = 0; i < nbVehicles; i++)
			result.add(new ArrayList<GraphNode>());
		
		// add starting point
		for (List<GraphNode> circuit: result)
			circuit.add(startNode);
		
		// distribute nodes to the routes
		int vehicleIdx = 0;
		for (GraphNode node: nodes) {
			if (node != startNode) {
				result.get(vehicleIdx).add(node);
				vehicleIdx++;
				if (vehicleIdx >= nbVehicles)
					vehicleIdx = 0;
			}
		}
			
		// add starting point to close the circuit
		for (List<GraphNode> circuit: result)
			circuit.add(startNode);

		return result;
	}
}
