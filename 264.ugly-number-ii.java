/*
 * @lc app=leetcode id=264 lang=java
 *
 * [264] Ugly Number II
 *
 * https://leetcode.com/problems/ugly-number-ii/description/
 *
 * algorithms
 * Medium (35.13%)
 * Total Accepted:    93.4K
 * Total Submissions: 265.8K
 * Testcase Example:  '10'
 *
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3,
 * 5. 
 * 
 * Example:
 * 
 * 
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10
 * ugly numbers.
 * 
 * Note:  
 * 
 * 
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 * 
 */
class Solution {
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        } 
        int[] res = new int[n];
        res[0] = 1;
        int count2 = 0;
        int count3 = 0;
        int count5 = 0;
        int two = 2;
        int three = 3;
        int five = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(two, Math.min(three, five));
            res[i] = min;
            if (min == two) {
                count2++;
                two = 2 * res[count2];
            }
            if (min == three) {
                count3++;
                three = 3 * res[count3];
            }
            if (min == five) {
                count5++;
                five = 5 * res[count5];
            }
        }
        return res[n - 1];
    }
}
