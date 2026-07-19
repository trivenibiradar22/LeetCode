class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int fact = 1;

        for (int i = 1; i < n; i++) {
            fact *= i;
            numbers.add(i);
        }
        numbers.add(n);

        StringBuilder ans = new StringBuilder();
        k--;

        while (true) {
            int index = k / fact;
            ans.append(numbers.get(index));
            numbers.remove(index);

            if (numbers.size() == 0)
                break;

            k %= fact;
            fact /= numbers.size();
        }

        return ans.toString();
    }
}