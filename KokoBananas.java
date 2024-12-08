// Brute Force TC: O(n max(piles)) SC: O(1)

// iterate and Start with smallest as 1 from max file which is present in the array.
// for each iteration find the smallest k required where totaltime <= h

import java.util.*;

class BruteForceSolution {
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = Arrays.stream(piles).max().getAsInt();
        
        for (int k = 1; k <= maxPile; k++) {
            if (canFinish(piles, k, h)) {
                return k;
            }
        }
        
        return maxPile;
    }

    private boolean canFinish(int[] piles, int k, int h) {
        int hours = 0;
        for (int pile : piles) {
            hours += Math.ceil((double) pile / k); // Hours required for this pile
            if (hours > h) {
                return false; // If hours exceed h, k is too small
            }
        }
        return hours <= h;
    }

    public static void main(String[] args) {
        BruteForceSolution solution = new BruteForceSolution();
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        System.out.println(solution.minEatingSpeed(piles, h)); // Output: 4
    }
}


// Optimized soultion TC : O(n log(max(piles)))  SC : O(1)

class Solution {
  public int minEatingSpeed(int[] piles, int h) {
    int l = 1;
    int r = Arrays.stream(piles).max().getAsInt();

    while (l < r) {
      final int m = (l + r) / 2;
      if (eatHours(piles, m) <= h)
        r = m;
      else
        l = m + 1;
    }

    return l;
  }

  // Returns the hours to eat all the piles with speed m.
  private int eatHours(int[] piles, int m) {
    return Arrays.stream(piles).reduce(
        0, (subtotal, pile) -> subtotal + (pile - 1) / m + 1); // ceil(pile / m)
  }
}
