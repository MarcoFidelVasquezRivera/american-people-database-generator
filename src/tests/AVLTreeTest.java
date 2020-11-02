package tests;

import static org.junit.jupiter.api.Assertions.*;
import model.AVLTree;
import model.Node;

import org.junit.jupiter.api.Test;

import customExceptions.ElementAlreadyExistException;

class AVLTreeTest {

	@Test
	public void InsertTestCase1() throws ElementAlreadyExistException {
		AVLTree<Integer,Integer> tree = new AVLTree<>();
		tree.insert(10, 10);
		
		int number = tree.searchValue(10).getElement();
		assertTrue(number==10,"The insert method is not insertting the root");
		
		tree.insert(5, 5);
		tree.insert(6, 6);
		
		Node<Integer,Integer> root = tree.getRoot();
		assertTrue(root.getElement()==6,"the rotation is not working correctly");
		
		number = root.getLeft().getElement();
		assertTrue(number==5,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getElement();
		assertTrue(number==10,"The insert method is not doing the rotations in the correct way");

		assertTrue(root.getHeight()==1,"the height is not updaten when a rotation is done");
	}
	
	@Test
	public void insertTestCase2() throws ElementAlreadyExistException {
		AVLTree<Integer,Integer> tree = new AVLTree<>();
		tree.insert(10, 10);
		
		int number = tree.searchValue(10).getElement();
		assertTrue(number==10,"The insert method is not insertting the root");
		
		tree.insert(11, 11);
		tree.insert(12, 12);
		
		Node<Integer,Integer> root = tree.getRoot();
		assertTrue(root.getElement()==11,"the rotation is not working correctly");
		
		number = root.getLeft().getElement();
		assertTrue(number==10,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getElement();
		assertTrue(number==12,"The insert method is not doing the rotations in the correct way");

		assertTrue(root.getHeight()==1,"the height is not updaten when a rotation is done");
	}
	
	@Test
	public void insertTestCase3() throws ElementAlreadyExistException {
		AVLTree<Integer,Integer> tree = new AVLTree<>();
		tree.insert(5, 5);
		
		int number = tree.searchValue(5).getElement();
		assertTrue(number==5,"The insert method is not insertting the root");
		
		tree.insert(4, 4);
		tree.insert(2, 2);
		
		Node<Integer,Integer> root = tree.getRoot();
		assertTrue(root.getElement()==4,"the rotation is not working correctly");
		
		number = root.getLeft().getElement();
		assertTrue(number==2,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getElement();
		assertTrue(number==5,"The insert method is not doing the rotations in the correct way");

		assertTrue(root.getHeight()==1,"the height is not updaten when a rotation is done");
	}
	
	@Test
	public void insertTestCase4() throws ElementAlreadyExistException {
		AVLTree<Integer,Integer> tree = new AVLTree<>();
		tree.insert(20, 20);
		
		int number = tree.searchValue(20).getElement();
		assertTrue(number==20,"The insert method is not insertting the root");
		
		tree.insert(27, 27);
		tree.insert(22, 22);
		
		Node<Integer,Integer> root = tree.getRoot();
		assertTrue(root.getElement()==22,"the rotation is not working correctly");
		
		number = root.getLeft().getElement();
		assertTrue(number==20,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getElement();
		assertTrue(number==27,"The insert method is not doing the rotations in the correct way");

		assertTrue(root.getHeight()==1,"the height is not updaten when a rotation is done");
	}
	
	@Test
	public void insertTestCase5() throws ElementAlreadyExistException {
		AVLTree<Integer,Integer> tree = new AVLTree<>();
		tree.insert(22, 22);
		
		int number = tree.searchValue(22).getElement();
		assertTrue(number==22,"The insert method is not insertting the root");
		
		tree.insert(20, 20);
		tree.insert(27, 27);
		tree.insert(21, 21);
		tree.insert(10, 10);
		tree.insert(5, 5);
		
		Node<Integer,Integer> root = tree.getRoot();
		assertTrue(root.getElement()==20,"the rotation is not working correctly");
		
		number = root.getLeft().getElement();
		assertTrue(number==10,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getElement();
		assertTrue(number==22,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getRight().getElement();
		assertTrue(number==27,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getLeft().getElement();
		assertTrue(number==21,"The insert method is not doing the rotations in the correct way");
		
		number = root.getLeft().getLeft().getElement();
		assertTrue(number==5,"The insert method is not doing the rotations in the correct way");

		assertTrue(root.getHeight()==2,"the height is not updaten when a rotation is done");
	}
	
	public void insertTestCase6() throws ElementAlreadyExistException {
		AVLTree<Integer,Integer> tree = new AVLTree<>();
		tree.insert(20, 20);

		int number = tree.searchValue(20).getElement();
		assertTrue(number==20,"The insert method is not insertting the root");
		
		tree.insert(15, 15);
		tree.insert(30, 30);
		tree.insert(29, 29);
		tree.insert(40, 40);
		tree.insert(25, 25);
		
		Node<Integer,Integer> root = tree.getRoot();
		assertTrue(root.getElement()==29,"the rotation is not working correctly");
		
		number = root.getLeft().getElement();
		assertTrue(number==20,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getElement();
		assertTrue(number==30,"The insert method is not doing the rotations in the correct way");
		
		number = root.getLeft().getRight().getElement();
		assertTrue(number==25,"The insert method is not doing the rotations in the correct way");
		
		number = root.getLeft().getLeft().getElement();
		assertTrue(number==15,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getRight().getElement();
		assertTrue(number==40,"The insert method is not doing the rotations in the correct way");

		assertTrue(root.getHeight()==2,"the height is not updaten when a rotation is done");
	}
}
