package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class StreamProcessor {
    private final PriorityQueue<Integer> pq;
    private final int k;

    public StreamProcessor(int k){
        this.k = k;
        pq = new PriorityQueue<>(Collections.reverseOrder());
    }

    /* The idea is to maintain a max-heap of size 'n'.
       This way, the root of the heap always holds the largest among the smallest 'n' elements seen so far in the stream.
       As a result, the root represents the nth smallest element in the current stream. */
    public int getKthSmallestStream(int streamNum){
        if(k > pq.size()){
            pq.add(streamNum);
        }else if (streamNum < pq.peek()){
            pq.poll();
            pq.add(streamNum);
        }
        return pq.peek();
    }
}
