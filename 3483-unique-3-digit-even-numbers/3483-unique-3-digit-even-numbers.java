class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }
        
        int validCount = 0;
        
        for (int num = 100; num < 1000; num += 2) {
            int h = num / 100;
            int t = (num / 10) % 10;
            int u = num % 10;
            
            int[] tempCount = new int[10];
            tempCount[h]++;
            tempCount[t]++;
            tempCount[u]++;
            
            boolean possible = true;
            for (int i = 0; i < 10; i++) {
                if (tempCount[i] > count[i]) {
                    possible = false;
                    break;
                }
            }
            
            if (possible) {
                validCount++;
            }
        }
        
        return validCount;
    }
}