package org.sm.jdsa.tree;

public class RedBlackTree<E extends Comparable<E>> implements BinarySearchTree<E> {

	private RbNode<E> tree;
	
	private final RbNode<E> NULL_NODE;

	public RedBlackTree() {
		NULL_NODE = new RbNode<>(null, null, null, null, false);
		NULL_NODE.parent = NULL_NODE.left = NULL_NODE.right = NULL_NODE;
		NULL_NODE.setBlack();
		tree = NULL_NODE;
	}
	
	@Override
	public void insert(E e) {
		RbNode<E> n = createRedNode(e);
		RbNode<E> p = NULL_NODE;
		RbNode<E> n1 = tree;
		
		while (n1.equals(NULL_NODE)) {
			p = n1;
			if (n.element.compareTo(n1.element) < 0)
				n1 = n1.left;
			else
				n1 = n1.right;
		}
		
		n.parent = p;
		
		if (p.equals(NULL_NODE)) 
			tree = n;
		else if (n.element.compareTo(p.element) < 0)
			p.left = n;
		else
			p.right = n;
		
		
		checkAndRecolorNodes(n);
	}

	private void checkAndRecolorNodes(RbNode<E> n) {
		while (n.parent.isRed()) {
			if (n.parent == n.parent.parent.left) {
				RbNode<E> uncle = n.parent.parent.right;
				if (uncle.isRed()) {
					n.parent.setBlack();
					uncle.setBlack();
					n.parent.parent.setRed();
					n = n.parent.parent;
				} else if (n == n.parent.right) {
					n = n.parent;
					rotateLeft(n);
				} else {
					n.parent.setBlack();
					n.parent.parent.setRed();
					rotateRight(n.parent.parent);
				}
			} else {
				RbNode<E> uncle = n.parent.parent.left;
				if (uncle.isRed()) {
					n.parent.setBlack();
					uncle.setBlack();
					n.parent.parent.setRed();
					n = n.parent.parent;
				} else if (n == n.parent.left) {
					n = n.parent;
					rotateRight(n);
				} else {
					n.parent.setBlack();
					n.parent.parent.setRed();
					rotateLeft(n.parent.parent);
				}
			}
		}
		tree.setBlack();
	}
	
	private void rotateLeft(RbNode<E> n) {
		RbNode<E> n1 = n.right;
		n.right = n1.left;
		
		if (!n1.left.equals(NULL_NODE))
			n1.left.parent = n;
		n1.parent = n.parent;
		
		if (n.parent.equals(NULL_NODE))
			tree = n1;
		else if (n == n.parent.left)
			n.parent.left = n1;
		else
			n.parent.right = n1;
		
		n1.left = n;
		n.parent = n1;
	}

	private void rotateRight(RbNode<E> n) {
		RbNode<E> n1 = n.left;
		n.left = n1.right;
		
		if (!n1.right.equals(NULL_NODE))
			n1.right.parent = n;
		n1.parent = n.parent;
		
		if (n.parent.equals(NULL_NODE))
			tree = n1;
		else if (n == n.parent.right)
			n.parent.right = n1;
		else
			n.parent.left = n1;
		
		n1.right = n;
		n.parent = n1;
	}

	@Override
	public void remove(E e) {

	}

	@Override
	public boolean contains(E e) {
		return contains(e, tree);
	}

	private boolean contains(E e, RbNode<E> n) {
		if (n.equals(NULL_NODE))
			return false;

		int comparision = e.compareTo(n.element);

		if (comparision < 0)
			return contains(e, n.left);
		else if (comparision > 0)
			return contains(e, n.right);
		else
			return true;
	}

	@Override
	public E findMin() {
		return findMin(tree).element;
	}

	private RbNode<E> findMin(RbNode<E> n) {
		if (n.equals(NULL_NODE))
			return NULL_NODE;
		else if (n.left.equals(NULL_NODE))
			return n;
		else
			return findMin(n.left);
	}

	@Override
	public E findMax() {
		return findMax(tree).element;
	}

	private RbNode<E> findMax(RbNode<E> n) {
		if (n.equals(NULL_NODE))
			return NULL_NODE;
		else if (n.right.equals(NULL_NODE))
			return n;
		else
			return findMax(n.right);
	}

	private RbNode<E> createRedNode(E e) {
		return new RbNode<>(e, NULL_NODE, NULL_NODE, NULL_NODE, true);
	} 

	
}
