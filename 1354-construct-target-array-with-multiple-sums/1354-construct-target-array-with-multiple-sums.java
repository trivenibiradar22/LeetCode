import java.util.*;

class Solution {
    public boolean isPossible(int[] target) {
        long sum = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int x : target) {
            sum += x;
            pq.offer((long) x);
        }

        while (true) {
            long largest = pq.poll();
            long rest = sum - largest;

            if (largest == 1) {
                return true;
            }

            if (rest == 1) {
                return true;
            }

            if (rest == 0 || largest <= rest) {
                return false;
            }

            long previous = largest % rest;

            if (previous == 0) {
                return false;
            }

            pq.offer(previous);
            sum = rest + previous;
        }
    }
}