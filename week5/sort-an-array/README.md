# Sort an Array

## 1. Problem

I need to put the integers in an array in ascending order and return the sorted array. The values can be negative or repeated. I cannot use a built-in sorting method.

[Problem on LeetCode](https://leetcode.com/problems/sort-an-array/description/)

## 2. Approach

I use merge sort. The `mergeSort` method divides a range into two smaller ranges until each range has at most one element. A single element is already sorted.

After both parts are sorted, `merge` combines them. `firstIndex` follows the first part, `secondIndex` follows the second part, and `mergeIndex` marks where to put the next value in `temporaryValues`. I compare the current values and copy the smaller one. If they are equal, I take the value from the first part.

When one part ends, I copy all remaining values from the other part. Then I copy the merged range back into `nums`. The range includes both `startIndex` and `endIndex`.

I allocate `temporaryValues` once and reuse it in every merge. The smaller parts are index ranges in the original array, so splitting does not create more arrays. The method sorts and returns the same `nums` array. An empty array also returns unchanged because its initial range has no elements.

### Example trace

The input is `[5, 2, 3, 1]`.

```text
[5, 2, 3, 1]
    [5, 2]       [3, 1]
    [5] [2]      [3] [1]
```

First, merging `[5]` and `[2]` gives `[2, 5]`. Merging `[3]` and `[1]` gives `[1, 3]`. The array now holds `[2, 5, 1, 3]`.

The final merge combines the sorted ranges `[2, 5]` and `[1, 3]`.

| Step | First part value | Second part value | Action | Merged values so far |
| --- | --- | --- | --- | --- |
| 1 | 2 | 1 | Copy 1 and advance `secondIndex` | [1] |
| 2 | 2 | 3 | Copy 2 and advance `firstIndex` | [1, 2] |
| 3 | 5 | 3 | Copy 3 and advance `secondIndex` | [1, 2, 3] |
| 4 | 5 | No value remaining | Copy the remaining 5 | [1, 2, 3, 5] |

Copying this range back gives the final array `[1, 2, 3, 5]`.

### Challenges and testing

The main detail was what happens when one part runs out. I tested a merge that only copied while both parts still had values. For `[2, 3]` and `[1]`, that loop copied 1 and stopped. With a fresh zero-filled temporary array, the result was `[1, 0, 0]` instead of `[1, 2, 3]`. The two remaining-value loops handle this case for either part.

I also checked an empty array, a single value, already sorted values, reverse order, duplicates, and negative numbers. For example, `[3, -1, 3, 0]` should become `[-1, 0, 3, 3]`. I checked that the result was the same array object that was passed to the method.

## 3. Time Complexity

**Time Complexity: O(n log n)**

Here, `n` is the number of values. Each recursive call sorts two halves and merges them in linear time. For an even size, the recurrence is `T(n) = 2T(n / 2) + O(n)`. Odd sizes split into two parts that differ by one element.

The recursion has O(log n) levels because each part is roughly half the previous size. At each level, merging and copying back process O(n) values in total. Multiplying the number of levels by the work per level gives O(n log n). This also applies to already sorted input because the code still splits and merges it.

## 4. Space Complexity

**Space Complexity: O(n)**

The temporary array has `n` elements and is reused by every merge. Recursive calls also use O(log n) stack space. Adding these gives O(n) total extra space. I do not allocate a separate buffer for each call.

## 5. Reflection / Improvement

Merge sort has an efficient time bound for comparison sorting. For general input, comparison sorting cannot guarantee a better worst-case growth rate than n log n comparisons.

I could reduce the extra memory by switching to heapsort. It would sort inside the original array with O(1) extra space and keep O(n log n) time, but it would require a different algorithm. The merge sort version is easier for me to trace by following the two sorted parts.
