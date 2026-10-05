import java.util.*;

class Solution {
    private int maxQuality = 0;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(new int[]{edge[0], edge[2]});
        }

        int[] visitedCount = new int[values.length];
        visitedCount[0] = 1;
        dfs(0, 0, values[0], maxTime, graph, values, visitedCount);

        return maxQuality;
    }

    private void dfs(int currNode, int currentTime, int currentQuality, int maxTime, 
                     Map<Integer, List<int[]>> graph, int[] values, int[] visitedCount) {
        if (currNode == 0) {
            maxQuality = Math.max(maxQuality, currentQuality);
        }

        if (!graph.containsKey(currNode)) return;

        for (int[] neighbor : graph.get(currNode)) {
            int nextNode = neighbor[0];
            int travelTime = neighbor[1];

            if (currentTime + travelTime <= maxTime) {
                boolean firstVisit = (visitedCount[nextNode] == 0);
                visitedCount[nextNode]++;
                
                int addedQuality = firstVisit ? values[nextNode] : 0;
                
                dfs(nextNode, currentTime + travelTime, currentQuality + addedQuality, 
                    maxTime, graph, values, visitedCount);
                
                visitedCount[nextNode]--;
            }
        }
    }
}