package com.mycompany.ds;

/**
 *
 * @author Abdullah Maher
 */
public class ArrayList {

    private int[] arr = null;
    private int size;
    private int capacity;

    public ArrayList() {
        this(0);
    }

    public ArrayList(int size) {
        if (size < 0) {
            this.size = 1;
        } else {
            this.size = size;
        }

        capacity = this.size + 10;
        arr = new int[capacity];
    }

    /**
     * Method to get element at certain index
     *
     * @param idx index to search for
     * @return the value stored in the index location
     */
    public int get(int idx) {
        if (idx >= 0 && idx < size) {
            return arr[idx];
        } else {
            return -1;
        }
    }

    /**
     * Method to overwrite element at certain index
     *
     * @param idx index to search for
     * @param val the desired value to be stored
     */
    public void set(int idx, int val) {
        if (idx >= 0 && idx < size) {
            arr[idx] = val;
        }
    }

    /**
     * Method to print all the elements of the array
     *
     */
    public void print() {
        System.out.print("{ ");

        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + ", ");
        }

        System.out.printf("\b\b }\n");
    }

    /**
     * Method to find first occurrence of a certain value in array
     *
     * @param val value to be found
     * @return the index of value
     */
    public int find(int val) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == val) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Method to return first index value
     *
     * @return the first value
     */
    public int getFirst() {
        if (size == 0) {
            return -1;
        }

        return arr[0];
    }

    /**
     * Method to return last index value
     *
     * @return the last value
     */
    public int getLast() {
        if (size == 0) {
            return -1;
        }
        return arr[size - 1];
    }

    /**
     * Method to add element at the first index of array
     *
     * @param val element to be added at first index
     */
    public void addFirst(int val) {
        add(0, val);
    }

    /**
     * Method to add element at the last index of array
     *
     * @param val element to be added at last index
     */
    public void addLast(int val) {

        if (size == capacity) {
            expandCapacity();
        }

        arr[size++] = val;
    }

    /**
     * Method to add element at certain index of array
     *
     * @param idx index where element be added
     * @param val element to be added at certain index
     */
    public void add(int idx, int val) {

        if (idx < 0 || idx > size) {
            return;
        }

        if (size == capacity) {
            expandCapacity();
        }

        for (int i = size - 1; i >= idx; i--) {
            arr[i + 1] = arr[i];
        }

        arr[idx] = val;
        size++;
    }

    /**
     * Method to remove certain index
     *
     * @param idx the desired index to be removed
     */
    public void remove(int idx) {
        if (idx < 0 || idx > size) {
            return;
        }

        for (int i = idx; i < size; i++) {
            arr[i] = arr[i + 1];
        }

        size--;
    }

    public void rightRotate() {
        int lastElemnet = arr[size - 1];

        for (int i = size - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }

        arr[0] = lastElemnet;
    }

    public void rightRotate(int rotationCount) {
        rotationCount %= size;

        while (rotationCount > 0) {
            rightRotate();
            rotationCount--;
        }
    }

    public void leftRotate() {
        int firstElemnet = arr[0];

        for (int i = 1; i < size; i++) {
            arr[i - 1] = arr[i];
        }

        arr[size - 1] = firstElemnet;
    }

    /**
     * Method to expand the capacity of the array
     *
     *
     */
    private void expandCapacity() {

        capacity *= 2;

        int[] newArr = new int[capacity];

        System.arraycopy(arr, 0, newArr, 0, size);

        arr = newArr;
    }
}
