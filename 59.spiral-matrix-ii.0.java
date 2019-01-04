/*
 * @lc app=leetcode id=59 lang=java
 *
 * [59] Spiral Matrix II
 *
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 *
 * algorithms
 * Medium (44.43%)
 * Total Accepted:    121.7K
 * Total Submissions: 273.9K
 * Testcase Example:  '3'
 *
 * Given a positive integer n, generate a square matrix filled with elements
 * from 1 to n2 in spiral order.
 * 
 * Example:
 * 
 * 
 * Input: 3
 * Output:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 8, 9, 4 ],
 * ⁠[ 7, 6, 5 ]
 * ]
 * 
 * 
 */
class Solution {
    public int[][] generateMatrix(int n) {
        int count = 1;
        int tot = n * n;
        int rowa = 0, rowb = n - 1;
        int cola = 0, colb = n - 1;
        int[][] res = new int[n][n];
        while (count <= tot) {
            for (int i = cola; i <= colb; i++) {
                res[rowa][i] = count;
                count++;
            }
            rowa++;

            for (int i = rowa; i <= rowb; i++) {
                res[i][colb] = count;
                count++;
            }
            colb--;

            if (rowa <= rowb) {
                for (int i = colb; i >= cola; i--) {
                    res[rowb][i] = count;
                    count++;
                }
                rowb--;
            }
            if (cola <= colb) {
                for (int i = rowb; i >= rowa; i--) {
                    res[i][cola] = count;
                    count++;
                }
                cola++;
            }
        }
        return res;
    }
}
