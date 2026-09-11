class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = -1;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int forwardDistance = (i - startIndex + n) % n;
                int backwardDistance = (startIndex - i + n) % n;
                int currentMin = Math.min(forwardDistance, backwardDistance);

                if (minDistance == -1 || currentMin < minDistance) {
                    minDistance = currentMin;
                }
            }
        }

        return minDistance;
    }
}