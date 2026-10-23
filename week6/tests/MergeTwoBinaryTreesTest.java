import java.util.List;

class MergeTwoBinaryTreesTest extends TestSupport {
    public static void main(String[] args) {
        Solution solution = new Solution();
        same(null, solution.mergeTrees(null, null));
        TreeNode single = tree(5);
        same(single, solution.mergeTrees(single, null));
        same(single, solution.mergeTrees(null, single));

        TreeNode first = tree(1, 3, 2, 5);
        TreeNode second = tree(2, 1, 3, null, 4, null, 7);
        var secondBefore = snapshot(second);
        TreeNode existingLeftLeaf = first.left.left;
        TreeNode attachedFour = second.left.right;
        TreeNode attachedSeven = second.right.right;
        same(first, solution.mergeTrees(first, second));
        equal(java.util.Arrays.asList(3, 4, 5, 5, 4, null, 7), levelOrder(first));
        same(existingLeftLeaf, first.left.left);
        same(attachedFour, first.left.right);
        same(attachedSeven, first.right.right);
        unchanged(secondBefore);

        TreeNode smaller = tree(1);
        TreeNode larger = tree(1, 2);
        same(smaller, solution.mergeTrees(smaller, larger));
        equal(List.of(2, 2), levelOrder(smaller));
        same(larger.left, smaller.left);
        equal(List.of(-3, 0, -1), levelOrder(solution.mergeTrees(tree(-1, -2), tree(-2, 2, -1))));
        equal(List.of(2, 4, 4), levelOrder(solution.mergeTrees(tree(1, 2, 2), tree(1, 2, 2))));
        equal(List.of(3, 3, 4), levelOrder(solution.mergeTrees(tree(1, 3), tree(2, null, 4))));

        for (boolean left : new boolean[] {true, false}) {
            TreeNode longFirst = chain(2000, left, 10000);
            TreeNode longSecond = chain(2000, left, -9999);
            var secondSnapshot = snapshot(longSecond);
            same(longFirst, solution.mergeTrees(longFirst, longSecond));
            TreeNode node = longFirst;
            for (int index = 0; index < 2000; index++) {
                equal(1, node.val);
                same(null, left ? node.right : node.left);
                node = left ? node.left : node.right;
            }
            same(null, node);
            unchanged(secondSnapshot);
        }
        equal(List.of(9), levelOrder(solution.mergeTrees(tree(4), tree(5))));
        done("Merge trees");
    }
}
