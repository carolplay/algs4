import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;


public class Deque<Item> implements Iterable<Item> {
	private int n; 	// deque size
	private Node first;
	private Node last;
	
	// helper linked list class
    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }
	
	public Deque()                           // construct an empty deque
	{
		this.n = 0;
		this.first = null;
		this.last = null;
	}

	public boolean isEmpty()                 // is the deque empty?
	{
		return size() == 0;
	}

	public int size()                        // return the number of items on the deque
	{
		return n;
	}

	// Throw a java.lang.NullPointerException if the client attempts to add a null item; 
	public void addFirst(Item item)          // add the item to the front
	{
		if (item == null) throw (
				new NullPointerException("attempts to add a null item"));
		Node node = new Node();
		node.prev = null;
		node.item = item;
		node.next = first;
		if (n != 0) {
			first.prev = node;
		} else {
			last = node;
		}
		first = node;
		n++;
	}

	public void addLast(Item item)           // add the item to the end
	{
		if (item == null) throw (
				new NullPointerException("attempts to add a null item"));
		Node node = new Node();
		node.next = null;
		node.item = item;
		node.prev = last;
		if (n != 0) {
			last.next = node;
		} else {
			first = node;
		}
		last = node;
		n++;
	}

	// throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque; 
	public Item removeFirst()                // remove and return the item from the front
	{
		if (this.isEmpty()) throw (
				new NoSuchElementException(
						"Empty deque, cant remove."));
		Item item = first.item;
		if (n == 1) {
			first = null;
			last = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		n--;
		return item;
	}

	public Item removeLast()                 // remove and return the item from the end
	{
		if (this.isEmpty()) throw (
				new NoSuchElementException(
						"Empty deque, cant remove."));
		Item item = last.item;
		if (n == 1) {
			first = null;
			last = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		n--;
		return item;
	}
	
	// throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator; 
	// throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{
		return new DequeIterator(); 
	}

	private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
	
	public static void main(String[] args)   // unit testing
	{
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(0);
		deque.removeFirst();
		deque.isEmpty();
		deque.addFirst(1);
		deque.removeLast();
		
		 deque.addFirst(0);
         deque.addFirst(1);
         deque.isEmpty();
         deque.removeLast();
         
		deque.addFirst(1);
		Iterator<Integer> it = deque.iterator();
		it.hasNext();
		it.next();
	}
}