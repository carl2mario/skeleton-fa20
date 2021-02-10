public class ArrayDeque<T> {

	private int nextHead;
	private int nextTail;
	private int size;
	private T[] array;
	private double UTILIZATION = 0.25;
	private int SCALE_FACTOR = 2;

	public ArrayDeque(){
		array = (T[]) new Object[8];
		size = 0;
		nextHead = 4; //initial index
		nextTail = 5;
	}

	public void addFirst(T item){
		if (size >= array.length) {
			resizeArray(array.length * SCALE_FACTOR);
		}
		array[nextHead] = item;
		nextHead = getLeftIndex(nextHead);
		size = size + 1;
	}

	private void resizeArray(int newLength){
		final T[] temp = (T[]) new Object[newLength];
		int head = getRightIndex(nextHead);
		int tail = getLeftIndex(nextTail);
		if (head < tail) {
			System.arraycopy(array, head, temp, 0, tail - head + 1);
		}
		else {
			System.arraycopy(array, head, temp, 0, array.length-head);
			System.arraycopy(array, 0, temp, array.length-head, tail+1);
		}
		array = temp;
		nextHead = getLeftIndex(0);
		nextTail = size;
	}

	private int getLeftIndex(int index){
		if (index == 0)
			return array.length-1;
		else {
			return index - 1;
		}
	}

	private int getRightIndex(int index){
		if (index == array.length-1)
			return 0;
		else {
			return index + 1;
		}
	}

	public void addLast(T item){
		if (size >= array.length) {
			resizeArray(array.length * SCALE_FACTOR);
		}
		array[nextTail] = item;
		nextTail = getRightIndex(nextTail);
		size = size + 1;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public int size(){return this.size;}

	public void printDeque(){
		for(int i=0; i< array.length; i++){
			System.out.println(array[i]);
		}
		System.out.println("\n");
	}

	public T removeFirst(){
		if (size == 0)
			return null;
		int head = getRightIndex(nextHead);
		array[head] = null;
		nextHead = head;
		T first = array[getRightIndex(nextHead)];
		size = size - 1;
		if (size < Math.round(UTILIZATION*array.length)) {
			if (array.length>8 && size != 0) {
				resizeArray(Math.round(array.length / SCALE_FACTOR));
			}
		}
		return first;
	}

	public T removeLast(){
		if (size == 0)
			return null;
		int tail = getLeftIndex(nextTail);
		array[tail] = null;
		nextTail = tail;
		T last = array[getLeftIndex(nextTail)];
		size = size - 1;
		if (size < Math.round(UTILIZATION*array.length)) {
			if (array.length>8 && size != 0) {
				resizeArray(Math.round(array.length / SCALE_FACTOR));
			}
		}
		return last;
	}

	public T get(int index){
		if(size == 0)
			return null;
		return array[index];
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		System.out.println(deque.isEmpty());
		System.out.println(deque.removeFirst());
		deque.addFirst(20);
		deque.addFirst(10);
		deque.removeFirst();
		deque.addFirst(10);
		deque.addLast(30);
		deque.removeLast();
		System.out.println(deque.isEmpty());
		deque.removeLast();
		deque.removeFirst();
		System.out.println("size :" + deque.size());
		deque.printDeque();
		deque.addFirst(20);
		deque.addFirst(10);
		deque.addLast(30);
		deque.addLast(40);
		deque.addLast(50);
		deque.addLast(60);
		System.out.println("size :" + deque.size());
		deque.addLast(70);
		deque.addLast(80);
		deque.addLast(90);
		System.out.println("size :" + deque.size());
		deque.addLast(100);
		deque.printDeque();
		deque.removeFirst();
		deque.removeFirst();
		deque.removeFirst();
		deque.removeFirst();
		deque.removeFirst();
		deque.removeFirst();
		deque.removeFirst();
		deque.removeFirst();
		deque.printDeque();
		System.out.println("size :" + deque.size());
//		addIsEmptySizeTest();
//		addRemoveTest();
	}
} 