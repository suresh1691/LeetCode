/* Approach */

// Using Simple Math and recursive to find the pow of n.

// Time Complexicity O( log n) Space Complexicty O(log n)

class Solution {
  public double myPow(double x, long n) {
    if (n == 0)
      return 1;
    if (n < 0)
      return 1 / myPow(x, -n);
    if (n % 2 == 1)
      return x * myPow(x, n - 1);
    return myPow(x * x, n / 2);
  }
}