package tree.trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String key) {
        Node curNode = root;

        for (char c : key.toCharArray()) {

            if (!curNode.child.containsKey(c))
                curNode.child.put(c, new Node());

            curNode = curNode.child.get(c);
        }

        curNode.isEndOfWord = true;
    }

    public boolean isWordExist(String word) {
        Node curNode = root;

        for (int i = 0; i < word.length(); ++i) {
            char curChar = word.charAt(i);

            if (!curNode.child.containsKey(curChar))
                return false;

            if (i == word.length() - 1)
                return curNode.child.get(curChar).isEndOfWord; // a word can be a prefix of another word

            curNode = curNode.child.get(curChar);
        }

        return false;
    }

    public boolean isPrefixExist(String prefix) {
        Node curNode = root;

        for (int i = 0; i < prefix.length(); ++i) {
            char curChar = prefix.charAt(i);

            if (!curNode.child.containsKey(curChar))
                return false;

            curNode = curNode.child.get(curChar);
        }

        return true;
    }

    public String minimalPrefix(String word) {
        Node curChild = root;

        for (int i = 0; i < word.length(); ++i) {
            char curChar = word.charAt(i);

            if (!curChild.child.containsKey(curChar))
                break;

            if (curChild.child.get(curChar).isEndOfWord)
                return word.substring(0, i + 1);

            curChild = curChild.child.get(curChar);
        }

        return word;
    }

/* Assuming the trie is storing reversed words*/
//    public boolean isSuffixExist(String suffix) {
//        Node curNode = root;
//
//        for (int i = suffix.length() - 1; i >= 0; --i) {
//            char curChar = suffix.charAt(i);
//
//            if (!curNode.child.containsKey(curChar))
//                return false;
//
//            curNode = curNode.child.get(curChar);
//        }
//
//        return true;
//    }

    public void autoComplete(String prefix, List<String> listOfWords){
        Node curNode = root;

        for (char c : prefix.toCharArray()) {
            if ((curNode = curNode.child.get(c)) == null )
                return;
        }

        getAll(curNode, new StringBuilder(prefix), listOfWords);
    }

    public void getAll(List<String> listOfWords){
        getAll(root, new StringBuilder(), listOfWords);
    }

    private void getAll(Node curNode, StringBuilder str ,List<String> listOfWords){
        if (curNode.isEndOfWord)
            listOfWords.add(str.toString());

        for (Map.Entry<Character, Node> entry : curNode.child.entrySet()) {
            str.append(entry.getKey());

            getAll(entry.getValue(), str, listOfWords);

            str.deleteCharAt(str.length() - 1);  // backtrace
        }
    }


    private static class Node {
        HashMap<Character, Node> child;

        boolean isEndOfWord;

        public Node() {
            child = new HashMap<>();
            isEndOfWord = false;
        }
    }
}
