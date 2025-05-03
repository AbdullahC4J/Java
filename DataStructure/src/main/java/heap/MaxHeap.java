package heap;

public class MaxHeap {
    // Array to store elements in the heap
    private int[] heapArray;
    // Current number of elements in the heap
    private int size;
    // Maximum number of elements in the heap
    private int capacity;

    public MaxHeap (int capacity){
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive");

        this.capacity = capacity;
        heapArray = new int[capacity];
        this.size = 0;
    }

    public MaxHeap (int capacity, int[] arr){
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

    public void add(int item){
        if (size >= capacity)
            throw new IllegalStateException("Heap is full");

        heapArray[size++] = item;
        heapifyUp(size - 1);
    }

    public int poll(){
        if (isEmpty())
            throw new IllegalStateException("Heap is empty");

        int root = heapArray[0];
        heapArray[0] = heapArray[--size];
        heapifyDown(0);
        return root;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public int peek(){
        if (isEmpty())
            throw new IllegalStateException("Heap is empty");

        return heapArray[0];
    }

    private void heapifyUp(int index){
        int parent = parent(index);

        while (index > 0 && heapArray[index] > heapArray[parent]){
            swap(index,parent);
            index = parent;
            parent = parent(index);
        }
    }

    // Using floyed algorithm heapifyDown from last parent (non-leaf) node will reduce complexity of creation from O(nlogn) to O(n)
    private void heapify(){
        for (int i = parent(size - 1); i >= 0; --i) // Start from the last non-leaf node (parent of last element)
            heapifyDown(i);
    }

    private void heapifyDown(int index){
        int largest = index;

        int leftChild = left(index);
        int rightChild = right(index);

        if(leftChild < size && heapArray[leftChild] > heapArray[largest])
            largest = leftChild;

        if(rightChild < size && heapArray[rightChild] > heapArray[largest])
            largest = rightChild;

        if (largest != index){
            swap(largest, index);
            heapifyDown(largest);
        }
    }

    // Get the Parent index for the given index
    private int parent(int index){
        return (index == 0)? -1 :(index - 1)/2;
    }

    // Get the left child index for the given index
    private int left(int index){
        return 2 * index + 1;
    }

    // Get the right child index for the given index
    private int right(int index){
        return 2 * index + 2;
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
