package org.sm.jdsa.tree;


public class Node<E> {
	E element;
	Node<E> left;
	Node<E> right;

	Node(E element) {
		this(element, null, null);
	}

	Node(E element, Node<E> left, Node<E> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}

}
