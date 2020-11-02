package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import customExceptions.ElementAlreadyExistException;
import model.RedBlackTree;

class RedBlackTreeTest {

	@Test
	void test() throws ElementAlreadyExistException{
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>(-959, null);	
		
		tree.insertion(50, 50);		
		tree.insertion(70,70);
		assertTrue(tree.getRoot().getRight().getElement() == 70 && tree.getRoot().getRight().getColor() == true);
		
		tree.insertion(69, 69);
		assertTrue(tree.getRoot().getElement() == 69 && tree.getRoot().getColor() == false);
		assertTrue(tree.getRoot().getLeft().getElement() == 50 && tree.getRoot().getLeft().getColor() == true);
		assertTrue(tree.getRoot().getRight().getElement() == 70 && tree.getRoot().getRight().getColor() == true);
		
		tree.insertion(71, 71);
		assertTrue(tree.getRoot().getLeft().getElement() == 50 && tree.getRoot().getLeft().getColor() == false);
		assertTrue(tree.getRoot().getRight().getElement() == 70 && tree.getRoot().getLeft().getColor() == false);
		assertTrue(tree.getRoot().getRight().getRight().getElement() == 71 && tree.getRoot().getRight().getRight().getColor() == true);
		
		tree.insertion(72, 72);
		assertTrue(tree.getRoot().getElement() == 69 && tree.getRoot().getColor() == false);
		assertTrue(tree.getRoot().getRight().getLeft().getElement() == 70 && tree.getRoot().getRight().getRight().getElement() == 72);
		assertTrue(tree.getRoot().getRight().getLeft().getColor() == true && tree.getRoot().getRight().getRight().getColor() == true);
		
		tree.insertion(48, 48);
		assertTrue(tree.getRoot().getLeft().getLeft().getElement() == 48 && tree.getRoot().getLeft().getLeft().getColor() == true);
		
		tree.insertion(49, 49);
		assertTrue(tree.getRoot().getLeft().getElement() == 49 && tree.getRoot().getLeft().getColor() == false);
		assertTrue(tree.getRoot().getLeft().getLeft().getElement() == 48 && tree.getRoot().getLeft().getLeft().getColor() == true);
		assertTrue(tree.getRoot().getLeft().getRight().getElement() == 50 && tree.getRoot().getLeft().getRight().getColor() == true);

		try {
			tree.insertion(50, 50);
			fail();
		} catch (Exception e) {
		}
		
	}

}
