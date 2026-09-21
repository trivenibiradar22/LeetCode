class Solution {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }

    private void convert(TreeNode node) {
        if (node == null) {
            return;
        }
        convert(node.right);
        sum += node.val;
        node.val = sum;
        convert(node.left);
    }
}