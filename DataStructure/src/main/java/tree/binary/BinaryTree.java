package tree.binary;

import java.util.*;

/**
 * A binary tree implementation that supports various tree operations.
 * This class provides methods for tree traversal, node counting, height calculation,
 * and other common binary tree operations.
 */
public class BinaryTree {
    private Node root;

    /**
     * Constructs a binary tree with a single root node.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param data the value to be stored in the root node
     */
    public BinaryTree(int data) {
        this.root = new Node(data);
    }

//    /**
//     * Constructs a binary tree with pre and in orders.
//     *
//     * @param preOrder the deque of the preOrder
//     * @param inOrder the deque of the inOrder
//     */
//    public BinaryTree(Deque<Integer> preOrder, Deque<Integer> inOrder) {
//        this.root = buildTree(preOrder, inOrder);
//    }

//    /**
//     * Constructs a full binary tree with pre and in orders.
//     *
//     * @param preOrder the deque of the preOrder
//     * @param isLeaf the deque of the inOrder
//     */
//    public BinaryTree(String preOrder, Queue<Boolean> isLeaf) {
//    }

//    private Node buildTree(Deque<Integer> preOrder, Deque<Integer> inOrder){
//        if (inOrder.isEmpty()) return null;
//
//        Node curr = new Node(preOrder.remove());
//
//        Deque<Integer> left = new LinkedList<>();
//        Deque<Integer> right = new LinkedList<>();
//
//        while (inOrder.peek() != curr.data)
//            left.add(inOrder.remove());
//
//        inOrder.remove();
//
//        while (!inOrder.isEmpty())
//            right.add(inOrder.remove());
//
//        curr.lhs = buildTree(preOrder, left);
//        curr.rhs = buildTree(preOrder,right);
//
//        return curr;
//    }

//    /**
//     * Constructs a binary tree from a postfix expression.
//     * Operators and operands in the postfix expression are used to build the tree.
//     *
//     * @param postFix the postfix expression string
//     */
//    public BinaryTree(String postFix) {
//
//        Stack<Node> stack = new Stack<>();
//
//        for (int i = 0; i < postFix.length(); ++i){
//            Node curr = new Node(postFix.charAt(i));
//
//            if (curr.data < '0' || curr.data > '9') {
//                curr.rhs = stack.pop();
//                curr.lhs = stack.pop();
//            }
//
//            stack.push(curr);
//        }
//
//        root = stack.pop();
//    }

    /**
     * Adds nodes to the tree following the specified path of directions.
     * Each direction ('L' or 'R') indicates whether to go left or right from the current node.
     * Time Complexity: O(n) where n is the length of the directions array
     * Space Complexity: O(1)
     *
     * @param data the values to be stored in the nodes
     * @param directions the directions to follow when adding nodes ('L' for left, 'R' for right)
     * @throws IllegalArgumentException if data and directions arrays have different lengths or if an invalid direction is provided
     * @throws AssertionError if a node in the path already exists with a different value
     */
    public void add(int[] data, char[] directions){
        if (data.length != directions.length)
            throw new IllegalArgumentException("Mismatched values and directions length");

        Node curr = root;
        for (int i = 0; i < data.length; ++i){
            if (directions[i] == 'L'){
                if (curr.lhs == null)
                    curr.lhs = new Node(data[i]);
                else if (curr.lhs.data != data[i])
                    throw new AssertionError("Incorrect left value in path");

                curr = curr.lhs;

            }else if (directions[i] == 'R'){
                if (curr.rhs == null)
                    curr.rhs = new Node(data[i]);
                else if (curr.rhs.data != data[i])
                    throw new AssertionError("Incorrect right value in path");

                curr = curr.rhs;

            }else {
                throw new IllegalArgumentException("Invalid direction: " + directions[i]);
            }
        }
    }

    /**
     * Prints the tree nodes in in-order traversal (Left-Value-Right).
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @throws RuntimeException if the tree is empty
     */
    public void printInOrder(){
        if (root == null)
            throw new RuntimeException("The tree is empty");

        printInOrder(root);
        System.out.println();
    }

    /**
     * Prints the tree nodes in post-order traversal (Left-Right-Value).
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @throws RuntimeException if the tree is empty
     */
    public void printPostOrder(){
        if (root == null)
            throw new RuntimeException("The tree is empty");

        printPostOrder(root);
        System.out.println();
    }

//    /**
//     * Prints the tree nodes as characters in post-order traversal.
//     * Useful for expression trees where nodes contain character codes.
//     *
//     * @throws RuntimeException if the tree is empty
//     */
//    public void printPostOrderExpression(){
//        if (root == null)
//            throw new RuntimeException("The tree is empty");
//
//        printPostOrderExpression(root);
//        System.out.println();
//    }

