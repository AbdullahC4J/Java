package MyArrayList;

import java.util.ArrayList;

public class TestArrayList {
    public static void main(String[] args) {
        MyArrayList myObj = new MyArrayList(5);
        ArrayList<Integer> obj = new ArrayList<>(5);
        myObj.add(1);
        myObj.add(2);
        myObj.add(3);
        myObj.add(4);
        myObj.rightRotate(2);
        System.out.println(myObj);
        myObj.leftRotate(2);
        System.out.println(myObj);
    }
}
