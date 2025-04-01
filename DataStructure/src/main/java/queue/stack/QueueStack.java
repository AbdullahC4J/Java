package queue.stack;

import stack.array.StackArray;

public class QueueStack<E> {
    // The number of elements currently in the array
    private int size;
    // The maximum capacity of the queue
    private int capacity;

    StackArray<E> mainStack;
    StackArray<E> secStack;

    public QueueStack() {
        this(10);
    }

    public QueueStack(int capacity) {
        this.capacity = capacity;
        mainStack = new StackArray<>(capacity);
        secStack = new StackArray<>(capacity);
    }

    public void enqueue(E element){
        if (isFull())
            throw new RuntimeException("Queue is full");

        move(mainStack, secStack);
        secStack.push(element);
        move(secStack,mainStack);
        ++size;
    }

    public E dequeue(){
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        --size;
        return mainStack.pop();
    }

    public boolean isFull(){
        return mainStack.isFull();
    }

    public boolean isEmpty(){
        return mainStack.isEmpty();
    }

    private void move(StackArray<E> src, StackArray<E> dis){
        while (!src.isEmpty())
            dis.push(src.pop());
    }

    public String toString(){
        return mainStack.toString();
    }
}