    /**
     * Prints the tree nodes in pre-order traversal (Value-Left-Right).
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @throws RuntimeException if the tree is empty
     */
    public void printPreOrder(){
        if (root == null)
            throw new RuntimeException("The tree is empty");

        printPreOrder(root);
        System.out.println();
    }

    public void printPreOrderComplete(){
        printPreOrderComplete(root);
        System.out.println();
    }

    /**
     * Prints the nodes of the subtree rooted at the given node in level-order traversal.
     */
    public void printLevelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            System.out.println("Level : " + level);
            while (size-- > 0) {
                Node currNode = queue.remove();
                System.out.print(currNode.data + " ");

                if (currNode.lhs != null)
                    queue.add(currNode.lhs);

                if (currNode.rhs != null)
                    queue.add(currNode.rhs);

            }
            ++level;
            System.out.println();
        }
    }

    public void printLevelOrderSpiral(){
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(root);

        boolean fwdLvl = true;

        while (!deque.isEmpty()){
            int size = deque.size();

            while (size-- > 0){
                Node currNode;
                if (fwdLvl){
                    currNode = deque.removeFirst();
                    System.out.print(currNode.data + " ");

                    if (currNode.lhs != null)
                        deque.addLast(currNode.lhs);// left added first here

                    if (currNode.rhs != null)
                        deque.addLast(currNode.rhs);

                }else {
                    currNode = deque.removeLast();
                    System.out.print(currNode.data + " ");

                    if (currNode.rhs != null)
                        deque.addFirst(currNode.rhs);// right added first here

                    if (currNode.lhs != null)
                        deque.addFirst(currNode.lhs);
                }
            }
            fwdLvl = !fwdLvl;
        }
    }

    public String toParenthesizedString(){
        StringBuilder sb = new StringBuilder();
        toParenthesizedString(root, sb);
        return sb.toString();
    }

    public String toCanonicalParenthesisString(){
        return toCanonicalParenthesisString(root);
    }

