import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> nodeValues = new ArrayList<>();
        visitPreorder(root, nodeValues);
        return nodeValues;
    }

    private void visitPreorder(TreeNode node, List<Integer> nodeValues) {
        if (node == null) {
            return;
        }

        nodeValues.add(node.val);
        visitPreorder(node.left, nodeValues);
        visitPreorder(node.right, nodeValues);
    }
}
