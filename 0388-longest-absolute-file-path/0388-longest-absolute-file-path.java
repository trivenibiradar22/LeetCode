class Solution {
    public int lengthLongestPath(String input) {
        String[] parts = input.split("\n");
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int max = 0;

        for (String s : parts) {
            int level = 0;

            while (s.startsWith("\t")) {
                level++;
                s = s.substring(1);
            }

            if (s.contains(".")) {
                max = Math.max(max, map.get(level) + s.length());
            } else {
                map.put(level + 1, map.get(level) + s.length() + 1);
            }
        }

        return max;
    }
}