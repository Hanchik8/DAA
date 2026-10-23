# Week 6 - Binary Trees

Each problem has a Java solution and its own report with a worked trace, testing notes, time and space analysis, and a possible improvement.

| # | Problem | Source code |
| --- | --- | --- |
| 1 | [Binary Tree Inorder Traversal](binary-tree-inorder-traversal/README.md) | [Java](binary-tree-inorder-traversal/BinaryTreeInorderTraversal.java) |
| 2 | [Maximum Depth of Binary Tree](maximum-depth-of-binary-tree/README.md) | [Java](maximum-depth-of-binary-tree/MaximumDepthOfBinaryTree.java) |
| 3 | [Minimum Depth of Binary Tree](minimum-depth-of-binary-tree/README.md) | [Java](minimum-depth-of-binary-tree/MinimumDepthOfBinaryTree.java) |
| 4 | [Path Sum](path-sum/README.md) | [Java](path-sum/PathSum.java) |
| 5 | [Binary Tree Preorder Traversal](binary-tree-preorder-traversal/README.md) | [Java](binary-tree-preorder-traversal/BinaryTreePreorderTraversal.java) |
| 6 | [Binary Tree Postorder Traversal](binary-tree-postorder-traversal/README.md) | [Java](binary-tree-postorder-traversal/BinaryTreePostorderTraversal.java) |
| 7 | [Invert Binary Tree](invert-binary-tree/README.md) | [Java](invert-binary-tree/InvertBinaryTree.java) |
| 8 | [Binary Tree Paths](binary-tree-paths/README.md) | [Java](binary-tree-paths/BinaryTreePaths.java) |
| 9 | [Sum of Left Leaves](sum-of-left-leaves/README.md) | [Java](sum-of-left-leaves/SumOfLeftLeaves.java) |
| 10 | [Merge Two Binary Trees](merge-two-binary-trees/README.md) | [Java](merge-two-binary-trees/MergeTwoBinaryTrees.java) |

## Running the checks

From this folder, run `powershell -ExecutionPolicy Bypass -File .\run-tests.ps1`, or run `pwsh -File ./run-tests.ps1` in PowerShell 7. JDK 11 or later with `javac` and `java` on PATH is required for the test helpers. The script uses a temporary build directory and removes it afterward.

Each source file uses `class Solution` and the `TreeNode` type supplied by LeetCode, as in the earlier weeks. Compile each problem separately. The local tests supply `TreeNode` from `tests/TreeNode.java`; do not include that helper when submitting to LeetCode.

Tests cover examples, empty and single-node trees, missing children, negative and repeated values, repeated calls, and chains up to the problem limits. They also check mutation behavior: inversion reuses its input nodes, and merging updates the first tree and can attach subtrees from the second tree. The other solutions leave the input unchanged.

In the reports, `n` is the number of nodes, `h` is the height measured in nodes, and `w` is the maximum number of nodes on one level. The paths report also counts the total output length. The merge report defines the number of overlapping positions separately.
