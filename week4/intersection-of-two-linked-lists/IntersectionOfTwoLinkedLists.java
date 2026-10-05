import java.util.HashSet;

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> firstListNodes = new HashSet<>();
        ListNode firstList = headA;

        while (firstList != null) {
            firstListNodes.add(firstList);
            firstList = firstList.next;
        }

        ListNode secondList = headB;

        while (secondList != null) {
            if (firstListNodes.contains(secondList)) {
                return secondList;
            }

            secondList = secondList.next;
        }

        return null;
    }
}
