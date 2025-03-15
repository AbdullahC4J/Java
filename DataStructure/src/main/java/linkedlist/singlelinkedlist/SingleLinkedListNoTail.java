package linkedlist.singlelinkedlist;

public class SingleLinkedListNoTail {
    // Pointer to first node.
    private Node first;

    /**
     * Default constructor creates an linkedList.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public SingleLinkedListNoTail() {
        first = null;
    }

    /**
     * Adds an element at the tail of the LinkedList
     *
     * @param element Value to insert at the end
     * Time Complexity: O(n) - Must traverse to the end of the list without a tail pointer
     * Space Complexity: O(1) - Only creates one new node
     */
    public void addLast(int element) {
        if (first == null) {
            first = new SingleLinkedListNoTail.Node(element, null);
        } else {
            Node tempFirst = first;
            while (tempFirst.next != null)
                tempFirst = tempFirst.next;

            tempFirst.next = new SingleLinkedListNoTail.Node(element, null);
        }
    }

    /**
     * Adds an element at the head of the LinkedList
     *
     * @param element Value to insert at the start
     * Time Complexity: O(1) - Constant time operation
     * Space Complexity: O(1) - Only creates one new node
     */
    public void addFirst(int element) {
        first = new SingleLinkedListNoTail.Node(element, first);
    }

    /**
     * Returns a string representation of the LinkedList
     *
     * @return A string representation of the LinkedList in the format [element1, element2, ...]
     * Time Complexity: O(n) - Must traverse all nodes
     * Space Complexity: O(n) - Creates a string proportional to list size
     */
    public String toString() {
        StringBuilder strList = new StringBuilder();
        strList.append("[");

        Node tempFirst = first;
        while (tempFirst != null) {
            strList.append(tempFirst.data);
            if (tempFirst.next != null)
                strList.append(", ");

            tempFirst = tempFirst.next;
        }

        strList.append("]");
        return strList.toString();
    }

    /**
     * Node class for the LinkedList implementation
     * Contains the data and reference to the next node
     * Space Complexity: O(1) per node
     */
    private static class Node {
        private int data;
        private SingleLinkedListNoTail.Node next;

        public Node(int data, SingleLinkedListNoTail.Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
