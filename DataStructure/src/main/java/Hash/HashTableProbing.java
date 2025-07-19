package Hash;

public class HashTableProbing {
    private int tableSize = 31;
    private final double limitLoadFactor = 0.75;
    private int elementCount;
    private Entry[] table;
    private final Entry deleteMarker = new Entry("", "");

    public HashTableProbing(){
        elementCount = 0;
        table = new Entry[tableSize];
    }

    public void addNum(String name, String number){
        if ((double)(elementCount + 1) / tableSize > limitLoadFactor)
            rehashing(); // Add this before inserting

        int idx = hashStr(name);
        while (idx < tableSize-1){
            if (table[idx] == null || table[idx] == deleteMarker){
                table[idx] = new Entry(name, number);
                break;
            }
            ++idx;
        }
        ++elementCount;
    }

    public String getNum(String name){
        int idx = hashStr(name);

        while (idx < tableSize-1 && table[idx] != null){
            if (table[idx].name.equals(name))
               return table[idx].number;

            ++idx;
        }

        return null;
    }

    public void removeNum(String name){
        int idx = hashStr(name);
        while (idx < tableSize - 1 && table[idx] != null){
            if (table[idx].name.equals(name)) {
                table[idx] = deleteMarker;
                break;
            }
            ++idx;
        }
        --elementCount;
    }

    public void printAllNums(){
        for (Entry e : table)
            if (e != null)
                e.print();

    }

    public void rehashing(){
        double curLoadFactor = (double)elementCount / tableSize;
        if (curLoadFactor < limitLoadFactor)
            return;

        tableSize = tableSize * 2; // better to use tableSize = nextPrime(tableSize * 2);

        Entry[] oldTable = table;
        table = new Entry[tableSize];

        elementCount = 0;

        for (Entry e : oldTable)
            addNum(e.name, e.number);
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
                throw new IllegalArgumentException("Enter Valid Data");

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
        for (Entry e : table) {
            if (e == null || e == deleteMarker) {
                emptyBuckets++;
            }
        }
        System.out.println("Empty buckets: " + emptyBuckets);
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
