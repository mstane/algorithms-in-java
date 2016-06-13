package org.sm.jdsa.graph.algorithm.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.sm.jdsa.disjsets.DisjointSets;
import org.sm.jdsa.graph.GraphAdjacencyMatrixWeghtedImpl;

public class MstKruskalAdjacentMatrixStrategyImpl implements MstStrategy {
	
	private final GraphAdjacencyMatrixWeghtedImpl graph;
	private final int graphSize;
	
	Queue<Edge> edgePriorityQueue;
	DisjointSets edgeDisjointSets;
	List<Edge> mstEdgeList;

	public MstKruskalAdjacentMatrixStrategyImpl(GraphAdjacencyMatrixWeghtedImpl graph) {
		this.graph = graph;
		this.graphSize = graph.getGraphSize();
		
		this.edgePriorityQueue = new PriorityQueue<>(this.graph.getNumberOfEdges());
		this.edgeDisjointSets = new DisjointSets(this.graphSize);
		this.mstEdgeList = new ArrayList<>(this.graphSize - 1);
	}

	@Override
	public Edge[] run(int i) {
		fillQueueWithEdges();
		while (!edgePriorityQueue.isEmpty()) {
			Edge edge = edgePriorityQueue.poll();
			int v1Set = edgeDisjointSets.find(edge.v1);
			int v2Set = edgeDisjointSets.find(edge.v2);
			
			if (v1Set != v2Set) {
				mstEdgeList.add(edge);
				edgeDisjointSets.union(v1Set, v2Set);
			}
		}
		return mstEdgeList.toArray(new Edge[mstEdgeList.size()]);
	}

	
	
	private void fillQueueWithEdges() {
		for (int i = 0; i < graphSize; i++) {
			for (int j = i + 1; j < graphSize; j++) {
				int w = graph.getDataStructure()[i][j];
				if (w > 0) {
					Edge e = new Edge(i, j, w);
					edgePriorityQueue.add(e);
				}
			}
		}
	}

}
