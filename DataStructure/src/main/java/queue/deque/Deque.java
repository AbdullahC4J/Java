package queue.deque;

public class Deque<E> {
    // The number of elements currently in the array
    private int size;
    // The maximum capacity of the queue
    private int capacity;
    // Position of the first element
    private int head;
    // Position of the last element.
    private int tail;
    // Internal array to store elements
    private Object[] arr;


    /**
     * Constructs an empty queue with default capacity of 10.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public Deque(){
        this(10);
    }

    /**
     * Constructs an empty queue with the specified capacity.
     * Time Complexity: O(1)
     * Space Complexity: O(n) where n is the capacity
     *
     * @param capacity the maximum number of elements that can be stored in the queue
     */
    public Deque(int capacity){
        if (capacity < 0)
            throw new IllegalArgumentException("The capacity of queue must be >= 0");

        this.capacity = capacity;
        arr = new Object[capacity];
    }

    /**
     * Returns the next position in the circular queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param pos current position in the queue
     * @return next position in the circular queue
     * @throws IllegalArgumentException if position is negative
     */
    private int nextPos(int pos){
        if (pos < 0)
            throw new IllegalArgumentException("The position of queue must be >= 0");

        int next = pos + 1;
        return (next == capacity) ? 0 : next;
//        return ++pos % capacity;  using if condition is more efficient than % .
    }

    /**
     * Returns the previous position in the circular queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param pos current position in the queue
     * @return previous position in the circular queue
     * @throws IllegalArgumentException if position is negative
     */
    private int prevPos(int pos){
        if (pos < 0)
            throw new IllegalArgumentException("The position of queue must be >= 0");

        int prev = pos - 1;
        return (prev == -1) ? (capacity - 1) : prev;
    }

    /**
     * Adds an element to the end of the queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param element the element to add
     * @throws RuntimeException if the queue is full
     */
    public void enqueueLast(E element){
        if (isFull())
            throw new RuntimeException("Queue is full");

        arr[tail] = element;
        tail = nextPos(tail);
        ++size;
    }

    /**
     * Adds an element at the began of the queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param element the element to add
     * @throws RuntimeException if the queue is full
     */
    public void enqueueFirst(E element){
        if (isFull())
            throw new RuntimeException("Queue is full");

        head = prevPos(head);
        arr[head] = element;
        ++size;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    @SuppressWarnings("unchecked")
    public E dequeueFirst(){
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        E element = (E) arr[head];
        head = nextPos(head);
        --size;
        return  element;
    }

    /**
     * Removes and returns the element at the end of the queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return the element at the end of the queue
     * @throws RuntimeException if the queue is empty
     */
    @SuppressWarnings("unchecked")
    public E dequeueLast(){
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        tail = prevPos(tail);
        E element = (E) arr[tail];
        --size;
        return  element;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    @SuppressWarnings("unchecked")
    public E peek(){
        if (isEmpty())
            throw new RuntimeException("Queue is empty!");
        return (E)arr[head];
    }

    /**
     * Checks if the queue is empty.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Checks if the queue is full.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return true if the queue is full, false otherwise
     */
    public boolean isFull(){
        return size == capacity;
    }

    /**
     * Returns a string representation of the queue.
     * Time Complexity: O(n) where n is the current size of the queue
     * Space Complexity: O(n) due to StringBuilder usage
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        if (size == 0) return "[]";

        StringBuilder str = new StringBuilder();
        str.append("[");

        int cur = head;
        for (int i = 0; i < size; i++) {
            str.append(arr[cur]);
            if (i < size - 1)
                str.append(", ");

            cur = nextPos(cur);
        }

        str.append("]");

        return str.toString();
    }
}
