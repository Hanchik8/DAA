class Solution {
    public ListNode removeElements(ListNode head, int targetValue) {
        ListNode temporaryHead = new ListNode(0);
        temporaryHead.next = head;

        ListNode previousNode = temporaryHead;
        ListNode currentNode = head;

        while (currentNode != null) {
            if (currentNode.val == targetValue) {
                previousNode.next = currentNode.next;
            } else {
                previousNode = currentNode;
            }

            currentNode = currentNode.next;
        }

        return temporaryHead.next;
    }
}
