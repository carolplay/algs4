import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Subset {
	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			rq.enqueue(item);
		}
		
		Iterator<String> it = rq.iterator();
		for (int i = 0; i < k; i++) {
			StdOut.println(it.next());
		}
		
	}
}
