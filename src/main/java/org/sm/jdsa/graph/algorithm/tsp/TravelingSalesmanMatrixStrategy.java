package org.sm.jdsa.graph.algorithm.tsp;

import java.util.Arrays;

import org.sm.jdsa.graph.GraphAdjacencyMatrixWeightedImpl;

public class TravelingSalesmanMatrixStrategy implements TravelingSalesmanStrategy {
	
	private final static int NOT_VISITED_YET = -1;
	
	private final GraphAdjacencyMatrixWeightedImpl graph;
	
	private final int graphSize;
	
	private final int[] shortestRoute;
	
	private int startVertex = 0;
	
	public TravelingSalesmanMatrixStrategy(GraphAdjacencyMatrixWeightedImpl graph) {
		this.graph = graph;
		this.graphSize = graph.getGraphSize();
		this.shortestRoute = new int[graphSize];
		Arrays.fill(shortestRoute, -1);
	}

	@Override
	public int[] run(int startVertex) {
		this.startVertex = startVertex;
		int vertexToCheck = startVertex;
		int visitCounter = 1;
		
		while (visitCounter < graphSize) {
			vertexToCheck = getMinWeightAdjacentVertex(vertexToCheck);
			visitCounter++;
		}
		
		shortestRoute[startVertex] = vertexToCheck;
		
		return shortestRoute;		
	}
	
	public int getMinWeightAdjacentVertex(int inputVertex) {
		int min = Integer.MAX_VALUE;
		int minAdjacentVertex = 0;
		for(int i = 0; i < graphSize; i++) {
			if (notVisitedYet(i)) {
				int weight = graph.getDataStructure()[inputVertex][i];
				if (min >= weight) {
					min = weight;
					minAdjacentVertex = i;
				}
			}
		}
		shortestRoute[minAdjacentVertex] = inputVertex;
		return minAdjacentVertex;
	}
	
	private boolean notVisitedYet(int vertex) {
		return vertex != startVertex && shortestRoute[vertex] == NOT_VISITED_YET;  
	}

}
