package tree.avl;

import java.util.LinkedList;
import java.util.Queue;

/**
 * An implementation of an AVL (Adelson-Velsky and Landis) tree.
 * AVL tree is a self-balancing binary search tree where the heights of the left and right subtrees of any node differ by at most one.
 * Time Complexity for main operations:
 * - Insertion: O(log n)
 * - Deletion: O(log n)
 * - Search: O(log n)
 * Space Complexity: O(n) where n is the number of nodes in the tree
 */
public class AVLTree {
    private Node root;

    /**
     * Adds a new value to the AVL tree.
     * Time Complexity: O(log n) - Due to AVL tree balancing
     * Space Complexity: O(log n) - Due to recursion stack
     *
     * @param data the value to be added
     */
    public void add(int data){
        if (root == null)
            root = new Node(data);
        else
            root = insert(root,data);
    }

    /**
     * Removes a value from the AVL tree.
     * Time Complexity: O(log n) - Due to AVL tree balancing
     * Space Complexity: O(log n) - Due to recursion stack
     *
     * @param data the value to be removed
     */
    public void remove(int data){
        if (root != null)
            root = remove(root, data);
    }

    /**
     * Finds the smallest element in the tree that is greater than or equal to the target.
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) - Due to recursion stack
     *
     * @param target the target value to find the lower bound for
     * @return the smallest element >= target, or Integer.MAX_VALUE if no such element exists
     */
    public int getLowerBound(int target){
        return lowerBound(root,target);
    }

    /**
     * Helper method to find the lower bound recursively.
     * @param curNode current node being examined
     * @param target target value to find lower bound for
     * @return the smallest element >= target, or Integer.MAX_VALUE if no such element exists
     */
    private int lowerBound(Node curNode, int target){
        if(curNode == null)
            return Integer.MAX_VALUE;

        if (curNode.data >= target){
            int lb = lowerBound(curNode.left, target);
            if(lb != Integer.MAX_VALUE)
                return lb;

            return curNode.data;
        }

        return lowerBound(curNode.right, target);
    }

    /**
     * Finds the smallest element in the tree that is strictly greater than the target.
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) - Due to recursion stack
     *
     * @param target the target value to find the upper bound for
     * @return the smallest element > target, or Integer.MIN_VALUE if no such element exists
     */
    public int getUpperBound(int target){
        return upperBound(root,target);
    }

    /**
     * Helper method to find the upper bound recursively.
     * @param curNode current node being examined
     * @param target target value to find upper bound for
     * @return the smallest element > target, or Integer.MIN_VALUE if no such element exists
     */
    private int upperBound(Node curNode, int target){
        if(curNode == null)
            return Integer.MIN_VALUE;

        if (curNode.data > target){
            int lb = upperBound(curNode.left, target);
            if(lb != Integer.MIN_VALUE)
                return lb;

            return curNode.data;
        }

        return upperBound(curNode.right, target);
    }

    /**
     * Counts the number of inversions in an array using the AVL tree.
     * An inversion is a pair of indices (i,j) such that i < j and arr[i] > arr[j].
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     *
     * @param arr the input array
     * @return the number of inversions in the array
     */
    public int countInversions(int[] arr){
        int res = 0;
        for (int j : arr) {
            this.add(j); //Adding the number then count the inversions res
            res += upperBoundCount(root, j);
        }
        return res;
    }

    /**
     * Helper method to count elements greater than target in the tree.
     * @param curNode current node being examined
     * @param target target value to compare against
     * @return count of elements greater than target
     */
    private int upperBoundCount(Node curNode, int target){
        if (curNode == null)
            return 0;

        if (curNode.data > target){
            int sum = 1;
            
            if (curNode.right != null)
                sum += upperBoundCount(curNode.right, target);

            return sum + upperBoundCount(curNode.left, target);
        }

        return upperBoundCount(curNode.right, target);
    }

