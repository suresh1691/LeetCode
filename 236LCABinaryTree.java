
/* Approach 1:*/
================

// Use DFS with Recursion and have the base case if root is null or equal to any of the input node the retun root else iterate left and right nodes 
// finally if left and right is not null then root is the LCA else if left null then rght is LCA or Left.

// Time Complexicity: O(n) and Space Complexicity O(n)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}


/* Approach 2:*/
================

    // Use DFS with Iterative Approach have the Map and stack and set. As we traverse the tree, we store the parent of each node in a parentMap. This helps us know the parent of any node when we trace back from //p or q. then use the set to store the p's ancentor and then check if Q's ancestor is present in the set means its present else no.
 
// Time Complexicity: O(n) and Space Complexicity O(n)

    class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Map to store the parent of each node
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        
        // Use a stack for DFS
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        // Perform DFS and record the parent of each node
        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parentMap.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                stack.push(node.right);
            }
        }
        
        // Set to store ancestors of p
        Set<TreeNode> ancestors = new HashSet<>();
        
        // Trace all ancestors of p
        while (p != null) {
            ancestors.add(p);
            p = parentMap.get(p);
        }
        
        // Trace ancestors of q, and the first common one is the LCA
        while (!ancestors.contains(q)) {
            q = parentMap.get(q);
        }
        
        return q;
    }
}
