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
//        Node z = new Node(preOrder.remove());
//
//        Deque<Integer> left = new LinkedList<>();
//        Deque<Integer> right = new LinkedList<>();
//
//        while (inOrder.peek() != curNode.data)
//            left.add(inOrder.remove());
//
//        inOrder.remove();
//
//        while (!inOrder.isEmpty())
//            right.add(inOrder.remove());
//
//        curNode.lhs = buildTree(preOrder, left);
//        curNode.rhs = buildTree(preOrder,right);
//
//        return curNode;
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
//            Node curNode = new Node(postFix.charAt(i));
//
//            if (curNode.data < '0' || curNode.data > '9') {
//                curNode.rhs = stack.pop();
//                curNode.lhs = stack.pop();
//            }
//
//            stack.push(curNode);
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

        Node curNode = root;
        for (int i = 0; i < data.length; ++i){
            if (directions[i] == 'L'){
                if (curNode.lhs == null)
                    curNode.lhs = new Node(data[i]);
                else if (curNode.lhs.data != data[i])
                    throw new AssertionError("Incorrect left value in path");

                curNode = curNode.lhs;

            }else if (directions[i] == 'R'){
                if (curNode.rhs == null)
                    curNode.rhs = new Node(data[i]);
                else if (curNode.rhs.data != data[i])
                    throw new AssertionError("Incorrect right value in path");

                curNode = curNode.rhs;

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

    /**
     * Prints the tree nodes in pre-order traversal with null nodes explicitly shown.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     */
    public void printPreOrderComplete(){
        printPreOrderComplete(root);
        System.out.println();
    }

    /**
     * Prints the nodes of the subtree rooted at the given node in level-order traversal.
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(w) where w is the maximum width of the tree
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

                if (curNode.lhs != null)
                    queue.add(curNode.lhs);

                if (curNode.rhs != null)
                    queue.add(curNode.rhs);

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
                Node curNode;
                if (fwdLvl){
                    curNode = deque.removeFirst();
                    System.out.print(curNode.data + " ");

                    if (curNode.lhs != null)
                        deque.addLast(curNode.lhs);// left added first here

                    if (curNode.rhs != null)
                        deque.addLast(curNode.rhs);

                }else {
                    curNode = deque.removeLast();
                    System.out.print(curNode.data + " ");

                    if (curNode.rhs != null)
                        deque.addFirst(curNode.rhs);// right added first here

                    if (curNode.lhs != null)
                        deque.addFirst(curNode.lhs);
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
                Node curNode = queue.remove();

                if (curNode.lhs != null){
                    if (isLeafLevel)
                        return false;

                    queue.add(curNode.lhs);
                }else {
                    isLeafLevel = true;
                }

                if (curNode.rhs != null){
                    if (isLeafLevel)
                        return false;

                    queue.add(curNode.rhs);
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
        if (root == null)
            return false;

        return isMirror(root.lhs, root.rhs);
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
     * @param curNode the root of the subtree
     * @return the diameter of the subtree
     */
    private int getDiameter(Node curNode){
        if (curNode == null)
            return 0;

        int curDia = getHeight(curNode.lhs) + getHeight(curNode.rhs) + 2; //the 2 for left and right edges connected the children

        return Math.max(curDia, Math.max(getDiameter(curNode.lhs) , getDiameter(curNode.rhs)));
    }

    /**
     * Prints the right boundary of the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     */
    private void printRightBoundary(Node curNode){
        if (isLeaf(curNode)) return;

        System.out.print(curNode.data + " ");

        if (curNode.rhs != null)
            printRightBoundary(curNode.rhs);
        else if (curNode.lhs != null)
            printRightBoundary(curNode.lhs);
    }

    /**
     * Prints the left boundary of the subtree rooted at the given node.
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     */
    private void printLeftBoundary(Node curNode){
        if (isLeaf(curNode)) return;

        System.out.print(curNode.data + " ");

        if (curNode.lhs != null)
            printLeftBoundary(curNode.lhs);
        else if (curNode.rhs != null)
            printLeftBoundary(curNode.rhs);
    }

    /**
     * Recursively clears all children of the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the node whose children should be cleared
     */
    private void clearChildren(Node curNode){
        if (curNode == null)
            return;

        clearChildren(curNode.lhs);
        clearChildren(curNode.rhs);

        curNode.lhs = null;
        curNode.rhs = null;
    }

    /**
     * Checks if the subtree rooted at the given node is a perfect binary tree.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     * @param h the expected height of the subtree, or -1 to calculate it
     * @return true if the subtree is perfect, false otherwise
     */
    private boolean isPerfect(Node curNode, int h){
//         return countNodes(root) == (Math.pow(2, getHeight(root) + 1) - 1); // if perfect then no. of nodes = 2^(h+1) - 1
        if(h == -1)
            h = getHeight(curNode);

        if (isLeaf(curNode)) // leaf
            return h == 0;

        if (curNode.lhs == null || curNode.rhs == null) // have one child
            return false;

        return isPerfect(curNode.lhs, h - 1) && isPerfect(curNode.rhs, h - 1);
    }

    /**
     * Checks if two subtrees are flip equivalent.
     * Two binary trees are flip equivalent if they can be made identical by flipping some of their nodes.
     * Time Complexity: O(min(n1,n2)²) where n1 and n2 are the number of nodes in the subtrees
     * Space Complexity: O(min(h1,h2)) where h1 and h2 are the heights of the subtrees (due to recursion stack)
     *
     * @param curNode the root of the subtree
     * @param value the value to search for
     * @return true if the subtree contains the value, false otherwise
     */
    private boolean contains(Node curNode, int value){
        if (curNode == null)
            return false;

        if (curNode.data == value)
            return true;

        return contains(curNode.lhs, value) || contains(curNode.rhs, value);
    }

    /**
     * Counts the number of leaf nodes in the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     * @return the number of leaf nodes in the subtree
     */
    private int countLeaves(Node curNode){
        if (isLeaf(curNode))
            return 1;

        int result = 0;

        if (curNode.lhs != null)
            result += countLeaves(curNode.lhs);

        if(curNode.rhs != null)
            result += countLeaves(curNode.rhs);

        return result;
    }

    /**
     * Counts the total number of nodes in the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     * @return the number of nodes in the subtree
     */
    private int countNodes(Node curNode){
        if (curNode == null)
            return 0;

        return 1 + countNodes(curNode.lhs) + countNodes(curNode.rhs);
    }

    /**
     * Calculates the height of the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     * @return the height of the subtree, or -1 if the subtree is empty
     */
    private int getHeight(Node curNode){
        if (curNode == null)
            return -1;

        return 1 + Math.max(getHeight(curNode.lhs),getHeight(curNode.rhs));
    }

    /**
     * Finds the minimum value in the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     * @return the minimum value in the subtree, or Integer.MAX_VALUE if the subtree is empty
     */
    private int getMin(Node curNode){
        if(curNode == null)
            return Integer.MAX_VALUE;
        return Math.min(Math.min(getMin(curNode.rhs), getMin(curNode.lhs)), curNode.data);
    }

    /**
     * Finds the maximum value in the subtree rooted at the given node.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     * @return the maximum value in the subtree, or Integer.MIN_VALUE if the subtree is empty
     */
    private int getMax(Node curNode){
        if(curNode == null)
            return Integer.MIN_VALUE;
        return Math.max(Math.max(getMax(curNode.rhs), getMax(curNode.lhs)), curNode.data);
    }

    /**
     * Prints the nodes of the subtree rooted at the given node in post-order traversal.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     */
    private void printPostOrder(Node curNode){ //LRV -> Depth-First
        if (curNode == null)
            return;

        printPostOrder(curNode.lhs);
        printPostOrder(curNode.rhs);
        System.out.print(curNode.data + " ");
    }

//    /**
//     * Prints the nodes of the subtree rooted at the given node as characters in post-order traversal.
//     *
//     * @param curNode the root of the subtree
//     */
//    private void printPostOrderExpression(Node curNode){
//        if (curNode == null)
//            return;
//
//        printPostOrderExpression(curNode.lhs);
//        printPostOrderExpression(curNode.rhs);
//        System.out.print((char)curNode.data + " ");
//    }

    /**
     * Prints the nodes of the subtree rooted at the given node in pre-order traversal.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     */
    private void printPreOrder(Node curNode){ //VLR -> Depth-First
        if (curNode == null)
            return;
        System.out.print(curNode.data + " ");
        printPreOrder(curNode.lhs);
        printPreOrder(curNode.rhs);
    }

    /**
     * Prints the nodes of the subtree rooted at the given node in pre-order traversal fill null.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     */
    private void printPreOrderComplete(Node curNode){
        System.out.print(curNode.data + " ");

        if (curNode.lhs != null)
            printPreOrderComplete(curNode.lhs);
        else
            System.out.print("null ");

        if (curNode.rhs != null)
            printPreOrderComplete(curNode.rhs);
        else
            System.out.print("null ");
    }

    /**
     * Prints the nodes of the subtree rooted at the given node in in-order traversal.
     * Time Complexity: O(n) where n is the number of nodes in the subtree
     * Space Complexity: O(h) where h is the height of the subtree (due to recursion stack)
     *
     * @param curNode the root of the subtree
     */
    private void printInOrder(Node curNode){ //LVR -> Depth-First
        if (curNode == null)
            return;
        printInOrder(curNode.lhs);
        System.out.print(curNode.data + " ");
        printInOrder(curNode.rhs);

/*  Printing Inorder Iteratively
        if (curNode == null)
            return;

        Stack<Node> stack = new Stack<>();
        while (true){
            if (curNode != null){
                stack.push(curNode);
                curNode = curNode.lhs;
            }else{
                if (stack.isEmpty()) break;

                curNode = stack.pop();
                System.out.print(curNode.data + " ");
                curNode = curNode.rhs;
            }
        }
    */
    }

    private void printDuplicateSubtrees(Node curNode){
//        if (curNode == null) return;
//
//        String leftCanonical = toCanonicalParenthesisString(curNode.lhs);
//        String rightCanonical = toCanonicalParenthesisString(curNode.rhs);
//
//        if(leftCanonical.equals(rightCanonical)){
//            StringBuilder sb = new StringBuilder();
//            toParenthesizedString(curNode.lhs, sb);
//            System.out.println(sb.toString());
//        }
    }

    private void toParenthesizedString(Node curNode, StringBuilder str){
        if (curNode == null) return;

        str.append(curNode.data);

        if (curNode.lhs != null || curNode.rhs != null) {
            str.append("(");
            toParenthesizedString(curNode.lhs, str);
            str.append(")");
        }

        if (curNode.rhs != null) {
            str.append("(");
            toParenthesizedString(curNode.rhs, str);
            str.append(")");
        }
        // The assignment but not logical to print empty parentheses
//        str.append(curNode.data);
//        str.append("(");
//        toParenthesizedString(curNode.lhs, str);
//        str.append(")");
//        str.append("(");
//        toParenthesizedString(curNode.rhs, str);
//        str.append(")");

    }

    private String toCanonicalParenthesisString(Node curNode){
        if (curNode == null) {
            return "()";
        }

        String leftStr = toCanonicalParenthesisString(curNode.lhs);
        String rightStr = toCanonicalParenthesisString(curNode.rhs);

        String[] subTrees= {leftStr, rightStr};
        Arrays.sort(subTrees);

        return "(" + curNode.data + subTrees[0] + subTrees[1] + ")";
    }
    /**
     * Checks if the given node is a leaf node (has no children).
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param curNode the node to check
     * @return true if the node is a leaf, false otherwise
     */
    private boolean isLeaf(Node curNode){
        return curNode.lhs == null && curNode.rhs == null;
    }

    private boolean isMirror(Node firstTree, Node secondTree){
        if (firstTree == null && secondTree == null)
            return true;

        if (firstTree == null || secondTree == null || firstTree.data != secondTree.data)
            return false;

        return isMirror(firstTree.lhs, secondTree.rhs) && isMirror(firstTree.rhs, secondTree.lhs);
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
