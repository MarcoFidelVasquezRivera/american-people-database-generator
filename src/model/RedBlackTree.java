package model;

public class RedBlackTree<K extends Comparable<K>, E> extends BinarySearchTree<K, E> {

	public static final boolean RED = true;
	public static final boolean BLACK = false;
	private RBTNode<K,E> root;
	
	public RedBlackTree() {
		super();
	}
	
	public RBTNode<K,E> insert(RBTNode<K,E> root, RBTNode<K,E> node) {
		insertRecurse(root, node);

		//Repair tree if cases are violated
		insertRepairTree(node);
		
		//Find root to return
		root = node;
		while(root.getParent() != null) {
			root = root.getParent();
		}
		
		return root;
	}
	

	
	private void insertRecurse(RBTNode<K, E> current, RBTNode<K, E> toAdd) {	
		if(current == null) {
			root = toAdd;
		}
		else if(current != null) {
			if(toAdd.getKey().compareTo(current.getKey()) < 0) {
				if(current.getLeft() != null) {
					insertRecurse(current.getLeft(), toAdd);
					return;
				}else {
					current.setLeft(toAdd);
				}

			}else {// node.key >= root.key			
				if(current.getRight() != null) {
					insertRecurse(current.getRight(), toAdd);
					return;
				}else {
					current.setRight(toAdd);
				}
			}
			

		}		
		toAdd.setParent(current);
		toAdd.setLeft(null);
		toAdd.setRight(null);
		toAdd.setColor(RED);

	}
	
	private void insertRepairTree(RBTNode<K,E> node){
		if(node.getParent() == null) {
			//Node will be the root of the tree since its empty
			insertCase1(node);
		}
		else if(node.getParent().getColor() == BLACK) {
			//Node's parent is BLACK
			insertCase2(node);
		}
		else if(getUncle(node) != null && getUncle(node).getColor() == RED) {
			//Parent is RED(so it cant be root) and uncle is RED
			insertCase3(node);
		}else {
			//Parent is RED and Uncle is BLACK
			insertCase4(node);
		}
	}

	private void insertCase1(RBTNode<K,E> node) {
		node.setColor(BLACK);
	}
	
	private void insertCase2(RBTNode<K,E> node){
		//The tree is still valid
		return;
	}
	
	private void insertCase3(RBTNode<K,E> node) {
		node.getParent().setColor(BLACK);
		getUncle(node).setColor(BLACK);
		getGrandParent(node).setColor(RED);
		insertRepairTree(getGrandParent(node));
	}
	
	private void insertCase4(RBTNode<K,E> node) {
		RBTNode<K,E> parent = node.getParent();
		RBTNode<K,E> grandParent = getGrandParent(node);
		
		if(node == parent.getRight() && parent == grandParent.getLeft()) {
			leftRotate(parent);
			node = node.getLeft();
		}else if(node == parent.getLeft() && parent == grandParent.getRight()){
			rightRotate(parent);
			node = node.getRight();
		}
		
		insertCase4Phase2(node);
	}
	
	private void insertCase4Phase2(RBTNode<K,E> node) {
		RBTNode<K,E> parent = node.getParent();
		RBTNode<K,E> grandParent = getGrandParent(node);
		
		if(node == parent.getLeft()) {
			rightRotate(grandParent);
		}else {
			leftRotate(grandParent);
		}
		
		parent.setColor(BLACK);
		grandParent.setColor(RED);
	}
	
	public RBTNode<K,E> getRoot(){
		return this.root;
	}
	
	public RBTNode<K,E> getGrandParent(RBTNode<K,E> node){
		return node == null ? null: node.getParent().getParent();
	}
	
	public RBTNode<K,E> getSibling(RBTNode<K,E> node){
		RBTNode<K,E> parent = node.getParent();
		if(parent == null) {
			return null;
		}
		
		if(node == parent.getLeft()) {
			return parent.getRight();
		}else {
			return parent.getLeft();
		}
	}
	
	public RBTNode<K,E> getUncle(RBTNode<K,E> node){
		RBTNode<K,E> parent = node.getParent();
		return getSibling(parent);
	}
	
	public void leftRotate(RBTNode<K,E> x) {
		RBTNode<K,E> y = x.getRight();
	    x.setRight(y.getLeft());
	    if(y.getLeft() != null) {
	      y.getLeft().setParent(x);
	    }
	    y.setParent(x.getParent());
	    if(x.getParent() == null) { //x is root
	      this.root = y;
	    }
	    else if(x == x.getParent().getLeft()) { //x is left child
	      x.getParent().setLeft(y);
	    }
	    else { //x is right child
	      x.getParent().setRight(y);
	    }
	    y.setLeft(x);
	    x.setParent(y);
	  }
	
	  public void rightRotate(RBTNode<K,E> x) {
		  RBTNode<K,E> y = x.getLeft();
		    x.setLeft(y.getRight());
		    if(y.getRight() != null) {
		      y.getRight().setParent(x);
		    }
		    y.setParent(x.getParent());
		    if(x.getParent() == null) { //x is root
		      this.root = y;
		    }
		    else if(x == x.getParent().getRight()) { //x is left child
		    	x.getParent().setRight(y);
		    }
		    else { //x is right child
		      x.getParent().setLeft(y);
		    }
		    y.setRight(x);
		    x.setParent(y);
		  
}
}
