package map;

public class TreeMap <K extends Comparable<K>, V> {
    Node root;

    public TreeMap(){
        this.root = null;
    }

    public void put(K key, V value){
        this.root = insert(root, key,value);
    }

    public void remove(K key){
        this.root = remove(root, key);
    }

    public V get(K key){
        Node curNode = root;

        while (curNode != null){
            int cmp = key.compareTo(curNode.key);

            if (cmp < 0) curNode = curNode.left;
            else if (cmp > 0) curNode = curNode.right;
            else return curNode.val;
        }

        return null;
    }

    public boolean containsKey(K key){
        return get(key) != null;
    }

    private Node insert(Node curNode,K key, V value) {
        if (curNode == null) return new Node(key, value);

        if (key.compareTo(curNode.key) < 0) {
            curNode.left = insert(curNode.left, key, value);
        } else if (key.compareTo(curNode.key) > 0) {
            curNode.right = insert(curNode.right, key, value);
        } else {
            curNode.val = value; // update value if key exists
        }

        updateHeight(curNode);
        return balance(curNode);
    }

    private Node remove(Node curNode,K key){
        if (curNode == null) //Node not found
            return null;

        int cmp = key.compareTo(curNode.key);

        if (cmp > 0){
            curNode.right = remove(curNode.right, key);
        }else if (cmp < 0){
            curNode.left = remove(curNode.left, key);
        } else { //Node found

            // case have one child or leaf (return null in case of leaf)
            if (curNode.right == null)
                return curNode.left;

            if (curNode.left == null)
                return curNode.right;

            //case have two children (replace the node with its successor, then remove that successor node)
            Node successorNode = getMin(curNode.right);
            curNode.key = successorNode.key;
            curNode.val = successorNode.val;
            curNode.right = remove(curNode.right, successorNode.key);
        }

        // Recursively traverse back up the insertion path to update heights and re-balance.
        updateHeight(curNode);
        return balance(curNode);
    }

    private Node getMin(Node curNode){
        while (curNode.left != null)
            curNode = curNode.left;

        return curNode;
    }

    private void inOrder(Node curNode) {
        if (curNode == null) return;
        inOrder(curNode.left);
        System.out.print(curNode.key + " ");
        inOrder(curNode.right);
    }

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

    private int balanceFactor(Node curNode){
        return height(curNode.left) - height(curNode.right);
    }

    private void updateHeight(Node curNode){
        curNode.height = 1 + Math.max(height(curNode.left), height(curNode.right));
    }

    private int height(Node curNode){
        return (curNode == null) ? -1 : curNode.height; // -1 for null, 0 for leaf
    }

    private Node leftRotate(Node curNode){
        Node newRoot = curNode.right;
        curNode.right = newRoot.left;
        newRoot.left = curNode;
        updateHeight(curNode);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node rightRotate(Node curNode){
        Node newRoot = curNode.left;
        curNode.left = newRoot.right;
        newRoot.right = curNode;
        updateHeight(curNode);
        updateHeight(newRoot);
        return newRoot;
    }

    private class Node{
        public K key;
        public V val;
        public int height;
        public Node left;
        public Node right;


        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
    }

}
