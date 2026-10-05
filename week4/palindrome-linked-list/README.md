# Palindrome Linked List

## 1. Problem

I need to check whether the values in a linked list read the same from the beginning and the end. For example, `[1, 2, 2, 1]` is a palindrome, but `[1, 2]` is not. The method returns `true` or `false`.

[Problem on LeetCode](https://leetcode.com/problems/palindrome-linked-list/description/)

## 2. Approach

A singly linked list only lets me move forward. I copy its values into an `ArrayList<Integer>` called `nodeValues` so that I can read them from both ends.

After collecting the values, I set `startIndex` to 0 and `endIndex` to the last index. I compare these two values. If they differ, I return `false`. Otherwise, I move both indices toward the middle and repeat. If every pair matches, I return `true`.

I use `.equals` to compare the stored integers by value. The original list and its links stay unchanged.

The Java file uses the `ListNode` class provided by LeetCode.

### Example trace

For `1 -> 2 -> 2 -> 1 -> null`, the first loop collects the values.

| Step | Current value | nodeValues after adding it |
| --- | --- | --- |
| 1 | 1 | [1] |
| 2 | 2 | [1, 2] |
| 3 | 2 | [1, 2, 2] |
| 4 | 1 | [1, 2, 2, 1] |

The second loop compares pairs.

| Step | startIndex | endIndex | Values | Action |
| --- | --- | --- | --- | --- |
| 1 | 0 | 3 | 1 and 1 | Match, move to indices 1 and 2 |
| 2 | 1 | 2 | 2 and 2 | Match, move to indices 2 and 1 |

Now `startIndex < endIndex` is false, so I return `true`.

For an odd length list like `[1, 2, 1]`, I compare indices 0 and 2. Both indices then become 1, and the middle value needs no comparison. A single node also returns `true` because there are no pairs to check.

### Challenges and testing

Checking the first and last values alone is not enough. I tested that approach on `[1, 2, 3, 1]`. It returned `true` because the outer values were both 1. The full solution also compares 2 and 3, finds the mismatch, and returns `false`.

I checked even and odd length palindromes, a single node, and lists with a mismatch at the outside or near the middle. I also checked that the node links were unchanged after the call.

## 3. Time Complexity

**Time Complexity: O(n)**

Here, `n` is the number of nodes. The first loop visits all `n` nodes. Adding to an `ArrayList` takes O(1) amortized time, which means the occasional array resizing still gives O(n) total time for all additions.

The second loop makes at most `n / 2` comparisons. Reading an array list element and comparing two integers take O(1) time. Together, the two loops take O(n) time. Even if a mismatch is found early, collecting the values has already visited the whole list.

## 4. Space Complexity

**Space Complexity: O(n)**

The array list stores all `n` values and uses space proportional to the list length. The traversal reference and the two indices use O(1) space. There are no recursive calls.

## 5. Reflection / Improvement

I could use slow and fast pointers to find the middle, then reverse the second half of the list. I would compare the first half with the reversed second half and reverse that half again afterward to restore the original links, including when a mismatch is found.

This would still take O(n) time but reduce extra space to O(1). I would need more pointer updates and careful handling of odd lengths and restoring the list.
