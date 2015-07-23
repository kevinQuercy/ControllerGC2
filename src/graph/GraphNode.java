package graph;

import java.util.ArrayList;
import java.util.List;

/** @file
 * 
 * One node inside a graph
 *
 */

public abstract class GraphNode {
	public List<GraphEdge> edges;

	public GraphNode() {
		edges = new ArrayList<>();
	}
	
	abstract public double getWeight(GraphNode destNode);
}
