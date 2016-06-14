package org.sm.jdsa.list;

public interface List<E> {

	boolean add(E e);

	E get(int idx);
	
	E set(int idx, E e);
	
	E remove(int idx);
	
	int size();
	
	boolean isEmpty();
	
	Iterator<E> iterator();
	
	boolean addAll(List<E> l);
		
	
}
