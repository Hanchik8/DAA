import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodeValues = new ArrayList<>();
        visitInorder(root, nodeValues);
        return nodeValues;
    }

    private void visitInorder(TreeNode node, List<Integer> nodeValues) {
        if (node == null) {
            return;
        }

        visitInorder(node.left, nodeValues);
        nodeValues.add(node.val);
        visitInorder(node.right, nodeValues);
    }
}
