class MaximumDepthOfBinaryTreeTest extends TestSupport {
    public static void main(String[] args) {
        Solution solution = new Solution();
        equal(0, solution.maxDepth(null));
        equal(1, solution.maxDepth(tree(-4)));
        equal(2, solution.maxDepth(tree(1, null, 2)));
        equal(2, solution.maxDepth(tree(1, 2)));
        TreeNode example = tree(3, 9, 20, null, null, 15, 7);
        var saved = snapshot(example);
        equal(3, solution.maxDepth(example));
        unchanged(saved);
        equal(4, solution.maxDepth(tree(1, 2, 3, 4, 5, null, null, 6)));
        equal(3, solution.maxDepth(tree(0, 0, 0, 0, 0, 0, 0)));
        equal(10000, solution.maxDepth(chain(10000, true, -1)));
        equal(10000, solution.maxDepth(chain(10000, false, 1)));
        equal(1, solution.maxDepth(tree(0)));
        done("Maximum depth");
    }
}
