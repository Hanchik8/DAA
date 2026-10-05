import java.util.ArrayList;

class Solution {
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> nodeValues = new ArrayList<>();
        ListNode currentNode = head;

        while (currentNode != null) {
            nodeValues.add(currentNode.val);
            currentNode = currentNode.next;
        }

        int startIndex = 0;
        int endIndex = nodeValues.size() - 1;

        while (startIndex < endIndex) {
            if (!nodeValues.get(startIndex).equals(nodeValues.get(endIndex))) {
                return false;
            }

            startIndex++;
            endIndex--;
        }

        return true;
    }
}
