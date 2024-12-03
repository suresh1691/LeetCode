//Use Greedy Approach

// TC : O(N) SC : O(1)

public static boolean canJump(int[] nums) {
        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false; // We cannot reach this index
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) {
                return true; // We can reach or exceed the last index
            }
        }

        return true;
    }

