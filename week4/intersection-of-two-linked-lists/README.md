# Intersection of Two Linked Lists

## 1. Problem

I need to find the first node shared by two linked lists. Shared means that both lists reach the same node object. Equal values in different nodes do not count. If the lists never meet, the answer is `null`. The lists do not have cycles, and their links must stay unchanged.

[Problem on LeetCode](https://leetcode.com/problems/intersection-of-two-linked-lists/description/)

## 2. Approach

I go through the first list using `firstList` and put each node in a `HashSet` called `firstListNodes`. Then I go through the second list using `secondList`.

For each node in the second list, I check whether it is in the set. The first match is the intersection, so I return that node. If I reach the end without a match, I return `null`. I only read the nodes and their links.

The standard `ListNode` class supplied by LeetCode does not override `equals` or `hashCode`, so the set compares node identity.

### Example trace

These lists share C1 and every node after it. The labels identify the actual nodes.

```text
List A: A1(4) -> A2(1) -> C1(8) -> C2(4) -> C3(5) -> null
List B: B1(5) -> B2(6) -> B3(1) -> same C1(8)
```

| Step | Current node | Action | Set after the step |
| --- | --- | --- | --- |
| 1 | A1(4) | Add A1 | {A1} |
| 2 | A2(1) | Add A2 | {A1, A2} |
| 3 | C1(8) | Add C1 | {A1, A2, C1} |
| 4 | C2(4) | Add C2 | {A1, A2, C1, C2} |
| 5 | C3(5) | Add C3 | {A1, A2, C1, C2, C3} |
| 6 | B1(5) | Not in the set, move to B2 | Unchanged |
| 7 | B2(6) | Not in the set, move to B3 | Unchanged |
| 8 | B3(1) | Not in the set, move to C1 | Unchanged |
| 9 | C1(8) | Already in the set, return C1 | Unchanged |

A2 and B3 both hold 1, but they are different nodes. The search correctly continues past B3. If either list is empty, there cannot be a shared node, so the method returns `null`.

### Challenges and testing

The main detail was deciding what to compare. I tested a version that stored values in `HashSet<Integer>` using two separate lists `[1, 2]` and `[1, 2]`. It reported an intersection because both lists contained 1, even though none of their nodes were shared. Storing `ListNode` objects fixes this mistake.

I also checked lists with a shared tail, lists with the same head, empty lists, and separate lists with repeated values. The result must be the actual shared node, not a newly created node with the same value.

## 3. Time Complexity

**Time Complexity: O(n + m), expected**

Here, `n` is the length of the first list and `m` is the length of the second. I visit all `n` nodes of the first list and at most `m` nodes of the second.

Adding a node to a `HashSet` and checking whether it is present take O(1) time on average. Under this assumption, the total time is O(n + m). Hash collisions mean that a single set operation is not guaranteed to take constant time.

## 4. Space Complexity

**Space Complexity: O(n)**

The set stores a reference to every node in the first list. These are existing nodes, but storing `n` references still uses O(n) extra space. The two traversal references use O(1) space.

## 5. Reflection / Improvement

I could remove the set by counting both list lengths first. I would move the pointer in the longer list forward by the length difference, then advance both pointers together until they point to the same node or reach `null`.

This would take O(n + m) time in the worst case and O(1) extra space. The time complexity would stay the same, but I would use less memory.
