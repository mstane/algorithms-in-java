package org.sm.jdsa.combinatorial;

import java.util.ArrayList;
import java.util.Arrays;

public class Permutations<E> {
	
	private final E[] elements;
	
	public Permutations(E[] elements) {
		this.elements = elements;
	}
	
	public void run() {
		permutation(new ArrayList<E>(0), new ArrayList<E>(Arrays.asList(elements)));
	}
	
	@SuppressWarnings("unchecked")
	private void permutation(ArrayList<E> prefix, ArrayList<E> suffix) {
	    int suffixSize = suffix.size();
	    if (suffixSize == 0) consumePermutation(prefix);
	    else {
	        for (int i = 0; i < suffixSize; i++) {
	        	ArrayList<E> nextPrefix = (ArrayList<E>)prefix.clone();
	        	ArrayList<E> nextSuffix = (ArrayList<E>)suffix.clone();
	        	nextPrefix.add(nextSuffix.remove(i));
	        	permutation(nextPrefix, nextSuffix);
	        }
	    }
	}

	private void consumePermutation(ArrayList<E> permutation) {
		// Do something with current permutation
		
	}

}
