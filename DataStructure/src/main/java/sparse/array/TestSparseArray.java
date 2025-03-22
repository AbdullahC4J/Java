package sparse.array;

public class TestSparseArray {
    public static void main(String[] args) {
        SparseArray list = new SparseArray(10);
        SparseArray list1 = new SparseArray(10);

        list.setValue(0,0);
        list.setValue(1,1);
        list.setValue(4,4);
        list.setValue(6,6);
        list.setValue(7,7);

        list1.setValue(2,2);
        list1.setValue(3,3);
        list1.setValue(5,5);

        list.addTwoLists(list1);
        list.printArr();

        list.printArrNonZero();
        list1.printArrNonZero();


    }
}
