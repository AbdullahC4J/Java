package arraylist;

import java.util.Arrays;

/**
 * A custom implementation of a dynamic array (ArrayList)
 * This implementation provides dynamic resizing, element insertion/deletion,
 * and basic array operations.
 */
public class MyArrayList <E> {
    // The number of elements currently in the array
    private int size;
    // The total space available in the array
    private int capacity;
    // Internal array to store elements
    private Object[] arr;

    /*
     * Default constructor creates an ArrayList with initial capacity of 10
     */
    public MyArrayList() {
        this(0);
    }

    /*
     * Constructor that creates an ArrayList with specified initial size
     *
     * @param size Initial size of the ArrayList
     */
    public MyArrayList(int size) {
        this.size = 0;
        capacity = size + 10;
        arr = new Object[capacity];
    }

    /*
     * Retrieves element at specified index
     *
     * @param index Position to get element from
     * @return Element at given index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");

        return (E) arr[index];
    }

    /*
     * Returns the first element in the ArrayList
     *
     * @return The first element
     */
    @SuppressWarnings("unchecked")
    public E getFirst() {
        return (E) arr[0];
    }

    /*
     * Returns the last element in the ArrayList
     *
     * @return The last element
     */
    @SuppressWarnings("unchecked")
    public E getLast() {
        return (E) arr[size - 1];
    }

    /*
     * Returns the current number of elements in the ArrayList
     *
     * @return The size of ArrayList
     */
    public int getSize() {
        return size;
    }

    /*
     * Inserts element at specified index, shifting existing elements right
     *
     * @param index   Position to insert at
     * @param element Value to insert
     */
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");

        if (size == capacity)
            expandCapacity();

        for (int i = size; i > index; i--)
            arr[i] = arr[i - 1];

        arr[index] = element;
        size++;
    }

    /*
     * Inserts element at last index, shifting existing elements right
     *
     * @param element Value to insert
     */
    public void add(E element) {
        add(size, element);
    }

    /*
     * Adds an element at the beginning of the ArrayList
     *
     * @param element Value to insert at the start
     */
    public void addFirst(E element) {
        add(0, element);
    }

    /*
     * Adds an element at the end of the ArrayList
     *
     * @param element Value to append
     */
    public void addLast(E element) {
        if (size == capacity)
            expandCapacity();

        arr[size] = element;
        size++;
    }

    /*
     * Removes element at specified index and shifts remaining elements left
     *
     * @param index Position to remove from
     * @return The removed element
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");

        E val = (E) arr[index];
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }

        arr[--size] = null;
        return val;
    }

    /*
     * Removes the all elements of the ArrayList
     */
    public void clear(){
        size = 0;
        capacity = 10;
        arr = new Object[capacity];
    }

    /*
     * Search for the element in the ArrayList
     *
     * @return The index of the element
     */
    public int linearSearch(E element) {
        for (int i = 0; i < size; i++)
            if (arr[i].equals(element))
                return i;

        return -1;
    }

    @Override
    public String toString() {
        if (size >= 0)
            return Arrays.toString(Arrays.copyOf(arr, size));

        return null;
    }

    /*
     * Rotates the elements in the ArrayList to the right by specified positions
     *
     * @param rot Number of positions to rotate right
     */
    @SuppressWarnings("unchecked")
    public void rightRotate(int rot) {
        if (size == 0)
            return;

        E lastElement;
        rot %= size; // as a rotating number greater than the size will return to the original arrangement.

        while (rot > 0) {
            lastElement = (E) arr[size - 1];
            for (int i = size; i > 0; i--)
                arr[i] = arr[i - 1];

            arr[0] = lastElement;
            --rot;
        }
    }

    /*
     * Rotates the elements in the ArrayList to the left by specified positions
     *
     * @param rot Number of positions to rotate left
     */
    @SuppressWarnings("unchecked")
    public void leftRotate(int rot) {
        if (size == 0)
            return;

        E firstElement;
        rot %= size; // as a rotating number greater than the size will return to the original arrangement.

        while (rot > 0) {
            firstElement = (E) arr[0];
            for (int i = 0; i < size; i++)
                arr[i] = arr[i + 1];

            arr[size - 1] = firstElement;
            --rot;
        }
    }

    /*
     * Doubles the capacity of the array when it becomes full
     */
    private void expandCapacity() {
        capacity *= 2;
        Object[] newArr = new Object[capacity];
        if (size >= 0) System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }
}