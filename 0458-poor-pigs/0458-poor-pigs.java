class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int rounds = minutesToTest / minutesToDie;
        int pigs = 0;
        int states = rounds + 1;
        long total = 1;

        while (total < buckets) {
            pigs++;
            total *= states;
        }

        return pigs;
    }
}