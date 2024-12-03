// Brute Force we can merge the two sorted arrays to one and then check if its odd length take the middle element or take middle two elements and return;

// TC : O(m+n) SC : O(M+N)

public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Step 1: Merge the two sorted arrays
        int[] mergedArray = mergeArrays(nums1, nums2);
        int n = mergedArray.length;

        // Step 2: Find the median
        if (n % 2 == 1) {
            return mergedArray[n / 2]; // Odd length
        } else {
            return (mergedArray[n / 2 - 1] + mergedArray[n / 2]) / 2.0; // Even length
        }
    }

    private static int[] mergeArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;

        // Merge the two arrays
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // Copy remaining elements from nums1
        while (i < m) {
            merged[k++] = nums1[i++];
        }

        // Copy remaining elements from nums2
        while (j < n) {
            merged[k++] = nums2[j++];
        }

        return merged;
    }


// Optimized Solution use Binary Search and calculate the partition and return the median

// TC : O (Log min(m,n)) SC : O(1)

class Solution {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    final int n1 = nums1.length;
    final int n2 = nums2.length;
    if (n1 > n2)
      return findMedianSortedArrays(nums2, nums1);

    int l = 0;
    int r = n1;

    while (l <= r) {
      final int partition1 = (l + r) / 2;
      final int partition2 = (n1 + n2 + 1) / 2 - partition1;
      final int maxLeft1 = partition1 == 0 ? Integer.MIN_VALUE : nums1[partition1 - 1];
      final int maxLeft2 = partition2 == 0 ? Integer.MIN_VALUE : nums2[partition2 - 1];
      final int minRight1 = partition1 == n1 ? Integer.MAX_VALUE : nums1[partition1];
      final int minRight2 = partition2 == n2 ? Integer.MAX_VALUE : nums2[partition2];
      if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1)
        return (n1 + n2) % 2 == 0
            ? (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) * 0.5
            : Math.max(maxLeft1, maxLeft2);
      else if (maxLeft1 > minRight2)
        r = partition1 - 1;
      else
        l = partition1 + 1;
    }

    throw new IllegalArgumentException();
  }
}
