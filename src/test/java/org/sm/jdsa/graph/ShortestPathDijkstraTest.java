package org.sm.jdsa.graph;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.sm.jdsa.graph.GraphAdjacencyMatrixWeightedImpl;
import org.sm.jdsa.graph.algorithm.shortestpath.ShortestPathStrategy;
import org.sm.jdsa.graph.algorithm.shortestpath.ShortestPathDijkstraAdjacencyMatrixStrategyImpl;

public class ShortestPathDijkstraTest {
  
  @Test
  public void test1() {


// cost = {0, 15, 20, 16, 7, 22, 13, 23}
// path = {-1, 6,  1,  1, 0,  1,  4,  3}
	  
   GraphAdjacencyMatrixWeightedImpl graph = new GraphAdjacencyMatrixWeightedImpl.Builder(8, false)
   		.addEdge(0, 4, 7)
   		.addEdge(1, 2, 5)
   		.addEdge(1, 3, 1)
   		.addEdge(1, 5, 7)
   		.addEdge(1, 6, 2)
   		.addEdge(3, 5, 9)
   		.addEdge(3, 7, 7)
   		.addEdge(4, 6, 6)
   		.addEdge(5, 7, 2)
		.build();

   ShortestPathStrategy shortestPathStrategy = new ShortestPathDijkstraAdjacencyMatrixStrategyImpl(graph);
	
   int[] expected = {-1, 6,  1,  1, 0,  1,  4,  3};
   int[] result = shortestPathStrategy.run(0);
   assertArrayEquals(expected, result);
  }


}