    /**
     * Prints the tree in level order (breadth-first) traversal.
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Due to queue usage
     */
    public void printLevelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            System.out.println("Level : " + level);
            while (size-- > 0) {
                Node curNode = queue.remove();
                System.out.print(curNode.data + " ");

                if (curNode.left != null)
                    queue.add(curNode.left);

                if (curNode.right != null)
                    queue.add(curNode.right);
            }
            ++level;
            System.out.println();
        }
    }

    /**
     * Calculates the minimum number of nodes required for an AVL tree of given height.
     * Time Complexity: O(2^n) - Due to recursive nature
     * Space Complexity: O(n) - Due to recursion stack
     *
     * @return minimum number of nodes required for the current tree height
     */
    public int minNodeAvlHeight(){
        return minNodeAvlHeight(root.height);
    }

    /**
     * Helper method to calculate minimum nodes recursively.
     * @param height the height of the tree
     * @return minimum number of nodes required for the given height
     */
    private int minNodeAvlHeight(int height){
        if (height <= 2)
            return height - 1;

        return 1 + minNodeAvlHeight(height - 1) + minNodeAvlHeight(height - 2);
    }

    /**
     * Removes a node with the given target value from the tree.
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) - Due to recursion stack
     *
     * @param curNode current node being examined
     * @param target value to be removed
     * @return the root of the modified subtree
     */
    private Node remove(Node curNode, int target){
        if (curNode == null) //Node not found
            return null;

        if (curNode.data < target){
            curNode.right = remove(curNode.right, target);
        }else if (curNode.data > target){
            curNode.left = remove(curNode.left, target);
        } else { //Node found

            // case have one child or leaf (return null in case of leaf)
            if (curNode.right == null)
                 return curNode.left;

            if (curNode.left == null)
                return curNode.right;

            //case have two children (replace the node with its successor, then remove that successor node)
            Node successorNode = getMin(curNode.right);
            curNode.data = successorNode.data;
            curNode.right = remove(curNode.right, successorNode.data);
        }

        // Recursively traverse back up the insertion path to update heights and re-balance.
        updateHeight(curNode);
        return balance(curNode);
    }

    /**
     * Inserts a new value into the tree.
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) - Due to recursion stack
     *
     * @param curNode current node being examined
     * @param data value to be inserted
     * @return the root of the modified subtree
     */
    private Node insert(Node curNode, int data){
        if (data > curNode.data){
            if (curNode.right == null)
                curNode.right = new Node(data);
            else
                curNode.right = insert(curNode.right, data); //due to balancing it my return a complete different node.

        }else if (data < curNode.data){
            if (curNode.left == null)
                curNode.left = new Node(data);
            else
                curNode.left = insert(curNode.left, data); //due to balancing it my return a complete different node.
        }

        // Recursively traverse back up the insertion path to update heights and re-balance.
        updateHeight(curNode);
        return balance(curNode);
    }

    /**
     * Balances the tree at the given node.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param curNode node to be balanced
     * @return the root of the balanced subtree
     */
    private Node balance(Node curNode){
        int bf = balanceFactor(curNode); //We don't expect any bf out of [-2, 2] as the whole tree follows CHANGE-FIX approach.

        if(bf == 2){ //LL
            if (balanceFactor(curNode.left) == -1) //LR
                curNode.left = leftRotate(curNode.left); // LR -> LL

            curNode = rightRotate(curNode);
        }else if(bf == -2){ //RR
            if (balanceFactor(curNode.right) == 1) //RL
                curNode.right = rightRotate(curNode.right); // RL -> RR

            curNode = leftRotate(curNode);
        }

        return curNode;
    }

    /**
     * Calculates the balance factor of a node.
     * Balance factor = height(left subtree) - height(right subtree)
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param curNode node to calculate balance factor for
     * @return the balance factor
     */
    private int balanceFactor(Node curNode){
        return height(curNode.left) - height(curNode.right);
    }

    /**
     * Updates the height of a node based on its children's heights.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param curNode node to update height for
     */
    private void updateHeight(Node curNode){
        curNode.height = 1 + Math.max(height(curNode.left), height(curNode.right));
    }

    /**
     * Gets the height of a node.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param curNode node to get height for
     * @return height of the node (-1 for null nodes)
     */
    private int height(Node curNode){
        return (curNode == null) ? -1 : curNode.height; // -1 for null, 0 for leaf
    }

    /**
     * Performs a left rotation on the given node.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param curNode node to rotate
     * @return the new root of the rotated subtree
     */
    private Node leftRotate(Node curNode){
        Node newRoot = curNode.right;
        curNode.right = newRoot.left;
        newRoot.left = curNode;
        updateHeight(curNode);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * Performs a right rotation on the given node.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param curNode node to rotate
     * @return the new root of the rotated subtree
     */
    private Node rightRotate(Node curNode){
        Node newRoot = curNode.left;
        curNode.left = newRoot.right;
        newRoot.right = curNode;
        updateHeight(curNode);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * Finds the minimum value node in a subtree.
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param curNode root of the subtree
     * @return node with minimum value
     */
    private Node getMin(Node curNode){
        while (curNode.left != null)
            curNode = curNode.left;

        return curNode;
    }

    /**
     * Node class representing a node in the AVL tree.
     * Each node contains a value, height, and references to its left and right children.
     */
    private static class Node{
        /** The value stored in this node */
        public int data;
        /** The height of this node*/
        public int height;
        /** Reference to the left child */
        public Node left;
        /** Reference to the right child */
        public Node right;

        /**
         * Constructs a node with the specified value.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         *
         * @param data the value to be stored in the node
         */
        public Node(int data) {
            this.data = data;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
    }
}