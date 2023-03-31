package pa4;
import java.util.Iterator;
import java.util.LinkedList;

public class UnderstandingLinkedList {

	static void print(LinkedList<Integer> numbers) { // printing the content of the list
		Iterator<Integer> it = numbers.iterator();
		while (it.hasNext())
			System.out.print(it.next() + " ");
		System.out.println();
	}

	static void remove(LinkedList<Integer> numbers, 
			int index) { // remove node at index
		if (numbers.size() == 0) // get the size of the list
			return; // nothing to remove
		if (index == 0)
			numbers.removeFirst(); // remove node at index 0
		else if (index == numbers.size() - 1)
			numbers.removeLast(); // remove node at last index
		else {
			int i = 0;
			Iterator<Integer> it = numbers.iterator();
			while (it.hasNext()) {
				it.next();
				if (i == index) { // once i = index, we are at desired node; delete it
					it.remove();
					return;
				}
				i++;
			}
		}
	}

	static void play() {
		LinkedList<Integer> numbers = new LinkedList<Integer>(); // creating an integer list
		numbers.addLast(15); // adding a number at the end
		numbers.addLast(99);
		numbers.addLast(-10);
		numbers.addLast(100);
		numbers.addFirst(0); // adding a number at the front
		numbers.addFirst(57);
		numbers.addFirst(-99);
		print(numbers); // prints: -99 57 0 15 99 -10 100

		remove(numbers, 0);
		print(numbers); // prints: 57 0 15 99 -10 100

		remove(numbers, numbers.size() - 1);
		print(numbers); // prints: 57 0 15 99 -10

		remove(numbers, 2);
		print(numbers); // prints: 57 0 99 -10
	}
	
	public static void main(String[] args) {
		play();
	}

}
