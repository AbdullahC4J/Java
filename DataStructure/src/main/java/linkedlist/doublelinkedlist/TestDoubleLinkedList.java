package linkedlist.doublelinkedlist;

public class TestDoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> obj = new DoubleLinkedList<>();

        obj.addLast(1);
        obj.addLast(2);
        obj.addLast(3);
        obj.addLast(4);
        obj.addLast(5);
        obj.addLast(6);
        obj.addLast(7);
        obj.addLast(8);
        System.out.println(obj);
        obj.swapHeadTail();
        System.out.println(obj);
        System.out.println(obj.getMiddle());

        System.out.println();
        obj.debugPrintAll();
    }
}
