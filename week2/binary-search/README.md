# Binary Search

## 1. Problem

We are given an array of integers sorted in ascending order and a target value. I need to find the target in the array and return its index. If the target is not in the array, I return `-1`.

## 2. Approach

I used binary search because the array is already sorted.

At the start, `startIndex` is the first index and `endIndex` is the last index. In every loop I calculate `middleIndex` and compare that element with the target.

If the middle element is the target, I return its index. If the middle element is smaller, I continue searching in the right half. Otherwise, I continue in the left half. The loop ends when there are no elements left to check.

## 3. Time Complexity

**Time Complexity: O(log n)**

After every comparison, half of the remaining array is removed from the search. Because the search area is divided by two each time, the number of steps grows logarithmically as the array gets bigger.

## 4. Space Complexity

**Space Complexity: O(1)**

The solution only uses three integer variables called `startIndex`, `endIndex`, and `middleIndex`. It does not create another array or use recursion, so the extra memory does not depend on the size of the input.

## 5. Reflection / Improvement

A simple linear search would also work, but in the worst case it would check every element and take O(n) time. Binary search is more efficient because the input is sorted.

This solution already has the expected O(log n) time complexity. It could also be written with recursion, but that would use extra stack memory, so I would keep the iterative version.
