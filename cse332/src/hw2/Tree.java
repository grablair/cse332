package hw2;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary tree used for sorting values.
 * 
 * @author Graham Arthur Blair
 *
 * @param <E> The type of the tree.
 */
public class Tree<E extends Comparable<E>> {
	/** The root node of the tree */
	private TreeNode<E> root;
	
	/**
	 * Creates an empty tree.
	 */
	public Tree() {
		root = null;
	}
	
	/**
	 * Creates a tree with the root of <code>root</code>.
	 * @param root The root of the new tree.
	 */
	private Tree(TreeNode<E> root) {
		this.root = root;
	}
	
	/**
	 * Inserts <code>key</code> into the tree, sorted.
	 * 
	 * @param key The key to insert.
	 * @throws IllegalArgumentException if the key is null.
	 */
	public void insert(E key) {
		if (key == null)
			throw new IllegalArgumentException("key must not be null");
		root = insert(key, root);
	}
	
	/**
	 * Recursive helper to insert <code>key</code> into the
	 * subtree, <code>root</code>
	 * 
	 * @param key The key to insert.
	 * @param root The root of the subtree.
	 * @return The updated root of the subtree.
	 */
	private TreeNode<E> insert(E key, TreeNode<E> root) {
		if (root == null)
			return new TreeNode<E>(key);
		
		if (key.compareTo(root.key) > 0)
			root.right = insert(key, root.right);
		else if (key.compareTo(root.key) < 0)
			root.left = insert(key, root.left);
		else
			root.frequency++;
		
		return root;
	}
	
	/**
	 * Traverses the tree to create a sorted list of the trees
	 * contents. It does this non-recursively, by using
	 * pseudo-recursion which utilizes a stack.<br><br>
	 * 
	 * Mimics in-order traversal.
	 * 
	 * @return A list of the contents of the tree, sorted.
	 */
	public List<E> getSortedList() {
		Stack<TreeNode<E>> todo = new Stack<TreeNode<E>>();
		List<E> list = new ArrayList<E>();
		
		TreeNode<E> currentRoot = root;
		while (!todo.isEmpty() || currentRoot != null) {
			// Works to the farthest left leaf of the current subtree.
			while (currentRoot != null) {
				todo.push(currentRoot); 		// To print it's key and traverse
				currentRoot = currentRoot.left;	// it's right subtree later.
			}
			
			// Prints the next key(s) and works the right subtree of any
			// already-traversed nodes.
			if (!todo.isEmpty()) {
				for (int i = 0; i < todo.top().frequency; i++)
					list.add(todo.top().key);
				currentRoot = todo.pop().right;
			}
		}
		
		return list;
	}
	
	/**
	 * Gets the key of the root.
	 * 
	 * @return The key at the root node.
	 * @throws IllegalStateException if the tree is empty.
	 */
	public E firstKey() {
		if (isEmpty())
			throw new IllegalStateException("tree is empty");
		return root.key;
	}
	
	/**
	 * Creates a new tree starting with the left subtree's root.
	 * 
	 * @return The new tree.
	 * @throws IllegalStateException if the tree is empty.
	 */
	public Tree<E> getLeft() {
		if (isEmpty())
			throw new IllegalStateException("tree is empty");
		return new Tree<E>(root.left);
	}
	
	/**
	 * Creates a new tree starting with the right subtree's root.
	 * 
	 * @return The new tree.
	 * @throws IllegalStateException if the tree is empty.
	 */
	public Tree<E> getRight() {
		if (isEmpty())
			throw new IllegalStateException("tree is empty");
		return new Tree<E>(root.right);
	}
	
	/**
	 * Checks if the tree is empty.
	 * 
	 * @return 	<code>true</code> if the tree has one or more items.
	 * 			<code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Helper class for tree nodes.
	 * 
	 * @author Graham Arthur Blair
	 *
	 * @param <F> The type of the tree node.
	 */
	private class TreeNode<F extends Comparable<E>> {
		/** The root of the left subtree */
		private TreeNode<F> left;
		
		/** The root of the right subtree */
		private TreeNode<F> right;
		
		/** The key of this node */
		private F key;
		
		/** 
		 * The frequency of this key, i.e. how many times
		 * it's been added to the tree
		 */
		private int frequency;
		
		/**
		 * Creates a new tree node with key, <code>key</code>.
		 * 
		 * @param key The key of this node.
		 */
		private TreeNode(F key) {
			this(key, null, null);
		}
		
		/**
		 * Creates a new tree node with the key, <code>key</code> and
		 * the left and right subtrees, <code>left</code> and
		 * <code>right</code> respectively.
		 * 
		 * @param key The key of the new node.
		 * @param left The left-hand subtree root.
		 * @param right The right-hand subtree root.
		 */
		private TreeNode(F key, TreeNode<F> left, TreeNode<F> right) {
			this.key = key;
			this.left = left;
			this.right = right;
			frequency = 1;
		}
	}
}
