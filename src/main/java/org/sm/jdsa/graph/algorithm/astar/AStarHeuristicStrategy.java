package org.sm.jdsa.graph.algorithm.astar;

public interface AStarHeuristicStrategy {

	int estimateCost(int fromVertex, int toVertx);

}
