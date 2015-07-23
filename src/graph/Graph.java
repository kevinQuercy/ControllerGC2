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
		return null;
	}
}
