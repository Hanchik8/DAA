# Binary Tree Postorder Traversal

## 1. Problem

I need to return the node values in left subtree, right subtree, current node order. A parent must appear after both of its subtrees. Empty input returns an empty list.

[Problem on LeetCode](https://leetcode.com/problems/binary-tree-postorder-traversal/description/)

## 2. Approach

My recursive helper first handles the left child, then the right child, and only afterward adds the current node's value. A null node returns immediately. A leaf has two null children, so its value is added as soon as those calls return.

I pass the same result list through the calls instead of creating and joining a list at every node. The public method starts a fresh list each time. The input stays unchanged and `TreeNode` is supplied by LeetCode.

### Example trace

For `[1, null, 2, 3]`:

```text
1
 \
  2
 /
3
```

| Step | Action | Result |
| --- | --- | --- |
| 1 | The left subtree of 1 is empty; enter its right subtree at 2 | [] |
| 2 | Enter 2's left subtree at 3 | [] |
| 3 | Both children of 3 are empty; add 3 | [3] |
| 4 | The right subtree of 2 is empty; add 2 | [3, 2] |
| 5 | Both subtrees of 1 are finished; add 1 | [3, 2, 1] |

### Challenges and testing

Adding a parent before finishing its right subtree changes the order. Another tempting shortcut is to reverse ordinary preorder. For `[1, 2, 3]`, preorder is `[1, 2, 3]` and its reversal is `[3, 2, 1]`, but postorder must be `[2, 3, 1]`. Reversal works only with a different initial traversal order, root-right-left. The two-child test catches this shortcut.

Tests cover the worked example, a tree with both subtrees, empty input, a single node, duplicate and negative values, 100-node chains in both directions, repeated calls, and unchanged input.

## 3. Time Complexity

**Time Complexity: O(n)**

Every node runs the helper once, makes two child calls, and appends its value once. The total number of null-child calls is also O(n). `ArrayList` appends cost O(1) amortized time, so all work together is O(n).

## 4. Space Complexity

**Auxiliary Space: O(h). Including the result: O(n).**

The recursive stack has at most one active call for each node on a root-to-node path. It therefore uses O(h) space, with O(log n) for a balanced tree and O(n) for a chain. The result list adds O(n) space. Empty input uses O(1).

## 5. Reflection / Improvement

An explicit stack with a reference to the last completed node could avoid recursion and keep O(n) time and O(h) auxiliary space. A two-stack solution is easier to write but generally uses O(n) auxiliary space. Neither improves the required O(n) time for listing all values. With at most 100 nodes in this problem, recursion keeps the left-right-root rule direct.
