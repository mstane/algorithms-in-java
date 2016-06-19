package org.sm.jdsa.tree;

public class RbNode<E> {
	
	E element;
	RbNode<E> parent;
	RbNode<E> left;
	RbNode<E> right;
	boolean color;

	
	RbNode(E e, RbNode<E> parent, RbNode<E> left, RbNode<E> right, boolean color) {
		this.element = e;
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.color = color;
	}
	
	public boolean isBlack() {
		return !color;
	}
	
	public void setBlack() {
		color = false;
	}
	
	public boolean isRed() {
		return color;
	}
	
	public void setRed() {
		color = true;
	}

}
