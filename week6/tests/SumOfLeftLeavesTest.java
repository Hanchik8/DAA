class SumOfLeftLeavesTest extends TestSupport {
    public static void main(String[] args) {
        Solution solution = new Solution();
        equal(0, solution.sumOfLeftLeaves(null));
        equal(0, solution.sumOfLeftLeaves(tree(5)));
        equal(2, solution.sumOfLeftLeaves(tree(1, 2)));
        equal(0, solution.sumOfLeftLeaves(tree(1, null, 2)));
        TreeNode example = tree(3, 9, 20, null, null, 15, 7);
        var saved = snapshot(example);
        equal(24, solution.sumOfLeftLeaves(example));
        unchanged(saved);
        equal(4, solution.sumOfLeftLeaves(tree(1, 2, 3, 4, 5)));
        equal(4, solution.sumOfLeftLeaves(tree(1, null, 2, 4)));
        equal(-6, solution.sumOfLeftLeaves(tree(0, -2, 3, null, null, -4, 7)));
        equal(4, solution.sumOfLeftLeaves(tree(1, 2, 2, 2, 2, 2, 2)));
        equal(1, solution.sumOfLeftLeaves(chain(1000, true, 1)));
        equal(0, solution.sumOfLeftLeaves(chain(1000, false, 1)));
        equal(0, solution.sumOfLeftLeaves(tree(0)));
        done("Left leaves");
    }
}
