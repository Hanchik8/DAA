class PathSumTest extends TestSupport {
    public static void main(String[] args) {
        Solution solution = new Solution();
        equal(false, solution.hasPathSum(null, 0));
        equal(true, solution.hasPathSum(tree(5), 5));
        equal(false, solution.hasPathSum(tree(5), 0));
        equal(false, solution.hasPathSum(tree(1, 2), 1));
        equal(true, solution.hasPathSum(tree(5, -3), 2));
        equal(false, solution.hasPathSum(tree(1, 2, 3), 5));
        equal(true, solution.hasPathSum(tree(1, 2, 3), 4));
        equal(true, solution.hasPathSum(tree(0, 0, 0), 0));
        equal(true, solution.hasPathSum(tree(-2, null, -3), -5));
        TreeNode example = tree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        var saved = snapshot(example);
        equal(true, solution.hasPathSum(example, 22));
        equal(false, solution.hasPathSum(example, -100));
        unchanged(saved);
        // Long paths can have large intermediate sums even with a small target.
        for (boolean left : new boolean[] {true, false}) {
            TreeNode longTree = chain(5000, left, 1000);
            TreeNode node = longTree;
            for (int index = 0; index < 5000; index++) {
                if (index >= 2500) {
                    node.val = -1000;
                }
                node = left ? node.left : node.right;
            }
            equal(true, solution.hasPathSum(longTree, 0));
            equal(false, solution.hasPathSum(longTree, 1));
        }
        equal(true, solution.hasPathSum(tree(2), 2));
        done("Path sum");
    }
}
