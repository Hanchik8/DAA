# Maximum Depth of Binary Tree

## 1. Problem

I need to count the nodes on the longest path from the root to a leaf. An empty tree has depth 0, and a single node has depth 1.

[Problem on LeetCode](https://leetcode.com/problems/maximum-depth-of-binary-tree/description/)

## 2. Approach

I use breadth-first search with an `ArrayDeque` as a queue. The queue starts with the root. At the beginning of each round, I save its size in `levelSize`. I remove exactly that many nodes and add their existing children. After the round, one complete level has been processed, so I increase `depth`.

When the queue becomes empty, all levels have been counted. I return `depth`. I check for a null root before adding anything, because `ArrayDeque` does not accept null elements. The Java file uses LeetCode's `TreeNode`; the input stays unchanged.

### Example trace

For `[3, 9, 20, null, null, 15, 7]`:

```text
    3
   / \
  9  20
     / \
    15  7
```

| Round | Queue at the start | Saved levelSize | Queue after the round | depth |
| --- | --- | --- | --- | --- |
| 1 | [3] | 1 | [9, 20] | 1 |
| 2 | [9, 20] | 2 | [15, 7] | 2 |
| 3 | [15, 7] | 2 | [] | 3 |

The queue is empty, so the answer is 3.

### Challenges and testing

The queue size changes as children are added. Using `index < nodesToVisit.size()` directly in the inner loop mixes levels. For `[1, 2, 3, 4, 5, null, null, 6]`, that version processes nodes 1, 2, and 3 in its first round, then 4 and 5 in its second, and 6 in its third. It returns 3 even though the path `1 -> 2 -> 4 -> 6` has depth 4. Saving the size defines the round independently of those changes. The tests include this branching counterexample.

Tests also check an empty tree, one node, duplicate and negative values, a full tree, repeated calls, and a 10,000-node chain. Iteration handles the long chain without recursive calls.

## 3. Time Complexity

**Time Complexity: O(n)**

Each node enters and leaves the queue once, and inspecting its two children takes constant time. Queue additions take O(1) amortized time. Although there are nested loops, the inner loops together process n nodes, rather than n nodes for every round.

## 4. Space Complexity

**Space Complexity: O(w), which is O(n) in the worst case.**

The queue holds nodes from the current level and the next level. Their combined size is at most 2w, giving O(w) extra space. A broad tree can have O(n) nodes on its widest level. A chain keeps only one queued node and uses O(1) space. The returned integer uses O(1) space.

## 5. Reflection / Improvement

A depth-first traversal could track each node's depth with an explicit stack. It would take O(n) time and O(h) space, which can save memory on broad balanced trees. The queue is particularly small for a chain. Neither approach improves the O(n) worst-case time because every branch may contain the deepest leaf.
