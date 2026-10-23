import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class BinaryTreePostorderTraversalTest extends TestSupport {
    public static void main(String[] args) {
        Solution solution = new Solution();
        equal(List.of(), solution.postorderTraversal(null));
        equal(List.of(5), solution.postorderTraversal(tree(5)));
        equal(List.of(3, 2, 1), solution.postorderTraversal(tree(1, null, 2, 3)));
        equal(List.of(2, 3, 1), solution.postorderTraversal(tree(1, 2, 3)));
        equal(List.of(-2, 3, -1), solution.postorderTraversal(tree(-1, -2, 3)));
        equal(List.of(2, 2, 2), solution.postorderTraversal(tree(2, 2, 2)));
        TreeNode complex = tree(1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9);
        var saved = snapshot(complex);
        equal(List.of(4, 6, 7, 5, 2, 9, 8, 3, 1), solution.postorderTraversal(complex));
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
            Collections.reverse(expected);
            equal(expected, solution.postorderTraversal(root));
        }
        List<Integer> firstResult = solution.postorderTraversal(tree(9));
        equal(List.of(8), solution.postorderTraversal(tree(8)));
        equal(List.of(9), firstResult);
        done("Postorder traversal");
    }
}
