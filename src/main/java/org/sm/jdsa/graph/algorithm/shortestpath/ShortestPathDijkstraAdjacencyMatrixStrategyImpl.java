package org.sm.jdsa.graph.algorithm.shortestpath;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

import org.sm.jdsa.graph.GraphAdjacencyMatrixWeghtedImpl;
import org.sm.jdsa.hash.ClosedHashTable;
import org.sm.jdsa.hash.HashTable;

public class ShortestPathDijkstraAdjacencyMatrixStrategyImpl implements ShortestPathStrategy {

	private final GraphAdjacencyMatrixWeghtedImpl graph;
	private final int graphSize;
	
	Queue<Vertex> vertexPriorityQueue;
	HashTable<Integer, Vertex> vertexMap;
	
	public ShortestPathDijkstraAdjacencyMatrixStrategyImpl(GraphAdjacencyMatrixWeghtedImpl graph) {
		this.graph = graph;
		this.graphSize = graph.getGraphSize();
		
		vertexPriorityQueue = new PriorityQueue<>(graphSize, vertexComparator);
		vertexMap = new ClosedHashTable<>(graphSize);
	}

	@Override
	public int[] run(int startVertex) {
		Vertex vertex = addInTable(startVertex);
		vertex.cost = 0;

		while (!vertexPriorityQueue.isEmpty()) {
			vertex = vertexPriorityQueue.poll();
			vertex.known = true;
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
					}
				}
			}
			
		}
		
		return IntStream.range(0, graphSize).map(w -> vertexMap.get(w).path).toArray();
		
	}
	
	private Vertex addInTable(int v) {
		Vertex vertex = new Vertex(v, false, Integer.MAX_VALUE, -1);
		vertexPriorityQueue.add(vertex);
		vertexMap.put(v, vertex);
		return vertex;
	}

	private int[] getAdjacentArray(int v) {
		return IntStream.range(0, graphSize)
				.filter(w -> graph.getDataStructure()[v][w] > 0)
				.toArray();
	}
	
    public static Comparator<Vertex> vertexComparator = new Comparator<Vertex>(){
        @Override
        public int compare(Vertex v1, Vertex v2) {
            return v1.cost - v2.cost;
        }
    };
    
    private class Vertex {
    	int id;
    	boolean known;
    	int cost;
    	int path;
    	
    	public Vertex(int id, boolean known, int cost, int path) {
    		this.id = id;
    		this.known = known;
    		this.cost = cost;
    		this.path = path;
    	}

    	@Override
    	public String toString() {
    		return "Vertex [id=" + id + ", known=" + known + ", cost=" + cost + ", path=" + path + "]";
    	}
    	
    }
 
}

