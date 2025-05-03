package tree.bst;

import java.util.ArrayList;

/**
 * A Binary Search Tree implementation that maintains the BST property:
 * for each node, all elements in the left subtree are less than the node's value,
 * and all elements in the right subtree are greater than the node's value.
 */
public class BinarySearchTree {
    private Node root;

    /**
     * Constructs an empty binary search tree with a dummy root node.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public BinarySearchTree(){
        root = new Node(0,null);
    }

    /**
     * Constructs a binary search tree with a single root node.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the value to be stored in the root node
     */
    public BinarySearchTree(int data){
        root = new Node(data, null);
    }


    /**
     * Constructs a binary search tree from a pre-order traversal of the tree.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(n) for the tree nodes + O(log n) for the recursion stack
     *
     * @param preOrder the pre-order traversal of the tree
     */
//    public BinarySearchTree(int[] preOrder){
//        root = toBST(preOrder, 0, preOrder.length - 1);
//    }

    /**
     * Constructs a binary search tree from a level-order traversal of the tree.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(n) for the tree nodes + O(log n) for the recursion stack
     *
     * @param levelOrder the level-order traversal of the tree
     */ 
    public BinarySearchTree(int[] levelOrder){
        root = toBST(levelOrder);
    }

    /**
     * Adds a new value to the binary search tree.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) for the recursion stack
     * 
     * @param data the value to be added
     */
    public void add(int data){
        insert(root,data);
    }

    /**
     * Removes a value from the binary search tree.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) for the recursion stack
     * 
     * @param data the value to be removed
     * @return true if the value was found and removed, false otherwise
     */
    public boolean remove(int data){
        return remove(root, data) != null;
    }

    /**
     * Returns an ArrayList containing the values of the tree in in-order traversal.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(n) for the ArrayList + O(h) for the recursion stack
     * 
     * @return an ArrayList containing the values in in-order, or null if the tree is empty
     */
    public ArrayList<Integer> inOrderTraverse(){
        if (root == null)
            return null;

        ArrayList<Integer> arr = new ArrayList<>();
        inOrderTraverse(root, arr);
        return arr;
    }

    /**
     * Returns an ArrayList containing the values of the tree in pre-order traversal.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(n) for the ArrayList + O(h) for the recursion stack
     * 
     * @return an ArrayList containing the values in pre-order, or null if the tree is empty
     */
    public ArrayList<Integer> preOrderTraverse(){
        if (root == null)
            return null;

        ArrayList<Integer> arr = new ArrayList<>();
        preOrderTraverse(root, arr);
        return arr;
    }

    /**
     * Checks if the tree contains the specified value.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(1) for the iterative implementation
     * 
     * @param data the value to search for
     * @return true if the tree contains the value, false otherwise
     */
    public boolean contains(int data){
        return contains(root, data);
    }

    /**
     * Returns the successor of the specified value in the tree.
     * The successor is the smallest value greater than the specified value.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(1)
     * 
     * @param data the value to find the successor for
     * @return the successor value, or Integer.MIN_VALUE if no successor exists
     */
    public int getSuccessor(int data){
        Node res = getSuccessor(root,data);

        if (res == null)
            return Integer.MIN_VALUE;

        return res.data;
    }

    /**
     * Returns the minimum value in the tree.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(1)
     * 
     * @return the minimum value in the tree
     */
    public int getMin(){
        return getMin(root).data;
    }

    /**
     * Returns the maximum value in the tree.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(1)
     * 
     * @return the maximum value in the tree
     */
    public int getMax(){
        return getMax(root).data;
    }

