class Solution {
    public int search(int[] nums, int target) {
        int startIndex = 0;
        int endIndex = nums.length - 1;

        while (startIndex <= endIndex) {
            int middleIndex = startIndex + (endIndex - startIndex) / 2;

            if (nums[middleIndex] == target) {
                return middleIndex;
            }

            if (nums[middleIndex] < target) {
                startIndex = middleIndex + 1;
            } else {
                endIndex = middleIndex - 1;
            }
        }

        return -1;
    }
}
