package model;

import customExceptions.ElementAlreadyExistException;

public class RedBlackTree<K extends Comparable<K>, E> extends BinarySearchTree<K, E> {

	public static final boolean RED = true;
	public static final boolean BLACK = false;
	private RBTNode<K,E> root;
	private RBTNode<K,E> NIL;

	public RedBlackTree(K key, E element) {
		super();
		NIL = new RBTNode<K,E>(key, element);
		this.root = NIL;
	}
	
	public RBTNode<K,E> insertion(K key, E element) throws ElementAlreadyExistException{
		if(searchValue(key) != null) {
			throw new ElementAlreadyExistException();
		}else {
			RBTNode<K, E> node = new RBTNode<K,E>(key, element);
			if(this.root == NIL) {
				this.root = node;
				root.setColor(BLACK);
				root.setLeft(NIL);
				root.setRight(NIL);
				root.setParent(NIL);
				return root;
			}else return insert(this.root, node);
		}
		
	}

	private RBTNode<K,E> insert(RBTNode<K,E> root, RBTNode<K,E> node) {
		
		insertRecurse(root, node);

		//Repair tree if cases are violated
		insertRepairTree(node);

		//Find root to return
		root = node;
		while(root.getParent() != NIL) {
			root = root.getParent();
		}

		return root;
	}



	private void insertRecurse(RBTNode<K, E> current, RBTNode<K, E> toAdd) {	
		if(current != NIL) {
			if(toAdd.getKey().compareTo(current.getKey()) < 0) {
				if(current.getLeft() != NIL) {
					insertRecurse(current.getLeft(), toAdd);
					return;
				}else {
					current.setLeft(toAdd);
				}

			}else {// node.key >= root.key			
				if(current.getRight() != NIL) {
					insertRecurse(current.getRight(), toAdd);
					return;
				}else {
					current.setRight(toAdd);
				}
			}


		}		
		toAdd.setParent(current);
		toAdd.setLeft(NIL);
		toAdd.setRight(NIL);
		toAdd.setColor(RED);

	}

	private void insertRepairTree(RBTNode<K,E> node){
		if(node.getParent() == NIL) {
			//Node will be the root of the tree since its empty
			insertCase1(node);
		}
		else if(node.getParent().getColor() == BLACK) {
			//Node's parent is BLACK
			insertCase2(node);
		}
		else if(getUncle(node) != NIL && getUncle(node).getColor() == RED) {
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
			rotateLeft(parent);
			node = node.getLeft();
		}else if(node == parent.getLeft() && parent == grandParent.getRight()){
			rotateRight(parent);
			node = node.getRight();
		}

		insertCase4Phase2(node);
	}

    private void insertCase4Phase2(RBTNode<K,E> node) {
		RBTNode<K,E> parent = node.getParent();
		RBTNode<K,E> grandParent = getGrandParent(node);
	
		if(node == parent.getLeft()) {
			rotateRight(grandParent);
		}else {
			rotateLeft(grandParent);
		}
	
		parent.setColor(BLACK);
		grandParent.setColor(RED);
	}

	public RBTNode<K, E> searchValue(K key) {
		if(root==NIL) {
			return null;
		}else {
			return searchValue(root,key);
		}
	}

	public boolean delete(K key){
        RBTNode<K,E> z = searchValue(key);
        if(z == null || z == NIL) return false;
        
        
        RBTNode<K,E> x;
        RBTNode<K,E> y = z; // temporary reference y
        boolean y_original_color = y.getColor();
        
        if(z.getLeft() == NIL){
            x = z.getRight();  
            transplant(z, z.getRight());  
        }else if(z.getRight() == NIL){
            x = z.getLeft();
            transplant(z, z.getLeft()); 
        }else{
            y = treeMinimum(z.getRight());
            y_original_color = y.getColor();
            x = y.getRight();
            if(y.getParent() == z) {
                x.setParent(y);
                }else{
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor()); 
        }
        if(y_original_color==BLACK)
            deleteFixup(x);  
        return true;
    }
    
