package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import customExceptions.ElementAlreadyExistException;
import model.RedBlackTree;

class RedBlackTreeTest {
	
	//Tree has a pretty print function which helps visualize it more easily.
	private RedBlackTree<Integer,Integer> blackTree;
	
	public void setup1() throws ElementAlreadyExistException {
		blackTree = new RedBlackTree<Integer, Integer>(-9859341, null);
		blackTree.redBlackInsertion(15, 15);
		blackTree.redBlackInsertion(9, 9);
		blackTree.redBlackInsertion(8, 8);
		blackTree.redBlackInsertion(10, 10);
		blackTree.redBlackInsertion(18, 18);
		blackTree.redBlackInsertion(16, 16);
		blackTree.redBlackInsertion(20, 20);
	}
	

	@Test
	void insertTest() throws ElementAlreadyExistException{
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>(-1, null);	
		
		tree.redBlackInsertion(50,50);		
		assertTrue(tree.getRoot().getElement() == 50, "Tree is not adding proper value to the root");
		assertTrue(tree.getRoot().getColor() == false, "Tree is not adding root as black");
		
		tree.redBlackInsertion(60, 60);
		assertTrue(tree.getRoot().getRight().getElement() == 60, "Tree is not adding right nodes properly");
		assertTrue(tree.getRoot().getRight().getColor() == true, "Tree is not adding new nodes as red");
		
		tree.redBlackInsertion(40, 40);
		assertTrue(tree.getRoot().getLeft().getElement() == 40, "Tree is not adding left nodes properly");
		assertTrue(tree.getRoot().getLeft().getColor() == true, "Tree is not adding new nodes as red");
		
		tree.redBlackInsertion(41, 41);
		assertTrue(tree.getRoot().getLeft().getRight().getElement() == 41, "Tree is not adding left nodes properly");
		assertTrue(tree.getRoot().getLeft().getRight().getColor() == true, "Tree is not adding new nodes as red");
		assertTrue(tree.getRoot().getLeft().getColor() == false, "Tree is not changing colors properly");	
		
		tree.redBlackInsertion(39, 39);
		assertTrue(tree.getRoot().getLeft().getLeft().getElement() == 39, "Tree is not adding left nodes properly");
		assertTrue(tree.getRoot().getLeft().getLeft().getColor() == true, "Tree is not adding new nodes as red");
		
		tree.redBlackInsertion(59, 59);
		assertTrue(tree.getRoot().getRight().getLeft().getElement() == 59, "Tree is not adding right-left children nodes properly");
		assertTrue(tree.getRoot().getLeft().getLeft().getColor() == true, "Tree is not adding new nodes as red");	
		
		tree.redBlackInsertion(61, 61);
		assertTrue(tree.getRoot().getRight().getRight().getElement() == 61, "Tree is not adding right-right nodes properly");
		assertTrue(tree.getRoot().getLeft().getLeft().getColor() == true, "Tree is not adding right-right colors properly");
		
		tree.redBlackInsertion(62, 62);
		assertTrue(tree.getRoot().getRight().getRight().getRight().getElement() == 62, "Tree is not adding maximum nodes properly");
		assertTrue(tree.getRoot().getRight().getRight().getRight().getColor() == true, "Tree is not adding new nodes as red");
		assertTrue(tree.getRoot().getRight().getColor() == true, "Tree is not changing left colors properly");
		
		tree.redBlackInsertion(63, 63);
		assertTrue(tree.getRoot().getRight().getRight().getElement() == 62, "Tree is not doing left rotate properly");
		assertTrue(tree.getRoot().getRight().getRight().getColor() == false, "Tree is changing subroot colors on left rotate properly");
		
		assertTrue(tree.getRoot().getRight().getRight().getRight().getElement() == 63, "Tree is not doing left rotate properly");
		assertTrue(tree.getRoot().getRight().getRight().getRight().getColor() == true, "Tree is changing right child colors on left rotate properly");
		
		assertTrue(tree.getRoot().getRight().getRight().getLeft().getElement() == 61, "Tree is not doing left rotate properly");
		assertTrue(tree.getRoot().getRight().getRight().getLeft().getColor() == true, "Tree is changing left child colors on left rotate properly");

		tree.redBlackInsertion(38, 38);
		tree.redBlackInsertion(37, 37);
		
		assertTrue(tree.getRoot().getLeft().getLeft().getElement() == 38, "Tree is not doing left rotate properly");
		assertTrue(tree.getRoot().getLeft().getLeft().getColor() == false, "Tree is changing subroot colors on left rotate properly");
		
		assertTrue(tree.getRoot().getLeft().getLeft().getRight().getElement() == 39, "Tree is not doing left rotate properly");
		assertTrue(tree.getRoot().getLeft().getLeft().getRight().getColor() == true, "Tree is changing right child colors on left rotate properly");
		
		assertTrue(tree.getRoot().getLeft().getLeft().getLeft().getElement() == 37, "Tree is not doing left rotate properly");
		assertTrue(tree.getRoot().getLeft().getLeft().getLeft().getColor() == true, "Tree is changing left child colors on left rotate properly");

		tree.redBlackInsertion(42, 42);
		tree.redBlackInsertion(43, 43);
		
		assertTrue(tree.getRoot().getLeft().getRight().getElement() == 42, "Tree is not doing left rotate properly");
		assertTrue(tree.getRoot().getLeft().getLeft().getColor() == false, "Tree is changing subroot colors on left rotate properly");
		
		assertTrue(tree.getRoot().getLeft().getRight().getRight().getElement() == 43, "Tree is not doing left rotate properly");
		assertTrue(tree.getRoot().getLeft().getRight().getRight().getColor() == true, "Tree is changing right child colors on left rotate properly");
		
		assertTrue(tree.getRoot().getLeft().getRight().getLeft().getElement() == 41, "Tree is not doing left rotate properly");
		assertTrue(tree.getRoot().getLeft().getRight().getLeft().getColor() == true, "Tree is changing left child colors on left rotate properly");
		
		tree.redBlackInsertion(51, 51);
		tree.redBlackInsertion(52, 52);
		
		assertTrue(tree.getRoot().getRight().getLeft().getElement() == 52, "Tree is not doing left-right rotate properly");
		assertTrue(tree.getRoot().getRight().getRight().getColor() == false, "Tree is changing subroot colors on left rotate properly");
		
		assertTrue(tree.getRoot().getRight().getLeft().getRight().getElement() == 59, "Tree is not doing left-right rotate properly");
		assertTrue(tree.getRoot().getRight().getLeft().getRight().getColor() == true, "Tree is changing right child colors on left rotate properly");
		
		assertTrue(tree.getRoot().getRight().getLeft().getLeft().getElement() == 51, "Tree is not doing left-right rotate properly");
		assertTrue(tree.getRoot().getRight().getLeft().getLeft().getColor() == true, "Tree is changing left child colors on left rotate properly");
				
		
		try {
			tree.redBlackInsertion(50, 50);
			fail();
		} catch (Exception e) {
		}
	}

	
	@Test
	void searchTest() throws ElementAlreadyExistException{
		setup1();
		
		assertNull(blackTree.searchValue(1), "Search is not working when the node is not in the tree");
		assertTrue(blackTree.searchValue(9) != null, "Search is not working with the root");
		assertTrue(blackTree.searchValue(8) != null, "Search is not working with the root");
		assertNull(blackTree.searchValue(-100), "Search is not working with the root");
		assertTrue(blackTree.searchValue(10) != null, "Search is not working with the root");
		assertTrue(blackTree.searchValue(20) != null, "Search is not working with the root");
		
	}
	
	
	@Test
	 
