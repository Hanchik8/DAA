# Binary Tree Inorder Traversal

## 1. Problem

I need to return the values of a binary tree in left subtree, current node, right subtree order. An empty tree gives an empty list. The tree does not have to be a binary search tree.

[Problem on LeetCode](https://leetcode.com/problems/binary-tree-inorder-traversal/description/)

## 2. Approach

I use a recursive helper and one shared `ArrayList`. A null reference has nothing to add. For each real node, I first finish its left subtree, add its own value, and then finish its right subtree. The position of `nodeValues.add` determines the traversal order.

The public method creates a fresh result list on every call, so previous results do not carry over. The original node values and links stay unchanged. The Java file uses LeetCode's `TreeNode` class.

### Example trace

For `[1, null, 2, 3]`, the tree is:

```text
1
 \
  2
 /
3
```

| Step | Action | Result |
| --- | --- | --- |
| 1 | Visit the empty left subtree of 1 | [] |
| 2 | Add 1 | [1] |
| 3 | Enter the right subtree at 2, then its left subtree at 3 | [1] |
| 4 | After the empty left subtree of 3, add 3 | [1, 3] |
| 5 | Finish 3, return to 2, and add 2 | [1, 3, 2] |
| 6 | Finish the empty right subtree of 2 | [1, 3, 2] |

### Challenges and testing

The main detail is when to add the current value. Adding it before the left call produces preorder: the example becomes `[1, 2, 3]`, which is wrong for this task. The regression test distinguishes these orders.

Sorting the values would also be incorrect: inorder is sorted only when the tree satisfies the binary search tree rules. For `[2, 3, 1]`, the correct result is `[3, 2, 1]`. Tests include this case, an empty tree, one node, duplicates, negative values, left and right chains, and two calls on the same solution object.

## 3. Time Complexity

**Time Complexity: O(n)**

Every real node is visited once and appended once. `ArrayList.add` takes O(1) amortized time; its occasional resizing costs O(n) in total across the additions. Null-child calls add only O(n) constant work. Therefore the complete traversal takes O(n).

## 4. Space Complexity

**Auxiliary Space: O(h). Including the result: O(n).**

At most one root-to-node chain of recursive calls is active, so the call stack uses O(h) space. This is O(log n) for a balanced tree and O(n) for a chain. The returned list stores n values. The empty-tree case takes O(1) space.

## 5. Reflection / Improvement

Returning every value already requires O(n) time. An explicit stack would remove dependence on Java's recursive call stack while keeping O(n) time and O(h) auxiliary space. Morris traversal could reduce auxiliary space to O(1) by temporarily changing and restoring links, but the result would still need O(n) space. I kept recursion because the order is easy to follow and this problem allows at most 100 nodes.
