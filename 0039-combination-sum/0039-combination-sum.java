import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        solve(candidates, target, 0, list, ans);

        return ans;
    }

    public void solve(int[] candidates, int target, int index,
                      List<Integer> list, List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (target < 0 || index == candidates.length) {
            return;
        }

        list.add(candidates[index]);
        solve(candidates, target - candidates[index], index, list, ans);

        list.remove(list.size() - 1);
        solve(candidates, target, index + 1, list, ans);
    }
}