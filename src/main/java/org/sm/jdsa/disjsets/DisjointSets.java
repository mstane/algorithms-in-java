package org.sm.jdsa.disjsets;

import java.util.Arrays;

public class DisjointSets {
	
	int[] elements;
			
	public DisjointSets(int numberOfElements) {
		elements = new int[numberOfElements];
		Arrays.fill(elements, -1);
	}
	
	public int find(int id) {
		if (elements[id] < 0) {
			return id;
		} else {
			elements[id] = find(elements[id]); 
			return elements[id];
		}
	}

	public void union(int id1, int id2) {
		int head1 = find(id1);
		int head2 = find(id2);
		
		if (head1 != head2) {
			if (elements[head2] < elements[head1]) {
				elements[head2] += elements[head1];
				elements[head1] = head2;
			} else { 
				elements[head1] += elements[head2];
				elements[head2] = head1;
			}			
		}
		
	}
}
