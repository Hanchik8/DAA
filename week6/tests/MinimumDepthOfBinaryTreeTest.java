class MinimumDepthOfBinaryTreeTest extends TestSupport {
    public static void main(String[] args) {
        Solution solution = new Solution();
        equal(0, solution.minDepth(null));
        equal(1, solution.minDepth(tree(-4)));
        equal(2, solution.minDepth(tree(1, null, 2)));
        equal(2, solution.minDepth(tree(1, 2)));
        TreeNode example = tree(3, 9, 20, null, null, 15, 7);
        var saved = snapshot(example);
        equal(2, solution.minDepth(example));
        unchanged(saved);
        equal(5, solution.minDepth(tree(2, null, 3, null, 4, null, 5, null, 6)));
        equal(2, solution.minDepth(tree(1, 2, 3, 4, null, null, null, 5)));
        equal(3, solution.minDepth(tree(0, 0, 0, 0, 0, 0, 0)));
        equal(100000, solution.minDepth(chain(100000, true, -1)));
        equal(100000, solution.minDepth(chain(100000, false, 1)));
        equal(1, solution.minDepth(tree(0)));
        done("Minimum depth");
    }
}
