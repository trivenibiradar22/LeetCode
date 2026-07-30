class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int start, int[] nums, List<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(i + 1, nums, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}