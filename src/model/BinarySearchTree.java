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
		//TODO		
		//updateHeight(newNode);
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
	public Node<K,E> minimumValue(Node<K,E> node) {
		if(node!=null) {
			if(node.getLeft()==null) {
				return node;
			}else {
					return minimumValue(node.getLeft());		
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
	
	private boolean deleteValue(Node<K,E> current,K value) {
		if(current==null) {
			return false;
		}
		
		if(current.getKey().compareTo(value)<0) {
			return deleteValue(current.getRight(),value);
				
		}else if(current.getKey().compareTo(value)>0) {
			return deleteValue(current.getLeft(),value);
				
		}else {
			if(current.getLeft() != null && current.getRight() != null) {
				return deleteTreeTwoSons(current);	
				
			}else if(current.getLeft()!=null || current.getRight()!=null) {
				return deleteTreeOneSon(current);
				
			}else {
				return deleteTreeNoSons(current);
			}
		}	
	}
	
	private boolean deleteTreeNoSons(Node<K,E> node) {
		Node<K,E> parent = node.getParent();
		if(parent.getLeft() == node) {
			parent.setLeft(null);
		}else {
			parent.setRight(null);
		}
		//TODO	
		//updateHeight(parent);
		return true;
	}

	private boolean deleteTreeOneSon(Node<K,E> current) {
		if(current.getLeft()!=null) {
			
			Node<K,E> parent = current.getParent();
			if(parent.getLeft()==(current)) {
				Node <K,E> aux = current.getLeft();
				parent.setLeft(aux);
				aux.setParent(parent);
				return true;
			}else {
				Node <K,E> aux = current.getLeft();
				parent.setRight(aux);
				aux.setParent(parent);	
				return true;
			}
			//TODO	
			//updateHeight(p);
			
		}else if(current.getRight()!=null) {
			
			Node<K,E> parent = current.getParent();
			if(parent.getLeft()==(current)) {
				Node <K,E> aux = current.getRight();
				parent.setLeft(aux);
				aux.setParent(parent);
				return true;
			}else {
				Node <K,E> aux = current.getRight();
				parent.setRight(aux);
				aux.setParent(parent);	
				return true;
			}
			
			//TODO	
			//updateHeight(p);
			
		}
		return false;
	}
	
	//Revisar el caso de actuelizar el peso en este metodo
	private boolean deleteTreeTwoSons(Node<K,E> current) {
		Node<K,E> minRightValue = minimumValue(current.getRight());
		if(minRightValue.getRight()!=null) {
			Node<K,E> temp =minRightValue;
			deleteTreeOneSon(minRightValue);
			if(current==this.root) {
				temp.setRight(current.getRight());
				temp.setLeft(current.getLeft());
				current.getLeft().setParent(temp);
				current.getRight().setParent(temp);
				this.root=temp;
				return true;
			}else {
				temp.setRight(current.getRight());
				temp.setLeft(current.getLeft());
				Node<K,E> pop = current.getParent();
				if(pop.getLeft()==current) {
					pop.setLeft(temp);
				}else if(pop.getRight()==current) {
					pop.setRight(temp);
				}
				if(current.getLeft()!=null ) {
					current.getLeft().setParent(temp);
				}
				if(current.getRight()!=null) {
					current.getRight().setParent(temp);
				}
				current=temp;
				return true;
			}
		}else {
			Node<K,E> temp =minRightValue;
			deleteTreeNoSons(minRightValue);
			if(current==this.root) {
				temp.setRight(current.getRight());
				temp.setLeft(current.getLeft());
				
				if(current.getLeft()!=null) {
					current.getLeft().setParent(temp);
				}
				
				if(current.getRight()!=null) {
					current.getRight().setParent(temp);
				}
				
				this.root=temp;
				return true;
			}else {
				temp.setRight(current.getRight());
				temp.setLeft(current.getLeft());
				Node<K,E> pop = current.getParent();
				if(pop.getLeft()==current) {
					pop.setLeft(temp);
				}else if(pop.getRight()==current) {
					pop.setRight(temp);
				}
				
				if(current.getLeft()!=null) {
					current.getLeft().setParent(temp);
				}
				
				if(current.getRight()!=null) {
					current.getRight().setParent(temp);
				}
				
				current.setParent(null);
				current=temp;
				return true;
			}
		}
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
		
		if(current.getLeft()!=null) {
			inOrderR(current.getLeft(),list);
			
		}
		
		list.add(current.getElement());
		
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
