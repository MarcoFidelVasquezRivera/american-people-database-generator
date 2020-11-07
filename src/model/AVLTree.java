package model;


import java.io.Serializable;
import java.util.ArrayList;
import customExceptions.ElementAlreadyExistException;


@SuppressWarnings("serial")
public class AVLTree<K extends Comparable<K>,E> extends BinarySearchTree <K,E> implements Serializable {
	
	public AVLTree() {
		super();
	}
	
	@Override
	public void insert(E element,K key) throws ElementAlreadyExistException {
		super.insert(element, key);
		Node<K,E> n = super.searchValue(key);
		balance(n);
	}
	
	public void delete(K key) {
		Node<K,E> n = super.searchValue(key);
		Node<K,E> parent = n.getParent();
		boolean direction = true;

		if(n.getLeft()!=null && n.getRight()!=null) {

		}
		
		if(parent != null) {
			if(parent.getLeft()==n) {
				direction = false;
			}

			super.deleteValue(key);

			if(n.getLeft()!=null && n.getRight()!=null) {
				if(direction) {
					balance(parent.getRight());
				}else {
					balance(parent.getLeft());
				}
			}else {
				if(direction) {
					balance(parent);
				}else {
					balance(parent);
				}
			}
			
			
		}
		
		super.deleteValue(key);
		if(n.getRight() != null) {
			balance(n.getRight());
		}else if(n.getLeft() != null) {
			balance(n.getLeft());
		}

	}

	public int balanceFactor (Node<K,E> node) {
		if(node!=null) {
			int right = -1;
			int left = -1;
			
			if(node.getRight()!=null) {
				right = node.getRight().getHeight();
			}
			
			if(node.getLeft()!=null) {
				left = node.getLeft().getHeight();
			}
			return right - left;
		}
		return 0;
	}
	
	public void balance(Node<K,E> node) {
		
		if(node!=null) {
			int balanceFactor = balanceFactor(node);
			if(balanceFactor>1) {
				rightCases(node.getRight());
				updateHeight(node);
			}else if(balanceFactor<(-1)) {
				leftCases(node.getLeft());
				updateHeight(node);
			}
			balance(node.getParent());
		}
	}
	
	public void rightCases(Node<K,E> nodeRight) {
		int balanceFactor = balanceFactor(nodeRight);
		if(balanceFactor==1 || balanceFactor==0) {
			Node<K,E> parent = nodeRight.getParent();
			
			leftRotate(parent);
			super.updateHeight(parent);
		}else{
			Node<K,E> parent = nodeRight.getParent();
			
			rightRotate(nodeRight);
			super.updateHeight(nodeRight);
			
			leftRotate(parent);
			super.updateHeight(parent);
		}
		
	}
	
	public void leftCases(Node<K,E> nodeLeft) {
		int balanceFactor = balanceFactor(nodeLeft);
		if(balanceFactor==-1 || balanceFactor==0) {
			Node<K,E> parent = nodeLeft.getParent();
			rightRotate(parent);
			super.updateHeight(parent);
		}else{
			Node<K,E> parent = nodeLeft.getParent();
			
			leftRotate(nodeLeft);
			super.updateHeight(nodeLeft);
			
			rightRotate(parent);
			super.updateHeight(parent);
		}
	}

	//atributo para la altura bien melo B)
	public void rightRotate(Node <K,E> node) {	

		Node<K,E> parent =node.getParent();
		Node <K,E> left = node.getLeft();
		
		if(left.getRight()!=null) {
			Node <K,E> leftRightTree = left.getRight();
			node.setLeft(leftRightTree);
			leftRightTree.setParent(node);
		}else {
			node.setLeft(null);
		}

		left.setRight(node);
		node.setParent(left);
		left.setParent(parent);

		if(parent!=null && node==parent.getLeft()) {
			parent.setLeft(left);
		}else if(parent!=null && node==parent.getRight()) {
			parent.setRight(left);
		}else {
			setRoot(left);
		}
		
		updateHeight(left);
		updateHeight(node);

	}
	
	public void leftRotate(Node <K,E> node) {
		Node<K,E> parent =node.getParent();
		Node <K,E> right = node.getRight();

		if(right.getLeft()!=null) {
			Node <K,E> rightLeftTree = right.getLeft();
			node.setRight(rightLeftTree);
			rightLeftTree.setParent(node);
		}else {
			node.setRight(null);
		}

		right.setLeft(node);
		node.setParent(right);
		right.setParent(parent);

		if(parent!=null && node==parent.getLeft()) {
			parent.setLeft(right);
		}else if(parent!=null && node==parent.getRight()) {
			parent.setRight(right);
		}else {
			setRoot(right);
		}
		
		updateHeight(right);
		updateHeight(node);
	}
	
}	
