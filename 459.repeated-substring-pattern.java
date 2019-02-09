/*
 * @lc app=leetcode id=459 lang=java
 *
 * [459] Repeated Substring Pattern
 *
 * https://leetcode.com/problems/repeated-substring-pattern/description/
 *
 * algorithms
 * Easy (39.25%)
 * Total Accepted:    71.9K
 * Total Submissions: 183.2K
 * Testcase Example:  '"abab"'
 *
 * Given a non-empty string check if it can be constructed by taking a
 * substring of it and appending multiple copies of the substring together. You
 * may assume the given string consists of lowercase English letters only and
 * its length will not exceed 10000.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "aba"
 * Output: False
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring
 * "abcabc" twice.)
 * 
 * 
 */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        int gcd = 0;
        for (int i = 0; i < 26; i++) {
            if (gcd == 0 && map[i] != 0) {
                gcd = map[i];
            } 
            else if (gcd != 0) {
                gcd = gcf(map[i], gcd);
            }
        }
        if (gcd == 1) {

        }
    }

}




// public boolean repeatedSubstringPattern(String str) {
// 	int l = str.length();
// 	for(int i=l/2;i>=1;i--) {
// 		if(l%i==0) {
// 			int m = l/i;
// 			String subS = str.substring(0,i);
// 			StringBuilder sb = new StringBuilder();
// 			for(int j=0;j<m;j++) {
// 				sb.append(subS);
// 			}
// 			if(sb.toString().equals(str)) return true;
// 		}
// 	}
// 	return false;
// }
// The length of the repeating substring must be a divisor of the length of the input string
// Search for all possible divisor of str.length, starting for length/2
// If i is a divisor of length, repeat the substring from 0 to i the number of times i is contained in s.length
// If the repeated substring is equals to the input str return true
