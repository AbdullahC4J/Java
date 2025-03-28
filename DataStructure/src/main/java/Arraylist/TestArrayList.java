package arraylist;

public class TestArrayList {
    public static void main(String[] args) {
        MyArrayList<Integer> myObj = new MyArrayList<>(5);
        System.out.println(myObj.getSize());
        System.out.println(myObj);
        myObj.add(4,3);
        System.out.println(myObj.getSize());
        System.out.println(myObj);
    }
}
