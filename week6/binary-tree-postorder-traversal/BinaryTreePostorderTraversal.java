import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> nodeValues = new ArrayList<>();
        visitPostorder(root, nodeValues);
        return nodeValues;
    }

    private void visitPostorder(TreeNode node, List<Integer> nodeValues) {
        if (node == null) {
            return;
        }

        visitPostorder(node.left, nodeValues);
        visitPostorder(node.right, nodeValues);
        nodeValues.add(node.val);
    }
}
