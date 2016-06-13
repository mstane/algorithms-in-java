package org.sm.jdsa.list;

public class LinkedList<E> implements List<E> {
	
	private int size;
	private Node<E> first;
	private Node<E> last; 
	
	public LinkedList() {
		size = 0;
		first = null;
		last = null;
	}
	
	@Override
	public boolean add(E e) {
		Node<E> n = new Node<>(e, last, null);
		if (last != null) {
			last.next = n;
		} else {
			first = n;
		}
		last = n;
		size++;
		return true;
	}

	@Override
	public E get(int idx) {
		Node<E> n = getNode(idx);
		return n == null ? null : n.element;
	}

	@Override
	public E set(int idx, E e) {
		Node<E> nextNode = getNode(idx);
		if (nextNode != null) {
			Node<E> newNode = new Node<>(e, nextNode.prev, nextNode);
			nextNode.prev = newNode;
			if (nextNode.prev == null) first = newNode;
			size++;
			return e;			
		} else {
			return null;
		}
	}

	@Override
	public E remove(int idx) {
		Node<E> n = getNode(idx);
		if (n != null) {
			Node<E> prev = n.prev;
			Node<E> next = n.next;
			if (prev != null) {
				prev.next = next;
			} else {
				first = next;
			}
			if (next != null) {
				next.prev = prev;
			} else {
				last = prev;
			}
			size--;
			return n.element;
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 1;
	}
	
	private Node<E> getNode(int idx) {
		if (idx > size - 1) {
			return null;
		} else {
			Node<E> n = first;
			for (int i = 0; i < idx; i++) {
				n = n.next;
			}
			return n;
		}
	}

	private static class Node<E> {
		E element;
		Node<E> prev;
		Node<E> next;
		
		Node(E element, Node<E> prev, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
	}
	
}
