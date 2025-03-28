package stack.linkedlist;

import java.util.EmptyStackException;

/**
 * An implementation of a Stack data structure using a linkedList.
 * This implementation provides standard stack operations like push, pop, and peek
 * The stack follows Last-In-First-Out (LIFO) principle.
 *
 * @param <E> The type of elements stored in the stack
 */
public class StackList<E>{
    // Pointer to first node.
    private Node<E> first;

    /**
     * Constructs an empty stack
     */
    public StackList(){
    }

    /**
     * Pushes an item onto the top of this stack.
     * Time Complexity: O(1) amortized
     *
     * @param data The element to be pushed onto the stack
     * @return The item that was pushed
     */
    public E push(E data){
        first = new Node<E>(data,first);
        return first.data;
    }

    /**
     * Removes and returns the item at the top of this stack.
     * Time Complexity: O(1)
     *
     * @return The item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public E pop(){
        if(isEmpty())
            throw new EmptyStackException();

        Node<E> element = first;
        first = first.next;

        return element.data;
    }

    /**
     * Returns the item at the top of this stack without removing it.
     * Time Complexity: O(1)
     *
     * @return The item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public E peek(){
        if(isEmpty())
            throw new EmptyStackException();

        return first.data;
    }

    /**
     * Checks if the stack is empty.
     * Time Complexity: O(1)
     * Memory Complexity: O(1)
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty(){
        return first == null;
    }

    /**
     * Returns a string representation of the stack.
     */
    @Override
    public String toString() {
        StringBuilder strList = new StringBuilder();
        strList.append("[");

        Node<E> curr = first;
        while (curr != null) {
            strList.append(curr.data);
            if (curr.next != null)
                strList.append(", ");

            curr = curr.next;
        }

        strList.append("]");
        return strList.toString();
    }

    /**
     * Node class for the LinkedList.
     * Contains the data and reference to the next node.
     */
    private static class Node<E>{
        E data;
        Node<E> next;

        public Node(E data,Node<E> next){
            this.data = data;
            this.next = next;
        }
    }
}
