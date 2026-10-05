# Remove Linked List Elements

## 1. Problem

Given a linked list and a target value, I need to remove every node with that value. The order of the remaining nodes should stay the same. I return the new first node, or `null` if nothing remains.

[Problem on LeetCode](https://leetcode.com/problems/remove-linked-list-elements/description/)

## 2. Approach

I add `temporaryHead` before the original first node. It gives the first real node a previous node, so removing it works the same way as removing a node in the middle. The temporary node is not part of the result.

`currentNode` is the node being checked. `previousNode` is the last node kept, or the temporary node if none have been kept yet.

If the current value matches `targetValue`, I change `previousNode.next` to skip the current node. I keep `previousNode` in place because it is still the last node kept. Otherwise, I move `previousNode` to the current node. In both cases, I move `currentNode` forward.

After checking all nodes, I return `temporaryHead.next`. It can be `null` if the input was empty or every node was removed. The Java file uses the `ListNode` class provided by LeetCode.

### Example trace

The input is `[6, 6, 1, 6]` and `targetValue` is 6.

| Step | Current node | Action | Previous node after the step | Remaining list |
| --- | --- | --- | --- | --- |
| 1 | First 6 | Skip it | Temporary node | [6, 1, 6] |
| 2 | Second 6 | Skip it | Temporary node | [1, 6] |
| 3 | 1 | Keep it | Node with value 1 | [1, 6] |
| 4 | Last 6 | Skip it | Node with value 1 | [1] |

The current node is now `null`, so the loop ends. The result is `[1]`. The first two removals use the same previous node, which is why consecutive matches are handled correctly.

### Challenges and testing

Removing the first node needs attention because the answer's head can change. I tested a version that only checked `currentNode.next` and never handled the head. For `[6, 6, 1, 6]` with target 6, it returned `[6, 1]`. It removed later matches but kept the first 6. The temporary node lets the loop check and remove that first node too.

Useful checks include an empty list, a target that is absent, consecutive matches, and a list where every node matches. For `[6, 6]` with target 6, the result should be `null`.

## 3. Time Complexity

**Time Complexity: O(n)**

Here, `n` is the number of nodes in the original list. `currentNode` advances once in every iteration, so each node is checked exactly once. Comparing a value and changing a link take constant time. This gives O(n) total time.

## 4. Space Complexity

**Space Complexity: O(1)**

I use one temporary node and a few node references. The remaining nodes come from the original list. The amount of extra memory stays the same as the input gets longer.

## 5. Reflection / Improvement

This solution already takes O(n) time and O(1) extra space. Every node must be checked because the target could appear anywhere in the list.

Recursion could also solve the problem, but it would still take O(n) time and use up to O(n) stack space. I would keep the iterative approach.
