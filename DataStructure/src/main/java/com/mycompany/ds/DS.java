package com.mycompany.ds;

/**
 *
 * @author Abdullah Maher
 */
public class DS {

    public static void main(String[] args) {
        ArrayList arr = new ArrayList(10);
        
        for(int i = 0; i < 10; i++)
            arr.set(i, i);
        
        arr.print();
        
        arr.addLast(10);
        arr.print();
        arr.add(10, 11);
        arr.print();
    }
}
