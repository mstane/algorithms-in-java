package org.sm.jdsa.graph;

import java.util.Arrays;

import org.sm.jdsa.graph.algorithm.coloring.ColoringStrategy;
import org.sm.jdsa.graph.algorithm.mst.MstStrategy;
import org.sm.jdsa.graph.algorithm.search.SearchStrategy;
import org.sm.jdsa.graph.algorithm.shortestpath.ShortestPathStrategy;

public class GraphAdjacencyMatrixWeightedImpl implements Graph {
	
	private final boolean directed;
	private final int graphSize;
	private final int[][] dataStructure;
	private final int numberOfEdges;
	
	public static class Builder implements GraphBuilder {
		
		private final boolean directed;
		private final int graphSize;
		private int numberOfEdges = 0;
		
		private final int[][] dataStructure;
		
		public Builder(int graphSize, boolean directed) {
			this.graphSize = graphSize;
			
			this.directed = directed;
			
			this.dataStructure = new int[graphSize][graphSize];
			Arrays.stream(dataStructure).forEach(
					subArray -> Arrays.fill(subArray, 0)
					);
			
		}
		
		/**
		 * Adding an undirected edge
		 * 
		 * @param a
		 * @param b
		 * @return
		 */
		public Builder addEdge(int a, int b, int w) {
			dataStructure[a][b] = w;
			if (!directed) dataStructure[b][a] = w;
			numberOfEdges++;
			return this;
		}
		
		@Override
		public GraphAdjacencyMatrixWeightedImpl build() {
			return new GraphAdjacencyMatrixWeightedImpl(this);
		}


	}
	
	private GraphAdjacencyMatrixWeightedImpl(Builder graphBuilder) {
		this.graphSize = graphBuilder.graphSize;
		this.dataStructure = graphBuilder.dataStructure;
		this.directed = graphBuilder.directed;
		this.numberOfEdges = graphBuilder.numberOfEdges;
	}

	@Override
	public boolean isDirected() {
		return directed;
	}

	@Override
	public int getGraphSize() {
		return graphSize;
	}

	public int[][] getDataStructure() {
		return dataStructure;
	}

	@Override
	public int getNumberOfEdges() {
		return numberOfEdges;
	}

}
