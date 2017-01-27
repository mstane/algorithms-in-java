package org.sm.jdsa.tree;

public class RbNode<E> {
	
	E element;
	RbNode<E> parent;
	RbNode<E> left;
	RbNode<E> right;
	boolean color;
	
	RbNode(E e, RbNode<E> parent, RbNode<E> left, RbNode<E> right) {
		this.element = e;
		this.parent = parent;
		this.left = left;
		this.right = right;
		setBlack();
	}
	
	public boolean isBlack() {
		return color;
	}
	
	public void setBlack() {
		color = true;
	}
	
	public boolean isRed() {
		return !color;
	}
	
	public void setRed() {
		color = false;
	}

    public void setColor(RbNode<E> n) {
        color = n.color;
    }

}
