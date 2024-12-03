// Brute Force -- We can generate all the permutation and check if any string which doesnt have i , i-1 is not same..

// TC : O(N!) SC: O(N!)

public static String reorganizeString(String s) {
        List<String> permutations = new ArrayList<>();
        generatePermutations(s.toCharArray(), 0, permutations);

        for (String perm : permutations) {
            if (isValid(perm)) {
                return perm;
            }
        }
        return ""; // No valid permutation found
    }

    private static void generatePermutations(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        HashSet<Character> swapped = new HashSet<>();
        for (int i = index; i < chars.length; i++) {
            if (swapped.contains(chars[i])) continue; // Skip duplicates
            swapped.add(chars[i]);

            swap(chars, i, index);
            generatePermutations(chars, index + 1, result);
            swap(chars, i, index);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private static boolean isValid(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }


// Optimized Solution will be like Greedy With Max-Heap count the frequency and build the maxHeap and rearrange the string

// TC : O(n log K)  SC: O(k)

public static String reorganizeString(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < 26; i++) {
            if (charCount[i] > 0) {
                if (charCount[i] > (s.length() + 1) / 2) {
                    return ""; // Impossible to reorganize
                }
                maxHeap.add(new int[]{i, charCount[i]});
            }
        }

        StringBuilder result = new StringBuilder();
        while (maxHeap.size() > 1) {
            int[] first = maxHeap.poll(); // Most frequent character
            int[] second = maxHeap.poll(); // Second most frequent character

            result.append((char) (first[0] + 'a'));
            result.append((char) (second[0] + 'a'));

            if (--first[1] > 0) {
                maxHeap.add(first);
            }
            if (--second[1] > 0) {
                maxHeap.add(second);
            }
        }

        // If there's one character left, append it
        if (!maxHeap.isEmpty()) {
            int[] last = maxHeap.poll();
            if (last[1] > 1) {
                return ""; // Not possible to reorganize
            }
            result.append((char) (last[0] + 'a'));
        }

        return result.toString();
    }
