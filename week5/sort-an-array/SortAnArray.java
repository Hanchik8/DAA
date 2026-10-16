class Solution {
    public int[] sortArray(int[] nums) {
        int[] temporaryValues = new int[nums.length];
        mergeSort(nums, temporaryValues, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int[] temporaryValues, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int middleIndex = startIndex + (endIndex - startIndex) / 2;

        mergeSort(nums, temporaryValues, startIndex, middleIndex);
        mergeSort(nums, temporaryValues, middleIndex + 1, endIndex);
        merge(nums, temporaryValues, startIndex, middleIndex, endIndex);
    }

    private void merge(int[] nums, int[] temporaryValues, int startIndex, int middleIndex, int endIndex) {
        int firstIndex = startIndex;
        int secondIndex = middleIndex + 1;
        int mergeIndex = startIndex;

        while (firstIndex <= middleIndex && secondIndex <= endIndex) {
            if (nums[firstIndex] <= nums[secondIndex]) {
                temporaryValues[mergeIndex] = nums[firstIndex];
                firstIndex++;
            } else {
                temporaryValues[mergeIndex] = nums[secondIndex];
                secondIndex++;
            }

            mergeIndex++;
        }

        while (firstIndex <= middleIndex) {
            temporaryValues[mergeIndex] = nums[firstIndex];
            firstIndex++;
            mergeIndex++;
        }

        while (secondIndex <= endIndex) {
            temporaryValues[mergeIndex] = nums[secondIndex];
            secondIndex++;
            mergeIndex++;
        }

        for (int index = startIndex; index <= endIndex; index++) {
            nums[index] = temporaryValues[index];
        }
    }
}
