package linkedlist.doublelinkedlist;

public class DoubleLinkedList<E> {
    // Pointer to first node.
    private Node<E> first;
    // Pointer to last node.
    private Node<E> last;
    // The number of elements currently in the list
    private int size;

    /**
     * Default constructor creates an empty linked list.
     * Time Complexity: O(1) - Constant time operation
     * Space Complexity: O(1) - Only initializes pointers and counter
     */
    public DoubleLinkedList(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Adds an element at the end of the double LinkedList
     *
     * @param element Value to insert at the end.
     *                Time Complexity: O(1) - Constant time due to tail pointer
     *                Space Complexity: O(1) - Only creates one new node
     */
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null, null);
        if (first == null) {
            first = last = newNode;
        } else {
            linkNodes(last, newNode);
            last = newNode;
        }
        size++;
        verifyIntegrity();
    }

    /**
     * Adds an element at the first of the double LinkedList
     *
     * @param element Value to insert at the start
     *                Time Complexity: O(1) - Constant time operation
     *                Space Complexity: O(1) - Only creates one new node
     */
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element, null, null);
        if (last == null) {
            first = last = newNode;
        } else {
            linkNodes(newNode, first);
            first = newNode;
        }
        size++;
        verifyIntegrity();
    }

    /**
     * Removes the first element of the double LinkedList
     * Time Complexity: O(1) - Constant time operation
     * Space Complexity: O(1) - No additional space needed
     */
    public boolean removeFirst() {
        if (first == null) return false;

        first = first.next;
        if (first != null)
            first.prev = null;
        else
            last = null;

        size--;
        verifyIntegrity();
        return true;
    }

    /**
     * Removes the element of an index (0-indexed)
     *
     * @param index to the element to remove.
     *              Time Complexity: O(n) - May need to traverse the entire list to find the element
     *              Space Complexity: O(1) - No additional space needed
     */
    public void remove(int index) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");

        if (0 == index) {       // To update first pointer
            removeFirst();
            return;
        }

        if ((size - 1) == index) {       // To update last pointer
            removeLast();
            return;
        }

        Node<E> curr = first;
        while (index-- > 0)
            curr = curr.next;

        linkNodes(curr.prev, curr.next);
        size--;
        verifyIntegrity();
    }

    /**
     * Removes the first occurrence of an element
     *
     * @param element to remove.
     *                Time Complexity: O(n) - May need to traverse the entire list to find the element
     *                Space Complexity: O(1) - No additional space needed
     */
    public boolean remove(E element) {
        if (first == null)
            return false;

        if (first.data.equals(element)) {       // To update first pointer
            return removeFirst();
        }

        Node<E> curr = first.next;
        while (curr != null) {

            if (curr.data.equals(element)) {
                if (curr == last)
                    return removeLast();

                linkNodes(curr.prev, curr.next);
                size--;
                return true;
            }

            curr = curr.next;
        }
        return false;
    }

    /**
     * Removes the last element of the LinkedList.
     * Time Complexity: O(n) - Requires traversal to find the second-to-last node
     * Space Complexity: O(1) - No additional space needed
     */
    public boolean removeLast() {
        if (first == null || last == null) return false;

        last = last.prev;
        if (last != null)
            last.next = null;
        else
            first = null;

        size--;
        verifyIntegrity();
        return true;
    }

    /**
     * Returns a string representation of the LinkedList
     *
     * @return A string representation of the LinkedList in the format [element1, element2, ...]
     * Time Complexity: O(n) - Must traverse all nodes
     * Space Complexity: O(n) - Creates a string proportional to list size
     */
    @Override
    public String toString() {
        StringBuilder strList = new StringBuilder();
        strList.append("[");

        Node<E> curr = first;
        while (curr != null) {
            strList.append(curr.data);
            if (curr.next != null)
                strList.append(", ");

            curr = curr.next;
        }

        strList.append("]");
        return strList.toString();
    }

    /**
     * Node class for the Double LinkedList implementation.
     * Contains the integer data, reference to the next node and reference to the previous node.
     * Time Complexity: O(1) for all operations
     * Space Complexity: O(1) per node instance
     */
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private boolean linkNodes(Node<E> firstNode, Node<E> secNode) {
        if (firstNode == null || secNode == null)
            return false;

        firstNode.next = secNode;
        secNode.prev = firstNode;

        return true;
    }

    /**
     * Verifies the integrity of the LinkedList data structure.
     * Checks if size is consistent with the actual number of nodes.
     * Ensures that the last pointer correctly points to the last node.
     *
     * @throws RuntimeException if any inconsistency is found in the data structure
     *                          Time Complexity: O(n) - Must traverse all nodes to verify integrity
     *                          Space Complexity: O(1) - Uses only temporary counter variables
     */
    private void verifyIntegrity() {
        if (size == 0 && (first != null || last != null)) {
            throw new RuntimeException("First or Last has corrupted");
        } else if (size > 0) {
            int tempLen = 1;
            Node<E> currFirst = first;
            Node<E> currLast = last;

            while (currFirst.next != null) {
                tempLen++;
                currFirst = currFirst.next;
                currLast = currLast.prev;
            }

            if (tempLen != size) {
                throw new RuntimeException("The size of LinkedList is incorrect");
            }

            if (currFirst != last) {
                throw new RuntimeException("The Last pointer is incorrect");
            }

            if (currLast != first) {
                throw new RuntimeException("The first pointer is incorrect");
            }
        }
    }

    /**
     * Prints detailed information about the LinkedList for debugging purposes
     * Displays the head, all elements, size, and tail of the list
     * Time Complexity: O(n) - Must traverse all nodes
     * Space Complexity: O(1) - No additional space needed beyond output
     */
    public void debugPrintAll() {
        if (first == null && last == null) {
            System.out.println("Head -> null");
            System.out.println("Size = 0");
            System.out.println("Tail -> null");
        } else {

            Node<E> curr = first;
            System.out.println("Head ---> " + first.data);
            while (curr!= null) {
                if(first == last){
                    System.out.println(null + "  <--  " + curr.data + "  -->  " + null);
                    break;
                }

                if (curr == first)
                    System.out.println(null + "  <--  " + curr.data + "  -->  " + curr.next.data);
                else if (curr == last)
                    System.out.println("   " +curr.prev.data + "  <--  " + curr.data + "  -->  " + null);
                else
                    System.out.println("   " +curr.prev.data + "  <--  " + curr.data + "  -->  " + curr.next.data);
                curr = curr.next;
            }
            System.out.println("Tail ---> " + last.data);
            System.out.println("The size is : " + size);
        }
    }

    /*************************************************************************************************************/
    /*******************************************     Assignments     *********************************************/
    /*************************************************************************************************************/

    /**
     * Removes the all occurrence of an element
     *
     * @param element to remove.
     *                Time Complexity: O(n) - May need to traverse the entire list to find the element
     *                Space Complexity: O(1) - No additional space needed
     */
    public boolean removeAllOfElement(E element) {
        if (first == null) return false;

        if (first.data.equals(element)) {       // To update first pointer
            removeFirst();
        }

        Node<E> curr = first.next;
        while (curr != null) {
            if (curr.data.equals(element)) {
                if (curr == last)
                    return removeLast();

                linkNodes(curr.prev, curr.next);
                size--;
            }
            curr = curr.next;
        }
        return true;
    }

    public void removeOddPos() {
        if (first == null || last == null || first == last) return;

        Node<E> dummyFirst = new Node<>(null,first,null);
        Node<E> curr = dummyFirst;
        while (curr != null && curr.next != null) {
            curr.next = curr.next.next;

            if (curr.next != null)
                curr.next.prev = curr.next;
            else
                last = curr;

            curr = curr.next;
            size--;
        }
        first = dummyFirst.next;
    }

    public void removeEvenPos() {
        if (first == null || last == null || first == last) return;

        Node<E> curr = first;
        while (curr.next != null && curr.next.next != null){
            linkNodes(curr, curr.next.next);
            curr = curr.next;
            size--;
        }
        if (curr.next == last)
            removeLast();
    }

    public boolean isPalindrome(){
        if (first == null || last == null) return false;
        if(first == last) return  true;

        Node<E> tempFirst = first;
        Node<E> tempLast = last;

        while (tempFirst != tempLast){
            if(!tempFirst.data.equals(tempLast.data))
                return false;

            tempFirst = tempFirst.next;
            tempLast = tempLast.prev;
        }
        return true;
    }

    /*
    * Don't use size, or iterate more than once.
    * */
    public E getMiddle(){
        if (first == null || last == null) return null;
        if (first == last) return first.data;

        Node<E> slow = first;
        Node<E> fast = first;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }
    /* sol1
        if (first == null || last == null) return null;
        if (first == last) return first.data;

        Node<E> tempFirst = first;
        Node<E> tempLast = last;

        while ((tempFirst.prev != tempLast) && (tempFirst != tempLast)){
            tempFirst = tempFirst.next;
            tempLast = tempLast.prev;
        }

        return tempFirst.data;
    */

    public void swapFirstLast() {
        if (first == null || first == last) return;

        Node<E> dummyHead = first.next;
        first.next = null;
        first.prev = last.prev;
        last.prev.next = first;
        last.next = dummyHead;
        dummyHead.prev = last;
        last.prev = null;
        last = first;
        first = dummyHead.prev;
    }

    private void swapNodes(Node<E> firstNode, Node<E> secNode) {
        if (firstNode == null || secNode == null || firstNode == secNode) return;

        if (firstNode == first && secNode == last) {
            swapFirstLast();
        }
        else if (firstNode.next == secNode) {
            firstNode.next = secNode.next;
            secNode.prev = firstNode.prev;

            if (secNode.next != null) secNode.next.prev = firstNode;
            if (firstNode.prev != null) firstNode.prev.next = secNode;

            secNode.next = firstNode;
            firstNode.prev = secNode;
        }
        else {
            Node<E> firstPrev = firstNode.prev;
            Node<E> firstNext = firstNode.next;
            Node<E> secPrev = secNode.prev;
            Node<E> secNext = secNode.next;

            if (firstPrev != null) firstPrev.next = secNode;
            if (secPrev != null) secPrev.next = firstNode;

            if (firstNext != null) firstNext.prev = secNode;
            if (secNext != null) secNext.prev = firstNode;

            firstNode.prev = secPrev;
            firstNode.next = secNext;
            secNode.prev = firstPrev;
            secNode.next = firstNext;
        }
    }

    public void swapFwdBwd(int step) {
        if (first == null || first == last || step > size/2) return;

        Node<E> tempFirst = first;
        Node<E> tempLast = last;

        while (--step > 0) {
            tempFirst = tempFirst.next;
            tempLast = tempLast.prev;
        }
        swapNodes(tempFirst, tempLast);
        verifyIntegrity();
    }

    public void reverse(){

    }

    public void mergeSortedLists(DoubleLinkedList<E> secList){

    }
}