	void deleteTest() throws ElementAlreadyExistException{
		setup1();

		assertFalse(blackTree.delete(50), "Tree is deleting a value it should not");

		assertTrue(blackTree.delete(18), "Tree is not deleting correctly");
		assertTrue(blackTree.getRoot().getRight().getRight().getColor() == false, "The color of the replacement is not correct");
		
		assertTrue(blackTree.delete(9), "Tree is not deleting correctly");
		assertTrue(blackTree.getRoot().getColor() == false, "The color of the replacement is not correct");	
		
		assertTrue(blackTree.delete(20), "Tree is not deleting correctly");
		assertTrue(blackTree.getRoot().getRight().getRight().getColor() == false, "The color of the replacement is not correct");
		
		assertFalse(blackTree.delete(9), "Tree is deleting a value it should not");
	
		assertTrue(blackTree.delete(16), "Tree is not deleting correctly");
		assertTrue(blackTree.getRoot().getRight().getRight().getColor() == false, "The color of the replacement is not correct");
		
		assertTrue(blackTree.delete(10), "Tree is not deleting correctly");
		assertTrue(blackTree.getRoot().getColor() == false, "The color of the replacement is not correct");
		
		assertFalse(blackTree.delete(10), "Tree is deleting a value it should not");

		assertTrue(blackTree.delete(8), "Tree is not deleting correctly");
		assertTrue(blackTree.getRoot().getColor() == false, "The color of the replacement is not correct");
		
		assertTrue(blackTree.delete(15), "Tree is not deleting correctly");
		assertNull(blackTree.getRoot().getElement());		
		
		blackTree.redBlackInsertion(45, 45);
		assertTrue(blackTree.delete(45), "Tree is not deleting an added value");
		assertNull(blackTree.getRoot().getElement());
		
	}
	
	
}
