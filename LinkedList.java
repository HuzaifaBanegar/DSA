import java.util.List;

public class LinkedList {

    public static ListNode checkCycle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast= fast.next.next;
            if(slow==fast){
                // System.out.println("Cycle exists");
                return slow;
            }
        }
        System.out.println("Cycle doest exisits");
        return null;
  
    }

    public static void removeCycle(ListNode A){
        ListNode head = A;
        ListNode cyclePoint = checkCycle(A);
        if(cyclePoint == null){
            System.out.println("Cycle doest exisits");
            return;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = cyclePoint;
        while(slow != fast){
            prev = fast;
            slow=slow.next;
            fast = fast.next;
        }

        prev.next = null;
        head.printList();
  
    }

    public static ListNode removeKthNodeFromEnd(ListNode A, int K){
        ListNode slow = A;
        ListNode fast = A;
        for(int i=0; i<K; i++){
            fast= fast.next;
        }

        if(fast==null){
            return A.next;
        }

        while(fast.next != null){
            fast= fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return A;

    }

    public static void reverseLinkedList(ListNode head){
        ListNode prev= null;
        ListNode curr = head; 
        while(curr!=null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr= temp;
        }
        prev.printList();
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.add(2);
        node.add(3);
        node.add(4);
        // node.createCycle(1); 

        // checkCycle(node);
        // removeCycle(node);
        // ListNode ans = removeKthNodeFromEnd(node, 2);
        // ans.printList();

        //reverseLinkedList
        reverseLinkedList(node);
       
    }
}
