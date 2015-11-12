package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode(null);
		tail = new LLNode(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		LLNode curr = new LLNode(element);
		curr.prev = tail.prev;
		curr.next = tail;
		curr.prev.next = curr;
		tail.prev = curr;
		size ++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index >= size || index < 0 ){
			throw new IndexOutOfBoundsException(); 
		}
		
		return getNode(index).data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		
		if (index > size || index < 0 ){
			throw new IndexOutOfBoundsException(); 
		}
		
		LLNode<E> previous = null;
		LLNode<E> next = null;
		
		if (index == size){
			previous = head;
			next = tail;
		}else {
			previous = this.getNode(index);
			next = this.getNode(index+1);
		}		
		
		
		LLNode<E> newNode = new LLNode<E>(element, next, previous);
		
		previous.next = newNode;
		next.prev = newNode;
		
		size ++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		return null;
	}
	
	private LLNode<E> getNode(int index){
		LLNode<E> current = head;
		for (int i = 0; i < index+1; i++) {
			current = current.next;
		}
		return current;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode<E> next, LLNode<E> previous) 
	{
		this.data = e;
		this.prev = previous;
		this.next = next;
	}
	
}
