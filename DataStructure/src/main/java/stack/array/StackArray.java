package stack.array;

import arraylist.MyArrayList;
import java.util.NoSuchElementException;

/**
 * An implementation of a Stack data structure using a dynamic array (MyArrayList).
 * This implementation provides standard stack operations like push, pop, and peek
 * with a fixed maximum capacity. The stack follows Last-In-First-Out (LIFO) principle.
 *
 * @param <E> The type of elements stored in the stack
 */
public class StackArray <E> {
    // The maximum capacity of the stack
    private int capacity;
    // Index of the top element
    private int top;
    // Array to store stack elements
    private MyArrayList<E> array;

    /**
     * Constructs an empty stack with initial capacity of 10.
     * The underlying array will have a default capacity.
     */
    public StackArray(){
        this(10);
    }

    /**
     * Constructs an empty stack with the specified maximum capacity.
     *
     * @param capacity The maximum number of elements the stack can hold
     * @throws IllegalArgumentException if capacity is negative
     */
    public StackArray(int capacity){
        if (capacity < 0)
            throw new IllegalArgumentException("Stack capacity cannot be negative");
        this.capacity = capacity;
        this.top = -1;
        this.array = new MyArrayList<>(capacity);
    }

    /**
     * Pushes an item onto the top of this stack.
     * Time Complexity: O(1) amortized
     *
     * @param item The element to be pushed onto the stack
     * @return The item that was pushed
     * @throws IllegalStateException if the stack is full
     */
    public E push(E item) {
        if (isFull())
            throw new IllegalStateException("Stack is full");
        top++;
        array.addLast(item);
        return item;
    }

    /**
     * Removes and returns the item at the top of this stack.
     * Time Complexity: O(1)
     *
     * @return The item at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public E pop(){
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");

        return array.remove(top--);
    }

    /**
     * Returns the item at the top of this stack without removing it.
     * Time Complexity: O(1)
     *
     * @return The item at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public E peek(){
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");

        return array.get(top);
    }

    /**
     * Expands the maximum capacity of the stack to the specified capacity.
     * Note: This only updates the capacity limit, not the actual array capacity.
     *
     * @param newCapacity The new maximum capacity for the stack
     */
    public void expandCapacity(int newCapacity){
        if (newCapacity < capacity)
            throw new IllegalArgumentException("New capacity cannot be smaller than current capacity");
        this.capacity = newCapacity;
    }

    /**
     * Checks if the stack has reached its maximum capacity.
     * Time Complexity: O(1)
     *
     * @return true if the stack is full, false otherwise
     */
    public boolean isFull(){
        return top == capacity - 1;
    }

    /**
     * Checks if the stack is empty.
     * Time Complexity: O(1)
     * Memory Complexity: O(1)
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty(){
        return top == - 1;
    }

    /**
     * Returns the number of elements in the stack.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return The number of elements in the stack
     */
    public int size() {
        return top + 1;
    }

    /**
     * Inserts an element at the bottom of the stack.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param item The element to be inserted at the bottom of the stack
     */
    public void insertAtBottom(E item){ // could be implemented by using auxiliary stack or array
        if(isEmpty()){
            push(item);
        }else{
            E tempPeek = pop();
            insertAtBottom(item);
            push(tempPeek);
        }
    }

    /**
     * Reverses the order of elements in the stack.
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public void reverse(){
        if (isEmpty())
            return;

        E tempPeek = pop();
        reverse();
        insertAtBottom(tempPeek);
    }
//    MyArrayList<E> newArray = new MyArrayList<>(capacity);
//        while (!isEmpty()){
//        newArray.addLast(pop());
//    }
//        this.array = newArray;

    /**
     * Returns a string representation of the stack.
     */
    @Override
    public String toString(){
        return array.toString();
    }
}


/* implementation of two stacks with one array list (need to modify the arraylist class)
public class StackArray <E> {
    // The maximum capacity of the stack
    private int capacity;
    // Index of the top element
    private int top1;
    // Index of the top element
    private int top2;
    // Array to store stack elements
    private MyArrayList<E> array;


    public StackArray(){
        this(10);
    }


    public StackArray(int capacity){
        if (capacity < 0)
            throw new IllegalArgumentException("Stack capacity cannot be negative");
        this.capacity = capacity;
        this.top1 = -1;
        this.top2 = capacity;
        this.array = new MyArrayList<>(capacity);
    }

    public E push(int stackId, E item) {
        if (isFull())
            throw new IllegalStateException("Stack is full");
        if (stackId == 1)
            array.add(++top1,item);
        else
            array.add(--top2,item);

        return item;
    }

    public E pop(int stackId){
        if (isEmpty(stackId))
            throw new NoSuchElementException("Stack is empty");

        if (stackId == 1)
            return array.remove(top1--);
        else
            return array.remove(top2++);
    }

    public E peek(int stackId){
        if (isEmpty(stackId))
            throw new NoSuchElementException("Stack is empty");

        if(stackId == 1)
            return array.get(top1);
        else
            return array.get(top2);
    }

    public void expandCapacity(int newCapacity){
        if (newCapacity < capacity)
            throw new IllegalArgumentException("New capacity cannot be smaller than current capacity");
        this.capacity = newCapacity;
    }

    public boolean isFull(){
        return top2 <= top1 + 1;
    }

    public boolean isEmpty(int stackId){
        if (stackId == 1)
            return top1 == -1;
        else
            return top2 == capacity;
    }

    @Override
    public String toString(){
        return array.toString();
    }
}
*/

/* Another implementation of stack using addedElement instead of top (Not efficient implementation)

public class StackArray<E>{
    // The maximum capacity of the stack
    private int capacity;
    // Index of the top element
    private int addedElement;
    // Array to store stack elements
    private MyArrayList<E> array;


    public StackArray(){
        this(10);
    }

    public StackArray(int capacity){
        if (capacity < 0)
            throw new IllegalArgumentException("Stack capacity cannot be negative");
        this.capacity = capacity;
        this.addedElement = 0;
        this.array = new MyArrayList<>(capacity);
    }

    public E push(E item) {
        if (isFull())
            throw new IllegalStateException("Stack is full");

        array.addLast(item);
        array.rightRotate(1);
        addedElement++;
        return item;
    }

    public E pop(){
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");

        addedElement--;
        return array.remove(0);
    }

    public E peek(){
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");

        return array.get(0);
    }

    public void expandCapacity(int newCapacity){
        if (newCapacity < capacity)
            throw new IllegalArgumentException("New capacity cannot be smaller than current capacity");
        this.capacity = newCapacity;
    }

    public boolean isFull(){
        return addedElement == capacity;
    }

    public boolean isEmpty(){
        return addedElement == 0;
    }
}   */