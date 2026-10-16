# Sort List

## 1. Problem

I need to sort a linked list in ascending order and return its new first node. The list can contain duplicate and negative values. An empty list should stay empty.

[Problem on LeetCode](https://leetcode.com/problems/sort-list/description/)

## 2. Approach

I use merge sort. I split the list into two smaller lists, sort each one, and merge the sorted results. A list with zero or one node is already sorted, so it is the base case.

To find the middle, `middleNode` moves one node at a time and `fastNode` moves two. The fast reference starts at `head.next`. This also makes a two-node list split into two single nodes.

I save the beginning of the second half, then set `middleNode.next` to `null`. This separates the two halves before the recursive calls.

The merge helper compares the first available nodes in the sorted halves and attaches the smaller one to the result. `lastMergedNode` keeps track of the last node selected. When the values are equal, I take the node from the first half, which keeps equal nodes in their original order. When one half ends, I attach the rest of the other half.

I use a temporary node before the merged result and return its next node. The answer reuses the original list nodes and keeps their values unchanged. The Java file uses the `ListNode` class provided by LeetCode.

### Example trace

The input is `[4, 2, 1, 3]`.

1. Split it into `[4, 2]` and `[1, 3]`.
2. Split `[4, 2]` into `[4]` and `[2]`. Both are base cases, so merge them into `[2, 4]`.
3. Split `[1, 3]` into `[1]` and `[3]`. Merge them into `[1, 3]`.
4. Merge the two sorted halves, `[2, 4]` and `[1, 3]`.

Here is the final merge. The selected prefix shows the nodes already chosen.

| Step | First half value | Second half value | Action | Selected prefix |
| --- | --- | --- | --- | --- |
| 1 | 2 | 1 | Take 1 from the second half | [1] |
| 2 | 2 | 3 | Take 2 from the first half | [1, 2] |
| 3 | 4 | 3 | Take 3 from the second half | [1, 2, 3] |
| 4 | 4 | No node left | Attach the remaining first half | [1, 2, 3, 4] |

The new first node contains 1, so the returned list is `[1, 2, 3, 4]`.

### Challenges and testing

Finding the middle is only part of the split. I checked a version that saved the second half but left `middleNode.next` unchanged. For `2 -> 1`, the first recursive call still received `2 -> 1`. The same two-node list remained after repeating the split, so it could not reach the single-node base case. Setting the link to `null` makes the first part `[2]` and the second part `[1]`.

I also checked an empty list, a single node, two nodes in reverse order, duplicates, negative values, and a list that was already sorted. I checked the original node references as well as the values to confirm that sorting did not lose nodes or replace them with copies.

## 3. Time Complexity

**Time Complexity: O(n log n)**

Here, `n` is the number of nodes. Finding the middle takes O(n) time for the current list, and merging its sorted halves also takes O(n) time in the worst case. The recursive work is split between two halves of nearly equal size.

For an even size, the recurrence is `T(n) = 2T(n / 2) + O(n)`. For an odd size, the halves differ by one node. There are O(log n) levels of splitting, and the total work across the lists on each level is O(n). Multiplying these gives O(n log n).

## 4. Space Complexity

**Space Complexity: O(log n)**

The recursive calls use O(log n) stack space because each call works on about half as many nodes. Each call keeps only a few node references.

The merge helper is iterative. It uses a constant number of references and one temporary node for that merge. It returns the original data nodes, so it does not allocate a second list. The temporary node is not included in the result. The recursion stack is why the full solution uses O(log n) extra space rather than O(1).

## 5. Reflection / Improvement

An iterative merge sort could remove the recursion stack. I would merge adjacent groups of size 1, then 2, then 4, and keep doubling the group size until the whole list is sorted.

This would keep O(n log n) time and reduce the extra space to O(1). I used the recursive version because splitting the problem into two halves is easier for me to trace.
