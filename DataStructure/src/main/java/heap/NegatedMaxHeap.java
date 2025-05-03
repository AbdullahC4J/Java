package heap;

public class NegatedMaxHeap {
    MinHeap heap;

    public NegatedMaxHeap(int capacity){
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive");

        heap = new MinHeap(capacity);
    }

    public void add(int item){
        heap.add(-item);
    }

    public int poll(){
        return -heap.poll();
    }

    public int peek(){
        return -heap.peek();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    public int size(){
        return heap.size();
    }
}
