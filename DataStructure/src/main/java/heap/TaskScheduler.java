package heap;

public class TaskScheduler {
    // Array to store Tasks in the heap
    private final Task[] heapArray;
    // Current number of Tasks in the heap
    private int size;
    // Maximum number of Tasks in the heap
    private final int capacity;

    public TaskScheduler(int capacity){
        this.capacity = capacity;
        this.size = 0;
        heapArray = new Task[capacity];
    }

    public void enqueue(int data, int priority){
        if (size >= capacity)
            throw new IllegalStateException("Heap is full");

        heapArray[size++] = new Task(data, priority);
        heapifyUp(size - 1);
    }

    public int dequeue(){
        if (isEmpty())
            throw new IllegalStateException("Heap is empty");

        Task root = heapArray[0];
        heapArray[0] = heapArray[--size];
        heapifyDown(0);
        return root.data;
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

        return heapArray[0].data;
    }

    /*************************************************************************************************************/
    private void heapifyUp(int index){  // O(log n)
        int parent = parent(index);

        while (index > 0 && heapArray[index].priority < heapArray[parent].priority){
            swap(index,parent);
            index = parent;
            parent = parent(index);
        }
    }

    private void heapify(){
        for (int i = parent(size - 1); i >= 0; --i)
            heapifyDown(i);
    }

    private void heapifyDown(int index) {    // O(log n)
        int smallest = index;
        int leftChild;
        int rightChild;

        while (true) {
            leftChild = left(index);
            rightChild = right(index);

            if (leftChild != -1 && heapArray[leftChild].priority < heapArray[smallest].priority)
                smallest = leftChild;

            if (rightChild != -1 && heapArray[rightChild].priority < heapArray[smallest].priority)
                smallest = rightChild;

            if (smallest == index)
                break;

            swap(index, smallest);
            index = smallest;
        }
    }

    private int parent(int index){
        return (index <= 0 || index >= size)? -1 : (index - 1)/2;
    }

    private int left(int current) {
        int leftIndex = 2 * current + 1;
        return (leftIndex < size) ? leftIndex : -1;
    }

    private int right(int current) {
        int rightIndex = 2 * current + 2;
        return (rightIndex < size) ? rightIndex : -1;
    }

    private void swap(int firstIndex, int secIndex){
        Task temp = heapArray[firstIndex];
        heapArray[firstIndex] = heapArray[secIndex];
        heapArray[secIndex] = temp;
    }

    private static class Task {
        private int data;
        private int priority;

        public Task(int data, int priority){
            this.data = data;
            this.priority = priority;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }
}
