import java.util.Arrays;
import java.util.List;

class InvertBinaryTreeTest extends TestSupport {
    public static void main(String[] args) {
        Solution solution = new Solution();
        same(null, solution.invertTree(null));
        TreeNode single = tree(-4);
        same(single, solution.invertTree(single));
        equal(List.of(-4), levelOrder(single));

        TreeNode root = tree(4, 2, 7, 1, 3, 6, 9);
        var saved = snapshot(root);
        TreeNode originalLeft = root.left;
        TreeNode originalRight = root.right;
        same(root, solution.invertTree(root));
        equal(List.of(4, 7, 2, 9, 6, 3, 1), levelOrder(root));
        same(originalRight, root.left);
        same(originalLeft, root.right);
        // Snapshot verifies every original reference after a second inversion.
        same(root, solution.invertTree(root));
        unchanged(saved);

        equal(List.of(2, 3, 1), levelOrder(solution.invertTree(tree(2, 1, 3))));
        equal(Arrays.asList(1, null, -2), levelOrder(solution.invertTree(tree(1, -2))));
        equal(List.of(1, -2), levelOrder(solution.invertTree(tree(1, null, -2))));
        equal(List.of(2, 2, 2), levelOrder(solution.invertTree(tree(2, 2, 2))));

        for (boolean left : new boolean[] {true, false}) {
            TreeNode longTree = chain(100, left, 1);
            var original = snapshot(longTree);
            solution.invertTree(longTree);
            TreeNode node = longTree;
            for (int index = 0; index < 100; index++) {
                equal(1, node.val);
                same(null, left ? node.left : node.right);
                node = left ? node.right : node.left;
            }
            same(null, node);
            solution.invertTree(longTree);
            unchanged(original);
        }
        done("Invert tree");
    }
}
