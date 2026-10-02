# Merge Two Sorted Lists

## 1. Problem

There are two linked lists with their values already sorted. I need to join their existing nodes into one sorted list and return its first node. Either list can be empty.

[Problem on LeetCode](https://leetcode.com/problems/merge-two-sorted-lists/description/)

## 2. Approach

I compare the current nodes of the two lists and add the smaller one to the result. If their values are equal, I take the node from the first list.

`firstList` and `secondList` point to the next available node in each list. `lastMergedNode` points to the last node I added. After adding a node, I move forward in the list it came from and update `lastMergedNode`.

I use `temporaryHead` as an extra node before the result. It makes adding the first node work the same way as adding the others. Its value is not part of the answer, so I return `temporaryHead.next`.

When one list ends, I attach the rest of the other list. Those nodes are already sorted, so I do not need to compare them again. If both lists are empty, the result is `null`.

The Java file uses the `ListNode` class provided by LeetCode.

### Example trace

The first list is `[1, 2, 4]` and the second list is `[1, 3, 4]`. The selected prefix below shows the nodes already chosen for the result.

| Step | First list value | Second list value | Node chosen | Selected prefix |
| --- | --- | --- | --- | --- |
| 1 | 1 | 1 | 1 from the first list | [1] |
| 2 | 2 | 1 | 1 from the second list | [1, 1] |
| 3 | 2 | 3 | 2 from the first list | [1, 1, 2] |
| 4 | 4 | 3 | 3 from the second list | [1, 1, 2, 3] |
| 5 | 4 | 4 | 4 from the first list | [1, 1, 2, 3, 4] |

Now `firstList` is `null`, so the loop stops. I attach the remaining node with value 4 from the second list. The final result is `[1, 1, 2, 3, 4, 4]`.

## 3. Time Complexity

**Time Complexity: O(n + m)**

Here, `n` is the number of nodes in the first list and `m` is the number in the second list. Every loop iteration selects one node and moves forward in one list. No selected node is compared again. In the worst case, the number of comparisons grows linearly with the total number of nodes. Attaching the remaining list takes O(1) time because I only change one link.

## 4. Space Complexity

**Space Complexity: O(1)**

I reuse the input nodes instead of making copies. The algorithm only needs a few node references and one temporary node, regardless of the list lengths. It does not use recursion.

## 5. Reflection / Improvement

The iterative approach already has O(n + m) worst-case time and O(1) extra space. There is no need to sort the values again because both lists are sorted at the start.

A recursive solution could be shorter, but its calls would use O(n + m) stack space in the worst case. I would keep the loop because it is easy to trace and uses less memory.
