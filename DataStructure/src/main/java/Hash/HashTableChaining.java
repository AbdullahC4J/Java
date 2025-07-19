package Hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTableChaining {
    private int tableSize = 11; // table size
    private final double limitLoadFactor = 0.75;
    private int elementCount;
    private ArrayList<List<Entry>> table;

    public HashTableChaining(){
        table = new ArrayList<>(tableSize);
        for (int i = 0; i < tableSize; i++)
            table.add(new LinkedList<>());

        elementCount = 0;
    }

    public void addNum(String name, String number){
        if ((double)(elementCount + 1) / tableSize > limitLoadFactor)
            rehashing(); // Add this before inserting

        Entry entry = new Entry(name, number);
        table.get(hashStr(name)).add(entry);
        ++elementCount;
    }

    public String getNum(String name){
        List<Entry> chain = table.get(hashStr(name));

        for (Entry entry : chain){
            if (entry.name.equals(name))
                return entry.number;
        }

        return null;
    }

    public boolean removeNum(String name){
        List<Entry> chain = table.get(hashStr(name));

        for (int i = 0; i < chain.size(); ++i){
            if (chain.get(i).name.equals(name)) {
                chain.remove(i);
                --elementCount;
                return true;
            }
        }

        return false;
    }

    public void printAllNums(){
        for (List<Entry> chain : table)
            for (Entry entry : chain)
                entry.print();
    }

    public void rehashing(){
        double curLoadFactor = (double)elementCount / tableSize;
        if (curLoadFactor < limitLoadFactor)
            return;

        tableSize = tableSize * 2; // better to use tableSize = nextPrime(tableSize * 2);

        ArrayList<List<Entry>> oldTable = table;
        table = new ArrayList<>(tableSize);
        for (int i = 0; i < tableSize; i++)
            table.add(new LinkedList<>());

        elementCount = 0;

        for (List<Entry> chain : oldTable)
            for (Entry entry : chain)
                addNum(entry.name, entry.number);

    }

    /* NOTE : Any hash function that produces values within the valid range can work,
     but a poorly designed one may cause problems during searching due to collisions or uneven distribution.
     */
    private int hashStr(String str){
        int base = 2 * 26 + 10; // lower and upper char + 10 digits
        long sum = 0;


        for (char c : str.toCharArray()) {
            int val = 0;
            if (Character.isLowerCase(c))
                val = c - 'a';
            else if(Character.isUpperCase(c))
                val = 26 + c - 'A';
            else if(Character.isDigit(c))
                val = 26 + 26 + c - '0';
            else
                throw new IllegalArgumentException("Invalid character '" + c + "' in input: " + str);

            sum = (sum * base + val) % tableSize;
        }
        return (int)sum;
    }

/* shift folding technique
    private int hashStr(String str) {
        long sum = 0;
        long chunkHash = 0;

        str = str.toLowerCase(); // to prevent case-sensitive.

        for (int i = 0; i < str.length(); ++i) {
            chunkHash = (chunkHash * 26 + str.charAt(i)- 'a') % tableSize; // 26 is base, mod each iteration to prevent overflow

            if ((i + 1) % 4 == 0 || i == str.length() -1) {
                sum = (sum + chunkHash) % tableSize;
                chunkHash = 0;
            }
        }
        return (int) sum;
    }
*/
    private int hashNum(int num, long mod){
        return (int)((num % mod + mod) % mod);  //num % mod gives a value in (-mod, mod), + mod This shifts the range from (-mod, mod) to (0, 2*mod), % mod again brings the result back into [0, mod-1
    }

    public void printTableStats() {
        System.out.println("Table size: " + tableSize);
        System.out.println("Element count: " + elementCount);
        System.out.println("Load factor: " + (double)elementCount / tableSize);

        int emptyBuckets = 0;
        int maxChainLength = 0;
        for (List<Entry> chain : table) {
            if (chain.isEmpty()) {
                emptyBuckets++;
            } else {
                maxChainLength = Math.max(maxChainLength, chain.size());
            }
        }
        System.out.println("Empty buckets: " + emptyBuckets);
        System.out.println("Max chain length: " + maxChainLength);
    }

    private static class Entry {
        String name;
        String number;

        public Entry(String name, String number){
            this.name = name;
            this.number = number;
        }

        public void print(){
            System.out.println("Name: " + name + "\t|| Phone: " + number);
        }
    }
}