package linkedlist.singlelinkedlist;

import java.util.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> ob = new LinkedList<>();
        SingleLinkedList<String> ob1 = new SingleLinkedList<>();
        SingleLinkedListNoTail ob2 = new SingleLinkedListNoTail();

        ob1.addLast("ABDULLAH");
        ob1.addLast("ALI");
        ob1.addLast("NOAH");
        ob1.addLast("AHMED");
        ob1.addLast("HADI");
        ob1.addLast("TAREK");
        System.out.println(ob1);
        ob1.reverse();
        System.out.println(ob1);

        System.out.println();
        ob1.debugPrintAll();
    }
}
