class Solution {
    public int findMinDifference(List<String> timePoints) {
        int[] minutes = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            String time = timePoints.get(i);
            int h = Integer.parseInt(time.substring(0, 2));
            int m = Integer.parseInt(time.substring(3, 5));
            minutes[i] = h * 60 + m;
        }
        
        Arrays.sort(minutes);
        
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < minutes.length - 1; i++) {
            minDiff = Math.min(minDiff, minutes[i + 1] - minutes[i]);
        }
        
        int circularDiff = (1440 - minutes[minutes.length - 1]) + minutes[0];
        minDiff = Math.min(minDiff, circularDiff);
        
        return minDiff;
    }
}