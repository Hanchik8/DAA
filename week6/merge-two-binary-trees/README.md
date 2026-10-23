# Merge Two Binary Trees

## 1. Problem

I need to combine two binary trees at matching positions. Where both trees have a node, the result stores the sum of their values. Where only one tree has a node, that node and its subtree can be reused. I return the resulting root.

[Problem on LeetCode](https://leetcode.com/problems/merge-two-binary-trees/description/)

## 2. Approach

I merge into the first tree. If either root is null, I return the other root immediately. Otherwise, I use a stack of pairs of overlapping nodes.

For each pair, I add the second value to the first value. I then handle the right children and the left children. If the first child is missing, I attach the second child directly. If both children exist, I push that pair for later merging. If only the first child exists, I keep it as it is.

The stack contains only pairs where both nodes exist, and processing the left pair first gives a simple depth-first trace. The method changes the first tree. It does not assign values or links in the second tree, but attached subtrees are shared: changing such a subtree through the result later also changes what is visible through the second root. The intended inputs are separate trees. LeetCode supplies `TreeNode`.

### Example trace

The inputs are `[1, 3, 2, 5]` and `[2, 1, 3, null, 4, null, 7]`.

```text
First:       Second:       Result:
    1            2             3
   / \          / \           / \
  3   2        1   3         4   5
 /              \   \       / \   \
5                4   7     5   4   7
```

| Step | Pair | Action |
| --- | --- | --- |
| 1 | Roots 1 and 2 | Change the first value to 3; push right pair (2, 3), then left pair (3, 1) |
| 2 | Left nodes 3 and 1 | Change the first value to 4; keep its left child 5; attach the second tree's right child 4 |
| 3 | Right nodes 2 and 3 | Change the first value to 5; attach the second tree's right child 7 |

The result is `[3, 4, 5, 5, 4, null, 7]`. Attached subtrees do not need to be traversed or copied.

### Challenges and testing

Merging values alone loses branches present only in the second tree. For `[1]` and `[1, 2]`, the result must be `[2, 2]`; adding the roots without attaching the left child would give just `[2]`. Another mistake is returning null whenever either root is missing, which discards the existing tree.

Tests include these cases, the worked example, both trees empty, either tree empty, negative sums, duplicate values, nonoverlapping child positions, and two fully overlapping 2,000-node chains. They check that the returned root is the first root when it exists, attached subtree references are reused, and the second tree's values and links are unchanged immediately after merging. Repeated-call tests use fresh inputs because merging mutates its first input.

## 3. Time Complexity

**Time Complexity: O(k + 1), bounded by O(min(n1, n2) + 1).**

Here, n1 and n2 are the input node counts, and k is the number of positions with a node in both trees. Only overlapping pairs enter the stack. Each pair is processed once with constant work, so the merge costs O(k + 1). The +1 covers an immediate return when one input is empty. Because each overlapping position uses a node from each tree, k cannot exceed the smaller node count.

A general O(n1 + n2) bound is also valid, but less precise for this implementation. A large unmatched subtree is attached by one reference assignment; its nodes are not individually visited.

## 4. Space Complexity

**Auxiliary Space: O(hOverlap + 1), at most O(min(n1, n2) + 1).**

Here, hOverlap is the height of the tree formed by overlapping positions. The depth-first stack holds pending pairs along one branch, using O(hOverlap + 1) space. Each entry is a two-reference array. A simple overlapping chain keeps only one pair at a time; a branch with pending siblings can need space proportional to its height.

No new result tree nodes are allocated. The returned structure reuses input nodes and has O(n1 + n2) logical size, but those preexisting nodes are not extra allocated output space.

## 5. Reflection / Improvement

Every overlapping value must be added, so O(k + 1) time is already optimal for a merge that can reuse input nodes. If both inputs must remain independent of the result, I would create a fresh node at every result position and copy unmatched subtrees as well. That version would take O(n1 + n2) time, O(n1 + n2) new output space, and O(max(h1, h2)) traversal space with depth-first processing. It provides stronger ownership guarantees but uses more memory.
