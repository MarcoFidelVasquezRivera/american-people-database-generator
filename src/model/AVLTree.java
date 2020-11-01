package model;

public class AVLTree<K extends Comparable<K>,E> extends BinarySearchTree <K,E> {
	
	public AVLTree() {
		super();
	}
	
	@Override
	public void insert(E element,K key) {
		super.insert(element, key);
		Node<K,E> n = super.searchValue(key);
		balance(n);
	}
	
	public void delete(E element, K key) {
		Node<K,E> n = super.searchValue(key);
		Node<K,E> parent = n.getParent();
		boolean direction = true;
		
		if(parent.getLeft()==n) {
			direction = false;
		}
		
		super.deleteValue(key);
		
		if(direction) {
			balance(parent.getRight());
		}else {
			balance(parent.getLeft());
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
			//System.out.println("entra el metodo "+balanceFactor+" "+node.getElement());
			if(balanceFactor>1) {
				rightCases(node.getRight());
				updateHeight(node);
			}else if(balanceFactor<(-1)) {
				leftCases(node.getLeft());
				//System.out.println(node.getElement()+"---------------");
				super.updateHeight(node);
			}
			balance(node.getParent());
		}
	}
	
	public void rightCases(Node<K,E> nodeRight) {
		int balanceFactor = balanceFactor(nodeRight);
		if(balanceFactor==1 || balanceFactor==0) {
			leftRotate(nodeRight);
		}else{
			Node<K,E> parent = nodeRight.getParent();
			rightRotate(nodeRight);
			leftRotate(parent);
		}
	}
	
	public void leftCases(Node<K,E> nodeLeft) {
		int balanceFactor = balanceFactor(nodeLeft);
		if(balanceFactor==-1 || balanceFactor==0) {
			rightRotate(nodeLeft.getParent());
		}else{
			Node<K,E> parent = nodeLeft.getParent();
			leftRotate(nodeLeft);
			//System.out.println("entra a leftCases "+nodeLeft.getElement()+" "+nodeLeft.getHeight());
			super.updateHeight(nodeLeft);
			rightRotate(parent);
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
	}
	
	public void leftRotate(Node <K,E> node) {
		Node <K,E> right = node.getRight();
		Node <K,E> left = node.getLeft();
		Node <K,E> parent = node.getParent();
		if(parent!=null) {
			if(parent.getRight()==node) {
				parent.setRight(right);
				right.setParent(parent);
			}else {
				parent.setLeft(right);
				right.setParent(parent);
			}
		}else{
			setRoot(right);
		}
		if(right.getLeft()!=null) {
			node.setRight(right.getLeft());
			right.getLeft().setParent(node);
		}else {
			node.setRight(null);
		}
		right.setLeft(node);
		node.setParent(right);
	}
	
}	
