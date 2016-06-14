package org.sm.jdsa.graph;

import java.util.stream.Stream;

import org.sm.jdsa.list.LinkedList;


public class GraphAdjacencyListImpl implements Graph {
	
	private final boolean directed;
	private final int graphSize;
	private final LinkedList<Integer>[] dataStructure;
	private final int numberOfEdges;
	
	public static class Builder implements GraphBuilder {
		
		private final boolean directed;
		private final int graphSize;
		private int numberOfEdges = 0;

		private final LinkedList<Integer>[] dataStructure;
		
		@SuppressWarnings("unchecked")
		public Builder(int graphSize, boolean directed) {
			this.graphSize = graphSize;
			
			this.directed = directed;
			
			this.dataStructure = Stream.generate(LinkedList<Integer>::new).
					limit(graphSize).
					toArray(LinkedList[]::new);
			
		}
		
		/**
		 * Adding an undirected edge
		 * 
		 * @param a
		 * @param b
		 * @return
		 */
		public Builder addEdge(int a, int b) {
			dataStructure[a].add(b);
			if (!directed) dataStructure[b].add(a);
			numberOfEdges++;
			return this;
		}
		
		@Override
		public GraphAdjacencyListImpl build() {
			return new GraphAdjacencyListImpl(this);
		}


	}
	
	private GraphAdjacencyListImpl(Builder graphBuilder) {
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

	public LinkedList<Integer>[] getDataStructure() {
		return dataStructure;
	}

	@Override
	public int getNumberOfEdges() {
		return numberOfEdges;
	}
	

}
