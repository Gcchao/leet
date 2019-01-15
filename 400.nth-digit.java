/*
 * @lc app=leetcode id=400 lang=java
 *
 * [400] Nth Digit
 *
 * https://leetcode.com/problems/nth-digit/description/
 *
 * algorithms
 * Easy (29.98%)
 * Total Accepted:    43.1K
 * Total Submissions: 143.8K
 * Testcase Example:  '3'
 *
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8,
 * 9, 10, 11, ... 
 * 
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n <
 * 231).
 * 
 * 
 * Example 1:
 * 
 * Input:
 * 3
 * 
 * Output:
 * 3
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * 11
 * 
 * Output:
 * 0
 * 
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a
 * 0, which is part of the number 10.
 * 
 * 
 */
class Solution {
    public int findNthDigit(int n) {
        
        if (n <= 9) {
            return n;
        }
        int base = 1;
        while (n > 9 * Math.pow(10, base - 1) * base) {
            n -= 9 * (int) Math.pow(10, base - 1) * base;
            base++;
        }
        int number = (int)Math.pow(10, base - 1) + (n - 1) / base;


        int index = (n - 1) % base;
        return Integer.toString(number).charAt(index) - '0';

    }
}
