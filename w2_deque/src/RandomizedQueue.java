import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
   
	private int l = 0;
	private Item[] items = (Item[]) new Object[1];
	
	// construct an empty randomized queue
	public RandomizedQueue() {

	}
   
	// is the queue empty?
	public boolean isEmpty() { return l == 0; }
   
	// return the number of items on the queue
	public int size() {	return l; }
   
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < l; i++) 
			temp[i] = items[i];
		items = temp;		
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) throw (
				new NullPointerException("attempts to add a null item"));
		if (l == items.length) resize( items.length * 2);
		items[l++] = item;
	}
   
	// remove and return a random item
	public Item dequeue() {
		if (this.isEmpty()) throw (
				new NoSuchElementException(
						"Empty queue, cant remove."));
		int i = StdRandom.uniform(l);
		Item result = items[i];
		items[i] = items[--l];
		items[l] = null;
		if (l > 0 && l == items.length / 4) resize(items.length / 4);
		return result;
	}
   
	// return (but do not remove) a random item
	public Item sample() {
		if (this.isEmpty()) throw (
				new NoSuchElementException(
						"Empty queue, cant remove."));
		int i = StdRandom.uniform(l);
		return items[i];
	}
   
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RQIterator();
	}
   
	private class RQIterator implements Iterator<Item> {
		
		private int[] order = new int[l];
		private int p = 0;
		
		public RQIterator() {
			for (int i = 0; i < l; i++) 
				order[i] = i;
			StdRandom.shuffle(order);
		}
		
        public boolean hasNext()  { return p < l;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[order[p++]];
        }
    }
	
	// unit testing
	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(1);
		rq.enqueue(2);
		rq.dequeue();
		rq.sample();
		
		Iterator<Integer> it = rq.iterator();
		it.hasNext();
		it.next();
		
		rq.dequeue();
		rq.size();
		rq.isEmpty();
		
		
		
		
	}
}