# Minimum Depth of Binary Tree

## 1. Problem

I need to find the smallest number of nodes on a path from the root to a leaf. A leaf has no children. A missing child is not a leaf or a completed path. The empty tree has depth 0.

[Problem on LeetCode](https://leetcode.com/problems/minimum-depth-of-binary-tree/description/)

## 2. Approach

I use breadth-first search, starting at depth 1 for a nonempty root. Before processing each level, I save the number of queued nodes. I check those nodes one by one. When a node has neither child, I return the current depth immediately.

All nodes at a smaller depth have already been checked, so the first leaf found must have minimum depth. I only add non-null children. The Java file uses LeetCode's `TreeNode` class and does not change the input.

### Example trace

For `[3, 9, 20, null, null, 15, 7]`:

| Step | depth | Action | Queue afterward |
| --- | --- | --- | --- |
| 1 | 1 | Remove 3; it is not a leaf; add 9 and 20 | [9, 20] |
| 2 | 2 | Remove 9; both children are missing | [20] |

I return 2 without processing 20 or reaching its children.

For `[2, null, 3, null, 4, null, 5, null, 6]`, each level has one node. Nodes 2, 3, 4, and 5 have a right child, so none is a leaf. Node 6 is the first leaf and the answer is 5.

### Challenges and testing

A tempting recursive formula is `1 + min(leftDepth, rightDepth)` with null depth 0. On `[1, null, 2]`, that returns 1 because it chooses the missing left branch. The actual leaf is node 2, so the answer must be 2. The chain tests catch this mistake. Replacing the leaf test's `&&` with `||` makes the same error.

Tests include an empty tree, a single node, left-only and right-only chains, a shallow leaf beside a deeper subtree, a full tree, repeated calls, and a 100,000-node chain. The iterative queue avoids a recursion-depth failure on that last case.

## 3. Time Complexity

**Time Complexity: O(n) in the worst case.**

Each visited node is removed at most once, and its children are added at most once. A chain requires visiting all n nodes before finding its leaf. On other trees the search can stop much earlier; a single-node tree returns after one check. The worst-case bound remains O(n).

## 4. Space Complexity

**Space Complexity: O(w), or O(n) in the worst case.**

The queue contains at most parts of two consecutive levels. Both levels have at most w nodes, so its size is O(w). A chain uses O(1) queue space. A broad tree may require O(n) queued nodes before reaching the first leaf. The depth counter and result use O(1) space.

## 5. Reflection / Improvement

Breadth-first search already stops at the nearest leaf and has optimal O(n) worst-case time. Depth-first search could use O(h) space instead of O(w), but it might explore a long branch before finding a shallow leaf on the other side. To use recursion, I would also need to handle a missing child separately rather than taking a minimum with zero.
