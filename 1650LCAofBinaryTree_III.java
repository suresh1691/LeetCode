/* Approach 1*/

// Using Two Pointer. iterate the node and point the next node to its parent and if parent == null then point to second input.
// Time Complexcity O(h) Sapce Complexicity O(1)

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a.parent == null ? q : a.parent;
            b = b.parent == null ? p : b.parent;
        }
        return a;
    }
}

/* Approach 2*/

// Usinh Hash Set Store one input parent nodes in set and while storing second input parents if set contains then thats the LCA
//  Time Complexcity O(H) and Space Complexicity O(H)

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> set = new HashSet<Node>();
        Node temp = p;
        while (temp != null) {
            set.add(temp);
            temp = temp.parent;
        }
        temp = q;
        while (temp != null) {
            if (set.contains(temp))
                break;
            else
                temp = temp.parent;
        }
        return temp;
    }
}

