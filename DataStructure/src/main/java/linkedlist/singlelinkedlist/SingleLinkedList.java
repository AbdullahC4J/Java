package linkedlist.singlelinkedlist;

/**
 * A singly linked list implementation with both head and tail pointers.
 * This class provides various operations for manipulating a linked list including
 * adding, removing, accessing elements, and various utility methods.
 * The implementation maintains both first (head) and last (tail) pointers for efficient
 * operations at both ends of the list, and a size counter for O(1) size queries.
 */
public class SingleLinkedList<E> {
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
    public SingleLinkedList() {
    }

    /**
     * Adds an element at the tail of the LinkedList
     *
     * @param element Value to insert at the end.
     *                Time Complexity: O(1) - Constant time due to tail pointer
     *                Space Complexity: O(1) - Only creates one new node
     */
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;    // Make me confused due to the accessibility to private inner class filed.
            last = newNode;
        }
        size++;
    }

    /**
     * Adds an element at the head of the LinkedList
     *
     * @param element Value to insert at the start
     *                Time Complexity: O(1) - Constant time operation
     *                Space Complexity: O(1) - Only creates one new node
     */
    public void addFirst(E element) {
        if (last == null)
            first = last = new Node<>(element, null);
        else
            first = new Node<>(element, first);

        size++;
    }

    /**
     * Inserts element at specified index, shifting existing elements right (0-Indexed)
     *
     * @param index   Position to insert at.
     * @param element Value to insert.
     *                Time Complexity: O(n) - In worst case, traverses to the end of the list
     *                Space Complexity: O(1) - Only creates one new node
     */
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");

        if (index == 0) {       // To update first pointer
            addFirst(element);
            return;
        }

        if (index == size) {    // To update last pointer
            addLast(element);
            return;
        }

        Node<E> curr = first;
        while (index-- > 1) curr = curr.next;
        curr.next = new Node<>(element, curr.next);
        size++;
    }

    /**
     * Removes the node of an index
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

        Node<E> curr;
        Node<E> prev = first;
        while (--index > 0)
            prev = prev.next;

        curr = prev.next;
        prev.next = curr.next;
        size--;
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

        Node<E> curr = first;
        Node<E> prev = null;

        while (curr != null) {
            if (curr.data.equals(element)) {
                assert prev != null;
                prev.next = curr.next;

                if (curr == last)
                    last = prev;

                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /**
     * Removes the first element of the LinkedList
     * Time Complexity: O(1) - Constant time operation
     * Space Complexity: O(1) - No additional space needed
     */
    public boolean removeFirst() {
        if (first == null) return false;
        
        if (first == last) {
            first = last = null; 
        }else{
            first = first.next;
        }

        size--;
        return true;
    }

    /**
     * Removes the last element of the LinkedList.
     * Time Complexity: O(n) - Requires traversal to find the second-to-last node
     * Space Complexity: O(1) - No additional space needed
     */
    public boolean removeLast() {
        if (first == null || last == null) return false;
        
        if (first == last) {
            first = last = null; 
        }else{
            Node<E> prev = first;
            while (prev.next != last)
                prev = prev.next;
    
            last = prev;
            last.next = null;
        }
        size--;
        return true;
    }

    /**
     * Removes all elements from the list.
     * Time Complexity: O(1) - Requires no traversal.
     * Space Complexity: O(1) - No additional space needed
     */
    public Boolean removeAll() {
        Node<E> temp;
        while (first != null) {
            temp = first;
            first = first.next;
            temp.next = null;
        }

        last = null;
        size = 0;
        return true;
    }

    /**
     * Retrieves element at specified index (0-indexed)
     *
     * @param index Position to get element from
     * @return Element at given index
     * Time Complexity: O(n) - May need to traverse up to n nodes
     * Space Complexity: O(1) - No additional space needed
     */
    public E get(int index) {
        if (first == null || index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index out of bounds");

        Node<E> curr = first;
        while (--index >= 0)
            curr = curr.next;

        return curr.data;
    }

    /**
     * Returns the first element in the LinkedList
     *
     * @return The first element
     * Time Complexity: O(1) - Direct access to first element
     * Space Complexity: O(1) - No additional space needed
     */
    public E getFirst() {
        if (first == null)
            throw new NullPointerException("LinkedList is empty");

        return first.data;
    }

    /**
     * Returns the last element in the LinkedList
     *
     * @return The last element in the list
     * Time Complexity: O(1) - Direct access to last element via tail pointer
     * Space Complexity: O(1) - No additional space needed
     */
    public E getLast() {
        if (last == null)
            throw new NullPointerException("LinkedList is empty");

        return last.data;
    }

    /**
     * Returns the current number of elements in the LinkedList
     *
     * @return The size of the LinkedList
     * Time Complexity: O(1) - Direct access to size variable
     * Space Complexity: O(1) - No additional space needed
     */
    public int getSize() {
        return size;
    }

    /**
     * Reverses the entire LinkedList in-place
     * Time Complexity: O(n) - Each node is visited exactly once
     * Space Complexity: O(1) - No extra memory used beyond temporary variables
     */
    public void reverse() {
        if (first == null || last == null)
            return;

        Node<E> curr = first;
        Node<E> next;
        Node<E> prev = null;

        last = first;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        first = prev;
    }

    /**
     * Searches for a specific element in the LinkedList
     *
     * @param element The value to search for in the list
     * @return The index of the element if found, -1 otherwise
     * Time Complexity: O(n) - May need to traverse the entire list
     * Space Complexity: O(1) - No additional space needed
     */
    public int linearSearch(E element) {
        Node<E> curr = first;
        for (int i = 0; curr != null; i++) {
            if (curr.data.equals(element))
                return i;

            curr = curr.next;
        }
        return -1;
    }

    /**
     * Checks the similarity between two LinkedLists.
     *
     * @param secList The second LinkedList to compare with
     * @return true if both lists contain the same elements in the same order, false otherwise
     * Time Complexity: O(n) - Must traverse all nodes in both lists
     * Space Complexity: O(1) - No additional space needed
     */
    public boolean isSame(final SingleLinkedList<E> secList) {
        if (this.size != secList.size)
            return false;

        Node<E> curr1 = this.first;
        Node<E> curr2 = secList.first;
        while (curr1 != null && curr2 != null) {
            if (!curr1.data.equals(curr2.data))
                return false;

            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return true;
    }
    // Alternative implementation: return toString().equals(secList.toString()); - Inefficient in space & time complexity

    /**
     * Checks if the LinkedList is empty.
     *
     * @return true if the LinkedList is empty, false otherwise
     * Time Complexity: O(1) - Direct check of size counter
     * Space Complexity: O(1) - No additional space needed
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * Note: The linear search could be improved by left shifting the element you find each time you search for it.
     * The next time you search for it again the time complexity is reduced from O(n) to O(n-1)
     */

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
     * Node class for the LinkedList implementation.
     * Contains the data and reference to the next node.
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Removes node
     *
     * @param node to be removed.
     *              Time Complexity: O(n) - May need to traverse the entire list to find the element
     *              Space Complexity: O(1) - No additional space needed
     */
    private void remove(Node <E> node) {
        if (node == null || first == null || last == null) return;

        if (first == node) {       // To update first pointer
            removeFirst();
            return;
        }

        if (last == node) {       // To update last pointer
            removeLast();
            return;
        }

        Node<E> curr = first.next;
        Node<E> prev = first;
        while (curr != null){
            if(curr == node){
                prev.next = curr.next;
                size--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
        verifyIntegrity();
    }

    /*************************************************************************************************************/
    /*******************************************     Assignments     *********************************************/
    /*************************************************************************************************************/

    /**
     * Adds a numeric element to the LinkedList with keeping it sorted
     *
     * @param element Value to insert
     *                Time Complexity: O(n) - May need to traverse the entire list to insert the element
     *                Space Complexity: O(1) - Only creates one new node
     *                Don't use it
     */
    public <T extends Number & Comparable<T>> void addSorted(T element) {
        if (first == null || element.compareTo((T) first.data) <= 0) { // if the LinkedList is empty or Insert at if small
            addFirst((E) element);
        } else if (element.compareTo((T) last.data) >= 0) { // Insert at last
            addLast((E) element);
        } else {
            Node<E> curr = first;
            while (curr.next != null && element.compareTo((T) curr.next.data) > 0)
                curr = curr.next;

            curr.next = new Node<>((E) element, curr.next);

            if (curr.next.next == null) // Update Last if the newNode is the last one
                last = curr.next;

            size++;
        }
        verifyIntegrity();
    }

    /**
     * Removes all nodes at even positions from the LinkedList.
     * For example, in list [1,2,3,4], nodes with values 2 and 4 will be removed.
     * Time Complexity: O(n) - Traverses the list once
     * Space Complexity: O(1) - No additional space needed
     */
    public void removeEvenPos() {
        if (first == null || last == null)
            return;

        Node<E> curr = first;
        Node<E> next;

        while (curr != null && curr.next != null) {
            next = curr.next;
            curr.next = next.next;
            size--;

            if (curr.next == null)
                last = curr; // Update Last if last node has been deleted.

            curr = curr.next;
        }
    }

    /**
     * Swaps the first and last nodes of the LinkedList.
     * Updates both data and pointers to maintain list integrity.
     * Time Complexity: O(n) - Requires finding the node before last
     * Space Complexity: O(1) - Uses only temporary variables
     */
    public void swapFirstLast() {
        if (first == null || last == null || first == last)
            return;

        Node<E> curr = last;
        Node<E> prev = first;

        while (prev.next != last) {
            prev = prev.next;
        }

        prev.next = first;
        curr.next = first.next;
        first.next = null;

        last = first;
        first = curr;

        verifyIntegrity();
    }

    /**
     * Swaps the values of adjacent nodes in the LinkedList.
     * For example, [1,2,3,4] becomes [2,1,4,3].
     * Time Complexity: O(n) - Traverses the list once
     * Space Complexity: O(1) - Uses only temporary variables
     */
    public void swapPairs() {
        if (first == null || last == null) return;

        Node<E> curr = first;

        while (curr != null && curr.next != null) {
            E temp = curr.data;
            curr.data = curr.next.data;
            curr.next.data = temp;

            curr = curr.next.next;
        }
        verifyIntegrity();
    }

    /**
     * Retrieves element at specified position in forward (1-indexed)
     *
     * @param nth Position to get element from
     * @return Element at given index
     * Time Complexity: O(n) - May need to traverse up to n nodes
     * Space Complexity: O(1) - No additional space needed
     */
    public E getNthForward(int nth) {
        if (nth <= 0 || nth > size)
            throw new IllegalArgumentException("Index out of bounds");

        Node<E> curr = first;
        while (--nth > 0)
            curr = curr.next;

        return curr.data;
    }

    /**
     * Retrieves element at specified position in reverse (1-indexed)
     *
     * @param nth Position to get element from
     * @return Element at given index
     * Time Complexity: O(n) - Requires traversal from the beginning
     * Space Complexity: O(1) - No additional space needed
     */
    public E getNthReverse(int nth) {
        if (nth <= 0 || nth > size)
            throw new IllegalArgumentException("Index out of bounds");

        Node<E> curr = first;

        int revSize = size - nth + 1; // could be replaced with return getNthForward(size - nth + 1)
        while (--revSize > 0)
            curr = curr.next;

        return curr.data;
    }

    /**
     * Rotates the LinkedList to the left by the specified number of positions.
     * For example, rotating [1,2,3,4,5] by 2 results in [3,4,5,1,2].
     *
     * @param rot The number of positions to rotate left
     *            Time Complexity: O(n) - Requires traversal to find new last node
     *            Space Complexity: O(1) - No additional space needed
     */
    public void leftRotate(int rot) {
        if (first == null || last == null || first == last || rot % size == 0)
            return;

        rot %= size; // as a rotating number greater than the size will return to the original arrangement.

        Node<E> curr = first;
        int revCurr = size - rot;
        while (--revCurr > 0) {
            curr = curr.next;
        }

        last.next = first;
        first = curr.next;
        last = curr;
        last.next = null;

//        while (rot > 0) {         // O(n^2)
//            last.next = first;
//            first = first.next;
//            last = last.next;
//            last.next = null;
//            rot--;
//        }
        verifyIntegrity();
    }

    /**
     * Prints all elements of the LinkedList in reverse order
     * Time Complexity: O(n^2) - Calls getNthForward() n times, each taking O(n) time
     * Space Complexity: O(1) - No additional space needed beyond output
     */
    public void printAllReverse() {
        int tempSize = size;

        System.out.print("[");
        while (tempSize > 0) {
            System.out.print(this.getNthForward(tempSize--) + " ");
        }
        System.out.println("]");
    }
    /*
    Alternative implementation using two pointers algorithm:
    Node curr = first;
    Node prev = first;

      while (curr != null){
        if(--nth < 0)
            prev = prev.next;

        curr = curr.next;
    }
     */

    /**
     * Removes the last occurrence of the specified element from the LinkedList.
     * If the element appears multiple times, only the last occurrence is removed.
     *
     * @param element The element whose last occurrence should be removed
     * Time Complexity: O(n) - Requires a full traversal to find the last occurrence
     * Space Complexity: O(1) - Uses only a few temporary node references
     */
    public void removeLastOccur(E element) {
        if (first == null || last == null) return;
        if (first == last && element.equals(first.data)) {
            removeFirst();
            return;
        }

        Node<E> prevLastOccur = null;
        Node<E> lastOccur = null;
        Node<E> prev = null;
        Node<E> curr = first;
        while (curr != null){
            if(element.equals(curr.data)){
                prevLastOccur = prev;
                lastOccur = curr;
            }
            prev = curr;
            curr = curr.next;
        }

        if(prevLastOccur == null){
            removeFirst();
            return;
        }

        if(lastOccur == last) {
            removeLast();
            return;
        }

        prevLastOccur.next = lastOccur.next;
        size--;

        verifyIntegrity();
    }

    /**
     * Moves all occurrences of the specified element to the back of the LinkedList.
     * The relative order of other elements is preserved.
     *
     * @param element The element to be moved to the back of the list
     * Time Complexity: O(n) - Requires traversal of the list
     * Space Complexity: O(1) - Uses only a few temporary node references
     */
    public void moveToBack(E element) {
        if (first == null || last == null) return;

        Node<E> dummyFirst = new Node<>(null, first);
        Node<E> prev = dummyFirst;
        Node<E> curr = first;
        Node<E> originalLast = last;
        while (curr != null && curr != originalLast) {
            if (element.equals(curr.data)) {
                prev.next = curr.next;
                last.next = curr;
                last = curr;
                last.next = null;

                curr = prev.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        first = dummyFirst.next;
        verifyIntegrity();
    }


    /**
     * Remove all duplicate of elements in the LinkedList.
     * The relative order of other elements is preserved.
     *
     * Time Complexity: O(n^2) - Requires traversal of the list
     * Space Complexity: O(1) - Uses only a few temporary node references
     */
    public void removeDuplicate() {
        if(first == null || last == null) return;

        Node<E> element = first;

        while (element != null) {
            Node<E> prev = element;
            Node<E> curr = element.next;

            while (curr != null) {
                if ((element.data == null && curr.data == null) ||
                        (element.data != null && element.data.equals(curr.data))) {
                    prev.next = curr.next;

                    if (curr == last) {
                        last = prev;
                    }

                    size--;
                } else {
                    prev = curr;
                }
                curr = prev.next;
            }
            element = element.next;
        }
        verifyIntegrity();
    }

    public void arrangeOddPosFirst(){
        if (first == null || last == null || last == first ) return;

        Node<E> firstOdd = first;
        Node<E> firstEven = first.next;


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
            Node<E> curr = first;

            while (curr.next != null) {
                tempLen++;
                curr = curr.next;
            }

            if (tempLen != size) {
                throw new RuntimeException("The size of LinkedList is incorrect");
            }

            if (curr != last) {
                throw new RuntimeException("The Last pointer is incorrect");
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
            System.out.println("[ ]\t Size = 0");
            System.out.println("Tail -> null");
        } else {

            Node<E> curr = first;

            System.out.println("Head ---> " + first.data);
            while (curr.next != null) {
                System.out.print(curr.data + " --> " + curr.next.data);
                curr = curr.next;
            }
            System.out.println("\nTail ---> " + last.data);
            System.out.println("The size is : " + size);
        }
    }
}