import java.util.HashSet;

class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> visitedNodes = new HashSet<>();
        ListNode currentNode = head;

        while (currentNode != null) {
            if (visitedNodes.contains(currentNode)) {
                return true;
            }

            visitedNodes.add(currentNode);
            currentNode = currentNode.next;
        }

        return false;
    }
}
