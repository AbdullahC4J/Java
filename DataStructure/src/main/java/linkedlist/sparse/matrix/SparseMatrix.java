package linkedlist.sparse.matrix;

import linkedlist.sparse.array.SparseArray;
import java.util.Objects;

/**
 * A memory-efficient implementation of a sparse matrix using a linked list structure.
 * <p>
 * This class represents a sparse matrix, which is a matrix where most of the elements
 * have the same value (typically zero). Instead of storing all elements, this implementation
 * only stores non-zero elements using a linked list of nodes, where each node contains
 * a linked list of sparse array.
 * </p>
 * <p>
 * Time complexity for operations:
 * - Access: O(n) where n is the number of non-zero elements
 * - Insertion: O(n)
 * - Deletion: Not supported directly
 * </p>
 * <p>
 * Space complexity: O(k) where k is the number of non-zero elements
 * </p>
 */
public class SparseMatrix {

    // Pointer to first node.
    private RowNode first;
    // Pointer to last node.
    private RowNode last;
    // The number of lists currently in the matrix
    private int size;
    // The number of rows
    private int rows;
    // The number of columns
    private int cols;

    /**
     * Constructs a new sparse matrix with the specified rows and columns.
     *
     * @param rows The number of the rows in the matrix
     * @param cols The number of the columns in the matrix
     */
    public SparseMatrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        first = last = new RowNode(-1, cols);
    }

    /**
     * Sets the value at the specified index in the sparse matrix.
     * If the index doesn't exist, a new node is created.
     *
     * @param data The value to be stored
     * @param row The row at which to store the value
     * @param col The column at which to store the value
     * @throws NullPointerException If the index node cannot be created
     */
    public void setValue(int data, int row, int col){
        if (0 > row || rows < row)
            throw new IllegalArgumentException("Invalid Row Number");

        if (0 > col || cols < col)
            throw new IllegalArgumentException("Invalid Column Number");

        Objects.requireNonNull(getRow(row, true)).arrayList.setValue(data,col);
    }

    /**
     * Gets the value at the specified index in the sparse matrix.
     *
     * @param row The row from which to retrieve the value
     * @param col The column from which to retrieve the value
     * @return The value at the specified index, or 0 if the index doesn't exist
     */
    public int getValue(int row,int col){
        if (0 > row || rows < row)
            throw new IllegalArgumentException("Invalid Row Number");

        if (0 > col || cols < col)
            throw new IllegalArgumentException("Invalid Column Number");

        RowNode node = getRow(row,false);
        if(node != null)
            return node.arrayList.getValue(col);

        return 0;
    }

    /**
     * Prints all elements of the matrix, including zeros.
     * This method traverses the entire logical matrix size and prints
     * each element, showing zeros for indices that don't have nodes.
     */
    public void printMatrix(){
        RowNode currRow = first.next;
        for(int row = rows; row > 0; row--) {
            currRow.arrayList.printArr();
            currRow = currRow.next;
        }
    }

    /**
     * Prints only the non-zero elements of the matrix.
     * This method only traverses the linked list nodes and prints
     * their values, skipping the zero elements.
     */
    public void printMatrixNonZero() {
        RowNode currRow = first.next;
        for(int row = rows; row > 0; row--) {
            currRow.arrayList.printArrNonZero();
            currRow = currRow.next;
        }
    }

    /**
     * Adds the elements of another sparse matrix to this matrix.
     * Both Matrices must have the same size, rows and columns.
     *
     * @param secMatrix The second sparse matrix to add
     * @throws IllegalArgumentException If the array sizes don't match
     */
    public void addTwoMatrices(SparseMatrix secMatrix) {
        if (this.rows != secMatrix.rows || this.cols != secMatrix.cols || this.size != secMatrix.size)
            throw new IllegalArgumentException("Matrices must have the same size, rows and columns!");

    }

    /**
     * Gets the node at the specified row, optionally creating it if it doesn't exist.
     *
     * @param row The row to find
     * @param createIfMissing If true, creates a new node if the row doesn't exist
     * @return The node at the specified row, or null if it doesn't exist and createIfMissing is false
     */
    private RowNode getRow(int row, boolean createIfMissing) {
        RowNode prevRow = first;

        while (prevRow.next != null && prevRow.next.rowIndex < row)
            prevRow = prevRow.next;

        if(prevRow.next != null && prevRow.next.rowIndex == row)
            return prevRow.next;

        if(!createIfMissing)
            return null;

        return insertAfter(prevRow,row);
    }

    /**
     * Inserts a new node after the specified node.
     *
     * @param nodeBefore The node after which to insert
     * @param row The row value for the new node
     * @return The newly created node
     */
    private RowNode insertAfter(RowNode nodeBefore, int row) {
        RowNode newNode = new RowNode(row, cols);

        newNode.next = nodeBefore.next;
        linkNodes(nodeBefore,newNode);
        if(newNode.next == null)
            last = newNode;
        else
            linkNodes(newNode, newNode.next);

        size++;
        return newNode;
    }

    /**
     * Links two nodes together in the doubly-linked list.
     *
     * @param firstNode The first node to link
     * @param secNode The second node to link
     */
    private void linkNodes(RowNode firstNode, RowNode secNode) {
        if (firstNode == null || secNode == null)
            return;

        firstNode.next = secNode;
        secNode.prev = firstNode;
    }

    /**
     * Represents a node in the sparse matrix's linked list structure.
     * Each node corresponds to a row in the matrix and contains:
     * <ul>
     *   <li>The row index in the matrix.</li>
     *   <li>A pointer to a {@link SparseArray} that stores the non-zero elements of the row.</li>
     *   <li>Pointers to the previous and next {@link RowNode} in the linked list.</li>
     * </ul>
     */
    private static class RowNode {
        /** The row index in the matrix. */
        int rowIndex;
        /** The sparse array storing non-zero elements of this row. */
        SparseArray arrayList;

        /** Pointer to the next row node in the linked list. */
        RowNode next;
        /** Pointer to the previous row node in the linked list. */
        RowNode prev;

        /**
         * Constructs a new {@link RowNode} for the specified row index.
         *
         * @param rowIndex The row index in the matrix.
         * @param colSize  The number of columns in the matrix (used to initialize the {@link SparseArray}).
         */
        public RowNode(int rowIndex, int colSize) {
            this.rowIndex = rowIndex;
            this.arrayList = new SparseArray(colSize);
        }
    }
}
