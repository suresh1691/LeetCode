/* APPROACH */

// iterate the string and check  the first and last value , if its not matched start the same process for start +1 or end -1 to make sure we are skipping one char and return the result

// Time Complexcity O(n) Space Complexcity O(1)

class Solution {
    public boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
              
                return isValidPalindrome(s, start + 1, end) || isValidPalindrome(s, start, end - 1);
            }
            start++;
            end--;
        }
        return true;
    }

   
    private boolean isValidPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false; 
            }
            start++;
            end--;
        }
        return true; 
    }
}
