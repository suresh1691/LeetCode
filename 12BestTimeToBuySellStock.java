// Brute Force  where we can use two for loops to iterate the Array and calculate the maxProfit by arr[i+1] - arr[i];

// TC : O(N2) SC: O(1)

 public static int maxProfit(int[] prices) {
        int maxProfit = 0;

        // Check every pair of days
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i]; // Calculate profit
                maxProfit = Math.max(maxProfit, profit); // Update maxProfit
            }
        }

        return maxProfit;
    }

// Greedy Approach where i can have two variables currentProfit and maxProfit .. where the current Profit is Math.min(currentProfit, price) and max profit is Math.max(maxProfit, price-currentProfit)

// TC : O(N) SC: O(1)

public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // Minimum price encountered so far
        int maxProfit = 0; // Maximum profit

        for (int price : prices) {
            if (price < minPrice) {
                // Update minPrice if the current price is lower
                minPrice = price;
            } else {
                // Calculate profit if selling at the current price
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }

        return maxProfit;
    }
