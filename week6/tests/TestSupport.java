import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

class TestSupport {
    private static int checks;

    static void equal(Object expected, Object actual) {
        checks++;
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
    }

    static void same(Object expected, Object actual) {
        checks++;
        if (expected != actual) {
            throw new AssertionError("Expected the same object reference");
        }
    }

    static void done(String task) {
        System.out.println(task + ": " + checks + " checks passed");
    }

    // Values are in LeetCode's level order; null marks a missing child.
    static TreeNode tree(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int index = 1;
        while (index < values.length && !queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (values[index] != null) {
                node.left = new TreeNode(values[index]);
                queue.add(node.left);
            }
            index++;
            if (index < values.length && values[index] != null) {
                node.right = new TreeNode(values[index]);
                queue.add(node.right);
            }
            index++;
        }
        return root;
    }

    static List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // LinkedList accepts null placeholders, unlike ArrayDeque.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node == null) {
                result.add(null);
            } else {
                result.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        return result;
    }

    static TreeNode chain(int size, boolean left, int value) {
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(value);
        TreeNode tail = root;
        for (int index = 1; index < size; index++) {
            TreeNode next = new TreeNode(value);
            if (left) {
                tail.left = next;
            } else {
                tail.right = next;
            }
            tail = next;
        }
        return root;
    }

    static Map<TreeNode, NodeState> snapshot(TreeNode root) {
        Map<TreeNode, NodeState> saved = new IdentityHashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (saved.containsKey(node)) {
                throw new AssertionError("Input is not a tree");
            }
            saved.put(node, new NodeState(node));
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return saved;
    }

    static void unchanged(Map<TreeNode, NodeState> saved) {
        for (Map.Entry<TreeNode, NodeState> entry : saved.entrySet()) {
            TreeNode node = entry.getKey();
            NodeState state = entry.getValue();
            equal(state.value, node.val);
            same(state.left, node.left);
            same(state.right, node.right);
        }
    }

    static class NodeState {
        final int value;
        final TreeNode left;
        final TreeNode right;

        NodeState(TreeNode node) {
            value = node.val;
            left = node.left;
            right = node.right;
        }
    }
}
