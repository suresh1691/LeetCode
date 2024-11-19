
/* APPROACH*/
=============

  // first get the left most index and right most index and add the root node at 0th level and then add the value to the list.
  // Time Complexcity O(n) Space Complexcity O(n)

  class Solution1 {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        Queue<Pair<TreeNode, Integer>> q = new ArrayDeque<>(); // (TreeNode, x)
        int[] range = new int[2];

        // Get the leftmost and the rightmost x index.
        getRange(root, range, 0);

        // Initialize the result lists for each vertical level
        for (int i = range[0]; i <= range[1]; ++i) {
            ans.add(new ArrayList<>());
        }

        // Offer the root into the queue with its normalized x index
        q.offer(new Pair<>(root, -range[0]));

        // Perform BFS
        while (!q.isEmpty()) {
            final TreeNode node = q.peek().getKey();
            final int x = q.poll().getValue();
            ans.get(x).add(node.val); // Add the node's value to the correct vertical level

            // Offer left and right children to the queue with updated x values
            if (node.left != null) {
                q.offer(new Pair<>(node.left, x - 1)); // Left child goes to x - 1
            }
            if (node.right != null) {
                q.offer(new Pair<>(node.right, x + 1)); // Right child goes to x + 1
            }
        }

        return ans; // Return the final result
    }

    // Helper function to calculate the range of horizontal distances
    private void getRange(TreeNode root, int[] range, int x) {
        if (root == null) {
            return;
        }

        // Update the range of x
        range[0] = Math.min(range[0], x); // Leftmost
        range[1] = Math.max(range[1], x); // Rightmost

        // Recurse for the left and right children
        getRange(root.left, range, x - 1);
        getRange(root.right, range, x + 1);
    }
}




/*APPROACH*/
============

  // create three queues one for adding node and another for adding the vertical order and another for maintaining row.then add the key and value to the map 
  // Extract the result from the map, ensuring we return nodes sorted by row and from left to right
  // Time Complexcity O(n log n). Space Complexcity O(n)

  class Solution1 {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        // Map to store nodes grouped by horizontal distance
        Map<Integer, List<int[]>> map = new TreeMap<>();

        // Queue for BFS: stores (node, horizontal distance, row)
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> horizontalDistanceQueue = new LinkedList<>();
        Queue<Integer> rowQueue = new LinkedList<>();

        queue.offer(root);
        horizontalDistanceQueue.offer(0);
        rowQueue.offer(0);

        // Perform BFS
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int horizontalDistance = horizontalDistanceQueue.poll();
            int row = rowQueue.poll();

            // Add the node value and row to the map at the corresponding horizontal distance
            map.putIfAbsent(horizontalDistance, new ArrayList<>());
            map.get(horizontalDistance).add(new int[]{row, node.val});

            // Add left and right children to the queue with updated distances and row numbers
            if (node.left != null) {
                queue.offer(node.left);
                horizontalDistanceQueue.offer(horizontalDistance - 1);
                rowQueue.offer(row + 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                horizontalDistanceQueue.offer(horizontalDistance + 1);
                rowQueue.offer(row + 1);
            }
        }

        // Extract the result from the map, ensuring we return nodes sorted by row and from left to right
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            List<int[]> nodes = entry.getValue();
            // Sort by row first, and if rows are equal, the order is naturally left-to-right because of BFS
            nodes.sort((a, b) -> Integer.compare(a[0], b[0]));
            List<Integer> verticalNodes = new ArrayList<>();
            for (int[] node : nodes) {
                verticalNodes.add(node[1]);
            }
            result.add(verticalNodes);
        }

        return result;
    }
}