    public RBTNode<K,E> treeMinimum(RBTNode<K,E> subTreeRoot){
        while(subTreeRoot.getLeft() != NIL){
            subTreeRoot = subTreeRoot.getLeft();
        }
        return subTreeRoot;
    }
    
    private void deleteFixup(RBTNode<K,E> x){
        while(x != this.root && x.getColor() == BLACK){ 
            if(x == x.getParent().getLeft()){
            	RBTNode<K,E> w = x.getParent().getRight();
                if(w.getColor()  == RED){
                    w.setColor(BLACK);
                    x.getParent().setColor(RED);
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }
                if(w.getLeft().getColor()  == BLACK && w.getRight().getColor()  == BLACK){
                    w.setColor(RED);
                    x = x.getParent();
                    continue;
                }
                else if(w.getRight().getColor()  == BLACK){
                    w.getLeft().setColor(BLACK);
                    w.setColor(RED);
                    rotateRight(w);
                    w = x.getParent().getRight();
                }
                if(w.getRight().getColor()  == RED){
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(BLACK);
                    w.getRight().setColor(BLACK);
                    rotateLeft(x.getParent());
                    x = this.root;
                }
            }else{
            	RBTNode<K,E> w = x.getParent().getLeft();
                if(w.getColor()  == RED){
                    w.setColor(BLACK);
                    x.getParent().setColor(RED);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }
                if(w.getRight().getColor() == BLACK && w.getLeft().getColor() == BLACK){
                    w.setColor(RED);
                    x = x.getParent();
                    continue;
                }
                else if(w.getLeft().getColor() == BLACK){
                    w.getRight().setColor(BLACK);
                    w.setColor(RED);
                    rotateLeft(w);
                    w = x.getParent().getLeft();
                }
                if(w.getLeft().getColor() == RED){
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(BLACK);
                    w.getLeft().setColor(BLACK);
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(BLACK); 
    }
    
    private void transplant(RBTNode<K,E> target, RBTNode<K,E> with){ 
    	if(target.getParent() == NIL){
    		this.root = with;
    	}else if(target == target.getParent().getLeft()){
    		target.getParent().setLeft(with);
    	}else
    		target.getParent().setRight(with);
    	with.setParent(target.getParent());
    }

	private RBTNode<K,E> searchValue(RBTNode<K,E> root,K key){
		if(root==null) {
			return root;
		}else if(root.getKey().compareTo(key)==0) {
			return root;
		}else if(root.getKey().compareTo(key)>0) {
			return searchValue(root.getLeft(),key);
		}else {
			return searchValue(root.getRight(),key);
		}
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

	public void rotateLeft(RBTNode<K,E> x) {
		RBTNode<K,E> y = x.getRight();
		x.setRight(y.getLeft());
		if(y.getLeft() != NIL) {
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent() == NIL) { //x is root
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

	public void rotateRight(RBTNode<K,E> x) {
		RBTNode<K,E> y = x.getLeft();
		x.setLeft(y.getRight());
		if(y.getRight() != NIL) {
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent() == NIL) { //x is root
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

	public void prettyPrint() {
        printHelper(this.root, "", true);
	}
	
	private void printHelper(RBTNode<K,E> root, String indent, boolean last) {
		// print the tree structure on the screen
	   	if (root != null) {
		   System.out.print(indent);
		   if (last) {
		      System.out.print("R----");
		      indent += "     ";
		   } else {
		      System.out.print("L----");
		      indent += "|    ";
		   }
            
           String sColor = root.getColor() == true?"RED":"BLACK";
		   System.out.println(root.getElement() + "(" + sColor + ")");
		   printHelper(root.getLeft(), indent, false);
		   printHelper(root.getRight(), indent, true);
		}
	}
}
