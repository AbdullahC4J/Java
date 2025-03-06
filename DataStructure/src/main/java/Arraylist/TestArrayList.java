package arraylist;

public class TestArrayList {
    public static void main(String[] args) {
        MyArrayList<String> myObj1 = new MyArrayList<>(5);
        MyArrayList<Integer> myObj2 = new MyArrayList<>(5);
        myObj1.add("A");
        myObj1.add("W");
        myObj1.add("R");
        myObj1.add("5");
        System.out.println(myObj1);
        myObj1.rightRotate(2);
        System.out.println(myObj1);
        myObj1.leftRotate(2);
        System.out.println(myObj1);
    }
}
