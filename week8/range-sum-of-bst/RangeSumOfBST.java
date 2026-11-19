import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.push(root);
        int sum = 0;

        while (!nodesToVisit.isEmpty()) {
            TreeNode currentNode = nodesToVisit.pop();

            if (low <= currentNode.val && currentNode.val <= high) {
                sum += currentNode.val;
            }
            if (currentNode.val < high && currentNode.right != null) {
                nodesToVisit.push(currentNode.right);
            }
            if (currentNode.val > low && currentNode.left != null) {
                nodesToVisit.push(currentNode.left);
            }
        }

        return sum;
    }
}
