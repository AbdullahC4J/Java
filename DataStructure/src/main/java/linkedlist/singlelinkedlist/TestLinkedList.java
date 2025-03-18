package linkedlist.singlelinkedlist;

public class TestLinkedList {
    public static void main(String[] args) {
        SingleLinkedList<Integer> ob = new SingleLinkedList<>();
        SingleLinkedList<Integer> ob1 = new SingleLinkedList<>();

        ob.addLast(1);
        ob.addLast(2);
        ob.addLast(3);
        ob.addLast(4);
        ob.addLast(5);

        ob1.addLast(5);
        ob1.addLast(4);
        ob1.addLast(3);
        ob1.addLast(2);
        ob1.addLast(1);

        System.out.println(ob);
        System.out.println(ob1);
        System.out.println(ob);

        System.out.println();
        ob.debugPrintAll();
    }
}
