class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;

        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;

                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];

                int distance = dx * dx + dy * dy;

                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }

            for (int frequency : map.values()) {
                count += frequency * (frequency - 1);
            }
        }

        return count;
    }
}