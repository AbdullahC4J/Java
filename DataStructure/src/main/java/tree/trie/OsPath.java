package tree.trie;

import java.util.HashMap;

public class OsPath {
    
    Node root;

    public OsPath() {
        root = new Node();
    }

    public void insert(String[] path) {
        Node curNode = root;

        for (String dir : path) {
            if (!curNode.child.containsKey(dir))
                curNode.child.put(dir, new Node());

            curNode = curNode.child.get(dir);
        }

        curNode.isEndOfPath = true;
    }

    public boolean isSubPathExist(String[] subPath){
        Node curNode = root;

        for (String dir : subPath) {
            if (!curNode.child.containsKey(dir))
                return false;

            curNode = curNode.child.get(dir);
        }

        return true;
    }

    private static class Node {
        HashMap<String, Node> child;

        boolean isEndOfPath;

        public Node() {
            child   = new HashMap<>();
            isEndOfPath = false;
        }
    }
}
