# Invert Binary Tree

## 1. Problem

I need to mirror a binary tree: at every node, its original left subtree becomes its right subtree, and its original right subtree becomes its left subtree. I return the root of the mirrored tree. Empty input stays empty.

[Problem on LeetCode](https://leetcode.com/problems/invert-binary-tree/description/)

## 2. Approach

I use recursion. For a null root, I return null. Otherwise, I save the original left child in `originalLeft`. I recursively invert the original right subtree and assign it to `root.left`. Then I invert the saved left subtree and assign it to `root.right`.

Saving the original reference prevents the first assignment from losing the left subtree. The method returns the same root object and reuses all existing nodes. It changes child links but keeps the node values. The Java file uses LeetCode's `TreeNode` class.

### Example trace

For `[4, 2, 7, 1, 3, 6, 9]`:

```text
Before:         After:
    4               4
   / \             / \
  2   7           7   2
 / \ / \         / \ / \
1  3 6  9       9  6 3  1
```

1. At 4, save the subtree rooted at 2 and enter the original right subtree at 7.
2. At 7, invert its leaves 9 and 6. Its children become left 9 and right 6.
3. Attach that inverted subtree as 4's left child.
4. Invert the saved subtree at 2. Its children become left 3 and right 1.
5. Attach it as 4's right child and return 4.

Leaves remain the same objects because swapping their two null links changes nothing. The level-order result is `[4, 7, 2, 9, 6, 3, 1]`.

### Challenges and testing

Assigning `root.left = invertTree(root.right)` and then using the updated `root.left` to compute the right child loses the original left subtree. On `[2, 1, 3]`, both children can become node 3 instead of producing `[2, 3, 1]`. The tests check node references as well as values to catch lost or duplicated branches.

Tests include empty input, a single node, the worked example, one-child trees, duplicate and negative values, a 100-node chain, and inversion twice. Two inversions must restore every original child reference and value.

## 3. Time Complexity

**Time Complexity: O(n)**

Every node is inverted once. Saving a reference and assigning the two child links take O(1) work per node. The recursive calls cover disjoint subtrees, so the total is O(n).

## 4. Space Complexity

**Space Complexity: O(h), at most O(n).**

The method allocates no replacement tree nodes. Extra space comes from recursive calls along the current path. That is O(log n) for a balanced tree and O(n) for a chain. Returning the original root requires only one reference. Empty input uses O(1).

## 5. Reflection / Improvement

Every node's child links may need changing, so O(n) worst-case time is optimal. An explicit stack could avoid recursive calls while keeping O(h) auxiliary space; a queue would use O(w) space. I kept recursion because each subtree follows the same small operation and the problem allows at most 100 nodes.
