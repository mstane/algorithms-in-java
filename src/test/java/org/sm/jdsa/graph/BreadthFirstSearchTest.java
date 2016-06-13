package org.sm.jdsa.graph;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.sm.jdsa.graph.GraphAdjacencyListImpl;
import org.sm.jdsa.graph.algorithm.search.BfsAdjacentListStrategyImpl;
import org.sm.jdsa.graph.algorithm.search.DfsNonRecursiveAdjacentListStrategyImpl;
import org.sm.jdsa.graph.algorithm.search.DfsRecursiveAdjacentListStrategyImpl;
import org.sm.jdsa.graph.algorithm.search.SearchStrategy;

public class BreadthFirstSearchTest {
  
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
	 
	  SearchStrategy bfsSearchStrategy;
	  
	  int[] expected = {2, 0, 3, 1};
	  int[] result;

	  bfsSearchStrategy = new BfsAdjacentListStrategyImpl(graph);	  
	  result = bfsSearchStrategy.run(2);
	  assertArrayEquals(expected, result);
	  
	  

  }

}
