package org.sm.jdsa.graph.algorithm.search;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

import org.sm.jdsa.graph.GraphAdjacencyListImpl;

import org.sm.jdsa.list.Iterator;

/**
 * 
 * Breadth first search of a graph
 * 
 * @author Stanislav Markov
 *
 */
public class BfsAdjacentListStrategyImpl implements SearchStrategy {
	
	private final GraphAdjacencyListImpl graph;
	private final int graphSize;
	
	private final boolean[] visited;
	private final int[] visitOrder;
	private int visitCounter;


	public BfsAdjacentListStrategyImpl(GraphAdjacencyListImpl graph) {
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
		Deque<Integer> queue = new LinkedList<>();
		queue.addLast(startVertex);
		
		do {
			int vertex = queue.removeFirst();
			if (!visited[vertex]) {
				visit(vertex);
				for (Iterator<Integer> iterator = graph.getDataStructure()[vertex].iterator(); iterator.hasNext();) {
				  queue.addLast(iterator.next());
				}
			}
		} while (!queue.isEmpty());
		
		return visitOrder;
	}
	
	private void visit(int vertex) {
		visited[vertex] = true;
		visitOrder[visitCounter++] = vertex;
	}


}
