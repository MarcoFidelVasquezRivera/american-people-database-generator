package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Node<K extends Comparable<K>, E> implements Serializable{

	private E element;
	private K key;
	private Node<K,E> parent;
	private Node<K,E> left;
	private Node<K,E> right;
	private int height;

	public Node(K key, E element) {
		this.element = element;
		this.key = key;
		this.height = 0;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int hight) {
		this.height = hight;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public Node<K, E> getParent() {
		return parent;
	}

	public void setParent(Node<K, E> parent) {
		this.parent = parent;
	}

	public Node<K, E> getLeft() {
		return left;
	}

	public void setLeft(Node<K, E> left) {
		this.left = left;
	}

	public Node<K, E> getRight() {
		return right;
	}

	public void setRight(Node<K, E> right) {
		this.right = right;
	}
	
}