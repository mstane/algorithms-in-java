package org.sm.jdsa.tree;

public class AvlNode<E> {
	
	E element;
	AvlNode<E> left;
	AvlNode<E> right;
	int height;

	AvlNode(E e) {
		this(e, null, null, 0);
	}
	
	AvlNode(E e, AvlNode<E> left, AvlNode<E> right, int height) {
		this.element = e;
		this.left = left;
		this.right = right;
		this.height = height;
	}

}
