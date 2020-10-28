package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.BinarySearchTree;
import model.Node;

class BinarySearchTreeTest {

	private BinarySearchTree<Integer,Integer> tree;
	
	public void setup1() {
		tree = new BinarySearchTree<Integer, Integer>();
		tree.insert(1,1);
		tree.insert(42,42);
		tree.insert(-23,-23);
		tree.insert(-25,-25);
		tree.insert(-6,-6);
		tree.insert(6,6);
		tree.insert(11,11);
	}
	
	@Test
	public void insertTest() {
		BinarySearchTree<Integer, Integer> abb = new BinarySearchTree<Integer, Integer>();
		abb.insert(1,1);
		Node<Integer,Integer> root = abb.getRoot();
		assertTrue(root.getElement()==1,"insert is not setting the root correctly");
		
		abb.insert(42,42);
		int Number = root.getRight().getElement();
		assertTrue(Number==42,"insert is not setting the right child of the root in a correct way");
		
		abb.insert(-23,-23);
		Number = root.getLeft().getElement();
		assertTrue(Number==-23,"insert is not setting the left child of the root in a correct way");
		
		abb.insert(-25,-25);
		Number = root.getLeft().getLeft().getElement();
		assertTrue(Number==-25,"insert is not setting the left child of the left child of the root in a correct way");
	
		abb.insert(-6,-6);
		Number = root.getLeft().getRight().getElement();
		assertTrue(Number==-6,"insert is not setting the right child of the left child of the root in a correct way");
		
		abb.insert(6,6);
		Number = root.getRight().getLeft().getElement();
		assertTrue(Number==6,"insert is not setting the left child of the right child of the root in a correct way");
	
		abb.insert(11,11);
		Number = root.getRight().getLeft().getRight().getElement();
		assertTrue(Number==11,"insert is not setting the right child of the left child of the right child the root in a correct way");
	}
	
	@Test
	public void searchTest() {
		setup1();
		
		int Number = tree.searchValue(1).getElement();
		assertTrue(Number==1,"the search method is not working, it is not finding a value that it should find");
		
		assertNull(tree.searchValue(9),"the search method is not working, it is finding a value that it should not find");
		
		Number = tree.searchValue(42).getElement();
		assertTrue(Number==42,"the search method is not working, it is not finding a value that it should find");
		
		assertNull(tree.searchValue(-100),"the search method is not working, it is finding a value that it should not find");
		
		Number = tree.searchValue(11).getElement();
		assertTrue(Number==11,"the search method is not working, it is not finding a value that it should find");
		
		Number = tree.searchValue(6).getElement();
		assertTrue(Number==6,"the search method is not working, it is not finding a value that it should find");
	
		assertNull(tree.searchValue(7),"the search method is not working, it is finding a value that it should not find");
	}
}
