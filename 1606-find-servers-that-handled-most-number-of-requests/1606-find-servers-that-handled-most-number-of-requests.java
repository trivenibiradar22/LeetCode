class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        int[] count = new int[k];
        int maxCount = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int startTime = arrival[i];
            int endTime = startTime + load[i];
            
            while (!pq.isEmpty() && pq.peek()[0] <= startTime) {
                available.add(pq.poll()[1]);
            }
            
            if (available.isEmpty()) {
                continue;
            }
            
            Integer server = available.ceiling(i % k);
            if (server == null) {
                server = available.first();
            }
            
            available.remove(server);
            pq.offer(new int[] {endTime, server});
            count[server]++;
            maxCount = Math.max(maxCount, count[server]);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxCount) {
                result.add(i);
            }
        }
        
        return result;
    }
}