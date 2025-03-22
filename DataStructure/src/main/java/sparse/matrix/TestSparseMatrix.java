package sparse.matrix;

public class TestSparseMatrix {
    public static void main(String[] args) {
        SparseMatrix matrix = new SparseMatrix(3,3);
        matrix.setValue(1,0,0);
        matrix.setValue(3,0,2);
        matrix.setValue(5,1,1);
        matrix.setValue(7,2,0);
        matrix.setValue(9,2,2);

        matrix.printMatrix();
        System.out.println();
        matrix.printMatrixNonZero();
    }
}
