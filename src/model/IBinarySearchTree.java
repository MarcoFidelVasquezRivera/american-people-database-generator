package model;

import java.util.ArrayList;

public interface IBinarySearchTree<K extends Comparable<K>,E> {
	
	public void insert(E value,K key);
	public boolean deleteValue(K key);
	public int height();
	public int weight();
	public Node<K,E> searchValue(K key);
	public ArrayList<E> inOrder();
	public ArrayList<E> postOrder();
	public ArrayList<E> preOrder();

}
