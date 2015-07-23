package graph;

/** @file
 * 
 * One connection between two nodes of a graph
 *
 */

public class GraphEdge {
	public double weight; // logical distance or weight between node containing edge and target node (less is best)
	public GraphNode targetNode;
	
	public GraphEdge(double weight, GraphNode targetNode) {
		super();
		this.weight = weight;
		this.targetNode = targetNode;
	}
}
