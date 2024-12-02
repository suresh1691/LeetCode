// Using Java HashMap

// TC : O(N) SC : O(min(m))

public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> charIndexMap = new HashMap<>(); // To store the last index of each character
        int maxLength = 0;
        int start = 0; // Start index of the sliding window

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // If the character is already in the map and its index is within the current window
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= start) {
                start = charIndexMap.get(currentChar) + 1; // Move the start to the right of the repeated character
            }

            charIndexMap.put(currentChar, end); // Update the last index of the current character
            maxLength = Math.max(maxLength, end - start + 1); // Calculate the window length and update maxLength
        }

        return maxLength;
    }


// Using Array 

// TC : O(N) SC: O(128)

public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] charIndex = new int[128]; // Array to store the last index of each character
        int maxLength = 0;
        int start = 0; // Start index of the sliding window

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // If the character was seen before, update the start pointer
            if (charIndex[currentChar] > 0) {
                start = Math.max(start, charIndex[currentChar]);
            }

            // Update the character's last seen position to end + 1
            charIndex[currentChar] = end + 1;

            // Update the maximum length
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }


