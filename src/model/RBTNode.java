package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RBTNode<K extends Comparable<K>, E> extends Node<K ,E> implements Serializable{

	private RBTNode<K,E> parent;
	private RBTNode<K,E> left;
	private RBTNode<K,E> right;
	private boolean color;
	
	public RBTNode(K key, E element) {
		super(key, element);
	}

	public boolean getColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public RBTNode<K, E> getParent() {
		return parent;
	}

	public void setParent(RBTNode<K, E> parent) {
		this.parent = parent;
	}
	
	public RBTNode<K, E> getLeft() {
		return left;
	}

	public void setLeft(RBTNode<K, E> left) {
		this.left = left;
	}

	public RBTNode<K, E> getRight() {
		return right;
	}

	public void setRight(RBTNode<K, E> right) {
		this.right = right;
	}
}
