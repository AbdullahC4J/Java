package stack.queue;

import queue.array.QueueArray;

public class StackQueue<E> {
    // The number of elements currently in the array
    private int size;
    // Internal queue to store elements
    private QueueArray<E> queue;

    public StackQueue(){
        this(10);
    }

    public StackQueue(int capacity){
        queue = new QueueArray<>(capacity);
    }

    public void push(E element){
        int count = size;
        queue.enqueue(element);
        while (count-- > 0){
            queue.enqueue(queue.dequeue()); // bring the element to the head of the queue
        }
        ++size;
    }

    public E pop(){
        if (queue.isEmpty())
            return null;


        return queue.dequeue();
    }

    public E peek(){
        if (queue.isEmpty())
            return null;

        return queue.peek();
    }

//    @Override
//    public String toString(){
//        return queue.toString();
//    }
}
