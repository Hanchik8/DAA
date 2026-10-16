class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode middleNode = head;
        ListNode fastNode = head.next;

        while (fastNode != null && fastNode.next != null) {
            middleNode = middleNode.next;
            fastNode = fastNode.next.next;
        }

        ListNode secondHalf = middleNode.next;
        middleNode.next = null;

        ListNode firstHalf = sortList(head);
        secondHalf = sortList(secondHalf);

        return mergeSortedLists(firstHalf, secondHalf);
    }

    private ListNode mergeSortedLists(ListNode firstList, ListNode secondList) {
        ListNode temporaryHead = new ListNode(0);
        ListNode lastMergedNode = temporaryHead;

        while (firstList != null && secondList != null) {
            if (firstList.val <= secondList.val) {
                lastMergedNode.next = firstList;
                firstList = firstList.next;
            } else {
                lastMergedNode.next = secondList;
                secondList = secondList.next;
            }

            lastMergedNode = lastMergedNode.next;
        }

        if (firstList != null) {
            lastMergedNode.next = firstList;
        } else {
            lastMergedNode.next = secondList;
        }

        return temporaryHead.next;
    }
}
