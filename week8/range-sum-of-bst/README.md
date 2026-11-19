# Range Sum of BST

## 1. Problem

I need to add the values in a binary search tree that fall between `low` and `high`. Values equal to either endpoint count toward the sum.

[Problem on LeetCode](https://leetcode.com/problems/range-sum-of-bst/description/)

## 2. Approach

I use a stack for depth-first search. The stack starts with the root. Each time I pop a node, I add its value if it is inside the range. An empty root gives a sum of 0. The Java file uses LeetCode's `TreeNode` class.

I check the two children separately. The left subtree can contribute only when the current value is greater than `low`, since all its values are smaller. In the same way, the right subtree can contribute only when the current value is less than `high`. If a child exists and passes its check, I push it. I push the right child first so the left child is processed first.

These rules skip branches whose values are all outside the range. Once the stack is empty, every qualifying value has been counted once. Java's `int` can hold the sum because even 20,000 values of 100,000 would add up to 2,000,000,000, which fits.

### Example trace

I use the range `[8, 15]`. The top of the stack is shown first.

```text
          12
        /    \
       5      19
      / \    /  \
     2   8  15   23
        / \ / \
       6  9 14 17
```

| Popped node | Action | Stack afterward | Sum |
| --- | --- | --- | --- |
| 12 | Add 12. Push 19, then 5 | [5, 19] | 12 |
| 5 | Skip the left branch. Push 8 | [8, 19] | 12 |
| 8 | Add the lower endpoint. Skip 6 and push 9 | [9, 19] | 20 |
| 9 | Add 9 | [19] | 29 |
| 19 | Skip the right branch. Push 15 | [15] | 29 |
| 15 | Add the upper endpoint. Skip 17 and push 14 | [14] | 44 |
| 14 | Add 14 | [] | 58 |

The sum is 58 when the stack becomes empty.

### Challenges and testing

I tested the idea of skipping both children as soon as a node's value fell outside the range. It gave 12 on this example because it stopped at 5 and 19. The right subtree of 5 still contains 8 and 9, and the left subtree of 19 still contains 15 and 14. This shows why the add check and the two child checks are separate.

I also checked a range with no matches, a range covering the whole tree, a range with equal endpoints, an empty tree, and a 20,000-node chain. The single-value range checks that a node exactly on the boundary is included.

## 3. Time Complexity

**Time Complexity: O(h + k), with O(n) worst-case time.**

Here, `n` is the number of nodes, `h` is the height measured in nodes, and `k` is the number of values in the range. Each visited node is pushed and popped once, with O(1) amortized work per visit.

All `k` qualifying nodes are visited. A visited node below `low` lies on the search path for `low`. At each ancestor, the direction toward that node agrees with the search direction for `low`, since a left move from a value at or below `low` is pruned. The same reasoning places visited nodes above `high` on the search path for `high`. These paths contribute at most O(h) visits. If the range contains the whole tree, the traversal visits all `n` nodes.

## 4. Space Complexity

**Space Complexity: O(h), at most O(n).**

At most one sibling branch per level is waiting on the depth-first stack, so the stack uses O(h) space. This is O(log n) for a balanced tree. A simple one-child chain keeps just one node on the stack at a time. The running sum and the other local values use O(1) space.

## 5. Reflection / Improvement

Visiting both children at every node would take O(n) time for every range. The BST checks already avoid that work for narrow ranges. For many queries on the same tree, I could collect the sorted values by inorder traversal and build prefix sums in O(n) time and space. Two binary searches would locate the range endpoints, and subtracting the appropriate prefix totals would answer each query in O(log n) time.
