# Binary Tree Preorder Traversal

## 1. Problem

I need to return the values of a binary tree in current node, left subtree, right subtree order. For an empty tree, I return an empty list.

[Problem on LeetCode](https://leetcode.com/problems/binary-tree-preorder-traversal/description/)

## 2. Approach

I use recursion and a shared `ArrayList`. The helper returns immediately for a null node. Otherwise, it adds the current node's value before recursively visiting the left and right children.

This processes every subtree's root before its descendants, and finishes the entire left subtree before starting the right subtree. A new list is created inside the public method for each call. The tree is unchanged; the Java file uses the `TreeNode` class provided by LeetCode.

### Example trace

For `[1, 2, 3, 4, 5]`:

```text
    1
   / \
  2   3
 / \
4   5
```

| Step | Action | Result |
| --- | --- | --- |
| 1 | Add the root, 1 | [1] |
| 2 | Enter its left subtree and add 2 | [1, 2] |
| 3 | Enter 2's left subtree and add 4 | [1, 2, 4] |
| 4 | Finish 4; visit 2's right subtree and add 5 | [1, 2, 4, 5] |
| 5 | Finish the subtree at 2; visit 1's right subtree and add 3 | [1, 2, 4, 5, 3] |

Null-child calls return without adding anything.

### Challenges and testing

Preorder is different from visiting each level. For the example, a queue-based level traversal produces `[1, 2, 3, 4, 5]`. It visits 3 too early, before the left subtree has finished. The branching example in the tests distinguishes those approaches. For `[1, null, 2, 3]`, the preorder result must be `[1, 2, 3]`; moving the add between the child calls would produce inorder `[1, 3, 2]` instead.

Tests check these shapes, an empty tree, a single node, repeated and negative values, 100-node left and right chains, repeated calls on the same object, and that the input links and values remain unchanged.

## 3. Time Complexity

**Time Complexity: O(n)**

Each of the n nodes is visited once and added once. Array-list additions take O(1) amortized time, including O(n) total resizing work. Each node also has two child checks, so all recursive work is O(n).

## 4. Space Complexity

**Auxiliary Space: O(h). Including the result: O(n).**

The recursion stack follows the current root-to-node path. It has O(h) calls: O(log n) for a balanced tree and O(n) for a chain. The result stores n integers. Empty input uses O(1) space.

## 5. Reflection / Improvement

The O(n) time is optimal because the result needs all n values. An iterative version would pop a node from a stack, add its value, then push its right and left children in that order. It would keep O(n) time and O(h) auxiliary space while avoiding recursive calls. Morris preorder can achieve O(1) auxiliary space by temporarily threading tree links, but still needs O(n) output space and more careful restoration.
