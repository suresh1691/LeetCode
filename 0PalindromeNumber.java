// Brute Force Convert number to String ans use two pointer to check wether its palindrome or not.

// TC : O(N) SC : O(n)

public static boolean isPalindrome(int x) {
        // Negative numbers are not palindromes
        if (x < 0) {
            return false;
        }

        // Convert the number to a string
        String s = String.valueOf(x);

        // Check for palindrome using two pointers
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false; // Mismatch found
            }
            left++;
            right--;
        }

        return true; // All characters matched
    }

// Optimized we can just reverse the integer and compare the orignal and reversed wether its palindrome or not

// TC : O(log x) SC : O(1)

 public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0;
        int original = x;

        while (x > 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return original == reversed;
    }
