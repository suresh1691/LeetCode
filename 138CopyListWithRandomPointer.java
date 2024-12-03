// Using Map We can create the Node and then for copying put the original node in the map as key and newly created node as value 

// TC: O(n) SC : O(n)

class Solution {
  public Node copyRandomList(Node head) {
    if (head == null)
      return null;
    if (map.containsKey(head))
      return map.get(head);

    Node newNode = new Node(head.val);
    map.put(head, newNode);
    newNode.next = copyRandomList(head.next);
    newNode.random = copyRandomList(head.random);
    return newNode;
  }

  private Map<Node, Node> map = new HashMap<>();
}


// Using Interwaeaving the new Node then Assign Random Pointer and the separate the orginal and copy..  A_> B_> C   ----> A->A(Copy) -> B-> B(copy) ->C-> C Copy

// TC : O(n)  SC : O(1)

 public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Step 1: Create new nodes and interweave them with the original list
        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }

        // Step 2: Assign random pointers for the copied nodes
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        // Step 3: Separate the interwoven list into original and copied lists
        Node original = head;
        Node copyHead = head.next;
        Node copy = copyHead;

        while (original != null) {
            original.next = original.next.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
            original = original.next;
            copy = copy.next;
        }

        return copyHead;
    }

