package MyArrayList;

import java.util.Arrays;

/**
 * A custom implementation of a dynamic array (ArrayList)
 * This implementation provides dynamic resizing, element insertion/deletion,
 * and basic array operations.
 */
public class MyArrayList {
    // The number of elements currently in the array
    private int size;
    // The total space available in the array
    private int capacity;
    // Internal array to store elements
    private int[] arr;

    /**
     * Default constructor creates an ArrayList with initial capacity of 10
     */
    public MyArrayList() {
        size = 0;
        capacity = 10;
        arr = new int[capacity];
    }

    /**
     * Constructor that creates an ArrayList with specified initial size
     *
     * @param size Initial size of the ArrayList
     */
    public MyArrayList(int size) {
        this.size = 0;
        capacity = size + 10;
        arr = new int[capacity];
    }

    /**
     * Retrieves element at specified index
     *
     * @param index Position to get element from
     * @return Element at given index
     */
    public int get(int index) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");

        return arr[index];
    }

    /**
     * Returns the first element in the ArrayList
     *
     * @return The first element
     */
    public int getFirst() {
        return arr[0];
    }

    /**
     * Returns the last element in the ArrayList
     *
     * @return The last element
     */
    public int getLast() {
        return arr[size - 1];
    }

    /**
     * Returns the current number of elements in the ArrayList
     *
     * @return The size of ArrayList
     */
    public int getSize() {
        return size;
    }

    /**
     * Inserts element at specified index, shifting existing elements right
     *
     * @param index   Position to insert at
     * @param element Value to insert
     */
    public void add(int index, int element) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");

        if (size == capacity)
            expandCapacity();

        for (int i = size; i > index; i--)
            arr[i] = arr[i - 1];

        arr[index] = element;
        size++;
    }

    /**
     * Inserts element at specified index, shifting existing elements right
     *
     * @param element Value to insert
     */
    public void add(int element) {
        add(size, element);
    }

    /**
     * Adds an element at the beginning of the ArrayList
     *
     * @param element Value to insert at the start
     */
    public void addFirst(int element) {
        add(0, element);
    }

    /**
     * Adds an element at the end of the ArrayList
     *
     * @param element Value to append
     */
    public void addLast(int element) {
        if (size == capacity)
            expandCapacity();

        arr[size++] = element;
    }

    /**
     * Removes element at specified index and shifts remaining elements left
     *
     * @param index Position to remove from
     * @return The removed element
     */
    public int remove(int index) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");

        int val = arr[index];
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }

        --size;
        return val;
    }

    public int binarySearch(int element) {
        for (int i = 0; i < size; i++)
            if (arr[i] == element)
                return i;

        return -1;
    }

    @Override
    public String toString() {
        if (size >= 0)
            return Arrays.toString(Arrays.copyOf(arr, size));

        return null;
    }

    /**
     * Rotates the elements in the ArrayList to the right by specified positions
     *
     * @param rot Number of positions to rotate right
     */
    public void rightRotate(int rot) {
        int lastElement;
        rot %= size; // as a rotating number greater than the size will return to the original arrangement.

        while (rot > 0) {
            lastElement = arr[size - 1];
            for (int i = size; i > 0; i--)
                arr[i] = arr[i - 1];

            arr[0] = lastElement;
            --rot;
        }
    }

    /**
     * Rotates the elements in the ArrayList to the left by specified positions
     *
     * @param rot Number of positions to rotate left
     */
    public void leftRotate(int rot) {
        int firstElement;
        rot %= size; // as a rotating number greater than the size will return to the original arrangement.

        while (rot > 0) {
            firstElement = arr[0];
            for (int i = 0; i < size; i++)
                arr[i] = arr[i + 1];

            arr[size - 1] = firstElement;
            --rot;
        }
    }

    /**
     * Doubles the capacity of the array when it becomes full
     */
    private void expandCapacity() {
        capacity *= 2;
        int[] newArr = new int[capacity];
        if (size >= 0) System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }
}