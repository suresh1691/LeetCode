// Brute Force Where two for loops are used to calculate the maxLeft and Max Right and then which will subtract it from the heigh to calculate the trapped water.
// TC : O(n2) SC: O(1)

public int trapBruteForce(int[] height) {
    int n = height.length;
    int waterTrapped = 0;

    for (int i = 0; i < n; i++) {
        int maxLeft = 0, maxRight = 0;

        // Find the maximum height to the left of the current bar
        for (int j = 0; j <= i; j++) {
            maxLeft = Math.max(maxLeft, height[j]);
        }

        // Find the maximum height to the right of the current bar
        for (int j = i; j < n; j++) {
            maxRight = Math.max(maxRight, height[j]);
        }

        // Water trapped at the current bar
        waterTrapped += Math.min(maxLeft, maxRight) - height[i];
    }

    return waterTrapped;
}


// Using DP where we can have maxLeft Arry and Max Right Array which can be used to precompute it and eventually we will subtract the height from the min of maxLeft or MaxRight

// TC : O(n) SC: O(n)

public int trapDP(int[] height) {
    int n = height.length;
    if (n == 0) return 0;

    int[] maxLeft = new int[n];
    int[] maxRight = new int[n];

    // Precompute maxLeft
    maxLeft[0] = height[0];
    for (int i = 1; i < n; i++) {
        maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
    }

    // Precompute maxRight
    maxRight[n - 1] = height[n - 1];
    for (int i = n - 2; i >= 0; i--) {
        maxRight[i] = Math.max(maxRight[i + 1], height[i]);
    }

    // Calculate water trapped
    int waterTrapped = 0;
    for (int i = 0; i < n; i++) {
        waterTrapped += Math.min(maxLeft[i], maxRight[i]) - height[i];
    }

    return waterTrapped;
}


// Using Two Pointer where we can compute the maxLEft and Max Right by iterating the values in both direction and compute the trapped water count

// TC : O(n) SC: O(1)

public int trapTwoPointers(int[] height) {
    int left = 0, right = height.length - 1;
    int maxLeft = 0, maxRight = 0;
    int waterTrapped = 0;

    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= maxLeft) {
                maxLeft = height[left];
            } else {
                waterTrapped += maxLeft - height[left];
            }
            left++;
        } else {
            if (height[right] >= maxRight) {
                maxRight = height[right];
            } else {
                waterTrapped += maxRight - height[right];
            }
            right--;
        }
    }

    return waterTrapped;
}

