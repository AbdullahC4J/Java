package sparse.array;

import java.util.Objects;

/**
 * A memory-efficient implementation of a sparse array using a linked list structure.
 * <p>
 * This class represents a sparse array, which is an array where most of the elements
 * have the same value (typically zero). Instead of storing all elements, this implementation
 * only stores non-zero elements using a linked list of nodes, where each node contains
 * the value and its index in the array.
 * </p>
 * <p>
 * Time complexity for operations:
 * - Access: O(n) where n is the number of non-zero elements
 * - Insertion: O(n)
 * - Deletion: Not supported directly
 * </p>
 * <p>
 * Space complexity: O(k) where k is the number of non-zero elements
 * </p>
 */
public class SparseArray {
    // Pointer to first node.
    private ArrayNode  first;
    // Pointer to last node.
    private ArrayNode  last;
    // The number of elements currently in the LinkedList
    private int size;
    // The number of elements currently in the array
    private int arraySize;

    /**
     * Constructs a new sparse array with the specified size.
     * 
     * @param arraySize The logical size of the array
     */
    public SparseArray(int arraySize){
        this.first = this.last = new ArrayNode(0,-1);
        this.size = 1;
        this.arraySize = arraySize;
    }

    /**
     * Sets the value at the specified index in the sparse array.
     * If the index doesn't exist, a new node is created.
     * 
     * @param data The value to be stored
     * @param index The index at which to store the value
     * @throws NullPointerException If the index node cannot be created
     */
    public void setValue(int data, int index){
       Objects.requireNonNull(getIndex(index, true)).data = data;
    }

    /**
     * Gets the value at the specified index in the sparse array.
     * 
     * @param index The index from which to retrieve the value
     * @return The value at the specified index, or 0 if the index doesn't exist
     */
    public int getValue(int index){
        ArrayNode node = getIndex(index,false);
        if(node != null)
            return node.data;

        return 0;
    }

    /**
     * Prints all elements of the array, including zeros.
     * This method traverses the entire logical array size and prints
     * each element, showing zeros for indices that don't have nodes.
     */
    public void printArr(){
        ArrayNode curr = first.next;

        for (int indx = 0; indx < arraySize; indx++){
            if(curr != null && indx == curr.index){
                System.out.print(curr.data + " ");
                curr = curr.next;
            }else {
                System.out.print("0 ");
            }
        }
        System.out.println( );
    }

    /**
     * Prints only the non-zero elements of the array.
     * This method only traverses the linked list nodes and prints
     * their values, skipping the zero elements.
     */
    public void printArrNonZero() {
        ArrayNode curr = first.next;

        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * Adds the elements of another sparse array to this array.
     * Both arrays must have the same size.
     * 
     * @param secList The second sparse array to add
     * @throws IllegalArgumentException If the array sizes don't match
     */
    public void addTwoLists(SparseArray secList) {
        if (this.arraySize != secList.arraySize)
            throw new IllegalArgumentException("Array sizes must be the same!");

        ArrayNode secCurr = secList.first.next;
        ArrayNode curr = null;
        while (secCurr != null) {
            curr = getIndex(secCurr.index, true);
            assert curr != null;
            curr.data += secCurr.data;
            secCurr = secCurr.next;
        }
    }


//    public <T extends Number> void addTwoLists(SparseArray<T> secList){
//        if(this.arraySize != secList.arraySize)
//            throw new IllegalArgumentException("Array sizes must be the same!");
//
//        ArrayNode<T> secCurr = secList.first.next;
//        ArrayNode<E> curr = null;
//        while (secCurr != null){
//            curr = getIndex(secCurr.index, true);
//            if(curr.data == null){
//                curr.data = (E) secCurr.data;
//            }else{
//                // Perform addition based on numeric type
//                if (curr.data instanceof Integer) {
//                    curr.data = (E) Integer.valueOf(((Integer) curr.data) + secCurr.data.intValue());
//                } else if (curr.data instanceof Double) {
//                    curr.data = (E) Double.valueOf(((Double) curr.data) + secCurr.data.doubleValue());
//                } else if (curr.data instanceof Float) {
//                    curr.data = (E) Float.valueOf(((Float) curr.data) + secCurr.data.floatValue());
//                } else if (curr.data instanceof Long) {
//                    curr.data = (E) Long.valueOf(((Long) curr.data) + secCurr.data.longValue());
//                } else {
//                    throw new UnsupportedOperationException("Unsupported number type: " + curr.data.getClass());
//                }
//            }
//            secCurr = secCurr.next;
//        }
//    }


    /**
     * Gets the node at the specified index, optionally creating it if it doesn't exist.
     * 
     * @param index The index to find
     * @param createIfMissing If true, creates a new node if the index doesn't exist
     * @return The node at the specified index, or null if it doesn't exist and createIfMissing is false
     */
    private ArrayNode getIndex(int index, boolean createIfMissing) {
        ArrayNode prevIndex = first;

        while (prevIndex.next != null && prevIndex.next.index < index)
            prevIndex = prevIndex.next;

        if(prevIndex.next != null && prevIndex.next.index == index)
            return prevIndex.next;

        if(!createIfMissing)
            return null;

        return insertAfter(prevIndex, 0 , index);
    }

    /**
     * Inserts a new node after the specified node.
     * 
     * @param nodeBefore The node after which to insert
     * @param data The data value for the new node
     * @param index The index value for the new node
     * @return The newly created node
     */
    private ArrayNode insertAfter(ArrayNode nodeBefore, int data, int index) {
        ArrayNode newNode = new ArrayNode(data,index);

        newNode.next = nodeBefore.next;
        linkNodes(nodeBefore,newNode);
        if(newNode.next == null)
            last = newNode;
        else
            linkNodes(newNode, newNode.next);

        size++;
        return newNode;
    }

    /**
     * Links two nodes together in the doubly-linked list.
     * 
     * @param firstNode The first node to link
     * @param secNode The second node to link
     */
    private void linkNodes(ArrayNode firstNode, ArrayNode secNode) {
        if (firstNode == null || secNode == null)
            return;

        firstNode.next = secNode;
        secNode.prev = firstNode;
    }

    /**
     * Represents a node in the sparse array's linked list structure.
     * Each node contains a data value and its corresponding index in the array.
     */
    private static class ArrayNode{
        /** The data value stored at this node */
        int data;
        /** The index of this element in the logical array */
        int index; // element index in the array
        /** Reference to the next node in the linked list */
        ArrayNode next;
        /** Reference to the previous node in the linked list */
        ArrayNode prev;

        /**
         * Constructs a new array node with the specified data and index.
         * 
         * @param data The data value to store
         * @param index The index in the logical array
         */
        public ArrayNode(int data, int index) {
            this.data = data;
            this.index = index;
        }
    }


}
