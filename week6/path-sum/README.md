# Path Sum

## 1. Problem

I need to check whether any complete path from the root to a leaf has values that add up to `targetSum`. Stopping at an internal node does not count. An empty tree has no valid path, even for target 0.

[Problem on LeetCode](https://leetcode.com/problems/path-sum/description/)

## 2. Approach

I use an explicit stack for depth-first search. Each `PathState` stores a node and the sum along the path from the root to that node. The first state contains the root and its value.

I pop one state and check whether it is a leaf with the target sum. If so, I return `true`. Otherwise, I push each child with its own updated sum. I push the right child first so the left child is processed next. If the stack becomes empty, no path matched and I return `false`.

Keeping the sum in each state prevents one branch's total from affecting another. There is no pruning based on whether a sum exceeds the target, because negative values can lower it later. Java `int` is enough: at most 5,000 nodes of absolute value at most 1,000 give an absolute path sum at most 5,000,000. The input is unchanged and `TreeNode` is supplied by LeetCode.

### Example trace

For `[5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1]` and target 22, I process the left branch first:

| Popped node | Path sum | Action |
| --- | --- | --- |
| 5 | 5 | Push (8, 13), then (4, 9) |
| 4 | 9 | Push (11, 20) |
| 11 | 20 | Push (2, 22), then (7, 27) |
| 7 | 27 | Leaf, but 27 does not match |
| 2 | 22 | Leaf with a matching sum; return true |

The matching path is `5 -> 4 -> 11 -> 2`. The state for node 8 remains unprocessed.

### Challenges and testing

Checking only the sum would incorrectly accept `[1, 2]` with target 1 at the root. The test expects `false` because the only complete path totals 3. Another useful counterexample is `[5, -3]` with target 2: rejecting the root because 5 exceeds 2 would miss the valid path.

Tests include these cases, the worked example, an empty tree with target 0, zero-valued nodes, a single-node match and mismatch, a match in the right branch, repeated calls, and a 5,000-node chain with both matching and nonmatching targets.

## 3. Time Complexity

**Time Complexity: O(n) in the worst case.**

Each node produces one state and is popped at most once. Summing two integers, testing children, and checking a leaf take constant time. If no path matches, all n nodes are visited. An early matching leaf can reduce the work for a particular input.

## 4. Space Complexity

**Space Complexity: O(h), at most O(n).**

During depth-first search, the stack keeps pending siblings along the current branch, so it contains O(h) states at once. Each state holds one reference and one integer. A balanced tree uses O(log n) space. A tree with many pending siblings along a long branch can require O(n); a simple one-child chain keeps only one state. The boolean result uses O(1) space.

## 5. Reflection / Improvement

The worst-case time cannot improve below O(n), since an unchecked leaf might be the only match. Recursion could express the same search more briefly by subtracting each node's value from the target, with O(n) time and O(h) call-stack space. I used an explicit stack to handle long trees without relying on Java's recursion limit.
