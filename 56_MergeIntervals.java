// Brute Force - Use two for loops and iterate the array and merge the array if current arr[1] is greater than next arr[0]

// TC : O(n2) SC: O(N)

 public static int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        boolean[] visited = new boolean[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            if (visited[i]) continue;
            int start = intervals[i][0];
            int end = intervals[i][1];

            for (int j = 0; j < intervals.length; j++) {
                if (i != j && !visited[j] && intervals[j][0] <= end && intervals[j][1] >= start) {
                    start = Math.min(start, intervals[j][0]);
                    end = Math.max(end, intervals[j][1]);
                    visited[j] = true;
                }
            }

            result.add(new int[]{start, end});
            visited[i] = true;
        }

        return result.toArray(new int[result.size()][]);
    }


// Optimized Appraoch Sort the Array and iterate it and compare the current arr[1] is greater than next arr[0] 

// TC : O(n logn ) SC : O(n)

class Solution {
    public int[][] merge(int[][] intervals) {
        List<int []> list = new ArrayList<>();
         Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for(int[] inter : intervals){
            if(list.isEmpty() || list.get(list.size()-1)[1] < inter[0]){
                list.add(inter);
            }
            else{
                list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1], inter[1]);
            }
        }
        return  list.toArray(int[][] ::new);
    }
}
