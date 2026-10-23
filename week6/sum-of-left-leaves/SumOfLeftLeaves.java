import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.push(root);
        int sum = 0;

        while (!nodesToVisit.isEmpty()) {
            TreeNode currentNode = nodesToVisit.pop();
            if (currentNode.right != null) {
                nodesToVisit.push(currentNode.right);
            }
            if (currentNode.left != null) {
                TreeNode leftChild = currentNode.left;
                if (leftChild.left == null && leftChild.right == null) {
                    sum += leftChild.val;
                } else {
                    nodesToVisit.push(leftChild);
                }
            }
        }

        return sum;
    }
}