//    public void printDuplicateSubtrees(){
//        printDuplicateSubtrees(root);
//    }

    public boolean isComplete(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();

            boolean isLeafLevel = false;

            while (size-- > 0){
                Node currNode = queue.remove();

                if (currNode.lhs != null){
                    if (isLeafLevel)
                        return false;

                    queue.add(currNode.lhs);
                }else {
                    isLeafLevel = true;
                }

                if (currNode.rhs != null){
                    if (isLeafLevel)
                        return false;

                    queue.add(currNode.rhs);
                }else {
                    isLeafLevel = true;
                }
            }
        }
        return true;
    }

    /**
     * Returns the maximum value in the tree.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @return the maximum value in the tree, or Integer.MIN_VALUE if the tree is empty
     */
    public int getMax(){
        return getMax(root);
    }

    /**
     * Returns the minimum value in the tree.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @return the minimum value in the tree, or Integer.MAX_VALUE if the tree is empty
     */
    public int getMin(){
        return getMin(root);
    }

    /**
     * Returns the height of the tree.
     * The height is defined as the number of edges on the longest path from the root to a leaf.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @return the height of the tree, or -1 if the tree is empty
     */
    public int height(){
        return getHeight(root);
    }

    /**
     * Returns the total number of nodes in the tree.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @return the number of nodes in the tree
     */
    public int countNodes(){
        return countNodes(root);
    }

    /**
     * Returns the number of leaf nodes in the tree.
     * A leaf node is a node with no children.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @return the number of leaf nodes in the tree
     */
    public int countLeaves(){
        return countLeaves(root);
    }

    /**
     * Checks if the tree contains a node with the specified value.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @param value the value to search for
     * @return true if the tree contains the value, false otherwise
     */
    public boolean contains(int value){
       return contains(root, value);
    }

    /**
     * Checks if the tree is a perfect binary tree.
     * A perfect binary tree is a tree in which all interior nodes have two children
     * and all leaves are at the same level.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @return true if the tree is perfect, false otherwise
     */
    public boolean isPerfect(){
        return isPerfect(root, -1);
    }

    public boolean isSymmetric(){
        return isMirror(root.lhs, root.lhs);
    }

    /**
     * Clears the tree by removing all nodes.
     */
    public void clear(){
        clearChildren(root);
        root = null;
    }

    /**
     * Prints the left boundary of the tree.
     * The left boundary consists of nodes that are the leftmost nodes at each level,
     * excluding leaf nodes.
     */
    public void printLeftBoundary(){
        printLeftBoundary(root);
    }

    /**
     * Prints the right boundary of the tree.
     * The right boundary consists of nodes that are the rightmost nodes at each level,
     * excluding leaf nodes.
     */
    public void printRightBoundary(){
        printRightBoundary(root);
    }

    /**
     * Returns the diameter of the tree.
     * The diameter is the length of the longest path between any two nodes in the tree.
     * Time Complexity: O(n²) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @return the diameter of the tree
     */
    public int getDiameter(){
        return getDiameter(root);
    }

    /**
     * Calculates the diameter of the subtree rooted at the given node.
     * Time Complexity: O(n²) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     * @return the diameter of the subtree
     */
    private int getDiameter(Node curr){
        if (curr == null)
            return 0;

        int currDia = getHeight(curr.lhs) + getHeight(curr.rhs) + 2; //the 2 for left and right edges connected the children

        return Math.max(currDia, Math.max(getDiameter(curr.lhs) , getDiameter(curr.rhs)));
    }

    /**
     * Prints the right boundary of the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @param curr the root of the subtree
     */
    private void printRightBoundary(Node curr){
        if (isLeaf(curr)) return;

        System.out.print(curr.data + " ");

        if (curr.rhs != null)
            printRightBoundary(curr.rhs);
        else if (curr.lhs != null)
            printRightBoundary(curr.lhs);
    }

    /**
     * Prints the left boundary of the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @param curr the root of the subtree
     */
    private void printLeftBoundary(Node curr){
        if (isLeaf(curr)) return;

        System.out.print(curr.data + " ");

        if (curr.lhs != null)
            printLeftBoundary(curr.lhs);
        else if (curr.rhs != null)
            printLeftBoundary(curr.rhs);
    }

    /**
     * Recursively clears all children of the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the node whose children should be cleared
     */
    private void clearChildren(Node curr){
        if (curr == null)
            return;

        clearChildren(curr.lhs);
        clearChildren(curr.rhs);

        curr.lhs = null;
        curr.rhs = null;
    }

    /**
     * Checks if the subtree rooted at the given node is a perfect binary tree.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     * @param h the expected height of the subtree, or -1 to calculate it
     * @return true if the subtree is perfect, false otherwise
     */
    private boolean isPerfect(Node curr, int h){
//         return countNodes(root) == (Math.pow(2, getHeight(root) + 1) - 1); // if perfect then no. of nodes = 2^(h+1) - 1
        if(h == -1)
            h = getHeight(curr);

        if (isLeaf(curr)) // leaf
            return h == 0;

        if (curr.lhs == null || curr.rhs == null) // have one child
            return false;

        return isPerfect(curr.lhs, h - 1) && isPerfect(curr.rhs, h - 1);
    }

    /**
     * Checks if the subtree rooted at the given node contains the specified value.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     * @param value the value to search for
     * @return true if the subtree contains the value, false otherwise
     */
    private boolean contains(Node curr, int value){
        if (curr == null)
            return false;

        if (curr.data == value)
            return true;

        return contains(curr.lhs, value) || contains(curr.rhs, value);
    }

    /**
     * Counts the number of leaf nodes in the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     * @return the number of leaf nodes in the subtree
     */
    private int countLeaves(Node curr){
        if (isLeaf(curr))
            return 1;

        int result = 0;

        if (curr.lhs != null)
            result += countLeaves(curr.lhs);

        if(curr.rhs != null)
            result += countLeaves(curr.rhs);

        return result;
    }

    /**
     * Counts the total number of nodes in the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     * @return the number of nodes in the subtree
     */
    private int countNodes(Node curr){
        if (curr == null)
            return 0;

        return 1 + countNodes(curr.lhs) + countNodes(curr.rhs);
    }

    /**
     * Calculates the height of the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     * @return the height of the subtree, or -1 if the subtree is empty
     */
    private int getHeight(Node curr){
        if (curr == null)
            return -1;

        return 1 + Math.max(getHeight(curr.lhs),getHeight(curr.rhs));
    }

    /**
     * Finds the minimum value in the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     * @return the minimum value in the subtree, or Integer.MAX_VALUE if the subtree is empty
     */
    private int getMin(Node curr){
        if(curr == null)
            return Integer.MAX_VALUE;
        return Math.min(Math.min(getMin(curr.rhs), getMin(curr.lhs)), curr.data);
    }

    /**
     * Finds the maximum value in the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     * @return the maximum value in the subtree, or Integer.MIN_VALUE if the subtree is empty
     */
    private int getMax(Node curr){
        if(curr == null)
            return Integer.MIN_VALUE;
        return Math.max(Math.max(getMax(curr.rhs), getMax(curr.lhs)), curr.data);
    }

    /**
     * Prints the nodes of the subtree rooted at the given node in post-order traversal.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     */
    private void printPostOrder(Node curr){ //LRV -> Depth-First
        if (curr == null)
            return;

        printPostOrder(curr.lhs);
        printPostOrder(curr.rhs);
        System.out.print(curr.data + " ");
    }

