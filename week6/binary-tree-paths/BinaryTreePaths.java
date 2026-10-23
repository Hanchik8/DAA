import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        collectPaths(root, new StringBuilder(), paths);
        return paths;
    }

    private void collectPaths(TreeNode node, StringBuilder currentPath,
            List<String> paths) {
        if (node == null) {
            return;
        }

        int previousLength = currentPath.length();
        if (previousLength > 0) {
            currentPath.append("->");
        }
        currentPath.append(node.val);

        if (node.left == null && node.right == null) {
            paths.add(currentPath.toString());
        } else {
            collectPaths(node.left, currentPath, paths);
            collectPaths(node.right, currentPath, paths);
        }

        currentPath.setLength(previousLength);
    }
}
