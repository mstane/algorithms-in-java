package org.sm.jdsa.graph;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.sm.jdsa.graph.GraphAdjacencyListImpl;
import org.sm.jdsa.graph.algorithm.search.DfsNonRecursiveAdjacentListStrategyImpl;
import org.sm.jdsa.graph.algorithm.search.DfsRecursiveAdjacentListStrategyImpl;
import org.sm.jdsa.graph.algorithm.search.SearchStrategy;

public class DepthFirstSearchTest {
  
  @Test
  public void test1() {
	  GraphAdjacencyListImpl graph = new GraphAdjacencyListImpl.Builder(4, true)
	 	.addEdge(0, 1)
	 	.addEdge(0, 2)
	 	.addEdge(1, 2)
	 	.addEdge(2, 0)
	 	.addEdge(2, 3)
	 	.addEdge(3, 3)
	 	.build();
	 
	  SearchStrategy dfsSearchStrategy;
	  
	  int[] expected = {2, 0, 1, 3};
	  int[] result;

	  // Test DFS recursive algorithm
	  dfsSearchStrategy = new DfsRecursiveAdjacentListStrategyImpl(graph);	  
	  result = dfsSearchStrategy.run(2);
	  assertArrayEquals(expected, result);
	  
	  // Test DFS non recursive algorithm
	  dfsSearchStrategy = new DfsNonRecursiveAdjacentListStrategyImpl(graph);	  
	  result = dfsSearchStrategy.run(2);
	  assertArrayEquals(expected, result);
	  
  }

}
