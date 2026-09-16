/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Integer currVal = null;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;
    private List<Integer> modesList = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);
        modesList = new ArrayList<>();
        currVal = null;
        currCount = 0;
        modeCount = 0;
        inorder(root);
        
        int[] result = new int[modesList.size()];
        for (int i = 0; i < modesList.size(); i++) {
            result[i] = modesList.get(i);
        }
        return result;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        
        inorder(node.left);
        
        if (currVal == null || node.val != currVal) {
            currVal = node.val;
            currCount = 1;
        } else {
            currCount++;
        }
        
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
            modesList.clear();
            modesList.add(node.val);
        } else if (currCount == maxCount) {
            modesList.add(node.val);
            modeCount++;
        }
        
        inorder(node.right);
    }
}