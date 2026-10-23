import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Deque<PathState> pathsToVisit = new ArrayDeque<>();
        pathsToVisit.push(new PathState(root, root.val));

        while (!pathsToVisit.isEmpty()) {
            PathState currentPath = pathsToVisit.pop();
            TreeNode currentNode = currentPath.node;

            if (currentNode.left == null && currentNode.right == null
                    && currentPath.sum == targetSum) {
                return true;
            }

            if (currentNode.right != null) {
                pathsToVisit.push(new PathState(currentNode.right,
                        currentPath.sum + currentNode.right.val));
            }
            if (currentNode.left != null) {
                pathsToVisit.push(new PathState(currentNode.left,
                        currentPath.sum + currentNode.left.val));
            }
        }

        return false;
    }

    private static class PathState {
        final TreeNode node;
        final int sum;

        PathState(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }
}
