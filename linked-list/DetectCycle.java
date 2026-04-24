
class CycleNode {
    int val;
    CycleNode next;

    CycleNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class DetectCycle {

    public static boolean hasCycle(CycleNode head) {

        CycleNode slow = head;
        CycleNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;        // move 1 step
            fast = fast.next.next;   // move 2 steps

            if (slow == fast) {
                return true;         // cycle detected
            }
        }

        return false;
    }

    public static void main(String[] args) {

        CycleNode head = new CycleNode(1);
        head.next = new CycleNode(2);
        head.next.next = new CycleNode(3);
        head.next.next.next = new CycleNode(4);

        // create cycle
        head.next.next.next.next = head.next;

        if (hasCycle(head)) {
            System.out.println("Cycle detected");
        } else {
            System.out.println("No cycle");
        }
    }
}
