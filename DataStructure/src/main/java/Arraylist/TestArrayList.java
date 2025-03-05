package Arraylist;

public class TestArrayList {
    public static void main(String[] args) {
        MyArrayList<String> myObj = new MyArrayList<>(5);
        myObj.add("A");
        myObj.add("W");
        myObj.add("R");
        myObj.add("5");
        myObj.rightRotate(2);
        System.out.println(myObj);
        myObj.leftRotate(2);
        System.out.println(myObj);
    }
}
