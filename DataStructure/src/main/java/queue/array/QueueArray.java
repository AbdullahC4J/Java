package queue.array;

/**
 * A generic circular queue implementation using an array.
 * This queue maintains elements in FIFO (First-In-First-Out) order using a circular array structure.
 * The circular implementation allows efficient space utilization by wrapping around to the beginning
 * of the array when space is available.
 *
 * @param <E> the type of elements held in this queue
 */
public class QueueArray<E> {
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
    public QueueArray(){
        this(10);
    }

    /**
     * Constructs an empty queue with the specified capacity.
     * Time Complexity: O(1)
     * Space Complexity: O(n) where n is the capacity
     *
     * @param capacity the maximum number of elements that can be stored in the queue
     */
    public QueueArray(int capacity){
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

        ++pos;
        return (pos == capacity) ? 0 : pos;
//        return ++pos % capacity;  using if condition is more efficient than % .
    }

    /**
     * Adds an element to the end of the queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param element the element to add
     * @throws RuntimeException if the queue is full
     */
    public void enqueue(E element){
        if (isFull())
            throw new RuntimeException("Queue is full");

        arr[tail] = element;
        tail = nextPos(tail);
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
    public E dequeue(){
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        E element = (E) arr[head];
        head = nextPos(head);
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
        return (E)arr[head];
    }

    public int getSize() {
        return size;
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

    /*
public class QueueArray<E> {
    // The maximum capacity of the queue
    private int capacity;
    // Position of the first element
    private int head;
    // Position of the last element.
    private int tail;
    // Internal array to store elements
    private Object[] arr;


    public QueueArray(){
        this(10);
    }

    public QueueArray(int capacity){
        if (capacity < 0)
            throw new IllegalArgumentException("The capacity of queue must be >= 0");

        this.capacity = capacity + 1;   //To differentiate between full and empty.
        arr = new Object[capacity];
    }

    private int nextPos(int pos){
        if (pos < 0)
            throw new IllegalArgumentException("The position of queue must be >= 0");

        ++pos;
        return (pos == capacity) ? 0 : pos;
    }

    public void enqueue(E element){
        if (isFull())
            throw new RuntimeException("Queue is full");

        arr[tail] = element;
        tail = nextPos(tail);
    }

    @SuppressWarnings("unchecked")
    public E dequeue(){
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        E element = (E) arr[head];
        head = nextPos(head);
        return  element;
    }

    @SuppressWarnings("unchecked")
    public E peek(){
        return (E)arr[head];
    }

    public boolean isEmpty(){
        return head == tail;
    }

    public boolean isFull(){
        return nextPos(tail) == head;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder str = new StringBuilder();
        str.append("[");

        int cur = head;
        while (cur != tail) {
            str.append(arr[cur]);
            cur = nextPos(cur);
            if (cur != tail) {
                str.append(", ");
            }
        }

        str.append("]");
        return str.toString();
    }
}
*/
