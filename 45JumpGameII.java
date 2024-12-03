// Use Greedy Approach

// TC : O(n) O(1)

public static int jump(int[] nums) {
        int jumps = 0; // Number of jumps
        int currentEnd = 0; // Current range covered by jumps
        int farthest = 0; // Farthest index reachable

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]); // Update the farthest index reachable
            if (i == currentEnd) { // Time to jump
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }
