class Solution {
    public TreeNode searchBST(TreeNode root, int targetValue) {
        TreeNode currentNode = root;

        while (currentNode != null) {
            if (currentNode.val == targetValue) {
                return currentNode;
            }

            if (targetValue < currentNode.val) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        return null;
    }
}
