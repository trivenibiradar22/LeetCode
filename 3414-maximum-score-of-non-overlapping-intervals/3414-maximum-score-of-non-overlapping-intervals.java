import java.util.*;

class Solution {

    static class Interval {
        int l, r, weight, index;

        Interval(int l, int r, int weight, int index) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.index = index;
        }
    }

    static class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    Interval[] arr;
    int n;
    Result[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        n = intervals.size();
        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);
            return Integer.compare(a.r, b.r);
        });

        dp = new Result[n + 1][5];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = new Result(0, new ArrayList<>());
        }

        for (int k = 1; k <= 4; k++) {
            dp[n][k] = new Result(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                Result skip = dp[i + 1][k];

                int next = findNext(i);

                Result takeNext = dp[next][k - 1];
                List<Integer> takeList = new ArrayList<>(takeNext.indices);
                takeList.add(arr[i].index);
                Collections.sort(takeList);

                Result take = new Result(
                    arr[i].weight + takeNext.score,
                    takeList
                );

                dp[i][k] = better(skip, take);
            }
        }

        List<Integer> answer = dp[0][4].indices;
        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    int findNext(int i) {
        int low = i + 1;
        int high = n;
        int target = arr[i].r;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid].l > target)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    Result better(Result a, Result b) {
        if (a.score != b.score)
            return a.score > b.score ? a : b;

        return compare(a.indices, b.indices) <= 0 ? a : b;
    }

    int compare(List<Integer> a, List<Integer> b) {
        int size = Math.min(a.size(), b.size());

        for (int i = 0; i < size; i++) {
            if (!a.get(i).equals(b.get(i)))
                return Integer.compare(a.get(i), b.get(i));
        }

        return Integer.compare(a.size(), b.size());
    }
}