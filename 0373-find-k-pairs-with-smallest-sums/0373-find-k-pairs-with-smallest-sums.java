class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return ans;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Set<Long> visited = new HashSet<>();

        pq.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add(0L);

        while (!pq.isEmpty() && ans.size() < k) {
            int[] cur = pq.poll();
            int i = cur[1], j = cur[2];

            ans.add(Arrays.asList(nums1[i], nums2[j]));

            if (i + 1 < nums1.length) {
                long key = ((long) (i + 1) << 32) | j;
                if (visited.add(key)) {
                    pq.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                }
            }

            if (j + 1 < nums2.length) {
                long key = ((long) i << 32) | (j + 1);
                if (visited.add(key)) {
                    pq.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                }
            }
        }

        return ans;
    }
}