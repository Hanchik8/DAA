# Linked List Cycle

## 1. Problem

I need to check whether a linked list has a cycle. A cycle means that following the `next` links eventually brings me back to a node I have already reached. The answer is `true` if there is a cycle and `false` otherwise.

[Problem on LeetCode](https://leetcode.com/problems/linked-list-cycle/description/)

## 2. Approach

I go through the list using `currentNode` and store the visited nodes in a `HashSet` called `visitedNodes`.

Before adding a node, I check if it is already in the set. If it is, I have reached the same node again and return `true`. Otherwise, I add it and move to its next node. If `currentNode` becomes `null`, the list ends and I return `false`.

I store the nodes themselves, not their values. Two different nodes can have the same value without making a cycle. The `ListNode` class provided by LeetCode uses object identity for these comparisons.

### Example trace

The list has values `[3, 2, 0, -4]`, and the last node points back to the node with value 2. I label the nodes A, B, C, and D so that each node is easy to identify.

```text
A(3) -> B(2) -> C(0) -> D(-4)
        ^                 |
        |_________________|
```

| Step | Current node | Visited nodes before the check | Action |
| --- | --- | --- | --- |
| 1 | A(3) | {} | Add A and move to B |
| 2 | B(2) | {A} | Add B and move to C |
| 3 | C(0) | {A, B} | Add C and move to D |
| 4 | D(-4) | {A, B, C} | Add D and move to B |
| 5 | B(2) | {A, B, C, D} | B is already visited, so return true |

For a list `A(1) -> B(1) -> null`, I add A on the first iteration and B on the second. They are different nodes even though their values match. After B, `currentNode` becomes `null`, so I return `false`. An empty list also returns `false` because the loop never starts.

## 3. Time Complexity

**Time Complexity: O(n), expected**

Here, `n` is the number of distinct nodes reachable from the head. Without a cycle, I check each node once. With a cycle, I check each distinct node once and stop when I reach a visited node again.

Checking and adding a node in a `HashSet` take O(1) time on average. With this assumption, the total time is O(n). These set operations are not guaranteed to take constant time in every case.

## 4. Space Complexity

**Space Complexity: O(n)**

The set can hold all `n` distinct nodes before the algorithm stops. It stores references to the existing nodes, but the number of stored references still grows with the list length. The `currentNode` reference takes O(1) space.

## 5. Reflection / Improvement

I could use two pointers instead of a set. One pointer would move one node at a time and the other would move two. If they meet, there is a cycle. If the faster pointer reaches the end, there is no cycle.

This approach would keep O(n) time and reduce the extra space to O(1). I would need to replace the set with the two pointers and check that the faster pointer and its next node are not `null` before moving it.
