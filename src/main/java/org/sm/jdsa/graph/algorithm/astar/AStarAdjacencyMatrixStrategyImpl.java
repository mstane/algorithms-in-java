package org.sm.jdsa.graph.algorithm.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

import org.sm.jdsa.graph.GraphAdjacencyMatrixWeightedImpl;
import org.sm.jdsa.hash.ClosedHashTable;
import org.sm.jdsa.hash.HashTable;

public class AStarAdjacencyMatrixStrategyImpl implements AStarStrategy {
	
	private final GraphAdjacencyMatrixWeightedImpl graph;
	private final int graphSize;
	private final AStarHeuristicStrategy heuristic;
	
	private Queue<Vertex> vertexPriorityQueue;
	private HashTable<Integer, Vertex> vertexMap;
	
	public AStarAdjacencyMatrixStrategyImpl(GraphAdjacencyMatrixWeightedImpl graph, AStarHeuristicStrategy heuristic) {
		this.graph = graph;
		this.graphSize = graph.getGraphSize();
		this.heuristic = heuristic;
		
		vertexPriorityQueue = new PriorityQueue<>(graphSize, vertexComparator);
		vertexMap = new ClosedHashTable<>(graphSize);
	}

	@Override
	public int[] run(int startVertex, int endVertex) {
		Vertex vertex = addInTable(startVertex);
		vertex.cost = 0;
		vertex.prediction = heuristic.estimateCost(startVertex, endVertex);

		while (!vertexPriorityQueue.isEmpty()) {
			vertex = vertexPriorityQueue.poll();
			vertex.known = true;
			
			if (vertex.id == endVertex) {
				return createSolutionPath(startVertex, endVertex);
			} else {
				int[] adjacentArray = getAdjacentArray(vertex.id);
				for (int vAdj : adjacentArray) {
					Vertex vertexAdj = vertexMap.get(vAdj);
					if (vertexAdj == null) {
						vertexAdj = addInTable(vAdj);
					}
					if (!vertexAdj.known) {
						int offeredCost = vertex.cost + graph.getDataStructure()[vertex.id][vertexAdj.id];
						if (offeredCost < vertexAdj.cost) {
							vertexAdj.cost = offeredCost;
							vertexAdj.path = vertex.id;
							vertexAdj.prediction = vertexAdj.cost + heuristic.estimateCost(vertexAdj.id, endVertex);
						}
					}
				}
				
			}
			
		}
		
		return new int[0];
	}
	
	private int[] createSolutionPath(int startVertex, int endVertex) {
		int vertex = endVertex;
		List<Integer> solutionPath = new ArrayList<>();
		solutionPath.add(vertex);
		
		while (vertex != startVertex) {
			vertex = vertexMap.get(vertex).path;
			solutionPath.add(vertex);
		}
		
		Collections.reverse(solutionPath);
		return solutionPath.stream().mapToInt(i -> i).toArray();
	}

	private Vertex addInTable(int v) {
		Vertex vertex = new Vertex(v, false, Integer.MAX_VALUE, -1, Integer.MAX_VALUE);
		vertexPriorityQueue.add(vertex);
		vertexMap.put(v, vertex);
		return vertex;
	}

	private int[] getAdjacentArray(int v) {
		return IntStream.range(0, graphSize)
				.filter(w -> graph.getDataStructure()[v][w] > 0)
				.toArray();
	}
	
	
    private static Comparator<Vertex> vertexComparator = new Comparator<Vertex>(){
        @Override
        public int compare(Vertex v1, Vertex v2) {
            return v1.prediction - v2.prediction;
        }
    };
	
    private class Vertex {
    	int id;
    	boolean known;
    	int cost;
    	int path;
    	int prediction;
    	
    	public Vertex(int id, boolean known, int cost, int path, int prediction) {
    		this.id = id;
    		this.known = known;
    		this.cost = cost;
    		this.path = path;
    		this.prediction = prediction;
    	}

		@Override
		public String toString() {
			return "Vertex [id=" + id + ", known=" + known + ", cost=" + cost + ", path=" + path + ", prediction="
					+ prediction + "]";
		}
    	
    }
	

}
