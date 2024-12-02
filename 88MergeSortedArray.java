// Brute Force.. Where you can directly merge the nums1 array with nums 2 with index startinf from M+1 and finally do a Sorting 

// TC: (M+N) Log(M+N) SC : O(1)

 public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Copy nums2 elements into nums1 after m elements
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }

        // Sort nums1
        Arrays.sort(nums1);
    }


// Using Two Pointer Where just iterate and compare the arr1 and arr2 add the higher values from the end .

// TC : O(M+N) SC: O(1)

public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1; // Pointer for nums1
        int p2 = n - 1; // Pointer for nums2
        int p = m + n - 1; // Pointer for the merged array

        // Merge nums1 and nums2 from the end
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }

        // Add remaining elements from nums2
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }
