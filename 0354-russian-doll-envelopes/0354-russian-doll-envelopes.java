import java.util.*;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        List<Integer> lis = new ArrayList<>();

        for (int[] envelope : envelopes) {
            int h = envelope[1];
            int index = Collections.binarySearch(lis, h);

            if (index < 0) {
                index = -(index + 1);
            }

            if (index == lis.size()) {
                lis.add(h);
            } else {
                lis.set(index, h);
            }
        }

        return lis.size();
    }
}