class MergeListNode {
    int val;
    MergeListNode next;

    MergeListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class MergeTwoSortedLists {

    public static MergeListNode mergeTwoLists(MergeListNode l1, MergeListNode l2) {
        MergeListNode dummy = new MergeListNode(0);
        MergeListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;

        return dummy.next;
    }

    public static void main(String[] args) {
        MergeListNode l1 = new MergeListNode(1);
        l1.next = new MergeListNode(3);
        l1.next.next = new MergeListNode(5);

        MergeListNode l2 = new MergeListNode(2);
        l2.next = new MergeListNode(4);
        l2.next.next = new MergeListNode(6);

        MergeListNode result = mergeTwoLists(l1, l2);

        while (result != null) {
            System.out.print(result.val + " -> ");
            result = result.next;
        }
        System.out.println("null");
    }
}
