package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.RBTNode;
import model.RedBlackTree;

class RedBlackTreeTest {

	@Test
	void test() {
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();	
		
		RBTNode<Integer,Integer> toAdd = new RBTNode<Integer,Integer>(50,50);	
		tree.insert(tree.getRoot(), toAdd);
		assertTrue(tree.getRoot().getElement() == 50 && tree.getRoot().getColor() == false);		
		
		toAdd = new RBTNode<Integer,Integer>(70,70);		
		tree.insert(tree.getRoot(), toAdd);
		assertTrue(tree.getRoot().getRight().getElement() == 70 && tree.getRoot().getRight().getColor() == true);
		
		toAdd = new RBTNode<Integer,Integer>(69,69);	
		tree.insert(tree.getRoot(), toAdd);
		assertTrue(tree.getRoot().getElement() == 69 && tree.getRoot().getColor() == false);
		assertTrue(tree.getRoot().getLeft().getElement() == 50 && tree.getRoot().getLeft().getColor() == true);
		assertTrue(tree.getRoot().getRight().getElement() == 70 && tree.getRoot().getRight().getColor() == true);
		
		toAdd = new RBTNode<Integer,Integer>(71,71);	
		tree.insert(tree.getRoot(), toAdd);
		assertTrue(tree.getRoot().getLeft().getElement() == 50 && tree.getRoot().getLeft().getColor() == false);
		assertTrue(tree.getRoot().getRight().getElement() == 70 && tree.getRoot().getLeft().getColor() == false);
		assertTrue(tree.getRoot().getRight().getRight().getElement() == 71 && tree.getRoot().getRight().getRight().getColor() == true);
		
		toAdd = new RBTNode<Integer,Integer>(72,72);	
		tree.insert(tree.getRoot(), toAdd);
		assertTrue(tree.getRoot().getElement() == 69 && tree.getRoot().getColor() == false);
		assertTrue(tree.getRoot().getRight().getLeft().getElement() == 70 && tree.getRoot().getRight().getRight().getElement() == 72);
		assertTrue(tree.getRoot().getRight().getLeft().getColor() == true && tree.getRoot().getRight().getRight().getColor() == true);
		
		toAdd = new RBTNode<Integer,Integer>(48,48);	
		tree.insert(tree.getRoot(), toAdd);
		assertTrue(tree.getRoot().getLeft().getLeft().getElement() == 48 && tree.getRoot().getLeft().getLeft().getColor() == true);
		
		toAdd = new RBTNode<Integer,Integer>(49,49);	
		tree.insert(tree.getRoot(), toAdd);
		assertTrue(tree.getRoot().getLeft().getElement() == 49 && tree.getRoot().getLeft().getColor() == false);
		assertTrue(tree.getRoot().getLeft().getLeft().getElement() == 48 && tree.getRoot().getLeft().getLeft().getColor() == true);
		assertTrue(tree.getRoot().getLeft().getRight().getElement() == 50 && tree.getRoot().getLeft().getRight().getColor() == true);
		
		tree.delete(71);
		tree.prettyPrint();
	}

}
