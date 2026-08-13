import java.util.*;

class Solution {
    HashMap<Integer, Boolean> memo = new HashMap<>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

        int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;

        if (sum < desiredTotal)
            return false;

        if (desiredTotal <= 0)
            return true;

        return solve(maxChoosableInteger, desiredTotal, 0);
    }

    private boolean solve(int max, int target, int used) {

        if (memo.containsKey(used))
            return memo.get(used);

        for (int i = 1; i <= max; i++) {

            int bit = 1 << (i - 1);

            if ((used & bit) != 0)
                continue;

            if (i >= target ||
                !solve(max, target - i, used | bit)) {

                memo.put(used, true);
                return true;
            }
        }

        memo.put(used, false);
        return false;
    }
}