    /**
     * Checks if the tree satisfies the binary search tree property.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     * 
     * @return true if the tree is a valid BST, false otherwise
     */
    public boolean isBST(){
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Converts the tree to a balanced BST using the provided sorted array.
     * Time Complexity: O(n) where n is the length of the array
     * Space Complexity: O(n) for the tree nodes + O(log n) for the recursion stack
     * 
     * @param arr the sorted array of values
     */
    public void toBalancedBST(int[] arr){
       root = toBalanced(arr, 0, arr.length - 1);
    }


//    private Node toBST(int[] preOrder, int start, int end){ // The idea is to split and assign all the lhs and rhs of each node.
//        Node curNode = new Node(preOrder[start]);
//
//        for(int i = start + 1; i <= end + 1; ++i){ // could be replaced with nextGreaterPosition().
//
//            if (i == end + 1|| curNode.data < preOrder[i]){ // reached to last element no greater || get greater than the node.
//
//                if (start + 1 <= i - 1) // all previous of the i (splitter) is lhs.
//                    curNode.lhs = toBST(preOrder, start + 1, i - 1);
//
//                if (i <= end) // all after the i (splitter) is rhs
//                    curNode.rhs = toBST(preOrder, i, end);
//
//                break; // break for curNode all rest after greater is not belong to it
//            }
//        }
//
//        return curNode;
//    }

    private Node toBST(int[] levelOrder){
        Node curNode = new Node(levelOrder[0]);

        for (int i = 1; i < levelOrder.length; ++i)
            insert(curNode, levelOrder[i]);

        return curNode;
    }

    /**
     * Returns the nth smallest value in the binary search tree.
     * This implementation uses in-order traversal to get sorted values, then retrieves the nth element.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(n) for the ArrayList + O(h) for the recursion stack
     * 
     * @param nth the position of the value to retrieve (1-based indexing)
     * @return the nth smallest value in the tree
     * @throws IndexOutOfBoundsException if nth is less than 1 or greater than the tree size
     * @throws IllegalStateException if the tree is empty
     * @see #inOrderTraverse() for the method used to get sorted values
     */
    public int getNthSmallest(int nth){ // could be implemented using reference to counter
        return inOrderTraverse().get(nth -1);
    }

    /**
     * Returns the lowest common ancestor of two values in the tree.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) for the recursion stack
     * 
     * @param firstVal the first value
     * @param secVal the second value
     * @return the value of the lowest common ancestor
     */
    public int lowestCommonAncestor(int firstVal, int secVal){
        return lowestCommonAncestor(root, firstVal, secVal).data;
    }

    /**
     * Checks if the tree is degenerate (each node has at most one child).
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(n) for the ArrayList + O(h) for the recursion stack
     * 
     * @return true if the tree is degenerate, false otherwise
     */
    public boolean isDegenerate(){
        return isDegenerate(preOrderTraverse());
    }

    /**
     * Checks if a pre-order traversal represents a degenerate BST.
     * Time Complexity: O(n) where n is the size of the pre-order list
     * Space Complexity: O(1)
     * 
     * @param preOrder the pre-order traversal of the tree
     * @return true if the tree is degenerate, false otherwise
     */
    private boolean isDegenerate(ArrayList<Integer> preOrder){
        if (preOrder.size() < 2)
            return true;

        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        for (int i = 1; i < preOrder.size(); ++i){
            int curr = preOrder.get(i);

            if (curr < min || curr > max)
                return false;

            if (curr > preOrder.get(i - 1))
                min = preOrder.get(i - 1) + 1;
            else
                max = preOrder.get(i - 1) - 1;
        }

        return true;
    }

    /**
     * Finds the lowest common ancestor of two values in the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the subtree
     * Space Complexity: O(h) for the recursion stack
     * 
     * @param curNode the root of the subtree
     * @param firstVal the first value
     * @param secVal the second value
     * @return the node that is the lowest common ancestor, or null if either value is not found
     */
    private Node lowestCommonAncestor(Node curNode, int firstVal, int secVal){
        if (curNode == null)
            return null;

        if (curNode.data > firstVal && curNode.data > secVal)
            return lowestCommonAncestor(curNode.lhs, firstVal, secVal);

        if (curNode.data < firstVal && curNode.data < secVal)
            return lowestCommonAncestor(curNode.rhs, firstVal, secVal);

        return curNode;
    }

    /**
     * Creates a balanced BST from a sorted array.
     * Time Complexity: O(n) where n is the length of the array
     * Space Complexity: O(n) for the tree nodes + O(log n) for the recursion stack
     * 
     * @param arr the sorted array of values
     * @param start the start index of the current subarray
     * @param end the end index of the current subarray
     * @return the root of the balanced BST
     */
    private Node toBalanced(int[] arr, int start, int end){
        if (start > end)
            return null;

        int mid = (start + end)/2;
        Node curNode = new Node(arr[mid]);
        curNode.lhs = toBalanced(arr, start, mid-1);
        curNode.rhs = toBalanced(arr,mid + 1, end);

        return curNode;
    }

    /**
     * Checks if the subtree rooted at the given node is a valid Binary Search Tree (BST).
     * A valid BST has the property that all nodes in the left subtree have values less than
     * the current node's value, and all nodes in the right subtree have values greater than
     * the current node's value.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     * 
     * @param curNode the root node of the subtree to check
     * @param min the minimum allowed value for nodes in this subtree
     * @param max the maximum allowed value for nodes in this subtree
     * @return true if the subtree is a valid BST, false otherwise
     */
    private boolean isBST(Node curNode, int min, int max){ // could be implemented by checking the inorder traversal is sorted or not
        if (curNode == null)
            return true;

        if(curNode.data <= min || curNode.data >= max) // Must check if all the lhs is smaller, and rhs is greater than the root.
            return false;

        return isBST(curNode.lhs, min, curNode.data) && isBST(curNode.rhs, curNode.data, max);
    }

    /**
     * Finds the node with the maximum value in the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the subtree
     * Space Complexity: O(1)
     * 
     * @param curNode the root of the subtree
     * @return the node with the maximum value
     */
    private Node getMax(Node curNode){
        while (curNode.rhs != null)
            curNode = curNode.rhs;

        return curNode;
    }

    /**
     * Finds the node with the minimum value in the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the subtree
     * Space Complexity: O(1)
     * 
     * @param curNode the root of the subtree
     * @return the node with the minimum value
     */
    private Node getMin(Node curNode){
        while (curNode.lhs != null)
            curNode = curNode.lhs;

        return curNode;
    }

    /**
     * Finds the successor node of the node with the specified value.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(1)
     * 
     * @param curNode the root of the tree
     * @param data the value to find the successor for
     * @return the successor node, or null if no successor exists
     */
    private Node getSuccessor(Node curNode, int data){
        Node successor = null;

        // find the node with passed data
        while (curNode != null){
            if (data < curNode.data){
                successor = curNode; // In case the required node is the greatest in the left subtree
                curNode = curNode.lhs;
            }else if (data > curNode.data){
                curNode = curNode.rhs;
            }else {
                break;
            }
        }

        if (curNode == null)
            return null; //Node not found

        // in case the node with passed data has rhs so the successor will be the most right value.
        if (curNode.rhs != null)
            return getMin(curNode.rhs);

        return successor;
    }
    /* another sol using parent

            // find the node with passed data
        while (curNode != null){
            if (data < curNode.data){
                curNode = curNode.lhs;
            }else if (data > curNode.data){
                curNode = curNode.rhs;
            }else {
                break;
            }
        }

        if (curNode == null)
            return null; //Node not found

        // in case the node with passed data has rhs so the successor will be the most right value.
        if (curNode.rhs != null)
            return getMin(curNode.rhs);

        while (curNode.parent.data < curNode.data)
            curNode = curNode.parent;

        return curNode.parent;

    * */

    /**
     * Removes a node with the specified value from the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the subtree
     * Space Complexity: O(h) for the recursion stack
     *
     * @param curNode the root of the subtree
     * @param data the value to remove
     * */
    private boolean contains(Node curNode, int data){
        while (curNode != null){
            if (curNode.data > data)
                curNode = curNode.lhs;
            else if (curNode.data < data)
                curNode = curNode.rhs;
            else
                return true;
        }

        return false;

        /* recursive sol

        if (data == curNode.data)
            return true;

        if (curNode.rhs != null && data > curNode.data)
            return contains(curNode.rhs,data);

        if (curNode.lhs != null && data < curNode.data)
            return contains(curNode.lhs,data);

        return false;*/
    }

    /**
     * Performs an in-order traversal of the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     * 
     * @param curNode the root of the subtree
     * @param arr the ArrayList to add the values to
     */
    private void inOrderTraverse(Node curNode, ArrayList<Integer> arr){
        if (curNode == null)
            return;

        inOrderTraverse(curNode.lhs, arr);
        arr.add(curNode.data);
        inOrderTraverse(curNode.rhs, arr);
    }

    /**
     * Performs a pre-order traversal of the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     * 
     * @param curNode the root of the subtree
     * @param arr the ArrayList to add the values to
     */
    private void preOrderTraverse(Node curNode, ArrayList<Integer> arr){
        if (curNode == null)
            return;

        arr.add(curNode.data);
        preOrderTraverse(curNode.lhs, arr);
        preOrderTraverse(curNode.rhs, arr);
    }

    /**
     * Removes the node with the specified value from the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the subtree
     * Space Complexity: O(h) for the recursion stack
     * 
     * @param curNode the root of the subtree
     * @param target the value to be removed
     * @return the new root of the subtree after removal
     */
    private Node remove(Node curNode, int target){
        if (curNode == null) //Node not found
            return null;

        if (curNode.data < target){
            curNode.rhs = remove(curNode.rhs, target);
        }else if (curNode.data > target){
            curNode.lhs = remove(curNode.lhs, target);
        } else { //Node found

            // case have one child or leaf (return null in case of leaf)
            if (curNode.rhs == null)
                return curNode.lhs;

            if (curNode.lhs == null)
                return curNode.rhs;

            //case have two children (replace the node with its successor, then remove that successor node)
            Node successorNode = getMin(curNode.rhs);
            curNode.data = successorNode.data;
            curNode.rhs = remove(curNode.rhs, successorNode.data);
        }

        return curNode;
    }

    /**
     * Inserts a new node with the specified value into the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the subtree
     * Space Complexity: O(h) for the recursion stack
     *
     * @param curNode the root of the subtree
     * @param data the value to be inserted
     */
    private void insert(Node curNode, int data){
        if (data > curNode.data){
            if (curNode.rhs == null)
                curNode.rhs = new Node(data,curNode); // curNode as parent
            else
                insert(curNode.rhs, data);

        }else if ((data < curNode.data)){
            if (curNode.lhs == null)
                curNode.lhs = new Node(data,curNode);
            else
                insert(curNode.lhs, data);
        }else {
            //Already in the tree
            return;
        }
    }
    private static class Node {
        /** The value stored in this node */
        int data;
        /** Reference to the parent */
        Node parent;
        /** Reference to the left child */
        Node lhs;
        /** Reference to the right child */
        Node rhs;

        /**
         * Constructs a node with the specified value.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         *
         * @param data the value to be stored in the node
         */
        public Node(int data) {
            this.data = data;
        }

        /**
         * Constructs a node with the specified value and parent.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         *
         * @param data the value to be stored in the node
         * @param parent the parent node
         */
        public Node(int data, Node parent) {
            this.data = data;
            this.parent = parent;
        }
    }
}
