// Brute Force .. Where we can iterate the Array and then sort the nest string and add it to the map .

// TC : O(n k log(k)) SC : O(n k)

 public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // Sort the string to create the "signature"
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);

            // Group by the sorted signature
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }


// Optimized Solution where we can iterate each array and then use the map to store the list values

// TC : O(nk) SC: O(nk)

 public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int[] charCounts = new int[26]; // Array to count character frequencies
            for (char c : s.toCharArray()) {
                charCounts[c - 'a']++;
            }

            // Use Arrays.toString() to convert frequency array to a unique key
            String key = Arrays.toString(charCounts);

            // Group strings by the frequency key
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }
