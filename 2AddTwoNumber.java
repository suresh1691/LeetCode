// Iterate two list and have the carry to carry forward the values 

// TC : O(max(m or n)) SC: O(max(m or n))

 public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // Dummy node to simplify result construction
        ListNode current = dummy; // Pointer to the current position in the result list
        int carry = 0; // To store carry from the sum of two digits

        // Traverse both linked lists
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry; // Start with the carry from the previous step

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10; // Calculate new carry
            current.next = new ListNode(sum % 10); // Create a new node with the digit
            current = current.next; // Move to the next node
        }

        return dummy.next; // Return the head of the resulting list
    }
