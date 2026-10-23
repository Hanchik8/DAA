import java.util.Collections;
import java.util.List;

class BinaryTreePathsTest extends TestSupport {
    public static void main(String[] args) {
        Solution solution = new Solution();
        equal(List.of(), solution.binaryTreePaths(null));
        equal(List.of("1"), solution.binaryTreePaths(tree(1)));
        TreeNode example = tree(1, 2, 3, null, 5);
        var saved = snapshot(example);
        equal(List.of("1->2->5", "1->3"), sorted(solution.binaryTreePaths(example)));
        unchanged(saved);
        equal(List.of("-10->-2", "-10->30"),
                sorted(solution.binaryTreePaths(tree(-10, -2, 30))));
        equal(List.of("1->2", "1->2"), solution.binaryTreePaths(tree(1, 2, 2)));
        equal(List.of("1->2->4", "1->2->5", "1->3->6", "1->3->7"),
                sorted(solution.binaryTreePaths(tree(1, 2, 3, 4, 5, 6, 7))));
        String expectedPath = String.join("->", Collections.nCopies(100, "-10"));
        for (boolean left : new boolean[] {true, false}) {
            equal(List.of(expectedPath), solution.binaryTreePaths(chain(100, left, -10)));
        }
        List<String> firstResult = solution.binaryTreePaths(tree(9));
        equal(List.of("8"), solution.binaryTreePaths(tree(8)));
        equal(List.of("9"), firstResult);
        done("Tree paths");
    }

    private static List<String> sorted(List<String> paths) {
        Collections.sort(paths);
        return paths;
    }
}
