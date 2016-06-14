package org.sm.jdsa.graph.algorithm.search;

import java.util.stream.IntStream;

import org.sm.jdsa.graph.GraphAdjacencyListImpl;
import org.sm.jdsa.list.Iterator;

/**
 * 
 * Depth first recursive search of a graph
 * 
 * @author Stanislav Markov
 *
 */
public class DfsRecursiveAdjacentListStrategyImpl implements SearchStrategy {

	private final GraphAdjacencyListImpl graph;
	private final int graphSize;
	
	private final boolean[] visited;
	private final int[] visitOrder;
	private int visitCounter;

	public DfsRecursiveAdjacentListStrategyImpl(GraphAdjacencyListImpl graph) {
		this.graph = graph;
		this.graphSize = graph.getGraphSize();
		
		this.visited = new boolean[graphSize];
		this.visitOrder = new int[graphSize];
		
		IntStream.range(0, graphSize).forEach(
			vertex -> {
				visited[vertex] = false;
				visitOrder[vertex] = -1;
		});
	}

	@Override
	public int[] run(int startVertex) {
		dfsRecursive(startVertex);
		return visitOrder;
	}
	
	private void dfsRecursive(int vertex) {
		if (!visited[vertex]) {
			visit(vertex);
			for (Iterator<Integer> iterator = graph.getDataStructure()[vertex].iterator(); iterator.hasNext();) {
			  dfsRecursive(iterator.next());
			}
		}
	}

	private void visit(int vertex) {
		visited[vertex] = true;
		visitOrder[visitCounter++] = vertex;
	}
	


	
	
}
