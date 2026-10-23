import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(root);
        int depth = 0;

        while (!nodesToVisit.isEmpty()) {
            int levelSize = nodesToVisit.size();

            for (int index = 0; index < levelSize; index++) {
                TreeNode currentNode = nodesToVisit.remove();
                if (currentNode.left != null) {
                    nodesToVisit.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nodesToVisit.add(currentNode.right);
                }
            }

            depth++;
        }

        return depth;
    }
}
