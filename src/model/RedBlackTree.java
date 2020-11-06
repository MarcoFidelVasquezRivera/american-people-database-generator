package model;

import java.util.ArrayList;

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
	
	public RBTNode<K,E> redBlackInsertion(K key, E element) throws ElementAlreadyExistException{
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
        RBTNode<K,E> toDelete = searchValue(key);
        if(toDelete == null || toDelete == NIL) {
        	return false;
        }
        
        
        RBTNode<K,E> child;
        RBTNode<K,E> temp = toDelete; // temporary reference temp
        boolean temp_original_color = temp.getColor();
        
        if(toDelete.getLeft() == NIL){
            child = toDelete.getRight();  
            transplant(toDelete, toDelete.getRight());  
        }else if(toDelete.getRight() == NIL){
            child = toDelete.getLeft();
            transplant(toDelete, toDelete.getLeft()); 
        }else{
            temp = treeMinimum(toDelete.getRight());
            temp_original_color = temp.getColor();
            child = temp.getRight();
            if(temp.getParent() == toDelete) {
                child.setParent(temp);
                }else{
                transplant(temp, temp.getRight());
                temp.setRight(toDelete.getRight());
                temp.getRight().setParent(temp);
            }
            transplant(toDelete, temp);
            temp.setLeft(toDelete.getLeft());
            temp.getLeft().setParent(temp);
            temp.setColor(toDelete.getColor()); 
        }
        if(temp_original_color==BLACK)
            deleteFixup(child);  
        return true;
    }
    
    public RBTNode<K,E> treeMinimum(RBTNode<K,E> subTreeRoot){
        while(subTreeRoot.getLeft() != NIL){
            subTreeRoot = subTreeRoot.getLeft();
        }
        return subTreeRoot;
    }
    
    private void deleteFixup(RBTNode<K,E> child){
        while(child != this.root && child.getColor() == BLACK){ 
            if(child == child.getParent().getLeft()){
            	RBTNode<K,E> w = child.getParent().getRight();
                if(w.getColor()  == RED){
                    w.setColor(BLACK);
                    child.getParent().setColor(RED);
                    rotateLeft(child.getParent());
                    w = child.getParent().getRight();
                }
                if(w.getLeft().getColor()  == BLACK && w.getRight().getColor()  == BLACK){
                    w.setColor(RED);
                    child = child.getParent();
                    continue;
                }
                else if(w.getRight().getColor()  == BLACK){
                    w.getLeft().setColor(BLACK);
                    w.setColor(RED);
                    rotateRight(w);
                    w = child.getParent().getRight();
                }
                if(w.getRight().getColor()  == RED){
                    w.setColor(child.getParent().getColor());
                    child.getParent().setColor(BLACK);
                    w.getRight().setColor(BLACK);
                    rotateLeft(child.getParent());
                    child = this.root;
                }
            }else{
            	RBTNode<K,E> w = child.getParent().getLeft();
                if(w.getColor()  == RED){
                    w.setColor(BLACK);
                    child.getParent().setColor(RED);
                    rotateRight(child.getParent());
                    w = child.getParent().getLeft();
                }
                if(w.getRight().getColor() == BLACK && w.getLeft().getColor() == BLACK){
                    w.setColor(RED);
                    child = child.getParent();
                    continue;
                }
                else if(w.getLeft().getColor() == BLACK){
                    w.getRight().setColor(BLACK);
                    w.setColor(RED);
                    rotateLeft(w);
                    w = child.getParent().getLeft();
                }
                if(w.getLeft().getColor() == RED){
                    w.setColor(child.getParent().getColor());
                    child.getParent().setColor(BLACK);
                    w.getLeft().setColor(BLACK);
                    rotateRight(child.getParent());
                    child = root;
                }
            }
        }
        child.setColor(BLACK); 
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

	public void rotateLeft(RBTNode<K,E> child) {
		RBTNode<K,E> temp = child.getRight();
		child.setRight(temp.getLeft());
		if(temp.getLeft() != NIL) {
			temp.getLeft().setParent(child);
		}
		temp.setParent(child.getParent());
		if(child.getParent() == NIL) { //child is root
			this.root = temp;
		}
		else if(child == child.getParent().getLeft()) { //child is left child
			child.getParent().setLeft(temp);
		}
		else { //child is right child
			child.getParent().setRight(temp);
		}
		temp.setLeft(child);
		child.setParent(temp);
	}

	public void rotateRight(RBTNode<K,E> child) {
		RBTNode<K,E> temp = child.getLeft();
		child.setLeft(temp.getRight());
		if(temp.getRight() != NIL) {
			temp.getRight().setParent(child);
		}
		temp.setParent(child.getParent());
		if(child.getParent() == NIL) { //child is root
			this.root = temp;
		}
		else if(child == child.getParent().getRight()) { //child is left child
			child.getParent().setRight(temp);
		}
		else { //child is right child
			child.getParent().setLeft(temp);
		}
		temp.setRight(child);
		child.setParent(temp);

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
	
	
	public ArrayList<E> searchList(K key) {
		ArrayList<E> personsList = new ArrayList<E>();
		String skey = String.valueOf(key);
		if(root==null) {
			return null;
		}else {
			searchList(skey, this.root,personsList);
		}
		
		
		return personsList;
	}

	private void searchList(String key, RBTNode<K,E> node, ArrayList<E> personsList) {
		if(node!=NIL) {
			String nodeKey = String.valueOf(node.getKey());
			if(key.length()<nodeKey.length()) {
				nodeKey = nodeKey.substring(0,key.length());

				if(key.equalsIgnoreCase(nodeKey)) {
					personsList.add(node.getElement());
				}
			}
			searchList(key,node.getLeft(),personsList);
			searchList(key,node.getRight(),personsList);

		}
	}
}
