package queue.linkedlist;

import linkedlist.singlelinkedlist.SingleLinkedList;

public class QueueList<E> {
    // Internal list to store elements
    private SingleLinkedList<E> list;

    /**
     * Constructs an empty queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1) where n is the capacity
     */
    public QueueList(){
        list = new SingleLinkedList<>();
    }

    /**
     * Adds an element to the end of the queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param element the element to add
     */
    public void enqueue(E element){
        list.addLast(element);
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

        E element = list.getFirst();
        list.removeFirst();

        return element;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    public E peek(){
        return list.getFirst();
    }

    /**
     * Clears all elements from the queue.
     * Time Complexity: O(1) or O(n) depending on SingleLinkedList implementation
     * Space Complexity: O(1)
     */
    public void clear() {
        list = new SingleLinkedList<>();
    }

    /**
     * Checks if the queue contains the specified element.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param element the element to check for
     * @return true if the queue contains the element, false otherwise
     */
    public boolean contains(E element) {
        return list.linearSearch(element) >= 0;
    }

    /**
     * Checks if the queue is empty.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty(){
        return list.getSize() == 0;
    }

    /**
     * Return size ot the queue.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return size od the queue
     */
    public int size(){
        return list.getSize();
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
        return list.toString();
    }

}
