package org.sm.jdsa.graph.algorithm.search;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

import org.sm.jdsa.graph.GraphAdjacencyListImpl;

/**
 * 
 * Depth first non recursive search of a graph
 * 
 * @author Stanislav Markov
 *
 */
public class DfsNonRecursiveAdjacentListStrategyImpl implements SearchStrategy {
	
	private final GraphAdjacencyListImpl graph;
	private final int graphSize;
	
	private final boolean[] visited;
	private final int[] visitOrder;
	private int visitCounter;
	
	private Deque<LinkedList<Integer>> stack = new LinkedList<>();
	
	public DfsNonRecursiveAdjacentListStrategyImpl(GraphAdjacencyListImpl graph) {
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
		dfsStack(startVertex);
		return visitOrder;
	}

	private void visit(int vertex) {
		visited[vertex] = true;
		visitOrder[visitCounter++] = vertex;
	}
	
	private void dfsStack(int startVertex) {
		visitAndAddToStack(startVertex);
		
		while (!stack.isEmpty()) {
			LinkedList<Integer> adjacentList = stack.peekFirst();
			int vertex = adjacentList.removeFirst();
			if (adjacentList.isEmpty()) stack.removeFirst();
			if (!visited[vertex]) {
				visitAndAddToStack(vertex);
			}
		}
	}
	
	
	private void visitAndAddToStack(int vertex) {
		visit(vertex);
		if (!graph.getDataStructure()[vertex].isEmpty()) {
			LinkedList<Integer> newList = new LinkedList<>();
			newList.addAll(graph.getDataStructure()[vertex]);
			stack.addFirst(newList);
		}
	}

	

}
