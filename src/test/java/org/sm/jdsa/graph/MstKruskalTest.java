package org.sm.jdsa.graph;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.sm.jdsa.graph.algorithm.mst.MstKruskalAdjacentMatrixStrategyImpl;
import org.sm.jdsa.graph.algorithm.mst.MstStrategy;
import org.sm.jdsa.graph.algorithm.mst.MstStrategy.Edge;

public class MstKruskalTest {

	  @Test
	  public void test1() {

		  
	   GraphAdjacencyMatrixWeightedImpl graph = new GraphAdjacencyMatrixWeightedImpl.Builder(8, false)
	   		.addEdge(0, 2, 4)
	   		.addEdge(0, 3, 2)
	   		.addEdge(1, 2, 5)
	   		.addEdge(1, 5, 8) // X
	   		.addEdge(1, 6, 2)
	   		.addEdge(2, 4, 6)
	   		.addEdge(3, 5, 7) // X
	   		.addEdge(3, 7, 9) // X
	   		.addEdge(4, 6, 9) // X
	   		.addEdge(4, 7, 6)
	   		.addEdge(5, 7, 7)
			.build();

	   MstStrategy shortestPathStrategy = new MstKruskalAdjacentMatrixStrategyImpl(graph);
		
	   Edge[] expected = {
	       new Edge(0, 3, 2),
	       new Edge(1, 6, 2),
	       new Edge(0, 2, 4),
	       new Edge(1, 2, 5),
	       new Edge(4, 7, 6),
	       new Edge(2, 4, 6),
	       new Edge(5, 7, 7)
	   };

	   Edge[] result = shortestPathStrategy.run(0);
	   assertArrayEquals(expected, result);
	  }

}
