package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import customExceptions.ElementAlreadyExistException;
import model.BinarySearchTree;
import model.Node;
import model.Person;

class BinarySearchTreeTest {

	private BinarySearchTree<Integer,Integer> tree;
	
	public void setup1() throws ElementAlreadyExistException {
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
	public void insertTest() throws ElementAlreadyExistException {
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
	public void searchTest() throws ElementAlreadyExistException {
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
	
	@Test
	public void deleteTest() throws ElementAlreadyExistException {
		setup1();

		assertFalse(tree.deleteValue(0), "Tree is deleting a value it should not delete");

		assertTrue(tree.deleteValue(11), "Tree is not deleting a value it should delete");
		assertNull(tree.searchValue(11));

		assertTrue(tree.deleteValue(42), "Tree is not deleting a value it should delete");
		assertNull(tree.searchValue(42));
		
		assertTrue(tree.deleteValue(-23), "Tree is not deleting a value it should delete");
		assertNull(tree.searchValue(-23));

		assertTrue(tree.deleteValue(1), "Tree is not deleting a value it should delete");
		assertNull(tree.searchValue(1));
	}
	
	@Test
	public void weightTest() throws ElementAlreadyExistException {
		setup1();
		assertTrue(tree.weight() == 7, "The amount of nodes was not counted properly");
		
		tree.insert(50, 50);
		assertTrue(tree.weight() == 8, "The amount of nodes was not counted properly");
		
		tree.deleteValue(11);
		assertTrue(tree.weight() == 7, "The amount of nodes was not counted properly");
		
		tree.deleteValue(50);
		assertTrue(tree.weight() == 6, "The amount of nodes was not counted properly");
	}
	
	//TODO Finish this method
	@Test
	public void heightTest() throws ElementAlreadyExistException {
		setup1();
		Node<Integer,Integer> root = tree.getRoot();
		
		assertTrue(tree.height() == 3);
		
		tree.insert(12, 12);
		assertTrue(tree.height() == 4);
		
		tree.deleteValue(12);
		assertTrue(tree.height() == 3);
		
		tree.deleteValue(11);
		assertTrue(tree.height() == 2);
		
		tree.deleteValue(42);
		assertTrue(root.getRight().getHeight() == 0);

		tree.deleteValue(-23);
		assertTrue(root.getLeft().getHeight() == 1);
		
	}
	

	@Test
	public void maxTest() throws ElementAlreadyExistException {
		setup1();
		assertTrue(tree.maximumValue(tree.getRoot()).getElement() == 42, "The method did not find the proper maximum value");
		tree.deleteValue(42);
		assertTrue(tree.maximumValue(tree.getRoot()).getElement() == 11, "The method did not find the proper maximum value");
	}
	
	@Test
	public void minTest() throws ElementAlreadyExistException {
		setup1();
		assertTrue(tree.minimumValue(tree.getRoot()).getElement() == -25, "The method did not find the proper minimum value");
		tree.deleteValue(-25);
		assertTrue(tree.minimumValue(tree.getRoot()).getElement() == -23, "The method did not find the proper minimum value");
	}
	
	@Test
	public void searchListTest() throws ElementAlreadyExistException {
		BinarySearchTree<Integer, Integer> abb = new BinarySearchTree<Integer, Integer>();
		
		abb.insert(200, 200);
		abb.insert(2000, 2000);
		abb.insert(20, 20);
		abb.insert(500, 500);
		abb.insert(300, 300);
		abb.insert(100, 100);
		abb.insert(50, 50);
		abb.insert(3200, 3200);
		abb.insert(6200, 6200);
		
		ArrayList<Integer> list = abb.searchList(2);

		assertTrue(list.get(0)==200,"the method is not adding to the list the 200 or is adding another one");
		assertTrue(list.get(1)==20,"the method is not adding to the list the 20 or is adding another one");
		assertTrue(list.get(2)==2000,"the method is not adding to the list the 2000 or is adding another one");
		
		list = abb.searchList(5);
		System.out.println(list.size());
		assertTrue(list.get(0)==500,"the method is not adding to the list the 200 or is adding another one");
		assertTrue(list.get(1)==50,"the method is not adding to the list the 20 or is adding another one");
	}
}
