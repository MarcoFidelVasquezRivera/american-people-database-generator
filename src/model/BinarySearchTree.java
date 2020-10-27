package model;

import java.util.ArrayList;


public class BinarySearchTree<K extends Comparable<K>,E> implements IBinarySearchTree<K, E> {
	private Node<K,E> root;
	public Node<K, E> getRoot() {
		return root;
	}

	public void setRoot(Node<K, E> root) {
		this.root = root;
	}

	private int weight;

	public BinarySearchTree() {
		this.root = null;
	}
	
	@Override
	public void insert(E element, K key) {
		Node<K,E> newNode = new Node<K,E>(key,element);	
		if(root == null) {
			root = newNode;
		}else {
			insert(newNode, root);
		}
		updateHeight(newNode);
	}
	
	private void insert(Node<K,E> toAdd,Node<K,E> current) {
		if(toAdd.getKey().compareTo(current.getKey()) >= 0) {
			if(current.getRight()==null) {
				current.setRight(toAdd);
				toAdd.setParent(current);
			}else {
				insert(toAdd,current.getRight());
			}
		}else {
			if(current.getLeft()==null) {
				current.setLeft(toAdd);
				toAdd.setParent(current);
			}else {
				insert(toAdd,current.getLeft());
			}
		}
	}
	
	//Delete method
	public Node<K,E> minimunValue(Node<K,E> node) {
		if(node!=null) {
			if(node.getLeft()==null) {
				return node;
			}else {
					return minimunValue(node.getLeft());		
			}
		}
		return null;
	
	}
	@Override
	public boolean deleteValue(K key) {
		if(root == null) {
			return false;
		}else {
			return deleteValue(root, key);
		}
	}
	
	private boolean deleteValue(Node<K,E> root,K value) {
		if(root==null) {
			return false;
		}
		if(root.getKey().compareTo(value)<0) {
				return deleteValue(root.getRight(), value);
				
		}else if(root.getKey().compareTo(value)>0) {
				return deleteValue(root.getLeft(),value);
				
		}else {
			if(root.getLeft()!=null && root.getRight()!=null) {
				return deleteTreeTwoSons(root);	
				
			}else if(root.getLeft()!=null) {
				return deleteTreeOneSon(root);
				
			}else if(root.getRight()!=null) {
				return deleteTreeOneSon(root);
				
			}else {
				return deleteTreeNoSons(root);
			}
		}	
	}
	
	public boolean deleteTreeOneSon(Node<K,E> root) {
		if(root.getLeft()!=null) {
			Node <K,E>aux=root.getLeft();
			Node <K,E> p=root.getParent();
			p.setLeft(aux);
			aux.setParent(p);
			updateHeight(p);
			return true;
		}else if(root.getRight()!=null) {
			Node <K,E>aux=root.getRight();
			Node <K,E> p=root.getParent();
			p.setRight(aux);;
			aux.setParent(p);
			updateHeight(p);
			return true;
		}
		return false;
	}
	//revisar el caso de actuelizar el peso en este metodo
	public boolean deleteTreeTwoSons(Node<K,E> root) {
		Node<K,E> minRightValue = minimunValue(root.getRight());
		if(minRightValue.getRight()!=null) {
			Node<K,E> temp =minRightValue;
			deleteTreeOneSon(minRightValue);
			if(root==this.root) {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				root.getLeft().setParent(temp);
				root.getRight().setParent(temp);
				this.root=temp;
				return true;
			}else {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				Node<K,E> pop = root.getParent();
				if(pop.getLeft()==root) {
					pop.setLeft(temp);
				}else if(pop.getRight()==root) {
					pop.setRight(temp);
				}
				if(root.getLeft()!=null ) {
					root.getLeft().setParent(temp);
				}
				if(root.getRight()!=null) {
					root.getRight().setParent(temp);
				}
				root=temp;
				return true;
			}
		}else {
			Node<K,E> temp =minRightValue;
			deleteTreeNoSons(minRightValue);
			if(root==this.root) {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				root.getLeft().setParent(temp);
				root.getRight().setParent(temp);
				this.root=temp;
				return true;
			}else {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				Node<K,E> pop = root.getParent();
				if(pop.getLeft()==root) {
					pop.setLeft(temp);
				}else if(pop.getRight()==root) {
					pop.setRight(temp);
				}
				root.getLeft().setParent(temp);
				root.getRight().setParent(temp);
				root=temp;
				return true;
			}
		}
	}
	
	public boolean deleteTreeNoSons(Node<K,E> node) {
		Node<K,E> parent = node.getParent();
		node = null;
		updateHeight(parent);
		return true;
	}
	
	//Delete end
	
	@Override
	public int height() {
		if(root == null) {
			return 0;
		}else {
			return root.getHeight();
		}
	}
	
	
	public int height(Node<K,E> currentNode) {
		if(currentNode == null) {
			return 0;
		}else {
			return currentNode.getHeight();
		}
	}
	
	@Override
	public void updateHeight(Node<K,E> currentNode) {
		if(currentNode!=null) {
			int leftHeight = 0;
			int rightHeight = 0;
			
			if(currentNode.getRight()!=null) {
				rightHeight = currentNode.getRight().getHeight();
			}
			if(currentNode.getLeft()!=null) {
				leftHeight = currentNode.getLeft().getHeight();
			}
			int currentHeight = Math.max(leftHeight, rightHeight);
			currentNode.setHeight(currentHeight);
			updateHeight(currentNode.getParent());
		}
	}
	
	@Override
	public int weight() {
		return this.weight;
	}
	//Search method
	@Override
	public Node<K, E> searchValue(K key) {
		if(root==null) {
			return null;
		}else {
			return searchValue(root,key);
		}
	}
	private Node<K,E> searchValue(Node<K,E> root,K key){
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
	//Search method end
	@Override
	public ArrayList<E> inOrder() {
		ArrayList<E> list = new ArrayList<E>();
		if(root!=null) {
			inOrderR(root,list);
		}
		return list;
	}
	
	private void inOrderR(Node<K,E> current, ArrayList<E> list) {
		
		if(current.getLeft()==null) {
			list.add(current.getElement());
		}else {
			inOrderR(current.getLeft(),list);
		}
		if(current.getRight()!=null) {
			inOrderR(current.getRight(),list);
		}
		
	}

	@Override
	public ArrayList<E> postOrder() {
		ArrayList<E> list = new ArrayList<>();
		if (root != null) {
		 	postOrder(root, list);
		}
		 return (ArrayList<E>) list;
	}
	
	private void postOrder(Node<K,E> current, ArrayList<E> list) {
		
		if (current.getLeft() != null) {
			postOrder(current.getLeft(), list);
		}
		else if (current.getRight() != null) {
			postOrder(current.getRight(), list);
		}
		
		list.add(current.getElement());
	}

	@Override
	public ArrayList<E> preOrder(){
		ArrayList<E> list = new ArrayList<E>();
		if(root != null) {
			preOrder(list, root);
		}
		
		return list;
	}
	
	private void preOrder(ArrayList<E> list,Node<K,E> n) {
		
		list.add(n.getElement());
		
		if(n.getRight() != null) {
			preOrder(list, n.getRight());
		}
		
		if(n.getLeft() != null) {
			preOrder(list, n.getLeft());
		}
	}
}
