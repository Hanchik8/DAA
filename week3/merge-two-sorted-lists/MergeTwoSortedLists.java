class Solution {
    public ListNode mergeTwoLists(ListNode firstList, ListNode secondList) {
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
