import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        int startLabelCount = 1;
        if (startLabelCount > k) return -1;
        
        pq.offer(new long[]{0, 0, startLabelCount});
        dist[0][startLabelCount] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long w = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (u == n - 1) {
                return (int) w;
            }

            if (w > dist[u][count]) {
                continue;
            }

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int weight = edge[1];

                int nextCount;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                if (nextCount <= k && w + weight < dist[v][nextCount]) {
                    dist[v][nextCount] = w + weight;
                    pq.offer(new long[]{w + weight, v, nextCount});
                }
            }
        }

        long minWeight = Long.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            minWeight = Math.min(minWeight, dist[n - 1][c]);
        }

        return minWeight == Long.MAX_VALUE ? -1 : (int) minWeight;
    }
}