package org.sm.jdsa.graph.algorithm.coloring;

import java.util.stream.IntStream;

import org.sm.jdsa.graph.GraphAdjacencyListImpl;
import org.sm.jdsa.list.LinkedList;
import org.sm.jdsa.list.Iterator;

/**
 * 
 * Counts number of colors upper bound for graph (vertex)
 * coloring problem using greedy algorithm. 
 * 
 * @author Stanislav Markov
 *
 */
public class ColoringAdjacentListStrategyImpl implements ColoringStrategy {

	private final GraphAdjacencyListImpl graph;
	private final int graphSize;
	
	private int colorsUsed;
	private final int[] vertexColors;
	private final boolean[] adjecentsColors;
	
	int numberOfColors = -1;
	
	public ColoringAdjacentListStrategyImpl(GraphAdjacencyListImpl graph) {
		this.graph = graph;
		this.graphSize = graph.getGraphSize();
		
		this.colorsUsed = 0;
		this.vertexColors = new int[graphSize];
		this.adjecentsColors = new boolean[graphSize];
		
		initVertexColors();	
		
	}
	
	/**
	 * Main Method, in charge of counting number of different colors.
	 * In case it is already calculated it just passes the value
	 * 
	 * @return
	 */
	@Override
	public int getNumberOfColors() {
		if (numberOfColors < 0 && graphSize > 0) {			
			vertexColors[0] = colorsUsed;
			
			IntStream.range(0, graphSize).forEach(vertex -> {
				int leastAvailableColor = getLeastAvailableColor(vertex);
				vertexColors[vertex] = leastAvailableColor;
				colorsUsed = Math.max(colorsUsed, leastAvailableColor);
			});
			
			numberOfColors = colorsUsed + 1;
		}
		return numberOfColors;
	}

	
	/**
	 * Finds the least available color from vertex's adjacents 
	 * 
	 * @param vertex
	 * @return
	 */
	private int getLeastAvailableColor(int vertex) {
		loadAdjecentsColors(vertex);
		
		int leastAvailableColor = -1;
		int i = 0;
		while (leastAvailableColor < 0 && i < adjecentsColors.length) {
			if (!adjecentsColors[i]) leastAvailableColor = i;
			i++;
		}
		return leastAvailableColor;
	}

	
	/**
	 * Loads helper array with already taken color by vertex's adjacents
	 * 
	 * @param vertex
	 */
	private void loadAdjecentsColors(int vertex) {
		resetAdjecentsColors();
		LinkedList<Integer> vertexAdjecents = graph.getDataStructure()[vertex];
		for (int i = 0; i < adjecentsColors.length; i++) {
      
    }
		
		for(Iterator<Integer> iterator = vertexAdjecents.iterator(); iterator.hasNext();) {
		  int adjacent = iterator.next();
      if (adjacent > -1) {
        adjecentsColors[vertexColors[adjacent]] = true;
      }     

		}
	}

	
	/*
	 * Resets helper array used for recording colors
	 * assigned to the vertex's adjecents for next vertex
	 */
	private void resetAdjecentsColors() {
		IntStream.range(0, graphSize).forEach(color -> adjecentsColors[color] = false);
	}	

	
	/*
	 * Initialization of the array which holds colors assigned to verticies
	 */
	private void initVertexColors() {
		IntStream.range(0, graphSize).forEach(vertex -> vertexColors[vertex] = -1);
	}
	
}
