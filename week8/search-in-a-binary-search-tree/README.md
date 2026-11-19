# Search in a Binary Search Tree

## 1. Problem

Given a binary search tree and a target value, I need to find the node with that value and return it with its subtree. If the value is missing, I return `null`.

[Problem on LeetCode](https://leetcode.com/problems/search-in-a-binary-search-tree/description/)

## 2. Approach

I start with `currentNode` at the root. If its value matches the target, I return that node. Otherwise, I move left for a smaller target or right for a larger one. In a BST, every value in the left subtree is smaller than the current value, and every value in the right subtree is larger. This leaves only one possible branch after each comparison.

If I reach an empty child, there is nowhere else the target could be, so I return `null`. The Java file uses the `TreeNode` class provided by LeetCode.

### Example trace

I search for 11 in this tree.

```text
       8
      / \
     3   14
        /  \
       11   17
         \
          12
```

| Step | Current node | Action |
| --- | --- | --- |
| 1 | 8 | 11 is larger, so move right to 14 |
| 2 | 14 | 11 is smaller, so move left to 11 |
| 3 | 11 | Return this node together with its right child, 12 |

For target 13, the first two moves are the same. At 11, I move right to 12 and then right again. That child is empty, so the search returns `null`.

### Challenges and testing

Returning a newly created node with the matching value looks correct if I check only the value. I tested that change with target 11. The new node had an empty right child, so the subtree lost 12. The test checks that the returned reference is the original node and still leads to its child.

I also checked a match at the root, a match on the left, a missing value, an empty tree, and a 5,000-node chain.

## 3. Time Complexity

**Time Complexity: O(h), with O(n) worst-case time over all tree shapes.**

Here, `h` is the height measured in nodes and `n` is the total number of nodes. Each iteration compares one node and moves down one level. A root match takes O(1) time. A balanced tree has height O(log n), so a search there takes at most O(log n). In a chain, the search can visit all `n` nodes.

## 4. Space Complexity

**Space Complexity: O(1)**

The loop uses one moving node reference and a constant number of other values. Returning the matching reference gives access to the existing subtree without copying its nodes.

## 5. Reflection / Improvement

A full traversal could take O(n) time even in a balanced tree. Using the BST ordering already reduces the search to one path. For a single search in a given tree, the target can be at the end of that path, so the O(h) worst-case bound is tight. If I controlled how the tree was built and updated, I could keep it balanced and guarantee O(log n) search time with the same O(1) search space.
