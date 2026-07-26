class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int radius = 0;

        for (int house : houses) {
            int idx = Arrays.binarySearch(heaters, house);

            if (idx >= 0) {
                continue; // Heater exactly at the house
            }

            idx = -idx - 1;

            int rightDist = idx == heaters.length
                    ? Integer.MAX_VALUE
                    : heaters[idx] - house;

            int leftDist = idx == 0
                    ? Integer.MAX_VALUE
                    : house - heaters[idx - 1];

            radius = Math.max(radius, Math.min(leftDist, rightDist));
        }

        return radius;
    }
}