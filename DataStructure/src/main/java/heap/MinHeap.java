package heap;

/**
 * A MinHeap implementation using an array-based heap structure.
 * Supports standard heap operations like insertion, deletion, and heap sort.
 */
public class MinHeap {
    /** Array to store elements in the heap */
    private int[] heapArray;

    /** Current number of elements in the heap */
    private int size;

    /** Maximum number of elements the heap can hold */
    private final int capacity;

    /**
     * Constructs an empty MinHeap with the specified capacity.
     * @param capacity The maximum number of elements the heap can hold.
     * @throws IllegalArgumentException If capacity is not positive.
     */
    public MinHeap (int capacity){
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive");

        this.capacity = capacity;
        heapArray = new int[capacity];
        this.size = 0;
    }

    /**
     * Constructs a MinHeap from an existing array.
     * @param capacity The maximum number of elements the heap can hold.
     * @param arr The input array to build the heap from.
     * @throws IllegalArgumentException If the array is null or larger than capacity.
     */
    public MinHeap (int capacity, int[] arr){
        if (arr == null)
            throw new IllegalArgumentException("Input array cannot be null");

        if (arr.length > capacity)
            throw new IllegalArgumentException("Capacity must be greater than the array");

        this.capacity = capacity;
        heapArray = new int[capacity];
        this.size = arr.length;

        System.arraycopy(arr, 0, heapArray, 0, size);

        heapify();
    }

    /**
     * Adds an item to the heap.
     * @param item The item to add.
     * @throws IllegalStateException If the heap is full.
     * complexity O(log n) time, O(1) space.
     */
    public void add(int item){
        if (size >= capacity)
            throw new IllegalStateException("Heap is full");

        heapArray[size++] = item;
        heapifyUp(size - 1);
    }

    /**
     * Removes and returns the smallest item (root) from the heap.
     * @return The smallest item in the heap.
     * @throws IllegalStateException If the heap is empty.
     * complexity O(log n) time, O(1) space.
     */
    public int poll(){
        if (isEmpty())
            throw new IllegalStateException("Heap is empty");

        int root = heapArray[0];
        heapArray[0] = heapArray[--size];
        heapifyDown(0);
        return root;
    }

    /**
     * Checks if the heap is empty.
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Returns the number of elements in the heap.
     * @return The current size of the heap.
     */
    public int size(){
        return size;
    }

    /**
     * Returns the smallest item (root) without removing it.
     * @return The smallest item in the heap.
     * @throws IllegalStateException If the heap is empty.
     */
    public int peek(){
        if (isEmpty())
            throw new IllegalStateException("Heap is empty");

        return heapArray[0];
    }


    public void printLessThan(int num){
        printLessThan(num, 0);
    }

    /**
     * Sorts the input array in descending order using heap sort (in-place).
     * @param arr The array to sort.
     * @throws IllegalArgumentException If the input array is null.
     * complexity O(n log n) time, O(1) space.
     */
    public void heapSort(int[] arr){
        if (arr == null)
            throw new IllegalArgumentException("Input array cannot be null");

        int mainSize = size;
        int[] mainArr = heapArray;

        heapArray = arr;
        this.size = arr.length;

        heapify(); //Build the heap if not already a heap array

        while (--size > 0){
            swap(0, size);
            heapify();
        }

        heapArray = mainArr;
        this.size = mainSize;
    }

//        int[] curHeap = heapArray;
//        int curSize = this.size;
//
//        heapArray = arr;
//        this.size = size; // To use remove()
//
//        heapify();
//
//        int[] res = new int[size];
//        for (int i = 0; i < size; ++i)
//            res[i] = remove();
//
//        heapArray = curHeap;
//        this.size = curSize;
//
//        return res;


    /**
     * Checks if the given array is a valid min-heap.
     * @param arr The array to check.
     * @return true if the array is a min-heap, false otherwise.
     */
    public boolean isHeap(int[] arr){
        return isHeap(arr, 0);
    }

    // Helper method to recursively check heap property
    private boolean isHeap(int[] arr, int index){
        if(index >= size || index == -1)
            return true;

        int leftChild = left(index);
        int rightChild = right(index);

        if (leftChild != -1)
            if(arr[leftChild] < arr[index])
                return false;

        if (rightChild != -1)
            if(arr[rightChild] < arr[index])
                return false;

        return isHeap(arr, leftChild) && isHeap(arr, rightChild);
    }

    private void printLessThan(int num, int index){
        if(index == -1 || heapArray[index] >= num)
            return;

        System.out.print(heapArray[index] + " ");

        printLessThan(num, left(index));
        printLessThan(num, right(index));
    }

    // Restores heap property by moving an element up.
    private void heapifyUp(int index){  // O(log n)
        int parent = parent(index);

        while (index > 0 && heapArray[index] < heapArray[parent]){
            swap(index,parent);
            index = parent;
            parent = parent(index);
        }
    }

    // Using floyed algorithm heapifyDown from last parent (non-leaf) node will reduce complexity of creation from O(n log n) to O(n)
    private void heapify(){
        for (int i = parent(size - 1); i >= 0; --i) // Start from the last non-leaf node (parent of last element)
            heapifyDown(i);
    }

    // Restores heap property by moving an element down.
    private void heapifyDown(int index) {    // O(log n)
        int smallest = index;
        int leftChild;
        int rightChild;

        while (true) {
            leftChild = left(index);
            rightChild = right(index);

            if (leftChild != -1 && heapArray[leftChild] < heapArray[smallest])
                smallest = leftChild;

            if (rightChild != -1 && heapArray[rightChild] < heapArray[smallest])
                smallest = rightChild;

            if (smallest == index)
                break;

            swap(index, smallest);
            index = smallest; // To continue the heapify the number for new position
        }
    }

    /**
     * Returns the parent index of the given node.
     * @param index The index of the current node.
     * @return Parent index, or -1 if no parent exists.
     */
    private int parent(int index){
        return (index <= 0 || index >= size)? -1 : (index - 1)/2;
    }

    /**
     * Returns the index of the left child of the specified node.
     * @param current The index of the current node
     * @return Index of the left child, or -1 if no left child exists
     */
    private int left(int current) {
        int leftIndex = 2 * current + 1;
        return (leftIndex < size) ? leftIndex : -1;
    }

    /**
     * Returns the index of the right child of the specified node.
     * @param current The index of the current node
     * @return Index of the right child, or -1 if no right child exists
     */
    private int right(int current) {
        int rightIndex = 2 * current + 2;
        return (rightIndex < size) ? rightIndex : -1;
    }

    private void swap(int firstIndex, int secIndex){
        int temp = heapArray[firstIndex];
        heapArray[firstIndex] = heapArray[secIndex];
        heapArray[secIndex] = temp;
    }

    @Override
    public String toString() {
        if (heapArray == null)
            return "null";
        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder str = new StringBuilder();
        str.append('[');
        for (int i = 0; ; i++) {
            str.append(heapArray[i]);
            if (i == iMax)
                return str.append(']').toString();
            str.append(", ");
        }
    }
}
