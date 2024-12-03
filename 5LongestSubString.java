

// Initialize variables to track the start and end of the longest palindrome.
// For each center in 
// Expand outward as long as the substring remains a palindrome.
// Update the start and end if a longer palindrome is found.
// Return the substring using the start and end indices.

//TC : O(n2) SC: O(1)

public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);       // Odd-length palindromes
            int len2 = expandAroundCenter(s, i, i + 1);   // Even-length palindromes
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }



// Manacher Algorithm   it invloves three Phases 1. Preprocessing ie (abca) -> (^#a#b#c#a#$) and  then move use the center variable to move around the cover the radius finally extraction 

// O(n) O(n)


public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // Preprocess the string
        String transformed = preprocess(s);
        int n = transformed.length();
        int[] P = new int[n]; // P[i] = radius of the palindrome centered at i
        int center = 0, right = 0;

        for (int i = 1; i < n - 1; i++) {
            // Mirror of i with respect to the center
            int mirror = 2 * center - i;

            if (i < right) {
                P[i] = Math.min(right - i, P[mirror]);
            }

            // Expand around center i
            while (transformed.charAt(i + P[i] + 1) == transformed.charAt(i - P[i] - 1)) {
                P[i]++;
            }

            // Update the center and right boundary if the palindrome expands beyond right
            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }
        }

        // Find the maximum length in P and its center
        int maxLen = 0;
        int maxCenter = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                maxCenter = i;
            }
        }

        // Extract the longest palindrome from the original string
        int start = (maxCenter - maxLen) / 2; // Map back to the original string
        return s.substring(start, start + maxLen);
    }

    private static String preprocess(String s) {
        StringBuilder sb = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            sb.append('#').append(c);
        }
        sb.append("#$");
        return sb.toString();
    }