//    /**
//     * Prints the nodes of the subtree rooted at the given node as characters in post-order traversal.
//     *
//     * @param curr the root of the subtree
//     */
//    private void printPostOrderExpression(Node curr){
//        if (curr == null)
//            return;
//
//        printPostOrderExpression(curr.lhs);
//        printPostOrderExpression(curr.rhs);
//        System.out.print((char)curr.data + " ");
//    }

    /**
     * Prints the nodes of the subtree rooted at the given node in pre-order traversal.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     */
    private void printPreOrder(Node curr){ //VLR -> Depth-First
        if (curr == null)
            return;
        System.out.print(curr.data + " ");
        printPreOrder(curr.lhs);
        printPreOrder(curr.rhs);
    }

    /**
     * Prints the nodes of the subtree rooted at the given node in pre-order traversal fill null.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     */
    private void printPreOrderComplete(Node curr){
        System.out.print(curr.data + " ");

        if (curr.lhs != null)
            printPreOrderComplete(curr.lhs);
        else
            System.out.print("null ");

        if (curr.rhs != null)
            printPreOrderComplete(curr.rhs);
        else
            System.out.print("null ");
    }

    /**
     * Prints the nodes of the subtree rooted at the given node in in-order traversal.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curr the root of the subtree
     */
    private void printInOrder(Node curr){ //LVR -> Depth-First
        if (curr == null)
            return;
        printInOrder(curr.lhs);
        System.out.print(curr.data + " ");
        printInOrder(curr.rhs);

/*  Printing Inorder Iteratively
        if (curr == null)
            return;

        Stack<Node> stack = new Stack<>();
        while (true){
            if (curr != null){
                stack.push(curr);
                curr = curr.lhs;
            }else{
                if (stack.isEmpty()) break;

                curr = stack.pop();
                System.out.print(curr.data + " ");
                curr = curr.rhs;
            }
        }
    */
    }

    private void printDuplicateSubtrees(Node curr){
//        if (curr == null) return;
//
//        String leftCanonical = toCanonicalParenthesisString(curr.lhs);
//        String rightCanonical = toCanonicalParenthesisString(curr.rhs);
//
//        if(leftCanonical.equals(rightCanonical)){
//            StringBuilder sb = new StringBuilder();
//            toParenthesizedString(curr.lhs, sb);
//            System.out.println(sb.toString());
//        }
    }

    private void toParenthesizedString(Node curr, StringBuilder str){
        if (curr == null) return;

        str.append(curr.data);

        if (curr.lhs != null || curr.rhs != null) {
            str.append("(");
            toParenthesizedString(curr.lhs, str);
            str.append(")");
        }

        if (curr.rhs != null) {
            str.append("(");
            toParenthesizedString(curr.rhs, str);
            str.append(")");
        }
        // The assignment but not logical to print empty parentheses
//        str.append(curr.data);
//        str.append("(");
//        toParenthesizedString(curr.lhs, str);
//        str.append(")");
//        str.append("(");
//        toParenthesizedString(curr.rhs, str);
//        str.append(")");

    }

    private String toCanonicalParenthesisString(Node curr){
        if (curr == null) {
            return "()";
        }

        String leftStr = toCanonicalParenthesisString(curr.lhs);
        String rightStr = toCanonicalParenthesisString(curr.rhs);

        String[] subTrees= {leftStr, rightStr};
        Arrays.sort(subTrees);

        return "(" + curr.data + subTrees[0] + subTrees[1] + ")";
    }
    /**
     * Checks if the given node is a leaf node (has no children).
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param curr the node to check
     * @return true if the node is a leaf, false otherwise
     */
    private boolean isLeaf(Node curr){
        return curr.lhs == null && curr.rhs == null;
    }

    private boolean isMirror(Node firstTree, Node secondTree){
        if (firstTree == null && secondTree == null)
            return true;

        if (firstTree == null || secondTree == null || firstTree.data != secondTree.data)
            return false;

        return isMirror(firstTree.lhs, secondTree.rhs) && isMirror(firstTree.lhs, secondTree.rhs);
    }

    private boolean isFlipEquivalent(Node firstTree, Node secondTree){
        if (firstTree == null && secondTree == null)
            return true;

        if (firstTree == null || secondTree == null || firstTree.data != secondTree.data)
            return false;

        return (isFlipEquivalent(firstTree.lhs, secondTree.lhs) || isFlipEquivalent(firstTree.lhs, secondTree.rhs) )
                && (isFlipEquivalent(firstTree.rhs, secondTree.rhs) || isFlipEquivalent(firstTree.rhs, secondTree.lhs));
    }

    /**
     * A node in the binary tree.
     * Each node contains an integer value and references to its left and right children.
     */
    private static class Node {
        /** The value stored in this node */
        int data;
        /** Reference to the left child */
        Node lhs;
        /** Reference to the right child */
        Node rhs;

        /**
         * Constructs a node with the specified value.
         *
         * @param data the value to be stored in the node
         */
        public Node(int data) {
            this.data = data;
        }
    }
}
