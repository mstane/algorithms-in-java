package org.sm.jdsa.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sm.jdsa.graph.GraphAdjacencyListImpl;
import org.sm.jdsa.graph.algorithm.coloring.ColoringStrategy;
import org.sm.jdsa.graph.algorithm.coloring.ColoringAdjacentListStrategyImpl;

public class GraphColoringTest {
  
  @Test
  public void test1() {
	  
	  GraphAdjacencyListImpl graph = new GraphAdjacencyListImpl.Builder(5, false)
    	.addEdge(0, 1)
    	.addEdge(0, 2)
    	.addEdge(1, 2)
    	.addEdge(1, 3)
    	.addEdge(2, 3)
    	.addEdge(3, 4)
    	.build();

	  ColoringStrategy coloringStrategy = new ColoringAdjacentListStrategyImpl(graph);
	  
	  assertEquals(3, coloringStrategy.getNumberOfColors());

  }

  @Test
  public void test3() {
	  GraphAdjacencyListImpl graph = new GraphAdjacencyListImpl.Builder(5, false)
		.addEdge(0, 1)
		.addEdge(0, 2)
		.addEdge(1, 2)
		.addEdge(1, 4)
		.addEdge(2, 4)
		.addEdge(4, 3)
		.build();
    
	  ColoringStrategy coloringStrategy = new ColoringAdjacentListStrategyImpl(graph);
	  
	  assertEquals(4, coloringStrategy.getNumberOfColors());
  }

}
