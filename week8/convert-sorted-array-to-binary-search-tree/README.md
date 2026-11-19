# Convert Sorted Array to Binary Search Tree

## 1. Problem

I need to build a binary search tree from a strictly increasing array, using each value once. At every node, the heights of its left and right subtrees must differ by at most one.

[Problem on LeetCode](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/)

## 2. Approach

The helper `buildTree` works with an inclusive range from `startIndex` to `endIndex`. It returns `null` for an empty range. For a nonempty range, I choose the middle value as the root. When the range has an even number of elements, integer division chooses the lower of the two middle indices.

Values before the middle are smaller, so I build the left child from the range ending at `middleIndex - 1`. Values after it are larger, so I build the right child from the range starting at `middleIndex + 1`. The two ranges have sizes that differ by at most one. Repeating the split gives a subtree with height `floor(log2(m)) + 1` for every nonempty range of size `m`, since the larger child range always has `floor(m / 2)` elements. The two child heights therefore differ by at most one at every node.

I pass indices into the same array and create one `TreeNode` for each value. LeetCode provides the `TreeNode` class.

### Example trace

The input is `[-8, -3, 0, 2, 5, 11]`. The table shows the nonempty calls in the order they start.

| Step | Index range | Middle index | Node created | Position |
| --- | --- | --- | --- | --- |
| 1 | 0 to 5 | 2 | 0 | Root |
| 2 | 0 to 1 | 0 | -8 | Left child of 0 |
| 3 | 1 to 1 | 1 | -3 | Right child of -8 |
| 4 | 3 to 5 | 4 | 5 | Right child of 0 |
| 5 | 3 to 3 | 3 | 2 | Left child of 5 |
| 6 | 5 to 5 | 5 | 11 | Right child of 5 |

At -8, the left range is 0 to -1, so that call returns `null`. The completed tree looks like this.

```text
         0
       /   \
     -8     5
       \   / \
       -3 2   11
```

Reading left subtree, root, then right subtree gives `[-8, -3, 0, 2, 5, 11]`. Both children of 0 have height 2.

### Challenges and testing

I tested inserting the values into a BST one at a time in array order. For `[1, 2, 3, 4]`, each new value went to the far right. That produced the correct inorder sequence, but at the root the left height was 0 and the right height was 3. Choosing a middle value first avoids that chain.

I checked both properties separately. Inorder must match the original array and the height difference must be at most one at every node. Tests include a single value, two values, odd and even lengths, negative values, and a 10,000-element array.

## 3. Time Complexity

**Time Complexity: O(n)**

Here, `n` is the array length. Every nonempty call does O(1) work to select a middle index and create one node. The child ranges are disjoint and exclude that index, so there are exactly `n` nonempty calls. There are `n + 1` empty child calls, each with a constant-time check. The total time is O(n).

## 4. Space Complexity

**Auxiliary Space: O(log n). Including the tree: O(n).**

Each call stores a few indices and references. Only one branch of calls is active at a time, and each step cuts the range to at most half its length. That gives O(log n) stack space. The returned tree holds `n` new nodes, which account for O(n) output space.

## 5. Reflection / Improvement

Creating all `n` result nodes requires O(n) time and O(n) output space. Using index ranges already avoids copying parts of the array. I could replace recursion with a stack of pending ranges and parent references. This would still take O(n) time and O(log n) auxiliary space if I process it depth first. The recursive version expresses the left and right splits directly.
