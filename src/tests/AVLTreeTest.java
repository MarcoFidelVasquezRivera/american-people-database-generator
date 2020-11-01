package tests;

import static org.junit.jupiter.api.Assertions.*;
import model.AVLTree;
import model.Node;

import org.junit.jupiter.api.Test;

class AVLTreeTest {

	@Test
	public void InsertTest() {
		AVLTree<Integer,Integer> tree = new AVLTree<>();
		tree.insert(10, 10);
		
		int number = tree.searchValue(10).getElement();
		assertTrue(number==10,"The insert method is not insertting the root");
		
		tree.insert(5, 5);
		tree.insert(6, 6);
		
		Node<Integer,Integer> root = tree.getRoot();
		System.out.println(root.getElement());
		assertTrue(root.getElement()==6,"the rotation is not working correctly");
		
		number = root.getLeft().getElement();
		assertTrue(number==5,"The insert method is not doing the rotations in the correct way");
		
		number = root.getRight().getElement();
		assertTrue(number==10,"The insert method is not doing the rotations in the correct way");

		System.out.println(root.getLeft().getHeight());
		assertTrue(root.getHeight()==1,"the height is not updaten when a rotation is done");
	}

}
