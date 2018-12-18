/*
 * @lc app=leetcode id=424 lang=java
 *
 * [424] Longest Repeating Character Replacement
 *
 * https://leetcode.com/problems/longest-repeating-character-replacement/description/
 *
 * algorithms
 * Medium (43.09%)
 * Total Accepted:    24.5K
 * Total Submissions: 56.8K
 * Testcase Example:  '"ABAB"\n2'
 *
 * Given a string that consists of only uppercase English letters, you can
 * replace any letter in the string with another letter at most k times. Find
 * the length of a longest substring containing all repeating letters you can
 * get after performing the above operations.
 * 
 * Note:
 * Both the string's length and k will not exceed 104.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input:
 * s = "ABAB", k = 2
 * 
 * Output:
 * 4
 * 
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s = "AABABBA", k = 1
 * 
 * Output:
 * 4
 * 
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * 
 * 
 */
class Solution {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        int pre = 0, res = 0, maxcount = 0;
        int[] map = new int[26];
        for (int end = 0; end < len; end++) {
            maxcount = Math.max(maxcount, ++map[s.charAt(end) - 'A']);
            while (end - pre + 1 > maxcount + k) {
                map[s.charAt(pre) - 'A']--;
                pre++;
            }
            res = Math.max(res, end - pre + 1);
        }
        return res;
    }
}
