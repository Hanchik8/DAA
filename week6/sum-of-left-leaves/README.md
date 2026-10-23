# Sum of Left Leaves

## 1. Problem

I need to add the values of leaves that are the left child of their parent. Being somewhere in the root's left subtree is not enough. The root itself is never a left leaf.

[Problem on LeetCode](https://leetcode.com/problems/sum-of-left-leaves/description/)

## 2. Approach

I use an explicit stack to visit nodes. For each popped node, I push its right child if it exists, because that subtree can contain left leaves further down.

For a left child, I first check whether both of its children are null. If so, I add its value directly. If it is not a leaf, I push it for later processing. There is no need to push a left leaf after counting it. An empty root returns 0.

Checking a node through its parent tells me whether it is a left child without storing a separate direction flag. The input is unchanged and LeetCode supplies `TreeNode`.

### Example trace

For `[3, 9, 20, null, null, 15, 7]`, the stack's top is shown first:

| Popped node | Action | Stack afterward | sum |
| --- | --- | --- | --- |
| 3 | Push right child 20; left child 9 is a leaf, so add 9 | [20] | 9 |
| 20 | Push right child 7; left child 15 is a leaf, so add 15 | [7] | 24 |
| 7 | No children; it was a right child, so add nothing | [] | 24 |

The answer is 24. Node 15 counts even though it is in the root's right subtree.

### Challenges and testing

Adding every left child would incorrectly include internal nodes. For `[1, 2, 3, 4, 5]`, only 4 is a left leaf: 2 has children and 5 is a right leaf. The correct sum is 4. Summing the whole left subtree would produce 11, and summing all left children would produce 6. The branching test distinguishes these mistakes.

A single-node tree returns 0 because the root has no parent. Tests include that case, empty input, left-only and right-only leaves, a left leaf below a right branch, negative and repeated values, a 1,000-node chain, repeated calls, and unchanged input.

## 3. Time Complexity

**Time Complexity: O(n)**

Every internal node is pushed and popped once. A left leaf is examined and counted once through its parent; other leaves are popped once. Each check and addition costs O(1), and stack operations take O(1) amortized time. No node is processed repeatedly, giving O(n) total time.

## 4. Space Complexity

**Space Complexity: O(h), at most O(n).**

The depth-first stack holds pending siblings along the current branch, giving O(h) extra references. A balanced tree uses O(log n) space. A long branch with pending siblings can use O(n), while a simple one-child chain keeps at most one stack entry. The sum and local references use O(1) space.

## 5. Reflection / Improvement

The O(n) worst-case time is optimal because a left leaf can occur anywhere. A recursive helper with an `isLeft` flag would also take O(n) time and O(h) stack space. It would make direction explicit but would not reduce complexity. The iterative version handles the longest allowed chains without recursive calls.
