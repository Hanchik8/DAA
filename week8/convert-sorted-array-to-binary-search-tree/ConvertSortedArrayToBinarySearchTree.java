class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }

        int middleIndex = startIndex + (endIndex - startIndex) / 2;
        TreeNode root = new TreeNode(nums[middleIndex]);
        root.left = buildTree(nums, startIndex, middleIndex - 1);
        root.right = buildTree(nums, middleIndex + 1, endIndex);
        return root;
    }
}
