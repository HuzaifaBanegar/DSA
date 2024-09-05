class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next= null;
    }

    public void add(int value) {
        ListNode newNode = new ListNode(value);
        ListNode current = this;

        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode; 
    }

    public void printList() {
        ListNode current = this;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void createCycle(int pos) {
        ListNode current = this;
        ListNode cycleNode = null;
        int index = 0;

        // Find the node at the specified position
        while (current.next != null) {
            if (index == pos) {
                cycleNode = current; // Save the cycle node
            }
            current = current.next;
            index++;
        }

        // Create the cycle by linking the last node to the cycle node
        if (cycleNode != null) {
            current.next = cycleNode; // Create a cycle
        }
    }

}