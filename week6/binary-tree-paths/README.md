# Binary Tree Paths

## 1. Problem

I need to return a string for every path from the root to a leaf, with node values separated by `->`. A path must end at a node with no children. The output paths can be in any order.

[Problem on LeetCode](https://leetcode.com/problems/binary-tree-paths/description/)

## 2. Approach

I use depth-first recursion with one `StringBuilder`. Before adding the current node, I save the builder's length. If it already contains a value, I append `->`, then append the node's value.

At a leaf, I add `currentPath.toString()` to the result. This creates an independent string for that completed path. At an internal node, I visit the left and right children. Before returning, I reset the builder to its saved length. This backtracking removes the current node's contribution so a sibling starts with the correct prefix.

Each public call creates a fresh builder and list. A null root returns an empty list even though the problem's stated inputs are nonempty. The input tree stays unchanged and LeetCode supplies `TreeNode`.

### Example trace

For `[1, 2, 3, null, 5]`:

```text
  1
 / \
2   3
 \
  5
```

| Step | Builder action | Current path | Saved output |
| --- | --- | --- | --- |
| 1 | Enter 1; save length 0 and append 1 | 1 | [] |
| 2 | Enter 2; save length 1 and append ->2 | 1->2 | [] |
| 3 | Enter leaf 5; save length 4 and append ->5 | 1->2->5 | ["1->2->5"] |
| 4 | Return from 5 to length 4, then from 2 to length 1 | 1 | ["1->2->5"] |
| 5 | Enter leaf 3 and append ->3 | 1->3 | ["1->2->5", "1->3"] |
| 6 | Return from 3, then from 1 | empty | ["1->2->5", "1->3"] |

### Challenges and testing

Without restoring the saved length, the right branch in this example inherits `1->2->5` and becomes `1->2->5->3`. The branching test checks the two separate path strings. Restoring by a fixed number of characters is also unsafe: values can be negative or have several digits. Saving the exact previous length handles both cases.

Tests cover the example, empty and single-node trees, negative and multi-digit values, duplicate path strings from different leaves, a 100-node chain, a branching tree with shared prefixes, repeated calls, and unchanged input. Paths ending at internal nodes are excluded.

## 3. Time Complexity

**Time Complexity: O(n + L), with O(n^2) worst-case output size.**

Here, L is the total number of characters in all returned strings, including separators. Visiting and appending at each node costs O(n), treating the bounded-size integer values as constant-length text. At every leaf, `toString()` copies the whole current path. These copies together cost O(L).

It would be inaccurate to count only node visits and claim O(n) for all inputs. A tree shaped like a long spine with a leaf branching off at each step has O(n) leaves whose path lengths grow from short to O(n). Its total output length can be O(n^2). A single chain has only one path and takes O(n) time.

## 4. Space Complexity

**Auxiliary Space: O(h). Including output: O(h + L).**

The recursion stack and the reusable builder hold one current path, using O(h) space with bounded-size values. The result strings use O(L) space. List references add O(number of leaves), which is covered by O(L) for nonempty input. The builder retains capacity at most proportional to the longest path.

## 5. Reflection / Improvement

Using a shared builder already avoids copying a new path prefix at every internal node. The complete result still needs O(L) work and memory, so the O(n + L) time is optimal for this output format. An explicit stack with enter/exit states could replace recursion and keep O(h) auxiliary space. Returning paths one at a time would reduce retained output memory, but it would change the required return type.
