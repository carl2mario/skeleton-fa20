public class LinkedListDeque<T> {
	public class Node{
		public T item;
		public Node next;
		public Node prev;

		public Node(T item, Node next, Node prev) {
			this.item = item;
			this.next = next;
			this.prev = prev;
		}

		public Node(T item) {
			this.item = item;
			this.next = null;
			this.prev = null;
		}
	}

	private int size;
	private final Node sentinel;

	public LinkedListDeque(){
		sentinel = new Node(null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	public void addFirst(T item){
		Node first = new Node(item, this.sentinel.next, this.sentinel);
		if (sentinel.next == sentinel)
			sentinel.prev = first;
		sentinel.next.prev = first;
		sentinel.next = first;
		size = size + 1;
	}

	public void addLast(T item){
		Node last = new Node(item, this.sentinel, this.sentinel.prev);
		if (sentinel.prev == sentinel)
			sentinel.next = last;
		sentinel.prev.next = last;
		sentinel.prev = last;
		size = size + 1;
	}

	public boolean isEmpty(){
		return sentinel.next == sentinel;
	}

	public int size(){return this.size;}

	public void printDeque(){
		Node temp = sentinel.next;
		while(temp != sentinel) {
			System.out.println(temp.item);
			temp = temp.next;
		}
		System.out.println("\n");
	}

	public T removeFirst(){
		if (sentinel.next == sentinel)
			return null;
		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		if (sentinel.next == sentinel)
			return null;
		return sentinel.next.item;
	}

	public T removeLast(){
		if (sentinel.prev == sentinel)
			return null;
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		if (sentinel.prev == sentinel)
			return null;
		return sentinel.prev.item;
	}

	public T get(int index){
		if(index >= size)
			return null;
		Node temp = sentinel.next;
		for(int i=0; i<index; i++){
			temp = temp.next;
		}
		return temp.item;
	}

	public T getRecursive(int index){
		if (sentinel.next == sentinel)
			return null;
		return getRecursive(index, sentinel.next);
	}

	public T getRecursive(int index, Node N){
		if (index != 0){
			return getRecursive(index -1, N.next);
		}
		else
			return N.item;
	}



	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		LinkedListDeque<Integer> intlist = new LinkedListDeque<>();
		System.out.println(intlist.isEmpty());
		System.out.println(intlist.removeFirst());
		intlist.addFirst(20);
		intlist.addFirst(10);
		intlist.addLast(30);
		System.out.println("size :" + intlist.size());
		intlist.printDeque();
		System.out.println(intlist.get(0));
		System.out.println("Recursive");
		System.out.println(intlist.getRecursive(0));
		System.out.println(intlist.getRecursive(1));
		System.out.println(intlist.getRecursive(2));
		System.out.println("End");
		System.out.println(intlist.removeFirst());
		System.out.println(intlist.removeLast());
		System.out.println(intlist.removeFirst());
		System.out.println(intlist.get(10));
		System.out.println(intlist.get(0));
		intlist.printDeque();
//		addIsEmptySizeTest();
//		addRemoveTest();
	}
} 