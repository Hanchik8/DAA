import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BinaryTreePreorderTraversalTest extends TestSupport {
    public static void main(String[] args) {
        Solution solution = new Solution();
        equal(List.of(), solution.preorderTraversal(null));
        equal(List.of(5), solution.preorderTraversal(tree(5)));
        equal(List.of(1, 2, 3), solution.preorderTraversal(tree(1, null, 2, 3)));
        equal(List.of(1, 2, 4, 5, 3), solution.preorderTraversal(tree(1, 2, 3, 4, 5)));
        equal(List.of(-1, -2, 3), solution.preorderTraversal(tree(-1, -2, 3)));
        equal(List.of(2, 2, 2), solution.preorderTraversal(tree(2, 2, 2)));
        TreeNode complex = tree(1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9);
        var saved = snapshot(complex);
        equal(List.of(1, 2, 4, 5, 6, 7, 3, 8, 9), solution.preorderTraversal(complex));
        unchanged(saved);

        for (boolean left : Arrays.asList(false, true)) {
            TreeNode root = chain(100, left, 0);
            List<Integer> expected = new ArrayList<>();
            TreeNode node = root;
            for (int value = 1; value <= 100; value++) {
                node.val = value;
                expected.add(value);
                node = left ? node.left : node.right;
            }
            equal(expected, solution.preorderTraversal(root));
        }
        List<Integer> firstResult = solution.preorderTraversal(tree(9));
        equal(List.of(8), solution.preorderTraversal(tree(8)));
        equal(List.of(9), firstResult);
        done("Preorder traversal");
    }
}
