package hw2;

import java.util.EmptyStackException;

/**
 * An implementation of a Stack using linked nodes.
 * 
 * @author Graham Arthur Blair
 *
 * @param <E> The type of the stack.
 */
public class Stack<E> {
	/** The top node in the stack */
	private StackNode<E> first;
	
	/**
	 * Creates an empty stack.
	 */
	public Stack() {
		first = null;
	}
	
	/**
	 * Pushes <code>key</code> to the top of the stack.
	 * 
	 * @param key The key to push.
	 */
	public void push(E key) {
		StackNode<E> StackNode = new StackNode<E>(key, first);
		first = StackNode;
	}
	
	/**
	 * Removes the top key on the stack and returns it.
	 * 
	 * @return The top key on the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E pop() {
		if (isEmpty())
			throw new EmptyStackException();
		E key = first.key;
		first = first.next;
		return key;
	}
	
	/**
	 * Removes the top key on the stack.
	 * 
	 * @return The top key on the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E top() {
		if (isEmpty())
			throw new EmptyStackException();
		return first.key;
	}
	
	/**
	 * Checks if the stack is empty.
	 * 
	 * @return 	<code>true</code> if the stack has one or more items.
	 * 			<code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * Stack node for use in stack management.
	 * 
	 * @author Graham Arthur Blair
	 *
	 * @param <F> Type of the stack node.
	 */
	private class StackNode<F> {
		/** The next node in the stack */
		private StackNode<F> next;
		/** The key value at this node */
		private F key;
		
		/**
		 * Creates a new <code>StackNode</code> with the key,
		 * <code>key</code>.
		 * 
		 * @param key The key of this new node.
		 */
		private StackNode(F key) {
			this.key = key;
		}
		
		/**
		 * Creates a new <code>StackNode</code> with the key,
		 * <code>key</code>, preceding the node <code>next</code>.
		 * 
		 * @param key The key of this new node.
		 * @param next The node that is after this one.
		 */
		private StackNode(F key, StackNode<F> next) {
			this.next = next;
			this.key = key;
		}
	}
}
