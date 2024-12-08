// Brute Force TC : O(nlogn) for sorting O(n2) for finding new rooms SC : O(n)

// Sort the meetings by their start times.
// Use a list (or array) to track the end times of meetings currently occupying rooms.
// For each meeting, check if it can be placed in an existing room (i.e., the meeting's start time is greater than or equal to the end time of any room).
// If no room is free, allocate a new room.
// Return the total number of rooms required.

import java.util.*;

public class MeetingRoomsBruteForce {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // Sort by start time

        List<Integer> endTimes = new ArrayList<>();
        for (int[] interval : intervals) {
            boolean roomAllocated = false;

            for (int i = 0; i < endTimes.size(); i++) {
                if (interval[0] >= endTimes.get(i)) {
                    endTimes.set(i, interval[1]);
                    roomAllocated = true;
                    break;
                }
            }

            if (!roomAllocated) {
                endTimes.add(interval[1]);
            }
        }

        return endTimes.size();
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(minMeetingRooms(intervals)); // Output: 2
    }
}



// Optimized Solution Using sorting with MinHeap  TC : O(nlogn) SC: O(n)
// Sort the meetings by their start times.
// Use a priority queue (min-heap) to keep track of the end times of meetings in rooms.
// For each meeting:
// If the meeting's start time is greater than or equal to the earliest end time (top of the heap), reuse the room by updating the end time.
// Otherwise, allocate a new room by adding the end time to the heap.
// The size of the heap at the end will represent the number of rooms required.

import java.util.*;

public class MeetingRoomsOptimized {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Min-heap to track end times of meetings
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Add the first meeting
        heap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            // If the room due to free up the earliest is free, reuse it
            if (intervals[i][0] >= heap.peek()) {
                heap.poll();
            }

            // Allocate a new room (or update the reused room's end time)
            heap.add(intervals[i][1]);
        }

        // The size of the heap is the number of rooms required
        return heap.size();
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(minMeetingRooms(intervals)); // Output: 2
    }
}


// Using sorting with Two Pointer Approach  TC : O(nlogn) SC: O(n)

class Solution {
  public int minMeetingRooms(int[][] intervals) {
    final int n = intervals.length;
    int ans = 0;
    int[] starts = new int[n];
    int[] ends = new int[n];

    for (int i = 0; i < n; ++i) {
      starts[i] = intervals[i][0];
      ends[i] = intervals[i][1];
    }

    Arrays.sort(starts);
    Arrays.sort(ends);

    // J points to ends
    for (int i = 0, j = 0; i < n; ++i)
      if (starts[i] < ends[j])
        ++ans;
      else
        ++j;

    return ans;
  }
}

