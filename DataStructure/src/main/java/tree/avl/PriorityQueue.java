package tree.avl;

import java.util.ArrayList;


/*
 - AVL Tree Priority Queue, Enqueue O(log n), Dequeue	O(log n), Remove O(log n), Update Priority O(log n)
 - Efficient priority updates, supports duplicate priorities
 - More complex implementation (balancing rotations), Peek operation is O(log n) to find max or min
 */

public class PriorityQueue {
    private Node root;

    public void enqueue(int taskId,int priority) {
        if (root == null){
            root = new Node(priority);
            root.addTask(taskId);
        } else {
            root = insert(root, priority, taskId);
        }
    }

    public int dequeue() {
        if (root == null) {
            System.out.println("Queue is empty");
            return -1;
        }

        Node max = getMax(root);
        int res = max.removeTask();

        if (max.taskIdsList.isEmpty()) {
            root = removeNode(root, max.priority);
        }

        return res;
    }

    public void remove(int priority) {
        if (root != null)
            root = removeNode(root, priority);
    }

    ///////////////////////////////////////////////////////////////////////
    private Node removeNode(Node curNode, int priority) {
        if (curNode == null) //Node not found
            return null;

        if (curNode.priority < priority) {
            curNode.right = removeNode(curNode.right, priority);
        } else if (curNode.priority > priority) {
            curNode.left = removeNode(curNode.left, priority);
        } else { //Node found

            // case have one child or leaf (return null in case of leaf)
            if (curNode.right == null)
                return curNode.left;

            if (curNode.left == null)
                return curNode.right;

            //case have two children (replace the node with its successor, then remove that successor node)
            Node successorNode = getMin(curNode.right);
            curNode.priority = successorNode.priority;
            curNode.taskIdsList = successorNode.taskIdsList;
            curNode.right = removeNode(curNode.right, successorNode.priority);
        }

        updateHeight(curNode);
        return balance(curNode);
    }

    private Node insert(Node curNode, int priority, int taskId) {
        if (priority > curNode.priority) {
            if (curNode.right == null) {
                curNode.right = new Node(priority);
                curNode.right.addTask(taskId);
            }else
                curNode.right = insert(curNode.right, priority,taskId); //due to balancing it my return a complete different node.

        } else if (priority < curNode.priority) {
            if (curNode.left == null) {
                curNode.left = new Node(priority);
                curNode.left.addTask(taskId);
            }else {
                curNode.left = insert(curNode.left, priority, taskId);
            }
        }else {
            curNode.addTask(taskId);
        }

        updateHeight(curNode);
        return balance(curNode);
    }

    private Node balance(Node curNode) {
        int bf = balanceFactor(curNode); //We don't expect any bf out of [-2, 2] as the whole tree follows CHANGE-FIX approach.

        if (bf == 2) { //LL
            if (balanceFactor(curNode.left) == -1) //LR
                curNode.left = leftRotate(curNode.left); // LR -> LL

            curNode = rightRotate(curNode);
        } else if (bf == -2) { //RR
            if (balanceFactor(curNode.right) == 1) //RL
                curNode.right = rightRotate(curNode.right); // RL -> RR

            curNode = leftRotate(curNode);
        }

        return curNode;
    }

    private int balanceFactor(Node curNode) {
        return height(curNode.left) - height(curNode.right);
    }

    private void updateHeight(Node curNode) {
        curNode.height = 1 + Math.max(height(curNode.left), height(curNode.right));
    }

    private int height(Node curNode) {
        return (curNode == null) ? -1 : curNode.height; // -1 for null, 0 for leaf
    }

    private Node leftRotate(Node curNode) {
        Node newRoot = curNode.right;
        curNode.right = newRoot.left;
        newRoot.left = curNode;
        updateHeight(curNode);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node rightRotate(Node curNode) {
        Node newRoot = curNode.left;
        curNode.left = newRoot.right;
        newRoot.right = curNode;
        updateHeight(curNode);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node getMin(Node curNode) {
        while (curNode.left != null)
            curNode = curNode.left;

        return curNode;
    }

    private Node getMax(Node curNode) {
        while (curNode.right != null)
            curNode = curNode.right;

        return curNode;
    }


    private static class Node {
        /**
         * The value stored in this node
         */
        public int priority;
        /**
         * The height of this node
         */
        public int height;
        /**
         * The ID of tasks
         */
        public ArrayList<Integer> taskIdsList;
        /**
         * Reference to the left child
         */
        public Node left;
        /**
         * Reference to the right child
         */
        public Node right;

        /**
         * Constructs a node with the specified value.
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         *
         * @param priority the value to be stored in the node
         */
        public Node(int priority) {
            this.priority = priority;
            this.taskIdsList = new ArrayList<>(10);
            this.height = 0;
        }

        public Node(int priority, int taskId) {
            this.priority = priority;
            this.taskIdsList = new ArrayList<>(10);
            taskIdsList.addLast(taskId);
            this.height = 0;
        }

        public void addTask(int taskId){
            taskIdsList.addLast(taskId);
        }

        public int removeTask(){
            return taskIdsList.isEmpty() ? -1 : taskIdsList.removeFirst();
        }
    }
}