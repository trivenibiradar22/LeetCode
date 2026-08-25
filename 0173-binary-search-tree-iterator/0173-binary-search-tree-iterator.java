class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        // Push all the left children of the root to simulate the leftmost path
        pushAllLeft(root);
    }
    
    public int next() {
        TreeNode node = stack.pop();
        // If the node has a right child, we need to push its leftmost path onto the stack
        if (node.right != null) {
            pushAllLeft(node.right);
        }
        return node.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}