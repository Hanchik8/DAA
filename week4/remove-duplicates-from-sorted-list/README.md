# Remove Duplicates from Sorted List

## 1. Problem

The values in a linked list are sorted. I need to remove repeated values so that each value appears once, then return the first node of the list. An empty list stays empty.

[Problem on LeetCode](https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/)

## 2. Approach

Because the list is sorted, nodes with the same value are next to each other. I use `currentNode` to compare a node with the node after it.

If their values are equal, I skip the next node by changing `currentNode.next`. I keep `currentNode` in the same place because there might be another duplicate after it. If the values are different, I move to the next node.

The loop ends when there is no next node to compare. I return `head` because the first node is kept. The Java file uses the `ListNode` class provided by LeetCode.

### Example trace

The input is `[1, 1, 2, 3, 3]`.

| Step | Current value | Next value | Action | List after the step |
| --- | --- | --- | --- | --- |
| 1 | 1 | 1 | Skip the next node and stay at 1 | [1, 2, 3, 3] |
| 2 | 1 | 2 | Move to the node with value 2 | [1, 2, 3, 3] |
| 3 | 2 | 3 | Move to the first node with value 3 | [1, 2, 3, 3] |
| 4 | 3 | 3 | Skip the next node and stay at 3 | [1, 2, 3] |

The remaining node with value 3 has no next node, so the loop stops. The result is `[1, 2, 3]`.

### Challenges and testing

The main detail was when to move `currentNode`. I tested a version that always moved forward after removing a duplicate. For `[1, 1, 1, 2]`, it returned `[1, 1, 2]`. It skipped one duplicate and then left the other one behind. Keeping the current position after a removal lets the next comparison catch it.

Useful checks include an empty list, a single node, no duplicates, and a list where every value is the same. For `[2, 2, 2]`, the first node should remain and the other two should be skipped.

## 3. Time Complexity

**Time Complexity: O(n)**

Here, `n` is the number of nodes in the original list. Each iteration either removes a node or moves forward by one node. The algorithm never goes back, so the number of iterations grows linearly with the list length. Each comparison and link change takes constant time.

## 4. Space Complexity

**Space Complexity: O(1)**

I change links between the existing nodes and use one extra node reference. I do not create another list or use recursion, so the extra memory does not grow with `n`.

## 5. Reflection / Improvement

This approach already uses one forward pass and constant extra space. In the worst case, every node must be checked to know whether duplicates are present, so the time cannot be improved below O(n).

A recursive solution is possible, but it would still take O(n) time and could use O(n) stack space. The loop is easier for me to follow.
