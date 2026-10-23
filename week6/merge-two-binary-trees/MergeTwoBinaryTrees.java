import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        Deque<TreeNode[]> nodesToMerge = new ArrayDeque<>();
        nodesToMerge.push(new TreeNode[] {root1, root2});

        while (!nodesToMerge.isEmpty()) {
            TreeNode[] currentPair = nodesToMerge.pop();
            TreeNode firstNode = currentPair[0];
            TreeNode secondNode = currentPair[1];
            firstNode.val += secondNode.val;

            if (firstNode.right == null) {
                firstNode.right = secondNode.right;
            } else if (secondNode.right != null) {
                nodesToMerge.push(new TreeNode[] {firstNode.right, secondNode.right});
            }

            if (firstNode.left == null) {
                firstNode.left = secondNode.left;
            } else if (secondNode.left != null) {
                nodesToMerge.push(new TreeNode[] {firstNode.left, secondNode.left});
            }
        }

        return root1;
    }
}
